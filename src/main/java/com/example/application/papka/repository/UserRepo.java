package com.example.application.papka.repository;

import com.example.application.papka.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends PagingAndSortingRepository<User, Long> {
    Optional<User> getByUsername(String username);
    List<User> getAllBy();
}
