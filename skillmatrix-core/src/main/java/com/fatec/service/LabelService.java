package com.fatec.service;

import com.fatec.dataprovider.entities.LabelEntity;
import com.fatec.dataprovider.repository.LabelRepository;
import com.fatec.model.Label;
import com.fatec.model.enums.LabelEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabelService {

    private final LabelRepository labelRepository;

    public List<Label> getLabels(){
        return labelRepository.findAll().stream().map(this::toLabelModel).toList();
    }

    private Label toLabelModel(LabelEntity label){
        return new Label(label.getId(), label.getLabel());
    }
}
