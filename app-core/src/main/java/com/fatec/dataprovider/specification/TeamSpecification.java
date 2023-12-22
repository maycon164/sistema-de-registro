package com.fatec.dataprovider.specification;

import com.fatec.dataprovider.entities.TeamEntity;
import com.fatec.dto.GetTeamsDTO;
import com.fatec.model.Team;
import org.springframework.data.jpa.domain.Specification;

import static java.util.Objects.isNull;

public class TeamSpecification {

    public static Specification<TeamEntity> buildSpecification(GetTeamsDTO getTeamsDTO){
        return Specification.where(searchFor(getTeamsDTO.search()));
    }

    private static Specification<TeamEntity> searchFor(String search) {
        if(isNull(search) || search.isEmpty()) return null;
        String formattedSearch = String.format("%%%s%%", search.toLowerCase());
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), formattedSearch);
        };
    }
}
