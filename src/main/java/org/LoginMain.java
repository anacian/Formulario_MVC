package org;

import javax.swing.*;
/**
 * Entry point of the application. It creates the Model, the View and
 * the Controller, links them together and shows the window.
 * @author Ana Cianca
 * @version 1.0
 * @date 10/10/2026
 */
public class LoginMain {
    /**
     * Starts the MySQL login application.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginForm view = new LoginForm();
            UserDAO userDAO = new UserDAO();
            LoginController controller = new LoginController(view, userDAO);

            view.setController(controller);

            JFrame ventana = new JFrame("MySQL Login");
            ventana.setContentPane(view.getPanelPrincipal());
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.pack();
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        });
    }
}
