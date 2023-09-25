package com.fatec.service;

import com.fatec.dataprovider.entities.SkillEntity;
import com.fatec.dataprovider.entities.SnapshotAnswerEntity;
import com.fatec.dataprovider.entities.SnapshotEntity;
import com.fatec.dataprovider.entities.UserEntity;
import com.fatec.dataprovider.repository.SkillRepository;
import com.fatec.dataprovider.repository.SnapshotAnswerRepository;
import com.fatec.dataprovider.repository.SnapshotRepository;
import com.fatec.dto.AnswerDTO;
import com.fatec.dto.SnapshotDTO;
import com.fatec.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SnapshotService {

    private final SkillRepository skillRepository;
    private final SnapshotRepository snapshotRepository;
    private final SnapshotAnswerRepository snapshotAnswerRepository;

    public void saveSnapshot(User user, SnapshotDTO snapshotDTO){
        List<SnapshotAnswerEntity> answers = snapshotDTO.answers().stream()
                .map(this::toAnswerEntity)
                .toList();

        snapshotAnswerRepository.saveAllAndFlush(answers);

        SnapshotEntity snapshotAnswerEntity = SnapshotEntity.builder()
                .user(UserEntity.builder().id(user.id()).build())
                .answers(answers)
                .build();

        snapshotRepository.saveAndFlush(snapshotAnswerEntity);
    }

    private SnapshotAnswerEntity toAnswerEntity(AnswerDTO answerDTO){

        return SnapshotAnswerEntity.builder()
                        .skill(SkillEntity.builder()
                                .id(answerDTO.skillId())
                                .build()
                        )
                        .rating(answerDTO.rating())
                        .workedWithTech(answerDTO.workedWithTech())
                        .willingToAnswerQuestions(answerDTO.willingToAnswerQuestions())
                        .willingToPresent(answerDTO.willingToPresent())
                        .build();
    }

    private boolean validateAnswer(AnswerDTO answerDTO){
        if(!skillRepository.existsById(answerDTO.skillId())){
            throw new RuntimeException("Skill Does not exists");
        }
        return true;
    }

}
