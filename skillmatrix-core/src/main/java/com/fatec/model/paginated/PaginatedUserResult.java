package com.fatec.model.paginated;

import com.fatec.model.User;
import lombok.Builder;

import java.util.List;

@Builder
public record PaginatedUserResult(
    List<User> users,
    Integer page,
    Integer totalPages
){}

