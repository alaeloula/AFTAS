package com.example.aftas.ranking;

import com.example.aftas.competition.CompetionRepository;
import com.example.aftas.competition.Competition;
import com.example.aftas.exception.ResourceNotFoundException;
import com.example.aftas.member.MemberRepository;
import com.example.aftas.member.Membre;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RankingService implements IRanking {

    private final RankingRepository rankingRepository;
    private final CompetionRepository competitionRepository;
    private final MemberRepository memberRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public RankingService(RankingRepository rankingRepository, CompetionRepository competitionRepository, MemberRepository memberRepository) {
        this.rankingRepository = rankingRepository;
        this.competitionRepository = competitionRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public List<RankingDtoRes> getAllRankings() {
        List<Ranking> rankings = rankingRepository.findAll();
        return rankings.stream()
                .map(ranking -> modelMapper.map(ranking, RankingDtoRes.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RankingDtoRes> findById(RankingId rankingId) {
        Optional<Ranking> ranking = rankingRepository.findById(rankingId);
        if (ranking.isPresent()) {
            return Optional.of(modelMapper.map(ranking.get(), RankingDtoRes.class));
        } else {
            throw new ResourceNotFoundException("Ranking with ID " + rankingId + " not found");
        }
    }

    @Override
    public Optional<RankingDtoRes> save(RankingDtoReq ranking) {
        if(validateRanking(ranking.getCompetitionId())){
            throw new IllegalArgumentException("Competition is closed : number of participant reached");
        }
        if(validateDate(ranking.getCompetitionId())){
            throw new IllegalArgumentException("Competition is closed : date of the competition is on less then 24h");
        }
        Competition competition = competitionRepository.findById(ranking.getCompetitionId()).orElseThrow(() -> new ResourceNotFoundException("Invalid competition Code"));
        Membre member = memberRepository.findById(ranking.getMemberId()).orElseThrow(() -> new ResourceNotFoundException("Invalid Member code"));
        RankingId rankingId = new RankingId(competition.getCode(), member.getNum());
        Ranking rankingToSave = modelMapper.map(ranking , Ranking.class);
        rankingToSave.setCompetition(competition);
        rankingToSave.setMember(member);
        rankingToSave.setId(rankingId);
        Optional<Ranking> isFound = rankingRepository.findById(rankingToSave.getId());
        if(isFound.isPresent()){
            throw new IllegalArgumentException("This member : "+isFound.get().getMember().getName() + " " +isFound.get().getMember().getFamilyName() + " is already registered in the competition "+ isFound.get().getCompetition().getCode());
        }else{
            rankingRepository.save(rankingToSave);
            return Optional.of(modelMapper.map(rankingToSave , RankingDtoRes.class));
        }
    }
    @Override
    public void deleteById(RankingId rankingId) {
        if (rankingRepository.existsById(rankingId)) {
            rankingRepository.deleteById(rankingId);
        } else {
            throw new ResourceNotFoundException("Ranking with ID " + rankingId + " not found");
        }
    }

    @Override
    public boolean existsById(RankingId rankingId) {
        return rankingRepository.existsById(rankingId);
    }

    @Override
    public long count() {
        return rankingRepository.count();
    }



    public boolean validateRanking(int competitionCode) {
        Competition competition = competitionRepository.findById(competitionCode).orElseThrow(() -> new IllegalArgumentException("Invalid competition Code"));
        int numberOfParticipant = rankingRepository.countRankingByCompetition(competition);
        return numberOfParticipant >= competition.getNumberOfParticipation();
    }

    public boolean validateDate(int competitionCode) {
        Competition competition = competitionRepository.findById(competitionCode).orElseThrow(() -> new IllegalArgumentException("Invalid competition Code"));
        LocalDate currentDate = LocalDate.now();
        LocalDate minDate = currentDate.plusDays(1);
        return minDate.isAfter(competition.getDate());
    }
}

