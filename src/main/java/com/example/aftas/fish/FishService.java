package com.example.aftas.fish;

import com.example.aftas.exception.ResourceNotFoundException;
import com.example.aftas.level.Level;
import com.example.aftas.level.LevelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FishService implements IFish{
    private final FishRepository fishRepository;
    @Autowired
    private LevelService levelService;
    @Autowired
    private  ModelMapper modelMapper;

    @Autowired
    public FishService(FishRepository fishRepository, ModelMapper modelMapper) {
        this.fishRepository = fishRepository;

    }
    /*
       @Override

       public FishDtoRes createFish(FishDtoReq fishDto) {
           Fish fishEntity = modelMapper.map(fishDto, Fish.class);
           Fish savedFish = fishRepository.save(fishEntity);
           return modelMapper.map(savedFish, FishDtoRes.class);
       }*/
    @Override
    public FishDtoRes createFish(FishDtoReq fishDto) {
        // Mapping du DTO FishDtoReq vers l'entité Fish
        Fish fishEntity = modelMapper.map(fishDto, Fish.class);

        // Vérification et récupération du Level à partir de la base de données ou d'une autre source
       Optional<Level>  level = levelService.getLevelById(fishDto.getLevel_id());
        if (level.isEmpty() ) {
            // Gérer le cas où le Level n'a pas été trouvé
             throw new ResourceNotFoundException("Level not found");
        }

        // Attribution du Level récupéré au Fish
        fishEntity.setLevel(level.get());

        // Sauvegarde du Fish dans la base de données
        Fish savedFish = fishRepository.save(fishEntity);

        // Mapping de l'entité Fish sauvegardée vers le DTO FishDtoRes
        return modelMapper.map(savedFish, FishDtoRes.class);
    }


    @Override
    public void deleteFish(String fishId) {
        if (!fishRepository.existsById(fishId)) {
            throw new ResourceNotFoundException("Fish with ID " + fishId + " not found");
        }
        fishRepository.deleteById(fishId);
    }

    @Override
    public Optional<FishDtoRes> getFishById(String fishId) {
        Optional<Fish> fish = fishRepository.findById(fishId);
        if (fish.isPresent()) {
            FishDtoRes fishDtoRes = modelMapper.map(fish.get(), FishDtoRes.class);
            return Optional.of(fishDtoRes);
        } else {
            throw new ResourceNotFoundException("Fish with ID " + fishId + " not found");
        }
    }


    @Override
    public List<FishDtoRes> getAllFish() {
        List<Fish> fishList = fishRepository.findAll();
        return fishList.stream()
                .map(fish -> modelMapper.map(fish, FishDtoRes.class))
                .collect(Collectors.toList());
    }

}
