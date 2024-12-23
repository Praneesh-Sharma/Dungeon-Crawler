import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;

public class PauseScreen extends JPanel {
    private Image backgroundImage;
    private Image logoImage;

    public PauseScreen(JFrame frame, Runnable resumeGameCallback, Runnable restartGameCallback, Runnable goHomeCallback) {
        // Load the background and logo images
        try {
            backgroundImage = ImageIO.read(new File("./img/backgroundPause.jpg")); // Background image
            logoImage = ImageIO.read(new File("./img/logo.png")); // Logo image
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(new BorderLayout());

        // Main Panel for Centering Content
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Vertical alignment
        mainPanel.setOpaque(false); // Make the panel transparent

        // Logo Label
        if (logoImage != null) {
            logoImage = logoImage.getScaledInstance(400, 400, Image.SCALE_SMOOTH); // Resize logo
        }
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage)); // Add resized logo
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center logo horizontally
        mainPanel.add(logoLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add space below logo

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Vertical stacking
        buttonPanel.setOpaque(false); // Make the panel transparent

        // Resume Button
        JButton resumeButton = new JButton("Resume");
        resumeButton.setFont(new Font("Arial", Font.PLAIN, 20));
        resumeButton.setPreferredSize(new Dimension(200, 50));
        resumeButton.setMaximumSize(new Dimension(200, 50)); // Ensure fixed size
        resumeButton.setBackground(new Color(0, 200, 0)); // Green button color
        resumeButton.setForeground(Color.WHITE); // Text color
        resumeButton.setFocusPainted(false); // Removes border when clicked
        resumeButton.addActionListener((ActionEvent e) -> resumeGameCallback.run()); // Callback to resume game
        resumeButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center button horizontally
        buttonPanel.add(resumeButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Space between buttons

        // Restart Button
        JButton restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Arial", Font.PLAIN, 20));
        restartButton.setPreferredSize(new Dimension(200, 50));
        restartButton.setMaximumSize(new Dimension(200, 50)); // Ensure fixed size
        restartButton.setBackground(new Color(0, 153, 255)); // Light blue button color
        restartButton.setForeground(Color.WHITE); // Text color
        restartButton.setFocusPainted(false); // Removes border when clicked
        restartButton.addActionListener((ActionEvent e) -> restartGameCallback.run()); // Callback to restart game
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center button horizontally
        buttonPanel.add(restartButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Space between buttons

        // Home Button
        JButton homeButton = new JButton("Home Screen");
        homeButton.setFont(new Font("Arial", Font.PLAIN, 20));
        homeButton.setPreferredSize(new Dimension(200, 50));
        homeButton.setMaximumSize(new Dimension(200, 50)); // Ensure fixed size
        homeButton.setBackground(new Color(255, 77, 77)); // Red button color
        homeButton.setForeground(Color.WHITE); // Text color
        homeButton.setFocusPainted(false); // Removes border when clicked
        homeButton.addActionListener((ActionEvent e) -> goHomeCallback.run()); // Callback to go back home
        homeButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center button horizontally
        buttonPanel.add(homeButton);

        // Add Buttons Panel to Main Panel
        mainPanel.add(buttonPanel);

        // Add Main Panel to Center
        add(mainPanel, BorderLayout.CENTER);

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
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
