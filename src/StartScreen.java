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
        // Load the background and logo images
        try {
            backgroundImage = ImageIO.read(new File("./img/background.jpg"));
            logoImage = ImageIO.read(new File("./img/logo.png"));
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

        // Start Game Button
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.PLAIN, 20));
        startButton.setPreferredSize(new Dimension(200, 50));
        startButton.setMaximumSize(new Dimension(200, 50)); // Ensure fixed size
        startButton.setBackground(new Color(0, 122, 255)); // Blue button color
        startButton.setForeground(Color.WHITE); // Text color
        startButton.setFocusPainted(false); // Removes border when clicked
        startButton.addActionListener(e -> {
            frame.getContentPane().removeAll(); // Clear the start screen
            startGameCallback.run(); // Start the game logic
            frame.revalidate();
            frame.repaint();
        });
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center button horizontally
        buttonPanel.add(startButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Space between buttons

        // Quit Game Button
        JButton quitButton = new JButton("Quit Game");
        quitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        quitButton.setPreferredSize(new Dimension(200, 50));
        quitButton.setMaximumSize(new Dimension(200, 50)); // Ensure fixed size
        quitButton.setBackground(new Color(255, 77, 77)); // Red button color
        quitButton.setForeground(Color.WHITE); // Text color
        quitButton.setFocusPainted(false); // Removes border when clicked
        quitButton.addActionListener(e -> System.exit(0)); // Terminate application
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center button horizontally
        buttonPanel.add(quitButton);

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
