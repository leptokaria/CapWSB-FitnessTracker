package pl.wsb.fitnesstracker.training.internal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wsb.fitnesstracker.training.api.TrainingDto;

import java.util.List;

/**
 * REST Controller for managing trainings.
 */
@RestController
@RequestMapping("/v1/trainings")
class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;

    public TrainingController(TrainingServiceImpl trainingService, TrainingMapper trainingMapper) {
        this.trainingService = trainingService;
        this.trainingMapper = trainingMapper;
    }

    /**
     * Retrieves all trainings.
     *
     * @return a list of all trainings
     */
    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Retrieves all trainings for a specific user.
     *
     * @param userId the ID of the user
     * @return a list of trainings for the user
     */
    @GetMapping("/user/{userId}")
    public List<TrainingDto> getTrainingsForUser(@PathVariable Long userId) {
        return trainingService.getTrainingsForUser(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }
}