package org.MTSG.finalExam.repository;

import org.MTSG.finalExam.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    List<Users> findByAddress_City(String city);
}
