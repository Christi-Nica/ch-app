import javax.swing.*;
import java.awt.*;

public class app {
    private static CardLayout cardLayout;
    private static JPanel cardPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Clicker Heroes Data");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the window (width, height) in pixels
        frame.setSize(700, 900);

        // Set the window's position on the screen (null centers the window)
        frame.setLocationRelativeTo(null);

        // Create a CardLayout and a panel to hold the cards
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel backgroundPanel = createBackgroundPanel();
        cardPanel.add(backgroundPanel, "backgroundPanel");

        JPanel RacePanel = new Race();
        cardPanel.add(RacePanel, "RacePanel");

        JPanel StatsPanel = new Stats();
        cardPanel.add(StatsPanel, "StatsPanel");

        JPanel DatabasePanel = new Database();
        cardPanel.add(DatabasePanel, "DatabasePanel");


        frame.add(cardPanel);

        frame.setVisible(true);
    }

    private static JPanel createBackgroundPanel() {
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("C:/Users/ramon/OneDrive/Desktop/Clicker Heroes app/background.jpg");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(null);

        CustomButton RaceButton = new CustomButton("Race");
        RaceButton.setBounds(250, 450, 200, 50);
        RaceButton.addActionListener(e -> cardLayout.show(cardPanel, "RacePanel"));
        

        CustomButton StatsButton = new CustomButton("Stats");
        StatsButton.setBounds(250, 550, 200, 50); 
        StatsButton.addActionListener(e -> cardLayout.show(cardPanel, "StatsPanel"));
         

        CustomButton DatabaseButton = new CustomButton("Database");
        DatabaseButton.setBounds(250, 650, 200, 50); 
        DatabaseButton.addActionListener(e -> cardLayout.show(cardPanel, "DatabasePanel"));
        

        CustomButton ExitButton = new CustomButton("Exit");
        ExitButton.setBounds(250, 750, 200, 50);
        ExitButton.addActionListener(e -> {
            // Action to be performed when the button is pressed
            System.exit(0); // This will terminate the application
        });


        backgroundPanel.add(RaceButton);
        backgroundPanel.add(StatsButton);
        backgroundPanel.add(DatabaseButton);
        backgroundPanel.add(ExitButton);

        return backgroundPanel;
    }
}
