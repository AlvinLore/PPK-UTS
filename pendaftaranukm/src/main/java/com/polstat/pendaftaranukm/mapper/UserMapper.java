package com.polstat.pendaftaranukm.mapper;

import com.polstat.pendaftaranukm.dto.UserDto;
import com.polstat.pendaftaranukm.entity.User;

public class UserMapper {
    public static User mapToUser(UserDto userDto){
        return User.builder()
            .id(userDto.getId())
            .nim(userDto.getNim())
            .name(userDto.getName())
            .userClass(userDto.getUserClass())
            .email(userDto.getEmail())
            .password(userDto.getPassword())
            .build();
    }
    public static UserDto mapToUserDto(User user){
        return UserDto.builder()
            .id(user.getId())
            .nim(user.getNim())
            .name(user.getName())
            .userClass(user.getUserClass())
            .email(user.getEmail())
            .password(user.getPassword())
            .build();
    }
}
