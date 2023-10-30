package com.fatec.dataprovider.specification;

import com.fatec.dataprovider.entities.*;
import com.fatec.dataprovider.repository.ViewUserSnapshotRepository;
import com.fatec.dataprovider.view.ViewUserAndSnapshot;
import com.fatec.dto.GetUsersDTO;
import com.fatec.dto.SkillFilterDTO;
import com.fatec.model.Skill;
import com.fatec.model.enums.JobPositionEnum;
import com.fatec.model.enums.LabelEnum;
import com.fatec.model.enums.LevelEnum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Component
@Slf4j
public class UserSpecifications {

    private final ViewUserSnapshotRepository viewUserSnapshotRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public Specification<UserEntity> buildSpecification(GetUsersDTO getUsersDTO){
        return Specification.where(searchFor(getUsersDTO.search()))
                .and(searchForIsActive(getUsersDTO.active()))
                .and(hasSkills(getUsersDTO.getSkillFilter()))
                .and(hasPosition(getUsersDTO.jobPosition()))
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


    private Specification<UserEntity> hasPosition(String position) {

        if(isNull(position) || position.isEmpty()) return null;
        if(position.equals("ALL")) return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("jobPosition"), position);
    }

    private Specification<UserEntity> searchForIsActive(Boolean isActive){
        if(isNull(isActive)) return null;

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isActive"), isActive);
    }

    private Specification<UserEntity> hasSkills(List<SkillFilterDTO> skills){
        if(isNull(skills) || skills.isEmpty()) return null;

        return (root, query, cb) -> {
            List<Long> userIds = withSkillFilters(skills);
            return  root.get("id").in(userIds);
        };
    }

    private Specification<UserEntity> hasLabels(List<LabelEnum> labels){
        if(isNull(labels) || labels.isEmpty()) return null;
        return (root, query, criteriaBuilder) -> {
            Join<UserEntity, LabelEntity> label = root.join("label");
            return criteriaBuilder.in(label.get("label")).value(labels);
        };
    }
    private List<Long> withSkillFilters(List<SkillFilterDTO> filters) {
        if(filters.isEmpty()) return null;

        log.info("Searching users by skills....");

        String firstParameter = "(skill_id = " + filters.get(0).id() + " and level = '"+ filters.get(0).level().toString() +"' )";
        filters.remove(0);
        String secondParameter = filters.stream().map(filter -> "or (skill_id = " + filter.id() + " and level = '"+ filter.level().toString() +"') ").collect(Collectors.joining());
        Integer thirdParameter = filters.size() + 1;
        String sql = """
                select user_id from view_user_last_snapshot
                where %s
                	%s
                group by snapshot_id, user_id
                having count(snapshot_id) = %d
                """.formatted(firstParameter, secondParameter, thirdParameter);

        List<Object> resultList = entityManager.createNativeQuery(sql).getResultList();

        // Map the results to Long
        return resultList.stream()
                .map(result -> ((Number) result).longValue()) // Assuming user_id is of type Long
                .toList();
    }
}
