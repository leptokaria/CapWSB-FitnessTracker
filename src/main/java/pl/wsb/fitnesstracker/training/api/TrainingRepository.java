package pl.wsb.fitnesstracker.training.api;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Finds all trainings for a specific user.
     *
     * @param userId the ID of the user
     * @return a list of trainings
     */
    List<Training> findByUserId(Long userId);
}