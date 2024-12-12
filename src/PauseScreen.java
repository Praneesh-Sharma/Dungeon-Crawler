import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.imageio.ImageIO;
import java.io.File;

public class PauseScreen extends JPanel {
    public PauseScreen(JFrame frame, Runnable resumeGameCallback, Runnable restartGameCallback, Runnable goHomeCallback) {
        setLayout(new BorderLayout());
        setBackground(new Color(0, 0, 0, 10)); // Semi-transparent black background for dim effect

        // Manually set logo size
        ImageIcon logoIcon = new ImageIcon("./img/logo.png");
        Image logoImage = logoIcon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH); // Manually set size to 300x150
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setAlignmentX(CENTER_ALIGNMENT);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Vertical stacking
        buttonPanel.setOpaque(false); // Transparent panel

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

        // Add components to the main panel
        add(logoLabel, BorderLayout.NORTH); // Logo at the top
        add(buttonPanel, BorderLayout.CENTER); // Buttons in the center
    }
}
