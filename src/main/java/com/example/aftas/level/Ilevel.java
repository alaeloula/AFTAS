package com.example.aftas.level;

import java.util.List;
import java.util.Optional;

public interface Ilevel {
    Level createLevel(LevelDto level);
    //Level updateLevel(int levelId, Level level);
    void deleteLevel(int levelId);
    Optional<Level> getLevelById(int levelId);
    List<Level> getAllLevels();
}
