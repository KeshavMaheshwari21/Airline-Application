import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class ViewDetails extends JFrame implements ActionListener {
    
    JButton back;

    ViewDetails(String username) {
        setBounds(400, 180, 450, 550);
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel title = new JLabel("Details");
        title.setFont(new Font("Tahoma", Font.BOLD, 20));
        title.setBounds(175, 10, 300, 30);
        add(title);

        int yPosition = 70;
        int fieldSpacing = 40;

        JLabel lblusername = createLabel("Username :", 30, yPosition);
        JLabel labelusername = createValueLabel(150, yPosition);
        yPosition += fieldSpacing;

        JLabel lblid = createLabel("ID :", 30, yPosition);
        JLabel labelid = createValueLabel(150, yPosition);
        yPosition += fieldSpacing;

        JLabel lblnumber = createLabel("Number :", 30, yPosition);
        JLabel labelnumber = createValueLabel(150, yPosition);
        yPosition += fieldSpacing;

        JLabel lblname = createLabel("Name :", 30, yPosition);
        JLabel labelname = createValueLabel(150, yPosition);
        yPosition += fieldSpacing;

        JLabel lblgender = createLabel("Gender :", 30, yPosition);
        JLabel labelgender = createValueLabel(150, yPosition);
        yPosition += fieldSpacing;

        JLabel lblcountry = createLabel("Country :", 30, yPosition);
        JLabel labelcountry = createValueLabel(150, yPosition);
        yPosition += fieldSpacing;

        JLabel lbladdress = createLabel("Address :", 30, yPosition);
        JLabel labeladdress = createValueLabel(150, yPosition);
        yPosition += fieldSpacing;

        JLabel lblphone = createLabel("Phone No. :", 30, yPosition);
        JLabel labelphone = createValueLabel(150, yPosition);
        yPosition += fieldSpacing;

        JLabel lblemail = createLabel("Email :", 30, yPosition);
        JLabel labelemail = createValueLabel(150, yPosition);
        yPosition += fieldSpacing;

        back = new JButton("Back");
        back.setBackground(Color.DARK_GRAY);
        back.setForeground(Color.WHITE);
        back.setBounds(165, yPosition + 20, 100, 30);
        back.setFont(new Font("Tahoma", Font.BOLD, 16));
        back.addActionListener(this);
        add(back);      

        // Fetch and set data
        try {
            Conn c = new Conn();
            String query = "Select * from customer where username = '" + username + "'";
            ResultSet rs = c.s.executeQuery(query);

            while (rs.next()) {
                labelusername.setText(rs.getString("username"));
                labelid.setText(rs.getString("id"));
                labelnumber.setText(rs.getString("number"));
                labelname.setText(rs.getString("name"));
                labelgender.setText(rs.getString("gender"));
                labelcountry.setText(rs.getString("country"));
                labeladdress.setText(rs.getString("address"));
                labelphone.setText(rs.getString("phone"));
                labelemail.setText(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    // Helper method to create labels
    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label.setBounds(x, y, 100, 25);
        add(label);
        return label;
    }

    // Helper method to create value labels
    private JLabel createValueLabel(int x, int y) {
        JLabel label = new JLabel();
        label.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label.setForeground(Color.DARK_GRAY);
        label.setBounds(x, y, 300, 25);
        add(label);
        return label;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String args[]) {
        new ViewDetails("");
    }
}
