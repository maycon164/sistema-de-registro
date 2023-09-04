package com.fatec.service;

import com.fatec.dataprovider.dao.CommonInfoDao;
import com.fatec.dataprovider.entities.LabelEntity;
import com.fatec.dataprovider.repository.LabelRepository;
import com.fatec.dataprovider.repository.SkillRepository;
import com.fatec.dataprovider.repository.UserRepository;
import com.fatec.dto.GetCommonInformationDTO;
import com.fatec.dto.InfoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CommonInfoService {

    private final UserRepository userRepository;
    private final LabelRepository labelRepository;
    private final SkillRepository skillRepository;

    private final CommonInfoDao commonInfoDao;

    public InfoResponseDTO buildCommonInfo(GetCommonInformationDTO getCommonInformationDTO){
        InfoResponseDTO infoResponseDTO = new InfoResponseDTO();

        getCommonInformationDTO.params().forEach(param -> {
            switch (param) {
                case USERS -> setInformationUsers(infoResponseDTO);
                case LABELS -> setInformationLabels(infoResponseDTO);
                case SKILLS -> setInformationSkills(infoResponseDTO);
            }
        });

        return infoResponseDTO;
    }

    private void setInformationLabels(InfoResponseDTO infoResponseDTO){
        infoResponseDTO.setQuantityLabels(labelRepository.count());
        infoResponseDTO.setLabels(labelRepository.findAll().stream().map(LabelEntity::getLabel).toList());
    }

    private void setInformationUsers(InfoResponseDTO infoResponseDTO){
        infoResponseDTO.setQuantityUsers(userRepository.count());
        infoResponseDTO.setQuantityOfUsersByLabel(commonInfoDao.getUserInfoByLabel());
    }

    private void setInformationSkills(InfoResponseDTO infoResponseDTO){
        infoResponseDTO.setQuantitySkills(skillRepository.count());
    }
}
