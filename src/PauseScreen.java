import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PauseScreen extends JPanel {

    private JButton resumeButton;
    private JButton homeButton;

    public PauseScreen(Runnable resumeAction, Runnable homeAction) {
        setLayout(new BorderLayout());

        // Create buttons
        resumeButton = new JButton("Resume");
        homeButton = new JButton("Home");

        // Add actions to buttons
        resumeButton.addActionListener((ActionEvent e) -> resumeAction.run());
        homeButton.addActionListener((ActionEvent e) -> homeAction.run());

        // Add buttons to panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1)); // Stack buttons vertically
        buttonPanel.add(resumeButton);
        buttonPanel.add(homeButton);

        // Add button panel to the main panel
        add(buttonPanel, BorderLayout.CENTER);

        // Set the panel's preferred size
        setPreferredSize(new Dimension(400, 600));
    }
}
