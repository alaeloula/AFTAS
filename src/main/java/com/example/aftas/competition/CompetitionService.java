package com.example.aftas.competition;

import com.example.aftas.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        Competition competition1 = modelMapper.map(competition, Competition.class);
        competition1.setCode(codeGenerator(competition.getLocation(), competition.getDate()));
        Competition savedCmp = competionRepository.save(competition1);
        return modelMapper.map(savedCmp, CompetitionDtoRes.class);
    }

    @Override
    public void deleteById(String competitionId) {
        if (competionRepository.existsById(competitionId)) {
            competionRepository.deleteById(competitionId);
        } else {
            throw new ResourceNotFoundException("Competition with ID " + competitionId + " not found");
        }
    }

    @Override
    public Optional<CompetitionDtoRes> findById(String competitionId) {
        Optional<Competition> competition = competionRepository.findById(competitionId);
        if (competition.isPresent()) {
            return Optional.of(modelMapper.map(competition.get(), CompetitionDtoRes.class));
        } else {
            throw new ResourceNotFoundException("Competition with ID " + competitionId + " not found");
        }
    }

    //@Override
    public Page<CompetitionDtoRes> getAllCompetitions(Pageable pageable) {
        Page<Competition> competitions = competionRepository.findAll(pageable);
        return competitions.map(competition -> modelMapper.map(competition, CompetitionDtoRes.class));
    }




    @Override
    public boolean existsById(Integer competitionId) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }

    public List<CompetitionDtoRes> getCompetitionsBeforeToday() {
        LocalDate today = LocalDate.now();
        List<Competition> cmp = competionRepository.findByDateBefore(today);
        return cmp.stream()
                .map(competition -> modelMapper.map(competition, CompetitionDtoRes.class))
                .collect(Collectors.toList());
    }
    public static String codeGenerator(String city, LocalDate date) {
        String codePrefix = city.substring(0, Math.min(city.length(), 3)).toLowerCase();

        String dateSuffix = date.format(DateTimeFormatter.ofPattern("yy-MM-dd"));

        return codePrefix + "-" + dateSuffix;
    }

}
