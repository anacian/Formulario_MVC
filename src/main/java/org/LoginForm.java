package org;

import javax.swing.*;
import java.awt.*;

/**
 *  View class. Shows the interface and collects what i write
 * @author Ana Cianca
 * @version 1.0
 * @date 7/10/2026
 */
public class LoginForm {
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JPanel panelPrincipal;
    private JTextField txfUsername;
    private JPasswordField pwfPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private JLabel lblMessage;
    private LoginController controller;

    /**
     * LoginForm Constructor
     */
    public LoginForm() {
        addEvents();
    }

    /**
     * Change controller's value
     * @param controller
     */
    public void setController(LoginController controller) {
        this.controller = controller;
    }

    /**
     * Shows text field username value
     * @return the value in the text field
     */
    public String getUsername() {
        return txfUsername.getText().trim();
    }

    /**
     * Shows password field password value
     * @return the value in the text field
     */
    public String getPassword() {
        return new String(pwfPassword.getPassword());
    }

    /**
     * Generates a message in the message label
     * @param message to print in the label
     */
    public void showMessage(String message) {
        lblMessage.setText(message);
    }

    /**
     * Method that cleans all the field in the interface
     */
    public void clearFields() {
        txfUsername.setText("");
        pwfPassword.setText("");
        txfUsername.requestFocus();
    }

    /**
     * Method that returns the panel
     * @return
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    private void addEvents() {
        btnLogin.addActionListener(e -> controller.onLoginButtonClick());
        btnCancel.addActionListener(e -> controller.onCancelButtonClick());
    }
}
