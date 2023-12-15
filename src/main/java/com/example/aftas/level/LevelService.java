package com.example.aftas.level;

import com.example.aftas.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LevelService implements Ilevel {
    private final LevelRepository levelRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public LevelService(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public Level createLevel(LevelDto level) {
        List<Level> allLevels = levelRepository.findAll();
        Level levelEntity = modelMapper.map(level, Level.class);

        if (allLevels.isEmpty()) {
            Level savedLevel = levelRepository.save(levelEntity);
            return modelMapper.map(savedLevel, Level.class);
        }

        // Vérifier si le point du nouveau niveau est supérieur à tous les autres niveaux
        boolean isGreater = allLevels.stream().allMatch(existingLevel -> level.getPoint() > existingLevel.getPoint());

        if (isGreater) {
            Level savedLevel = levelRepository.save(levelEntity);
            return modelMapper.map(savedLevel, Level.class);
        } else {

            throw new IllegalArgumentException("Le point du nouveau niveau n'est pas supérieur à tous les autres niveaux.");
        }
    }

    @Override
    public void deleteLevel(int levelId) {
        if (levelRepository.existsById(levelId)) {
            levelRepository.deleteById(levelId);
        } else {
            throw new ResourceNotFoundException("level with ID " + levelId + " not found");
        }
    }

    @Override
    public Optional<Level> getLevelById(int levelId) {
        Optional<Level> level = levelRepository.findById(levelId);
        if (level.isPresent()) {
            return Optional.of(modelMapper.map(level.get(), Level.class));
        } else {
            throw new ResourceNotFoundException("Competition with ID " + levelId + " not found");
        }
    }

    @Override
    public List<Level> getAllLevels() {
       List<Level> levels = levelRepository.findAll();
        return levels;
    }
}
