import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;

public class StartScreen extends JPanel {
    private Image backgroundImage;
    private Image logoImage;

    public StartScreen(JFrame frame, Runnable startGameCallback) {
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("./img/background.jpg"));
            logoImage = ImageIO.read(new File("./img/logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(new BorderLayout());

        // Title Label
//        JLabel title = new JLabel("Dungeon Crawler", SwingConstants.CENTER);
//        title.setFont(new Font("Arial", Font.BOLD, 48));
//        title.setForeground(Color.YELLOW);
//        add(title, BorderLayout.NORTH);

        // Main Panel for Logo and Buttons
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Vertical stacking
        mainPanel.setOpaque(false); // Make the panel transparent

        // Logo Label
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));  // Set the logo image
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);  // Center the logo
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  // Align to the center
        add(logoLabel, BorderLayout.CENTER);  // Add logo above buttons

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10)); // Two buttons stacked vertically
        buttonPanel.setOpaque(false); // Set panel to be transparent

        // Start Game Button
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.PLAIN, 20));
        startButton.setPreferredSize(new Dimension(200, 50)); // Smaller button
        startButton.setBackground(new Color(0, 122, 255)); // Blue button color
        startButton.setForeground(Color.WHITE); // Text color
        startButton.setFocusPainted(false); // Removes the border when clicked
        startButton.addActionListener(e -> {
            frame.getContentPane().removeAll(); // Clear the start screen
            startGameCallback.run(); // Start the game logic
            frame.revalidate();
            frame.repaint();
        });
        buttonPanel.add(startButton);

        // Quit Game Button
        JButton quitButton = new JButton("Quit Game");
        quitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        quitButton.setPreferredSize(new Dimension(200, 50)); // Smaller button
        quitButton.setBackground(new Color(255, 77, 77)); // Red button color
        quitButton.setForeground(Color.WHITE); // Text color
        quitButton.setFocusPainted(false); // Removes the border when clicked
        quitButton.addActionListener(e -> System.exit(0)); // Terminate the application
        buttonPanel.add(quitButton);

        // Add Button Panel to Center
        add(buttonPanel, BorderLayout.CENTER);

        // Credits/Instructions Label
        JLabel credits = new JLabel("Created by Praneesh Sharma", SwingConstants.CENTER);
        credits.setFont(new Font("Arial", Font.ITALIC, 14));
        credits.setForeground(Color.WHITE);
        add(credits, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image
        if (backgroundImage != null) {
            // Make the image fill the whole panel
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
