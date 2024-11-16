package com.polstat.pendaftaranukm.service;

import com.polstat.pendaftaranukm.dto.UserDto;

public interface UserService {
    public UserDto createUser(UserDto user);
    public UserDto getUserByEmail(String email);
    public void updateUserProfile(String email, UserDto userDto);
    public void deleteUser(String email);
    public void changePassword(String email, String oldPassword, String newPassword);
}
