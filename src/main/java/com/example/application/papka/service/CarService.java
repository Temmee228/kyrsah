package com.example.application.papka.service;

import com.example.application.papka.model.Car;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface CarService {

    Car insert(Car book);

    List<Car> selectAll();

    Car select(int id);

    Car update(Car book, int id);

    void delete(int id);

    void deleteAll();

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}