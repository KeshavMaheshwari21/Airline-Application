import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddDetails extends JFrame implements ActionListener {

    JLabel labelusername, labelname;
    JComboBox<String> comboid;
    JTextField tfnumber, tfcountry, tfaddress, tfemail, tfphone;
    JRadioButton rmale, rfemale;
    JButton add, back;

    AddDetails(String username) {
        setBounds(165, 180, 900, 600);
        setLayout(null);
        getContentPane().setBackground(new Color(240, 248, 255)); // Light blue background

        // Heading Label
        JLabel heading = new JLabel("Add Customer Details");
        heading.setBounds(250, 10, 400, 40);
        heading.setFont(new Font("Tahoma", Font.BOLD, 30));
        heading.setForeground(new Color(0, 51, 102)); // Navy blue
        add(heading);

        // Username Label
        JLabel lblusername = new JLabel("Username :");
        lblusername.setBounds(30, 80, 150, 25);
        lblusername.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblusername);

        labelusername = new JLabel();
        labelusername.setBounds(200, 80, 200, 25);
        labelusername.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(labelusername);

        // ID Combo Box
        JLabel lblid = new JLabel("ID :");
        lblid.setBounds(30, 120, 150, 25);
        lblid.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblid);

        comboid = new JComboBox<>(new String[]{"Passport", "Aadhar Card", "Pan Card"});
        comboid.setBounds(200, 120, 200, 25);
        comboid.setBackground(Color.white);
        comboid.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(comboid);

        // Number Text Field
        JLabel lblnumber = new JLabel("Number :");
        lblnumber.setBounds(30, 160, 150, 25);
        lblnumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblnumber);

        tfnumber = new JTextField();
        tfnumber.setBounds(200, 160, 200, 25);
        add(tfnumber);

        // Name Label
        JLabel lblname = new JLabel("Name :");
        lblname.setBounds(30, 200, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblname);

        labelname = new JLabel();
        labelname.setBounds(200, 200, 200, 25);
        labelname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(labelname);

        // Gender Radio Buttons
        JLabel lblgender = new JLabel("Gender :");
        lblgender.setBounds(30, 240, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblgender);

        rmale = new JRadioButton("Male");
        rmale.setBounds(200, 240, 80, 25);
        rmale.setBackground(new Color(240, 248, 255));
        rmale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(rmale);

        rfemale = new JRadioButton("Female");
        rfemale.setBounds(290, 240, 80, 25);
        rfemale.setBackground(new Color(240, 248, 255));
        rfemale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(rfemale);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rmale);
        genderGroup.add(rfemale);

        // Country Text Field
        JLabel lblcountry = new JLabel("Country :");
        lblcountry.setBounds(30, 280, 150, 25);
        lblcountry.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblcountry);

        tfcountry = new JTextField();
        tfcountry.setBounds(200, 280, 200, 25);
        add(tfcountry);

        // Address Text Field
        JLabel lbladdress = new JLabel("Address :");
        lbladdress.setBounds(30, 320, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(200, 320, 200, 25);
        add(tfaddress);

        // Phone Number Text Field
        JLabel lblphone = new JLabel("Phone No. :");
        lblphone.setBounds(30, 360, 150, 25);
        lblphone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(200, 360, 200, 25);
        add(tfphone);

        // Email Text Field
        JLabel lblemail = new JLabel("Email :");
        lblemail.setBounds(30, 400, 150, 25);
        lblemail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblemail);

        tfemail = new JTextField();
        tfemail.setBounds(200, 400, 200, 25);
        add(tfemail);

        // Buttons
        add = new JButton("Add");
        add.setBounds(80, 470, 120, 30);
        add.setBackground(new Color(0, 102, 204)); // Blue
        add.setForeground(Color.WHITE);
        add.setFont(new Font("Tahoma", Font.BOLD, 16));
        add.addActionListener(this);
        add(add);

        back = new JButton("Back");
        back.setBounds(240, 470, 120, 30);
        back.setBackground(new Color(0, 102, 204)); // Blue
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Tahoma", Font.BOLD, 16));
        back.addActionListener(this);
        add(back);

        // Right-Side Image
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("images/adddetails.jpg"));
        Image img = icon.getImage().getScaledInstance(400, 500, Image.SCALE_SMOOTH);
        JLabel image = new JLabel(new ImageIcon(img));
        image.setBounds(450, 80, 400, 450);
        add(image);

        // Database Connection
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM account WHERE username = '" + username + "'");
            while (rs.next()) {
                labelusername.setText(rs.getString("username"));
                labelname.setText(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            String username = labelusername.getText();
            String id = (String) comboid.getSelectedItem();
            String number = tfnumber.getText();
            String name = labelname.getText();
            String gender = rmale.isSelected() ? "Male" : "Female";
            String country = tfcountry.getText();
            String address = tfaddress.getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();

            try {
                Conn c = new Conn();
                String query = "INSERT INTO customer VALUES ('" + username + "', '" + id + "', '" + number + "', '"
                        + name + "', '" + gender + "', '" + country + "', '" + address + "', '" + phone + "', '" + email
                        + "')";
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Customer Details Added Successfully!");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddDetails("testuser");
    }
}
