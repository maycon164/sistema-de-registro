package com.fatec.dataprovider.dao;

import com.fatec.dto.LabelSkillInfo;
import com.fatec.dto.LabelUserInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonInfoDao {

    public List<LabelUserInfo> getUserInfoByLabel(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = factory.createEntityManager();

        String sql = """
                SELECT l.label, count(u.name) as users\s
                FROM label l
                LEFT JOIN users u on l.id = u.label_id
                GROUP BY l.label
        """;

        List<Object[]> result = entityManager.createNativeQuery(sql).getResultList();

        List<LabelUserInfo> labelUserInfos = new ArrayList<>();

        for(Object[] line: result){

        }


        return null;
    }

    public List<LabelSkillInfo> getSkillInfoByLabel(){
        String sql = """
                        SELECT l.label, count(s.name) as skills
                        FROM label l
                        LEFT JOIN skills s on l.id = s.label_id
                        GROUP BY l.label
                """;

        return null;
    }

}
