package org.MTSG.finalExam.controller;

import org.MTSG.finalExam.dto.BaseResponseDto;
import org.MTSG.finalExam.dto.ExceptionDto;
import org.MTSG.finalExam.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.MTSG.finalExam.service.impl.UserService_Impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UsersController {
    private final Logger _logger = LoggerFactory.getLogger(UsersController.class);
    private final UserService_Impl _userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>>  getAllUsers() {
        try {
            List<UserDto> userList = _userService.getAllUsers();

            _logger.info("Fetching all users.");
            return ResponseEntity.status(200).body(userList);
        } catch (Exception e) {
            _logger.error("An error occurred while fetching all users. Exception: {}", e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<BaseResponseDto> register(@RequestBody UserDto dtoObj) {
        try {
            _userService.register(dtoObj);
            _logger.info("User registered successfully");
            return ResponseEntity.status(201).body(dtoObj);
        } catch (DataIntegrityViolationException e) {
            _logger.error("An DataIntegrity error occurred while adding the user. Exception: {}", e.getMessage());
            return ResponseEntity.status(400).body(ExceptionDto.builder().message("User already exists.").build());
        } catch (Exception e) {
            _logger.error("An error occurred while adding the user. Exception: {}", e.getMessage());
            return ResponseEntity.status(500).body(ExceptionDto.builder().message(e.getMessage()).build());
        }
    }

    @PostMapping("/search")
    public ResponseEntity<List<UserDto>> search(@RequestBody UserDto dtoObj) {
        try {
            List<UserDto> userList = _userService.search(dtoObj.getCity());
            _logger.info("Fetching all users by city.");
            return ResponseEntity.status(200).body(userList);
        } catch (Exception e) {
            _logger.error("An error occurred while fetching all users by city. Exception: {}", e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

}
