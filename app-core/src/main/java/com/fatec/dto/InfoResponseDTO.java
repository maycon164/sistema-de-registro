package com.fatec.dto;

import com.fatec.model.enums.LabelEnum;
import lombok.Data;

import java.util.List;

@Data
public class InfoResponseDTO {
    private Long quantityLabels;
    private List<LabelEnum> labels;
    private Long quantitySkills;
    private List<LabelSkillInfo> quantityOfSkillsByLabel;
    private Long quantityUsers;
    private List<LabelUserInfo> quantityOfUsersByLabel;
}
