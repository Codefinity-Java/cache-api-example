package com.codefinity.cacheexample.mapper;

import com.codefinity.cacheexample.dto.UserRequestDTO;
import com.codefinity.cacheexample.dto.UserResponseDTO;
import com.codefinity.cacheexample.model.User;
import org.modelmapper.ModelMapper;

public class MapperUser {
    private static final ModelMapper mapper = new ModelMapper();

    public static User dtoRequestToModel(UserRequestDTO dto) {
        return mapper.map(dto, User.class);
    }

    public static UserResponseDTO modelToResponseDto(User book) {
        return mapper.map(book, UserResponseDTO.class);
    }
}
