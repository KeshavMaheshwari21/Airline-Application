import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Dashboard extends JFrame implements ActionListener {

    String username;
    JButton addDetails, updateDetails, viewDetails, bookTicket, logout;

    private JLabel animatedPlane; // For airplane animation

    Dashboard(String username) {
        this.username = username;

        setBounds(100, 50, 1364, 760);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Top Panel with gradient
        JPanel topPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(new GradientPaint(0, 0, new Color(0, 51, 102), getWidth(), 0, new Color(0, 102, 204)));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        topPanel.setLayout(null);
        topPanel.setBounds(0, 0, 1366, 80);
        add(topPanel);

        // Logo and heading
        ImageIcon logoIcon = new ImageIcon(ClassLoader.getSystemResource("images/Logo.png"));
        Image logoImage = logoIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setBounds(585, 17, 50, 50);
        topPanel.add(logoLabel);

        JLabel heading = new JLabel("Cyborg Airlines");
        heading.setBounds(650, 20, 300, 40);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Tahoma", Font.BOLD, 28));
        topPanel.add(heading);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(new Color(0, 51, 102));
        buttonPanel.setBounds(1000, 80, 350, 650);
        add(buttonPanel);

        addDetails = new JButton("Add Details");
        addDetails.setBounds(20, 20, 300, 50);
        styleButton(addDetails);
        addDetails.addActionListener(this);
        buttonPanel.add(addDetails);

        updateDetails = new JButton("Update Details");
        updateDetails.setBounds(20, 100, 300, 50);
        styleButton(updateDetails);
        updateDetails.addActionListener(this);
        buttonPanel.add(updateDetails);

        viewDetails = new JButton("View Details");
        viewDetails.setBounds(20, 180, 300, 50);
        styleButton(viewDetails);
        viewDetails.addActionListener(this);
        buttonPanel.add(viewDetails);

        bookTicket = new JButton("Book Ticket");
        bookTicket.setBounds(20, 260, 300, 50);
        styleButton(bookTicket);
        bookTicket.addActionListener(this);
        buttonPanel.add(bookTicket);

        logout = new JButton("Logout");
        logout.setBounds(20, 340, 300, 50);
        styleButton(logout);
        logout.addActionListener(this);
        buttonPanel.add(logout);

        ImageIcon bgIcon = new ImageIcon(ClassLoader.getSystemResource("images/Dashboard.png"));
        Image bgImage = bgIcon.getImage().getScaledInstance(1366, 768, Image.SCALE_SMOOTH);
        JLabel bgLabel = new JLabel(new ImageIcon(bgImage));
        bgLabel.setBounds(0, 80, 1000, 688);
        add(bgLabel);

        // Airplane Animation Image (hidden initially)
        ImageIcon planeIcon = new ImageIcon(ClassLoader.getSystemResource("images/aeroplane.png"));
        Image planeImage = planeIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        animatedPlane = new JLabel(new ImageIcon(planeImage));
        animatedPlane.setBounds(632, 365, 200, 200); // Center position
        animatedPlane.setVisible(false);
        bgLabel.add(animatedPlane);

        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(0, 102, 204));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button.setFocusPainted(false);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addDetails) {
            new AddDetails(username);
        } else if (ae.getSource() == updateDetails) {
            new UpdateDetails(username);
        } else if (ae.getSource() == viewDetails) {
            new ViewDetails(username);
        } else if (ae.getSource() == bookTicket) {
            // Trigger Book Ticket
            BookTicket bookTicketWindow = new BookTicket(username);

            // Add a window listener to detect when the BookTicket window is closed
            bookTicketWindow.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    startAirplaneAnimation();
                }
            });

        } else if (ae.getSource() == logout) {
            setVisible(false);
            JOptionPane.showMessageDialog(this, "Logged Out!");
        }
    }

    private void startAirplaneAnimation() {
        animatedPlane.setVisible(true);
    
        // Starting position (bottom-left corner)
        final int startX = 0;
        final int startY = 450;
    
        // Target position (top-right corner)
        final int targetX = 694; // Adjusted for the frame's width and airplane's width
        final int targetY = 0;
    
        Timer timer = new Timer(10, new ActionListener() {
            int currentX = startX;
            int currentY = startY;
            int steps = 0;
    
            @Override
            public void actionPerformed(ActionEvent e) {
                if (steps < 100) { // Divide animation into 100 steps
                    currentX += (targetX - startX) / 76;
                    currentY += (targetY - startY) / 76;
                    animatedPlane.setBounds(currentX, currentY, 200, 200); // Update position
                    steps++;
                } else {
                    ((Timer) e.getSource()).stop(); // Stop the timer
                    animatedPlane.setVisible(false); // Hide the airplane after animation
                }
            }
        });
        timer.start();
    }
    

    public static void main(String[] args) {
        new Dashboard("Keshav21");
    }
}
