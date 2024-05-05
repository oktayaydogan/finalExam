package org.MTSG.finalExam.service.impl;

import org.MTSG.finalExam.dto.UserDto;
import org.MTSG.finalExam.model.Users;
import org.MTSG.finalExam.model.Users_Addresses;
import org.MTSG.finalExam.repository.UserRepository;
import org.MTSG.finalExam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService_Impl implements UserService {
    private final Logger _logger = LoggerFactory.getLogger(UserService_Impl.class);
    private final UserRepository _userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        try {
            List<Users> userList = _userRepository.findAll();

            if(!userList.isEmpty()){
                _logger.info("Fetching all users.");

                return userList.stream()
                        .map(user -> UserDto.builder()
                                .name(user.getName())
                                .surname(user.getSurname())
                                .email(user.getEmail())
                                .city(user.getAddress().getCity())
                                .district(user.getAddress().getDistrict())
                                .hometown(user.getAddress().getHometown())
                                .build())
                        .collect(Collectors.toList());
            }

            _logger.info("No users found.");
            return Collections.emptyList();
        } catch (Exception e) {
            _logger.error("An error occurred while fetching the users. Exception: {}", e.getMessage());
            throw new RuntimeException("An error occurred while fetching the users.");
        }
    }

    @Override
    public void register(UserDto obj) {
        try {

            Users_Addresses address = Users_Addresses.builder()
                    .city(obj.getCity())
                    .district(obj.getDistrict())
                    .hometown(obj.getHometown())
                    .build();

            Users user = Users.builder()
                    .name(obj.getName())
                    .surname(obj.getSurname())
                    .email(obj.getEmail())
                    .address(address)
                    .build();

            Users savedEntity = _userRepository.save(user);
            _logger.info("User saved: {}", savedEntity);
        } catch (DataIntegrityViolationException e) {
            _logger.error("An DataIntegrity error occurred while saving the User. Exception: {}", e.getMessage());
            throw new DataIntegrityViolationException(e.getMessage());
        } catch (Exception e) {
            _logger.error("An error occurred while saving the User. Exception: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<UserDto> search(String city) {
        try {
            List<Users> userList = _userRepository.findByAddress_City(city);

            if(!userList.isEmpty()){
                _logger.info("Fetching all users by city.");

                return userList.stream()
                        .map(user -> UserDto.builder()
                                .name(user.getName())
                                .surname(user.getSurname())
                                .email(user.getEmail())
                                .city(user.getAddress().getCity())
                                .district(user.getAddress().getDistrict())
                                .hometown(user.getAddress().getHometown())
                                .build())
                        .collect(Collectors.toList());
            }

            _logger.info("No users found.");
            return Collections.emptyList();
        } catch (Exception e) {
            _logger.error("An error occurred while fetching the users by city. Exception: {}", e.getMessage());
            throw new RuntimeException("An error occurred while fetching the users by city.");
        }
    }
}
