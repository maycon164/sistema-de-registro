package com.fatec.model;

import lombok.Builder;

import java.util.List;

@Builder
public record PaginatedSkillResult (List<Skill> skills, Integer page, Integer totalPages ){}


