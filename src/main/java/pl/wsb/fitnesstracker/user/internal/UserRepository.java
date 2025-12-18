package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    Optional<User> findByEmail(String email);

    /**
     * Query searching users by email address (case-insensitive) containing a fragment.
     *
     * @param emailFragment fragment of the email to search
     * @return list of found users
     */
    List<User> findByEmailContainingIgnoreCase(String emailFragment);

    /**
     * Query searching users born before a specified date.
     *
     * @param date date threshold
     * @return list of found users
     */
    List<User> findByBirthdateBefore(LocalDate date);
}