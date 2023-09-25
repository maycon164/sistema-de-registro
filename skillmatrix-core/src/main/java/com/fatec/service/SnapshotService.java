package com.fatec.service;

import com.fatec.dataprovider.repository.SkillRepository;
import com.fatec.dto.AnswerDTO;
import com.fatec.dto.SnapshotDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SnapshotService {

    private final SkillRepository skillRepository;

    public void saveSnapshot(SnapshotDTO snapshotDTO){
    }

    private boolean validateAnswer(AnswerDTO answerDTO){
        if(!skillRepository.existsById(answerDTO.skillId())){
            throw new RuntimeException("Skill Does not exists");
        }

        return true;
    }

}
