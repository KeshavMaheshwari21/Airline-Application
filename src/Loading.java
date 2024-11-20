import java.awt.*;
import javax.swing.*;

public class Loading extends JFrame implements Runnable {

    Thread t;
    JProgressBar bar;
    String username;

    public void run() {
        try {
            for (int i = 1; i <= 101; i++) {
                int max = bar.getMaximum();
                int value = bar.getValue();

                if (value < max) {
                    bar.setValue(bar.getValue() + 1);
                } else {
                    setVisible(false);
                    new Dashboard(username);
                }
                Thread.sleep(30);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Loading(String username) {

        this.username = username;

        t = new Thread(this);

        setBounds(500, 200, 650, 300);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel text = new JLabel("Welcome to Cyborg Airlines");
        text.setBounds(163, 40, 600, 40);
        text.setForeground(new Color(0, 50, 100));
        text.setFont(new Font("Raleway", Font.BOLD, 24));
        add(text);

        JLabel loading = new JLabel("Loading, Please Wait...");
        loading.setBounds(230, 100, 200, 30);
        loading.setForeground(new Color(0, 120, 215));
        loading.setFont(new Font("Raleway", Font.BOLD, 16));
        add(loading);

        bar = new JProgressBar();
        bar.setBounds(150, 150, 350, 35);
        bar.setStringPainted(true);
        bar.setForeground(new Color(0, 120, 215));
        bar.setBackground(Color.LIGHT_GRAY);
        add(bar);

        t.start();

        setVisible(true);
    }

    public static void main(String args[]) {
        new Loading("");
    }
}
