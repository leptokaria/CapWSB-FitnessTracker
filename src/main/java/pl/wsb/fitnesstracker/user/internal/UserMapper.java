package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;

/**
 * Mapper for converting between {@link User} entity and {@link UserDto}.
 */
@Component
class UserMapper {

    /**
     * Converts a User entity to a UserDto.
     *
     * @param user the User entity
     * @return the UserDto
     */
    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    /**
     * Converts a UserDto to a User entity.
     *
     * @param userDto the UserDto
     * @return the User entity
     */
    User toEntity(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
    }
}