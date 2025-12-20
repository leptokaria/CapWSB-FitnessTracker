package pl.wsb.fitnesstracker.user.internal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserNotFoundException;

import java.time.LocalDate;
import java.util.List;

/**
 * REST Controller for managing users.
 * Provides endpoints for creating, retrieving, updating, and deleting users.
 */
@RestController
@RequestMapping("/v1/users")
class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    public UserController(UserServiceImpl userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    /**
     * Retrieves all users with full details.
     *
     * @return a list of all users
     */
    @GetMapping
    public List<UserDto> getAllUsersFull() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Retrieves all users with basic details (same as full for now, but mapped to a different endpoint).
     *
     * @return a list of all users
     */
    @GetMapping("/simple")
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user
     * @return the user details
     * @throws UserNotFoundException if the user is not found
     */
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Creates a new user.
     *
     * @param userDto the user data
     * @return the created user
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User savedUser = userService.createUser(user);
        return userMapper.toDto(savedUser);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    /**
     * Updates an existing user.
     *
     * @param id      the ID of the user to update
     * @param userDto the new user data
     * @return the updated user
     * @throws UserNotFoundException if the user is not found
     */
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        // Sprawdzamy czy user istnieje, jeśli nie - rzuci wyjątek
        User existingUser = userService.getUser(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        // Aktualizujemy dane
        existingUser.setFirstName(userDto.firstName());
        existingUser.setLastName(userDto.lastName());
        existingUser.setBirthdate(userDto.birthdate());
        existingUser.setEmail(userDto.email());

        // Zapisujemy
        User updatedUser = userService.updateUser(existingUser);
        return userMapper.toDto(updatedUser);
    }

    /**
     * Searches for users by email fragment.
     *
     * @param email the email fragment to search for
     * @return a list of matching users
     */
    @GetMapping("/email")
    public List<UserDto> searchUsersByEmail(@RequestParam String email) {
        return userService.searchByEmail(email)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Searches for users older than a specified date.
     *
     * @param date the date threshold
     * @return a list of users older than the specified date
     */
    @GetMapping("/older/{date}")
    public List<UserDto> searchUsersByAge(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return userService.searchByOlderThan(date)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
}