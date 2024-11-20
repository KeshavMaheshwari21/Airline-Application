import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class BookTicket extends JFrame implements ActionListener {

    Choice cpackage, cfrom, cto;
    JTextField tfpersons;
    JLabel labelusername, labelid, labelnumber, labelphone, labelprice;
    JButton checkprice, bookpackage, back;
    JTextField[] personNames = new JTextField[3];
    int cost;
    Dimension originalSize;
    Point originalLocation;

    BookTicket(String username) {

        setBounds(165, 178, 900, 600);
        setLayout(null);
        getContentPane().setBackground(new Color(240, 248, 255));

        originalSize = getSize();
        originalLocation = getLocation();

        JLabel text = new JLabel("BOOK TICKET");
        text.setBounds(150, 10, 400, 40);
        text.setFont(new Font("Tahoma", Font.BOLD, 30));
        text.setForeground(new Color(25, 25, 112));
        add(text);

        JLabel lblusername = new JLabel("Username:");
        lblusername.setBounds(40, 70, 150, 25);
        lblusername.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblusername);

        labelusername = new JLabel();
        labelusername.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelusername.setBounds(250, 70, 200, 25);
        add(labelusername);

        JLabel lblfrom = new JLabel("From:");
        lblfrom.setBounds(40, 110, 150, 25);
        lblfrom.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblfrom);

        cfrom = new Choice();
        cfrom.add("Chennai");
        cfrom.add("Mumbai");
        cfrom.add("Delhi");
        cfrom.add("Kolkata");
        cfrom.setBounds(250, 110, 200, 25);
        add(cfrom);

        JLabel lblto = new JLabel("To:");
        lblto.setBounds(40, 150, 150, 25);
        lblto.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblto);

        cto = new Choice();
        cto.add("Chennai");
        cto.add("Mumbai");
        cto.add("Delhi");
        cto.add("Kolkata");
        cto.setBounds(250, 150, 200, 25);
        add(cto);

        JLabel lblpackage = new JLabel("Select Class:");
        lblpackage.setBounds(40, 190, 150, 25);
        lblpackage.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblpackage);

        cpackage = new Choice();
        cpackage.add("Economy");
        cpackage.add("Luxury");
        cpackage.setBounds(250, 190, 200, 25);
        add(cpackage);

        JLabel lblpersons = new JLabel("Total Persons:");
        lblpersons.setBounds(40, 230, 150, 25);
        lblpersons.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblpersons);

        tfpersons = new JTextField("1");
        tfpersons.setBounds(250, 230, 200, 25);
        tfpersons.addActionListener(e -> updatePersonFields());
        add(tfpersons);

        for (int i = 0; i < 3; i++) {
            personNames[i] = new JTextField("Name of Person " + (i + 1));
            personNames[i].setBounds(250, 270 + i * 40, 200, 25);
            personNames[i].setVisible(false);
            add(personNames[i]);
        }

        JLabel lblprice = new JLabel("Total Price:");
        lblprice.setBounds(40, 400, 150, 25);
        lblprice.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblprice);

        labelprice = new JLabel();
        labelprice.setBounds(250, 400, 200, 25);
        labelprice.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelprice);

        checkprice = new JButton("Check Price");
        checkprice.setBounds(50, 450, 120, 30);
        checkprice.setBackground(new Color(25, 25, 112));
        checkprice.setForeground(Color.WHITE);
        checkprice.addActionListener(this);
        add(checkprice);

        bookpackage = new JButton("Book Ticket");
        bookpackage.setBounds(190, 450, 120, 30);
        bookpackage.setBackground(new Color(25, 25, 112));
        bookpackage.setForeground(Color.WHITE);
        bookpackage.addActionListener(this);
        add(bookpackage);

        back = new JButton("Back");
        back.setBounds(330, 450, 120, 30);
        back.setBackground(new Color(25, 25, 112));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/bookticket.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 500, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(500, 30, 350, 500);
        add(image);

        try {
            Conn c = new Conn();
            String query = "SELECT * FROM customer WHERE username = '" + username + "'";
            ResultSet rs = c.s.executeQuery(query);

            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "No customer details found. Please add your details.");
                new AddDetails(username);
                dispose();
                return;
            } else {
                labelusername.setText(rs.getString("username"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    private void updatePersonFields() {
        int persons = Math.min(3, Integer.parseInt(tfpersons.getText()));
        for (int i = 0; i < 3; i++) {
            personNames[i].setVisible(i < persons);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == checkprice) {
            String pack = cpackage.getSelectedItem();
            int persons = Integer.parseInt(tfpersons.getText());
    
            cost = pack.equals("Economy") ? 2000 + (int) (Math.random() * 3000) : 5000 + (int) (Math.random() * 3000);
            cost *= persons;
    
            labelprice.setText("Rs " + cost);
        } else if (ae.getSource() == bookpackage) {
            try {
                Conn c = new Conn();
                StringBuilder names = new StringBuilder();
                for (int i = 0; i < Math.min(3, Integer.parseInt(tfpersons.getText())); i++) {
                    if (!personNames[i].getText().trim().isEmpty()) {
                        names.append(personNames[i].getText()).append(",");
                    }
                }
    
                String query = "INSERT INTO bookpackage VALUES ('" + labelusername.getText() + "', '"
                        + cfrom.getSelectedItem() + "', '" + cto.getSelectedItem() + "', '"
                        + cpackage.getSelectedItem() + "', '" + tfpersons.getText() + "', '"
                        + names.toString() + "', '" + cost + "')";
                c.s.executeUpdate(query);
    
                JOptionPane.showMessageDialog(null, "Ticket Booked Successfully!");
                dispose(); // Close the window immediately after booking the ticket
    
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            dispose(); // Close the window when "Back" is pressed
        }
    }
    

    public static void main(String[] args) {
        new BookTicket("Keshav21");
    }
}
