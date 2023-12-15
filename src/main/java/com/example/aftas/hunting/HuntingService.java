package com.example.aftas.hunting;


import com.example.aftas.competition.CompetionRepository;
import com.example.aftas.competition.Competition;
import com.example.aftas.exception.ResourceNotFoundException;
import com.example.aftas.fish.Fish;
import com.example.aftas.fish.FishRepository;
import com.example.aftas.member.MemberRepository;
import com.example.aftas.member.Membre;
import com.example.aftas.ranking.RankingId;
import com.example.aftas.ranking.RankingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HuntingService implements IHunting {
    private final HuntingRepository huntingRepository;
    private final CompetionRepository competitionRepository;
    private final MemberRepository memberRepository;
    private final FishRepository fishRepository;
    private final RankingRepository rankingRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HuntingService(HuntingRepository huntingRepository, CompetionRepository competionRepository, MemberRepository memberRepository, FishRepository fishRepository, RankingRepository rankingRepository, ModelMapper modelMapper) {
        this.huntingRepository = huntingRepository;
        this.competitionRepository = competionRepository;
        this.memberRepository = memberRepository;
        this.fishRepository = fishRepository;
        this.rankingRepository = rankingRepository;
        this.modelMapper = modelMapper;
    }

    //@Override
    public HuntingDtoRes createHunting(HuntingDtoReq huntingDto) {
        Hunting huntingEntity = modelMapper.map(huntingDto, Hunting.class);
        Competition competition = competitionRepository.findById(huntingDto.getCompetition_id()).orElseThrow(() -> new ResourceNotFoundException("Invalid competition Code"));
        Membre member = memberRepository.findById(huntingDto.getMembre_id()).orElseThrow(() -> new ResourceNotFoundException("Invalid Member code"));
        Fish fish = fishRepository.findById(huntingDto.getFish_id()).orElseThrow(() -> new ResourceNotFoundException("Invalid Fish code"));
        RankingId rankingId = new RankingId(competition.getCode(), member.getNum());
        rankingRepository.findById(rankingId).orElseThrow(() -> new ResourceNotFoundException("This member : " + member.getName() + member.getFamilyName() + " is not registered in this competition" + competition.getCode()));

        Optional<Hunting> huntingFound = huntingRepository.findByCompetitionAndFishAndMembre(competition, fish, member);
        if (huntingFound.isEmpty()) {
            huntingEntity.setFish(fish);
               huntingEntity.setMembre(member);
                huntingEntity.setCompetition(competition);

            Hunting savedHunting =
                    huntingRepository.save(huntingEntity);
            return modelMapper.map(savedHunting, HuntingDtoRes.class);
        }


//            Hunting huntingToSave = modelMapper.map(hunting , Hunting.class);
//            Competition competition = competitionRepository.findById(hunting.getCompetition()).orElseThrow(() -> new ResourceNotFoundException("Invalid competition Code"));
//            Member member = memberRepository.findById(hunting.getMember()).orElseThrow(() -> new ResourceNotFoundException("Invalid Member code"));
//            Fish fish = fishRepository.findById(hunting.getFish()).orElseThrow(() -> new ResourceNotFoundException("Invalid Fish code"));
//            RankingId rankingId = new RankingId(competition.getCode(), member.getNum());
//            rankingRepository.findById(rankingId).orElseThrow(()-> new ResourceNotFoundException("This member : "+ member.getName() + member.getFamilyName()+" is not registered in this competition" + competition.getCode()));
//            Optional<Hunting> huntingFound = huntingRepository.findByCompetitionAndFishAndMember(competition , fish ,member);
//            if(huntingFound.isPresent()){
//                int number = huntingFound.get().getNumberOfFish();
//                huntingFound.get().setNumberOfFish(number + huntingToSave.getNumberOfFish());
//                huntingRepository.save(huntingFound.get());
//                return Optional.of(modelMapper.map(huntingFound , HuntingResp.class));
//            }else{
//                huntingToSave.setFish(fish);
//                huntingToSave.setMember(member);
//                huntingToSave.setCompetition(competition);
//                huntingRepository.save(huntingToSave);
//                return Optional.of(modelMapper.map(huntingToSave , HuntingResp.class));
//            }
        return null;
    }



    //@Override
    public void deleteHunting(int huntingId) {
        huntingRepository.deleteById(huntingId);
    }

    //@Override
    public Optional<HuntingDtoRes> getHuntingById(int huntingId) {
        Optional<Hunting> hunting = huntingRepository.findById(huntingId);
        if (hunting.isPresent()) {
            return hunting.map(value -> modelMapper.map(value, HuntingDtoRes.class));
        } else {
            throw new ResourceNotFoundException("Hunting not found with ID: " + huntingId);
        }
    }


    //@Override
    public List<HuntingDtoRes> getAllHuntings() {
        List<Hunting> allHuntings = huntingRepository.findAll();
        return allHuntings.stream()
                .map(hunting -> modelMapper.map(hunting, HuntingDtoRes.class))
                .collect(Collectors.toList());
    }


    //@Override
    public List<HuntingDtoRes> getHuntingsByFish(String fishId) {

        return null;
    }


    //@Override
    public List<HuntingDtoRes> getHuntingsByCompetition(int competitionId) {
        return null;
    }


    //@Override
    public List<HuntingDtoRes> getHuntingsByMember(int memberId) {
        return null;
    }

}


