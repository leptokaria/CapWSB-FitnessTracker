package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

/**
 * Entity representing a user in the system.
 * Contains basic personal information such as name, birthdate, and email.
 */
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User {

    /**
     * Unique identifier for the user.
     * Generated automatically by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    /**
     * User's first name.
     */
    @Column
    private String firstName;

    /**
     * User's last name.
     */
    @Column
    private String lastName;

    /**
     * User's birthdate.
     * Cannot be null.
     */
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    /**
     * User's email address.
     * Must be unique and cannot be null.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Constructs a new User with the specified details.
     *
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @param birthdate the user's birthdate
     * @param email     the user's email address
     */
    public User(
            final String firstName,
            final String lastName,
            final LocalDate birthdate,
            final String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
    }

    /**
     * Gets the user's ID.
     *
     * @return the user's ID, or null if not persisted yet
     */
    @Nullable
    public Long getId() {
        return id;
    }

    /**
     * Sets the user's ID.
     *
     * @param id the new ID
     */
    public void setId(@Nullable Long id) {
        this.id = id;
    }

    /**
     * Gets the user's first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the user's first name.
     *
     * @param firstName the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the user's last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the user's last name.
     *
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the user's birthdate.
     *
     * @return the birthdate
     */
    public LocalDate getBirthdate() {
        return birthdate;
    }

    /**
     * Sets the user's birthdate.
     *
     * @param birthdate the new birthdate
     */
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * Gets the user's email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}