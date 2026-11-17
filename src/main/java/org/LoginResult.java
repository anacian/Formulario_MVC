package org;
/**
 * Possible outcomes of a login attempt.
 * The Model returns one of these values and the
 * Controller decides which message to show.
 *
 * @author Ana Cianca
 * @version 1.0
 * @date 10/10/2026
 */
public enum LoginResult {
    SUCCESS,
    USER_NOT_FOUND,
    WRONG_PASSWORD
}
