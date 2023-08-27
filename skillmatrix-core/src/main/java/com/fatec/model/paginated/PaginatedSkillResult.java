package com.fatec.model.paginated;

import com.fatec.model.Skill;
import lombok.Builder;

import java.util.List;

@Builder
public record PaginatedSkillResult (List<Skill> skills, Integer page, Integer totalPages ){}


