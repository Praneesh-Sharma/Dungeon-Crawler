import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    JFrame displayZoneFrame; //graphical window to display elements
    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;

    //Declare timers as instance variables
    Timer renderTimer, gameTimer, physicTimer;

    private boolean isPaused = false; //Flag to track if the game is paused

    public Main() throws Exception {
        displayZoneFrame = new JFrame("Java Labs"); //makes a blank canvas (frame/window)
        displayZoneFrame.setSize(770, 670); //dimensions of the window (sets size in pixels)
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE); //end of program on press of close button

        showStartScreen();
    }

    private void showStartScreen(){
        displayZoneFrame.getContentPane().removeAll();
        displayZoneFrame.getContentPane().add(new StartScreen(displayZoneFrame, this::startGame));
        displayZoneFrame.revalidate();
        displayZoneFrame.repaint();
        displayZoneFrame.setVisible(true);

        // Ensure the JFrame has focus
        displayZoneFrame.setFocusable(true);
        displayZoneFrame.requestFocusInWindow();
    }

    public void startGame(){
        try {
            //Stop previous timers before starting new ones
            stopTimers();

            //Reset previous game states and initialize game components
            DynamicSprite hero = new DynamicSprite(190, 300,
                    ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50);

            renderEngine = new RenderEngine(displayZoneFrame);
            physicEngine = new PhysicEngine();
            gameEngine = new GameEngine(hero);

            //Add the Esc key listener to the game engine
            gameEngine.addEscListener(()->{
                // Stop the game and show the pause screen
                // System.out.println("Esc key pressed. Pausing the game..."); //debug statement
                pauseGame();
            });

            // Create timers for game update, render, and physics
            renderTimer = new Timer(50, (time) -> renderEngine.update());
            gameTimer = new Timer(50, (time) -> gameEngine.update());
            physicTimer = new Timer(50, (time) -> physicEngine.update());

            renderTimer.start();
            gameTimer.start();
            physicTimer.start();

            displayZoneFrame.getContentPane().add(renderEngine);
            displayZoneFrame.setVisible(true);  // triggers the display

            Playground level = new Playground("./data/level1.txt");
            renderEngine.addToRenderList(level.getSpriteList());
            renderEngine.addToRenderList(hero);
            physicEngine.addToMovingSpriteList(hero);
            physicEngine.setEnvironment(level.getSolidSpriteList());

            // Add the key listener for the game
            displayZoneFrame.addKeyListener(gameEngine);
            displayZoneFrame.setFocusable(true);  // Ensure the window can receive input
            displayZoneFrame.requestFocusInWindow(); // Request focus explicitly
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pauseGame() {
        stopTimers();

        PauseScreen pauseScreen = new PauseScreen(
                displayZoneFrame,
                this::resumeGame,  // Resume callback
                this::restartGame, // Restart callback
                this::goHome       // Home callback
        );

        displayZoneFrame.getContentPane().removeAll();
        displayZoneFrame.getContentPane().add(pauseScreen);
        displayZoneFrame.revalidate();
        displayZoneFrame.repaint();

        displayZoneFrame.removeKeyListener(gameEngine); // Disable key inputs while paused
    }

    private void resumeGame(){
        //Restart timers when the game is resumed
        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        //Remove the pause screen
        displayZoneFrame.getContentPane().removeAll();
        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.revalidate();
        displayZoneFrame.repaint();

        // Add the game engine's key listener again
        displayZoneFrame.addKeyListener(gameEngine);
        displayZoneFrame.setFocusable(true);  // Ensure the window can receive input
        displayZoneFrame.requestFocusInWindow(); // Request focus explicitly

        // Set the paused flag
        isPaused = false;
    }

    private void restartGame() {
        try {
            // Stop timers to ensure no game updates during restart
            stopTimers();

            // Clear the frame to remove existing content
            displayZoneFrame.getContentPane().removeAll();

            // Reset game components
            DynamicSprite hero = new DynamicSprite(190, 300,
                    ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50);

            renderEngine = new RenderEngine(displayZoneFrame);
            physicEngine = new PhysicEngine();
            gameEngine = new GameEngine(hero);

            // Add the Escape key listener again for pausing
            gameEngine.addEscListener(() -> pauseGame());

            // Initialize timers
            renderTimer = new Timer(50, (time) -> renderEngine.update());
            gameTimer = new Timer(50, (time) -> gameEngine.update());
            physicTimer = new Timer(50, (time) -> physicEngine.update());

            // Start timers for new game
            renderTimer.start();
            gameTimer.start();
            physicTimer.start();

            // Setup the new game environment
            Playground level = new Playground("./data/level1.txt");
            renderEngine.addToRenderList(level.getSpriteList());
            renderEngine.addToRenderList(hero);
            physicEngine.addToMovingSpriteList(hero);
            physicEngine.setEnvironment(level.getSolidSpriteList());

            // Add render engine back to the frame
            displayZoneFrame.getContentPane().add(renderEngine);
            displayZoneFrame.revalidate();
            displayZoneFrame.repaint();

            // Add key listener for the new game
            displayZoneFrame.addKeyListener(gameEngine);
            displayZoneFrame.setFocusable(true);  // Ensure window receives input
            displayZoneFrame.requestFocusInWindow(); // Request focus explicitly

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void goHome() {
        // Go back to the start screen
        showStartScreen();

        // Remove key listener when returning to the start screen
        displayZoneFrame.removeKeyListener(gameEngine);
        isPaused = false;
    }

    private void stopTimers() {
        // Stop the timers if they exist
        if (renderTimer != null) renderTimer.stop();
        if (gameTimer != null) gameTimer.stop();
        if (physicTimer != null) physicTimer.stop();
    }

    public static void main (String[] args) throws Exception {
        // Initialize the main game
        Main main = new Main();
    }
}


