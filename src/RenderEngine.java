import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine {
    private ArrayList<Displayable> renderList;
    private int fps = 0;  // Store FPS value
    private boolean showFPS = false;  // Flag to toggle FPS display

    // Constructor
    public RenderEngine(JFrame jFrame) {
        renderList = new ArrayList<>();
    }

    // Method to update FPS
    public void setFps(int fps) {
        this.fps = fps;  // Method to update FPS
    }

    // Method to set the showFPS flag
    public void setShowFPS(boolean showFPS) {
        this.showFPS = showFPS;
    }

    // Adds displayable objects to the render list
    public void addToRenderList(Displayable displayable) {
        if (!renderList.contains(displayable)) {
            renderList.add(displayable);
        }
    }

    // Adds a list of displayable objects to the render list
    public void addToRenderList(ArrayList<Displayable> displayable) {
        if (!renderList.contains(displayable)) {
            renderList.addAll(displayable);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Displayable renderObject : renderList) {
            renderObject.draw(g);
        }

        // Render FPS at the top-right corner if showFPS is true
        if (showFPS) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            String fpsText = "FPS: " + fps;
            int x = getWidth() - 100;  // Position 100px from the right
            int y = 20;  // Position 20px from the top
            g.drawString(fpsText, x, y);
        }
    }

    @Override
    public void update() {
        this.repaint();  // Repaints the screen to update the FPS display
    }

}
