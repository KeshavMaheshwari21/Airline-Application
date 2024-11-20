import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JButton signup, login;
    JPasswordField tfpassword;
    JTextField tfusername;

    Login() {
        setSize(420, 400);
        setLocation(550, 200);
        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        JPanel p1 = new JPanel();
        p1.setBackground(new Color(0, 50, 100));
        p1.setBounds(0, 0, 420, 400);
        p1.setLayout(null);
        add(p1);

        JLabel lbltitle = new JLabel("Cyborg Airlines");
        lbltitle.setBounds(60, 20, 300, 30);
        lbltitle.setFont(new Font("SAN_SERIF", Font.BOLD, 23));
        lbltitle.setForeground(Color.WHITE);
        lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(lbltitle);

        JLabel lblusername = new JLabel("Username:");
        lblusername.setBounds(60, 80, 100, 25);
        lblusername.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
        lblusername.setForeground(Color.WHITE);
        p1.add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(60, 110, 300, 30);
        tfusername.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 2));
        p1.add(tfusername);

        JLabel lblpassword = new JLabel("Password:");
        lblpassword.setBounds(60, 160, 100, 25);
        lblpassword.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
        lblpassword.setForeground(Color.WHITE);
        p1.add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(60, 190, 300, 30);
        tfpassword.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 2));
        p1.add(tfpassword);

        login = new JButton("Login");
        login.setBounds(60, 250, 130, 35);
        login.setBackground(new Color(0, 120, 215));
        login.setForeground(Color.WHITE);
        login.setFont(new Font("SAN_SERIF", Font.BOLD, 15));
        login.setBorder(new LineBorder(Color.WHITE, 2));
        login.addActionListener(this);
        p1.add(login);

        signup = new JButton("Sign Up");
        signup.setBounds(230, 250, 130, 35);
        signup.setBackground(new Color(0, 120, 215));
        signup.setForeground(Color.WHITE);
        signup.setFont(new Font("SAN_SERIF", Font.BOLD, 15));
        signup.setBorder(new LineBorder(Color.WHITE, 2));
        signup.addActionListener(this);
        p1.add(signup);

        JLabel lblfooter = new JLabel("Welcome to Cyborg Airlines");
        lblfooter.setBounds(60, 310, 300, 30);
        lblfooter.setFont(new Font("SAN_SERIF", Font.ITALIC, 14));
        lblfooter.setForeground(Color.WHITE);
        lblfooter.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(lblfooter);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == signup) {
            setVisible(false);
            new SignUp();
        } else {
            try {
                String username = tfusername.getText();
                String pass = new String(tfpassword.getPassword());

                String query = "select * from account where username = '" + username + "' AND password = '" + pass + "'";
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                if (rs.next()) {
                    setVisible(false);
                    new Loading(username);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Username or Password!");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        new Login();
    }
}
