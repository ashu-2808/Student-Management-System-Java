package College;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
/**
 *
 * @author Aman
 */
public class Register extends JFrame implements ActionListener {

    JTextField tfusername;
    JPasswordField tfpassword;
    JButton Add, Back;

    Register() {
        getContentPane().setBackground(Color.WHITE);

        setLayout(null);
        setTitle("NEW USER REGISTRATION");

        JLabel user = new JLabel("Username");
        user.setBounds(40, 20, 100, 30);
        add(user);

        tfusername = new JTextField();
        tfusername.setBounds(150, 20, 150, 30);
        add(tfusername);

        JLabel pass = new JLabel("Password");
        pass.setBounds(40, 70, 100, 30);
        add(pass);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(150, 70, 150, 30);
        add(tfpassword);

        Add = new JButton("Add User");
        Add.setBounds(40, 150, 120, 30);
        Add.setBackground(Color.BLACK);
        Add.setForeground(Color.WHITE);
        Add.addActionListener(this);
        add(Add);

        Back = new JButton("BACK");
        Back.setBounds(200, 150, 120, 30);
        Back.setBackground(Color.BLACK);
        Back.setForeground(Color.WHITE);
        Back.addActionListener(this);
        add(Back);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 10, 200, 200);
        add(image);

        setBounds(500, 200, 600, 250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == Add) {

            String username = tfusername.getText();
            String password = tfpassword.getText();
           try {
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/studentmanagementsystem", "root", "@Ashu1208");
    String query = "INSERT INTO login VALUES (?, ?)";
    PreparedStatement pst = con.prepareStatement(query);

    pst.setString(1, username);
    pst.setString(2, password);

    int rowsAffected = pst.executeUpdate();

    if (rowsAffected > 0) {
        JOptionPane.showMessageDialog(null, "User added successfully");
        setVisible(false);
        new Login();
    } else {
        JOptionPane.showMessageDialog(null, "User not added. Check your query.");
    }

    // Close resources
    pst.close();
    con.close();
} catch (SQLException e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
}
        } else {
            setVisible(false);
            new Login();
        }

    }

    public static void main(String[] args) {
        new Register();
    }

}