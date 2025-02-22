package qllh.view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import qllh.entity.User;

public class LoginView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JLabel userNameLabel;
    private JLabel passwordlabel;
    private JPasswordField passwordField;
    private JTextField userNameField;
    private JButton loginBtn;
    private JLabel jldangnhap;

    public LoginView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        userNameLabel = new JLabel("UserName");
        passwordlabel = new JLabel("Password");
        userNameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        jldangnhap = new JLabel("đăng nhập");
        this.setLocation(600, 300);
        loginBtn = new JButton();

        loginBtn.setText("Login");
        loginBtn.addActionListener(this);
        

    
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
      
        panel.setSize(400, 300);
        panel.setLayout(layout);
        panel.add(userNameLabel);
        panel.add(passwordlabel);
        panel.add(userNameField);
        panel.add(passwordField);
        panel.add(loginBtn);
        panel.add(jldangnhap);
        

        
        layout.putConstraint(SpringLayout.WEST, userNameLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, userNameLabel, 80, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, passwordlabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, passwordlabel, 105, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, userNameField, 80, SpringLayout.WEST, userNameLabel);
        layout.putConstraint(SpringLayout.NORTH, userNameField, 80, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, passwordField, 80, SpringLayout.WEST, passwordlabel);
        layout.putConstraint(SpringLayout.NORTH, passwordField, 105, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, loginBtn, 80, SpringLayout.WEST, passwordlabel);
        layout.putConstraint(SpringLayout.NORTH, loginBtn, 130, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, jldangnhap, 130, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jldangnhap, 60, SpringLayout.NORTH, panel);
        

       
        this.add(panel);
        this.pack();
        
        this.setTitle("Login");
        this.setSize(400, 300);
        this.setResizable(false);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public User getUser() {
        return new User(userNameField.getText(), 
                String.copyValueOf(passwordField.getPassword()));
    }

    public void actionPerformed(ActionEvent e) {
    }
    
    public void addLoginListener(ActionListener listener) {
        loginBtn.addActionListener(listener);
    }

   }
