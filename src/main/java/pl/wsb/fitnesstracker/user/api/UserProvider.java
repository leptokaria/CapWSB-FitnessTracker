package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interface (API) for retrieving operations on {@link User} entities.
 */
public interface UserProvider {

    /**
     * Retrieves a user based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param userId id of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUser(Long userId);

    /**
     * Retrieves a user based on their email.
     * If the user with given email is not found, then {@link Optional#empty()} will be returned.
     *
     * @param email The email of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Retrieves all users.
     *
     * @return A list of all users
     */
    List<User> findAllUsers();

    /**
     * Searches for users whose email contains the given fragment (case-insensitive).
     *
     * @param emailFragment the fragment of the email to search for
     * @return a list of matching users
     */
    List<User> searchByEmail(String emailFragment);

    /**
     * Searches for users born before the specified date.
     *
     * @param date the date threshold
     * @return a list of users older than the specified date
     */
    List<User> searchByOlderThan(LocalDate date);

}