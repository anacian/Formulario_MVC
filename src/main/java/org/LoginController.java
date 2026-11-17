package org;
/**
 * Controller of the application. It receives the events raised by the
 * View, asks the Model to check the credentials and tells the View
 * which message to display.
 * @author Ana Cianca
 * @version 1.0
 * @date 10/10/2026
 */
public class LoginController {
    private LoginForm view;
    private UserDAO userDAO;
    /**
     * Creates a Controller linked to a View and a Model.
     *
     * @param view    the login form
     * @param userDAO the data access object
     */
    public LoginController(LoginForm view, UserDAO userDAO) {
        this.view = view;
        this.userDAO = userDAO;
    }
    /**
     * Handles the LOGIN button: reads the data from the View, validates
     * it and, if it is valid, starts the authentication process.
     */
    public void onLoginButtonClick() {
        String username = view.getUsername();
        String password = view.getPassword();

        if (!validateInput(username, password)) {
            view.showMessage("Username and password are required");
            return;
        }

        processLogin(username, password);
    }
    /**
     * Handles the CANCEL button: clears the fields and the message.
     */
    public void onCancelButtonClick() {
        view.clearFields();
        view.showMessage(" ");
    }
    /**
     * Checks that neither field is empty before querying the database.
     *
     * @param username the username typed by the user
     * @param password the password typed by the user
     * @return {@code true} if both values contain text; {@code false} otherwise
     */
    private boolean validateInput(String username, String password) {
        return !username.isBlank() && !password.isBlank();
    }
    /**
     * Asks the Model to authenticate the user and shows in the View
     * the message that matches the result. If the database cannot be
     * reached, an error message is shown instead.
     *
     * @param username the username typed by the user
     * @param password the password typed by the user
     */

    private void processLogin(String username, String password) {
        try {
            LoginResult result = userDAO.authenticate(username, password);

            switch (result) {
                case SUCCESS -> view.showMessage("Login successful");
                case USER_NOT_FOUND -> view.showMessage("User does not exist");
                case WRONG_PASSWORD -> view.showMessage("Incorrect password");
            }

        } catch (RuntimeException e) {
            view.showMessage("Database connection error");
            e.printStackTrace();
        }
    }
}
