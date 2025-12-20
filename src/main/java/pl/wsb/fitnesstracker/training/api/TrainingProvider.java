package pl.wsb.fitnesstracker.training.api;

import java.util.List;
import java.util.Optional;

public interface TrainingProvider {

    /**
     * Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Optional<Training> getTraining(Long trainingId);

    /**
     * Retrieves all trainings.
     *
     * @return a list of all trainings
     */
    List<Training> getAllTrainings();

    /**
     * Retrieves all trainings for a specific user.
     *
     * @param userId the ID of the user
     * @return a list of trainings for the user
     */
    List<Training> getTrainingsForUser(Long userId);

}