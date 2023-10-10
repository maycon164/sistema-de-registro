package com.fatec.service;

import com.fatec.dataprovider.entities.SkillEntity;
import com.fatec.dataprovider.entities.SnapshotAnswerEntity;
import com.fatec.dataprovider.entities.SnapshotEntity;
import com.fatec.dataprovider.entities.UserEntity;
import com.fatec.dataprovider.repository.SnapshotAnswerRepository;
import com.fatec.dataprovider.repository.SnapshotRepository;
import com.fatec.dto.AnswerDTO;
import com.fatec.dto.SnapshotDTO;
import com.fatec.exceptions.SnapshotException;
import com.fatec.model.Snapshot;
import com.fatec.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SnapshotService {

    private final SnapshotRepository snapshotRepository;
    private final SnapshotAnswerRepository snapshotAnswerRepository;

    public void saveSnapshot(User user, SnapshotDTO snapshotDTO){
        System.out.println(user);

        try {
            List<SnapshotAnswerEntity> answers = snapshotDTO.answers().stream()
                    .map(this::toAnswerEntity)
                    .toList();

            snapshotAnswerRepository.saveAllAndFlush(answers);

            SnapshotEntity snapshotAnswerEntity = SnapshotEntity.builder()
                    .user(UserEntity.builder().id(user.id()).build())
                    .answers(answers)
                    .build();

            snapshotRepository.saveAndFlush(snapshotAnswerEntity);
        } catch (Exception e) {
            log.error("Could not save snapshot: ", e.getMessage());
            throw new SnapshotException();
        }
    }

    public Snapshot getSnapshot(User user){
        return toSnapshotModel(snapshotRepository.findLastSnapshot(user.id()));
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

    private Snapshot toSnapshotModel(SnapshotEntity snapshotEntity){
        List<AnswerDTO> answers = snapshotEntity.getAnswers().stream().map(snap ->
                new AnswerDTO(
                        snap.getSkill().getLabel().getLabel().toString(),
                        snap.getRating(),
                        snap.getSkill().getId(),
                        snap.getSkill().getName(),
                        snap.getWillingToAnswerQuestions(),
                        snap.getWillingToPresent(),
                        snap.getWorkedWithTech(),
                        null
                        ))
                .toList();

        return new Snapshot(
                snapshotEntity.getId(),
                snapshotEntity.getCreatedAt(),
                answers
        );
    }
}
