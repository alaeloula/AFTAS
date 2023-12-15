package com.example.aftas.competition;

import com.example.aftas.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompetitionService implements ICompetition {
    private final CompetionRepository competionRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public CompetitionService(CompetionRepository competionRepository) {
        this.competionRepository = competionRepository;
    }

    @Override
    public CompetitionDtoRes save(CompetitionDtoReq competition) {
        Competition competition1 =modelMapper.map(competition,Competition.class);
        Competition savedCmp =competionRepository.save(competition1);
        return modelMapper.map(savedCmp, CompetitionDtoRes.class);
    }

    @Override
    public void deleteById(Integer competitionId) {
        if (competionRepository.existsById(competitionId)) {
            competionRepository.deleteById(competitionId);
        } else {
            throw new ResourceNotFoundException("Competition with ID " + competitionId + " not found");
        }
    }

    @Override
    public Optional<CompetitionDtoRes> findById(Integer competitionId) {
        Optional<Competition> competition = competionRepository.findById(competitionId);
        if (competition.isPresent()) {
            return Optional.of(modelMapper.map(competition.get(), CompetitionDtoRes.class));
        } else {
            throw new ResourceNotFoundException("Competition with ID " + competitionId + " not found");
        }
    }

    @Override
    public List<CompetitionDtoRes> getAllCompetitions() {
        List<Competition> competitions = competionRepository.findAll();
        return competitions.stream()
                .map(competition -> modelMapper.map(competition, CompetitionDtoRes.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(Integer competitionId) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public List<CompetitionDtoRes> getCompetitionsByDateBefore(LocalDate date)
    {
        return null;
    }
}
