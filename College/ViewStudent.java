package College;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class ViewStudent extends JFrame implements ActionListener {

    JTable table;
    JButton back;
    Connection con;

    ViewStudent() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setTitle("STUDENT INFO");

        JLabel l1 = new JLabel("Name");
        l1.setBounds(40, 10, 100, 20);
        add(l1);

        JLabel l2 = new JLabel("Entry Number");
        l2.setBounds(170, 10, 100, 20);
        add(l2);

        JLabel l3 = new JLabel("Email");
        l3.setBounds(320, 10, 100, 20);
        add(l3);

        JLabel l4 = new JLabel("Contact Number");
        l4.setBounds(470, 10, 100, 20);
        add(l4);

        JLabel l5 = new JLabel("Homecity");
        l5.setBounds(610, 10, 100, 20);
        add(l5);

        table = new JTable();
        table.setBounds(0, 40, 750, 400);
        add(table);

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/studentmanagementsystem", "root", "@Ashu1208");
            String query = "SELECT * FROM student";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs)); // Use DbUtils to populate the JTable
        } catch (Exception e) {
            e.printStackTrace();
        }

        back = new JButton("BACK");
        back.setBackground(Color.DARK_GRAY);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        back.setBounds(420, 500, 120, 30);
        add(back);

        setBounds(300, 200, 750, 600);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu();
				menu.show();
				dispose();
			}



    public static void main(String args[]) {
        new ViewStudent();
    }
}
