package com.fatec.dataprovider.specification;

import com.fatec.dataprovider.entities.LabelEntity;
import com.fatec.dataprovider.entities.UserEntity;
import com.fatec.dto.GetUsersDTO;
import com.fatec.dto.SkillFilterDTO;
import com.fatec.model.enums.LabelEnum;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static java.util.Objects.isNull;

public class UserSpecifications {
    public Specification<UserEntity> buildSpecification(GetUsersDTO getUsersDTO){
        return Specification.where(searchFor(getUsersDTO.search()))
                .and(searchForIsActive(getUsersDTO.active()))
                .and(hasLabels(getUsersDTO.labels()));
    }

    private Specification<UserEntity> searchFor(String search){
        if(isNull(search) || search.isEmpty()) return null;

        String formattedSearch = String.format("%%%s%%", search.toLowerCase());

        return (root, query, criteriaBuilder) -> {
            Predicate searchNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), formattedSearch);
            Predicate searchEmailPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), formattedSearch);
            return criteriaBuilder.or(searchEmailPredicate, searchNamePredicate);
        };
    }

    private Specification<UserEntity> searchForIsActive(Boolean isActive){
        if(isNull(isActive)) return null;

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isActive"), isActive);
    }

    private Specification<UserEntity> hasSkills(List<SkillFilterDTO> skills){
        throw new RuntimeException("#Method not impemented");
    }

    private Specification<UserEntity> hasLabels(List<LabelEnum> labels){
        if(isNull(labels) || labels.isEmpty()) return null;
        return (root, query, criteriaBuilder) -> {
            Join<UserEntity, LabelEntity> label = root.join("label");
            return criteriaBuilder.in(label.get("label")).value(labels);
        };
    }

}
