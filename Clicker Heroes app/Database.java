import javax.swing.*;
import java.awt.*;

public class Database extends JPanel {
    public Database() {
        setLayout(new BorderLayout());

        // Add your components and customizations to the new layout here
        JLabel label = new JLabel("DATABASE");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

        // Set the background image for the new layout
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Replace "path/to/your/image.jpg" with the actual path to your image file
        ImageIcon imageIcon = new ImageIcon("C:/Users/ramon/OneDrive/Desktop/Clicker Heroes app/background.jpg");
        Image image = imageIcon.getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
