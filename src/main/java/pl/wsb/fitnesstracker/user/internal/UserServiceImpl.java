package pl.wsb.fitnesstracker.user.internal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(final User user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    // --- Nowa metoda do aktualizacji ---
    public User updateUser(final User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("User must have an ID to be updated!");
        }
        return userRepository.save(user);
    }

    // --- Nowa metoda do usuwania ---
    public void deleteUser(final Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // --- Nowe metody wyszukiwania (delegacja do repozytorium) ---
    public List<User> searchByEmail(String emailFragment) {
        return userRepository.findAllByEmailFragment(emailFragment);
    }

    public List<User> searchByAge(int age) {
        return userRepository.findUsersOlderThan(age);
    }
}