import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateDetails extends JFrame implements ActionListener {

    JLabel labelusername, labelname;
    JTextField tfnumber, tfcountry, tfaddress, tfemail, tfphone, tfid, tfgender;
    JButton add, back;

    UpdateDetails(String username) {
        setBounds(165, 160, 900, 640);
        setLayout(null);
        getContentPane().setBackground(Color.LIGHT_GRAY);

        JLabel text = new JLabel("UPDATE CUSTOMER DETAILS");
        text.setBounds(250, 20, 400, 30);
        text.setFont(new Font("Tahoma", Font.BOLD, 22));
        text.setHorizontalAlignment(SwingConstants.CENTER);
        add(text);

        int labelX = 50, labelWidth = 150, labelHeight = 30;
        int fieldX = 220, fieldWidth = 200, fieldHeight = 30;
        int verticalSpacing = 50;

        JLabel lblusername = new JLabel("Username:");
        lblusername.setBounds(labelX, 80, labelWidth, labelHeight);
        lblusername.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblusername);

        labelusername = new JLabel();
        labelusername.setBounds(fieldX, 80, fieldWidth, labelHeight);
        labelusername.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(labelusername);

        JLabel lblid = new JLabel("ID:");
        lblid.setBounds(labelX, 80 + verticalSpacing, labelWidth, labelHeight);
        lblid.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblid);

        tfid = new JTextField();
        tfid.setBounds(fieldX, 80 + verticalSpacing, fieldWidth, fieldHeight);
        add(tfid);

        JLabel lblnumber = new JLabel("Number:");
        lblnumber.setBounds(labelX, 80 + 2 * verticalSpacing, labelWidth, labelHeight);
        lblnumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblnumber);

        tfnumber = new JTextField();
        tfnumber.setBounds(fieldX, 80 + 2 * verticalSpacing, fieldWidth, fieldHeight);
        add(tfnumber);

        JLabel lblname = new JLabel("Name:");
        lblname.setBounds(labelX, 80 + 3 * verticalSpacing, labelWidth, labelHeight);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblname);

        labelname = new JLabel();
        labelname.setBounds(fieldX, 80 + 3 * verticalSpacing, fieldWidth, labelHeight);
        labelname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(labelname);

        JLabel lblgender = new JLabel("Gender:");
        lblgender.setBounds(labelX, 80 + 4 * verticalSpacing, labelWidth, labelHeight);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblgender);

        tfgender = new JTextField();
        tfgender.setBounds(fieldX, 80 + 4 * verticalSpacing, fieldWidth, fieldHeight);
        add(tfgender);

        JLabel lblcountry = new JLabel("Country:");
        lblcountry.setBounds(labelX, 80 + 5 * verticalSpacing, labelWidth, labelHeight);
        lblcountry.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblcountry);

        tfcountry = new JTextField();
        tfcountry.setBounds(fieldX, 80 + 5 * verticalSpacing, fieldWidth, fieldHeight);
        add(tfcountry);

        JLabel lbladdress = new JLabel("Address:");
        lbladdress.setBounds(labelX, 80 + 6 * verticalSpacing, labelWidth, labelHeight);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(fieldX, 80 + 6 * verticalSpacing, fieldWidth, fieldHeight);
        add(tfaddress);

        JLabel lblphone = new JLabel("Phone No.:");
        lblphone.setBounds(labelX, 80 + 7 * verticalSpacing, labelWidth, labelHeight);
        lblphone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(fieldX, 80 + 7 * verticalSpacing, fieldWidth, fieldHeight);
        add(tfphone);

        JLabel lblemail = new JLabel("Email:");
        lblemail.setBounds(labelX, 80 + 8 * verticalSpacing, labelWidth, labelHeight);
        lblemail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblemail);

        tfemail = new JTextField();
        tfemail.setBounds(fieldX, 80 + 8 * verticalSpacing, fieldWidth, fieldHeight);
        add(tfemail);

        add = new JButton("Update");
        add.setBackground(Color.DARK_GRAY);
        add.setForeground(Color.WHITE);
        add.setBounds(100, 538, 120, 40);
        add.setFont(new Font("Tahoma", Font.BOLD, 16));
        add.addActionListener(this);
        add(add);

        back = new JButton("Back");
        back.setBackground(Color.DARK_GRAY);
        back.setForeground(Color.WHITE);
        back.setBounds(300, 538, 120, 40);
        back.setFont(new Font("Tahoma", Font.BOLD, 16));
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/updatedetails.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 450, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(500, 80, 350, 450);
        add(image);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("Select * from customer where username = '" + username + "'");
            while (rs.next()) {
                labelusername.setText(rs.getString("username"));
                labelname.setText(rs.getString("name"));
                tfid.setText(rs.getString("id"));
                tfnumber.setText(rs.getString("number"));
                tfgender.setText(rs.getString("gender"));
                tfcountry.setText(rs.getString("country"));
                tfaddress.setText(rs.getString("address"));
                tfphone.setText(rs.getString("phone"));
                tfemail.setText(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            try {
                Conn c = new Conn();
                String query = "UPDATE customer SET id='" + tfid.getText() + "', number='" + tfnumber.getText() +
                               "', gender='" + tfgender.getText() + "', country='" + tfcountry.getText() + 
                               "', address='" + tfaddress.getText() + "', phone='" + tfphone.getText() +
                               "', email='" + tfemail.getText() + "' WHERE username='" + labelusername.getText() + "'";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Customer Details Updated Successfully!");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String args[]) {
        new UpdateDetails("TestUser");
    }
}
