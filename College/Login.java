/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package College;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {
    JTextField username;
    JPasswordField password;
    JButton login, cancel, register;
    Connection con;

    public Login() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel user = new JLabel("Username");
        user.setBounds(40, 20, 100, 30);
        add(user);

        username = new JTextField();
        username.setBounds(150, 20, 150, 30);
        add(username);

        JLabel pass = new JLabel("Password");
        pass.setBounds(40, 70, 100, 30);
        add(pass);

        password = new JPasswordField();
        password.setBounds(150, 70, 150, 30);
        add(password);

        login = new JButton("Login");
        login.setBounds(40, 150, 120, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        cancel = new JButton("Cancel");
        cancel.setBounds(180, 150, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 10, 200, 200);
        add(image);

        register = new JButton("REGISTER");
        register.setBounds(100, 200, 120, 30);
        register.setBackground(Color.BLACK);
        register.setForeground(Color.WHITE);
        register.addActionListener(this);
        add(register);

        setBounds(500, 200, 600, 300);
        setVisible(true);
        setTitle("LOGIN/REGISTER");
    }

    public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == login) {
        String user = username.getText();
        String pass = new String(password.getPassword());

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/studentmanagementsystem", "root", "@Ashu1208");

            String query = "select * from login where username = ? and password = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, user);
            pst.setString(2, pass);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Login successful. Welcome, " + user);
                setVisible(false);

                // Create and display the 'Menu' class
                Menu menu = new Menu();
                menu.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password");
            }

            rs.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
        }
    } else if (ae.getSource() == cancel) {
        setVisible(false);
        new StudentManagementSystem();
    } else if (ae.getSource() == register) {
        setVisible(false);
        new Register();
        // Replace 'new Register();' with your logic to open the registration window
    }
}


    public static void main(String[] args) {
        new Login();
    }
}
