import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignUp extends JFrame implements ActionListener {

    JButton create, back;
    JTextField tfname, tfusername;
    JPasswordField tfpassword;

    SignUp() {
        setBounds(500, 200, 600, 400);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JPanel p1 = new JPanel();
        p1.setBackground(new Color(0, 50, 100)); // Airline-inspired blue
        p1.setBounds(0, 0, 600, 400);
        p1.setLayout(null);
        add(p1);

        JLabel lbltitle = new JLabel("Sign Up for Airline Management");
        lbltitle.setBounds(50, 10, 500, 40);
        lbltitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        lbltitle.setForeground(Color.WHITE);
        lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(lbltitle);

        JLabel lblusername = new JLabel("Username:");
        lblusername.setBounds(100, 70, 125, 30);
        lblusername.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblusername.setForeground(Color.WHITE);
        p1.add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(250, 70, 200, 30);
        tfusername.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 2));
        p1.add(tfusername);

        JLabel lblname = new JLabel("Name:");
        lblname.setBounds(100, 130, 125, 30);
        lblname.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblname.setForeground(Color.WHITE);
        p1.add(lblname);

        tfname = new JTextField();
        tfname.setBounds(250, 130, 200, 30);
        tfname.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 2));
        p1.add(tfname);

        JLabel lblpassword = new JLabel("Password:");
        lblpassword.setBounds(100, 190, 125, 30);
        lblpassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblpassword.setForeground(Color.WHITE);
        p1.add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(250, 190, 200, 30);
        tfpassword.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 2));
        p1.add(tfpassword);

        create = new JButton("Create");
        create.setBounds(150, 260, 100, 40);
        create.setBackground(new Color(0, 120, 215)); // Bright blue for buttons
        create.setForeground(Color.WHITE);
        create.setFont(new Font("Tahoma", Font.BOLD, 14));
        create.addActionListener(this);
        create.setFocusPainted(false);
        p1.add(create);

        back = new JButton("Back");
        back.setBounds(300, 260, 100, 40);
        back.setBackground(new Color(0, 120, 215)); // Bright blue for buttons
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Tahoma", Font.BOLD, 14));
        back.addActionListener(this);
        back.setFocusPainted(false);
        p1.add(back);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == create) {
            String username = tfusername.getText();
            String name = tfname.getText();
            String password = new String(tfpassword.getPassword());

            if (username.isEmpty() || name.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields are required!");
                return;
            }

            try {
                String query = "INSERT INTO account(username, name, password) VALUES('" + username + "', '" + name + "', '" + password + "')";

                Conn c = new Conn();
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Account Created Successfully!");
                setVisible(false);
                new Login();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String args[]) {
        new SignUp();
    }
}
