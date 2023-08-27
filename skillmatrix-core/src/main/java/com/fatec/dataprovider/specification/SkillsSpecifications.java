package com.fatec.dataprovider.specification;

import com.fatec.dataprovider.entities.LabelEntity;
import com.fatec.dataprovider.entities.SkillEntity;
import com.fatec.dto.GetSkillsDTO;
import com.fatec.model.LabelEnum;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static java.util.Objects.isNull;

public class SkillsSpecifications {

    public Specification<SkillEntity> buildSpecifications(GetSkillsDTO getSkillsDTO){
        return Specification.where(searchName(getSkillsDTO.search()))
                .and(hasLabels(getSkillsDTO.labels()));
    }

    private Specification<SkillEntity> searchName(String search){
        if(isNull(search) || search.isEmpty()) return null;

        String finalSearch = String.format("%%%s%%",search.toLowerCase());
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), finalSearch);
    }

    private Specification<SkillEntity> hasLabels(List<LabelEnum> labels){
        if(isNull(labels) || labels.isEmpty()) return null;

        return (root, query, criteriaBuilder) -> {
            Join<SkillEntity, LabelEntity> label = root.join("label");
            return criteriaBuilder.in(label.get("label")).value(labels);
        };
    }
}
