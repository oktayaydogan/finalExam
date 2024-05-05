package org.MTSG.finalExam.service;

import org.MTSG.finalExam.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    void register(UserDto userDto);
    List<UserDto> search(String city);
}
