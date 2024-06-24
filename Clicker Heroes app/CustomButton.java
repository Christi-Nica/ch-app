import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomButton extends JButton {
    private static final int ARC_SIZE = 50; // Adjust the value to control the corner radius
    private static final int ENLARGE_AMOUNT = 10; // Adjust the value to control the amount of size increase on hover

    public CustomButton(String text) {
        super(text);
        initStyle();
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the action when the button is clicked (you can customize this)
                System.out.println("Button Clicked!");
            }
        });

        // Add a MouseAdapter for the hover effect
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                animateSizeChange(getWidth() + ENLARGE_AMOUNT, getHeight() + ENLARGE_AMOUNT);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                animateSizeChange(getWidth() - ENLARGE_AMOUNT, getHeight() - ENLARGE_AMOUNT);
            }
        });
    }

    private void initStyle() {
        // Apply custom CSS-like styles here
        setForeground(Color.decode("#773400"));
        setFont(new Font("Algerian", Font.BOLD, 20));
        setOpaque(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setContentAreaFilled(false);
    }
    

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        int diameter = Math.max(size.width, size.height);
        return new Dimension(diameter, diameter);
    }

    private void animateSizeChange(int targetWidth, int targetHeight) {
       
        Timer timer = new Timer(10, new ActionListener() {
            private int currentWidth = getWidth();
            private int currentHeight = getHeight();
            private int stepWidth = (targetWidth - currentWidth) / 10;
            private int stepHeight = (targetHeight - currentHeight) / 10;
            private int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
                currentWidth += stepWidth;
                currentHeight += stepHeight;

                // Apply bounds to prevent overshooting the target size
                if ((stepWidth > 0 && currentWidth >= targetWidth) || (stepWidth < 0 && currentWidth <= targetWidth)) {
                    currentWidth = targetWidth;
                }

                if ((stepHeight > 0 && currentHeight >= targetHeight) || (stepHeight < 0 && currentHeight <= targetHeight)) {
                    currentHeight = targetHeight;
                }

                setSize(currentWidth, currentHeight);
                if (count >= 10) {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }
    @Override
    protected void paintComponent(Graphics g) {
        // Draw the background of the button (fill the whole component with the background color)
        if (getModel().isArmed() || getModel().isRollover()) {
            g.setColor(Color.decode("#FDFD00")); // Hover color
        } else {
            g.setColor(Color.decode("#FECB00")); // Default color
        }
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC_SIZE, ARC_SIZE);
    
        // Call the super method to paint the text and icon
        super.paintComponent(g);
    }
    
    @Override
    protected void paintBorder(Graphics g) {
        if (getModel().isArmed() || getModel().isRollover()) {
            g.setColor(Color.decode("#773400"));
            ((Graphics2D) g).setStroke(new BasicStroke(3)); // Increase the border thickness to 3 pixels
        } else {
            g.setColor(Color.decode("#773400"));
            ((Graphics2D) g).setStroke(new BasicStroke(1)); // Set the border thickness to 1 pixel
        }
    
        // Draw rounded rectangle with the border
        float borderWidth = ((BasicStroke) ((Graphics2D) g).getStroke()).getLineWidth();
        int borderOffset = Math.round(borderWidth * 2); // Convert float to int using Math.round() and use a larger border offset
        int roundedRectWidth = Math.round(getWidth() - borderWidth);
        int roundedRectHeight = Math.round(getHeight() - borderWidth);
        g.drawRoundRect(borderOffset, borderOffset, roundedRectWidth - 1 - borderOffset * 2, roundedRectHeight - 1 - borderOffset * 2, ARC_SIZE, ARC_SIZE);
    }
      

    // Remove the duplicated paintComponent(Graphics g) method

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CustomButton button = new CustomButton("Hover Me!");
            JFrame frame = new JFrame("Custom Button Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new FlowLayout(FlowLayout.CENTER));
            frame.add(button);
            frame.setSize(200, 200);
            frame.setVisible(true);
        });
    }
}
