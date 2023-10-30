package com.fatec.dataprovider.dao;

import com.fatec.dto.LabelSkillInfo;
import com.fatec.dto.LabelUserInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CommonInfoDao {

    private final EntityManager entityManager;

    public List<LabelUserInfo> getUserInfoByLabel(){
        String sql = """
                SELECT l.label, count(u.name) as users
                FROM label l
                LEFT JOIN users u on l.id = u.label_id
                GROUP BY l.label
            """;
        Query nativeQuery = entityManager.createNativeQuery(sql);
        List<Object[]> resultList = nativeQuery.getResultList();

        List<LabelUserInfo> infos = new ArrayList<>();

        for (Object[] result : resultList) {
            String label = (String) result[0];
            Long skillsCount = (Long) result[1];

            infos.add(new LabelUserInfo(label, skillsCount));
        }

        return infos;
    }

    public List<LabelSkillInfo> getSkillInfoByLabel(){
        String sql = """
                        SELECT l.label, count(s.name) as skills
                        FROM label l
                        LEFT JOIN skills s on l.id = s.label_id
                        GROUP BY l.label
                """;
        Query nativeQuery = entityManager.createNativeQuery(sql);
        List<Object[]> resultList = nativeQuery.getResultList();

        List<LabelSkillInfo> infos = new ArrayList<>();

        for (Object[] result : resultList) {
            String label = (String) result[0];
            Long skillsCount = (Long) result[1];

            infos.add(new LabelSkillInfo(label, skillsCount));
        }

        return infos;
    }

}
