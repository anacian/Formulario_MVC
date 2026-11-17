package org;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Model of the application. It is the only class that accesses MySQL,
 * using JDBC, to check whether a username and password are valid.
 *
 * @author Ana Cianca
 * @version 1.0
 * @date 10/10/2026
 */
public class UserDAO {
    private String url;
    private String user;
    private String password;
    /**
     * Creates a Model with the default connection values:
     * database {@code formulario} on {@code localhost:3306}, user {@code admin}.
     */
    public UserDAO() {
        this("jdbc:mysql://localhost:3306/formulario", "admin", "Admin-123");
    }
    /**
     * Creates a Model with custom connection values.
     *
     * @param url      JDBC URL of the database
     * @param user     MySQL user name
     * @param password MySQL user password
     */
    public UserDAO(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }
    /**
     * Opens a new connection to the database.
     *
     * @return an open {@link Connection}; the caller must close it
     * @throws SQLException if the connection cannot be established
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    /**
     * Checks whether a user exists in the {@code usuarios} table and
     * whether the given password matches the stored one.
     *
     * @param username the username typed in the form
     * @param password the password typed in the form
     * @return {@link LoginResult#SUCCESS} if the credentials are correct,
     *         {@link LoginResult#USER_NOT_FOUND} if the user does not exist,
     *         or {@link LoginResult#WRONG_PASSWORD} if the password is incorrect
     * @throws RuntimeException if a database error occurs
     */
    public LoginResult authenticate(String username, String password) {
        String sql = "SELECT password FROM usuarios WHERE usuario = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return LoginResult.USER_NOT_FOUND;
                }

                String storedPassword = rs.getString("password");

                if (storedPassword.equals(password)) {
                    return LoginResult.SUCCESS;
                }
                return LoginResult.WRONG_PASSWORD;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }
}
