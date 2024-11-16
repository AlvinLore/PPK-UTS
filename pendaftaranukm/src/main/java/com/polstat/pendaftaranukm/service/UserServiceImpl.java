package com.polstat.pendaftaranukm.service;

import com.polstat.pendaftaranukm.dto.UserDto;
import com.polstat.pendaftaranukm.entity.User;
import com.polstat.pendaftaranukm.mapper.UserMapper;
import com.polstat.pendaftaranukm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userRepository.save(UserMapper.mapToUser(userDto));
        return UserMapper.mapToUserDto(user);
    }
    
    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return UserMapper.mapToUserDto(user);
    }
    
    @Override
    public void updateUserProfile(String email, UserDto userDto) {
        User user = userRepository.findByEmail(email);
        user.setNim(userDto.getNim());
        user.setName(userDto.getName());
        user.setUserClass(userDto.getUserClass());
        userRepository.save(user);
    }
        
    @Override
    public void changePassword(String email, String oldPassword, String newPassword) {
        User user = userRepository.findByEmail(email);
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("Incorrect old password");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
    
    @Override
    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email);
        userRepository.delete(user);
    }
}
