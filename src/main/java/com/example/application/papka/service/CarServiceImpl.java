package com.example.application.papka.service;

import com.example.application.papka.exception.CarNotFoundException;
import com.example.application.papka.repository.CarRepository;
import com.example.application.papka.model.Car;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class CarServiceImpl implements CarService {

    private final CarRepository repository;
    public CarServiceImpl(CarRepository repository) {
        this.repository = repository;
    }

    @Override
    public Car insert(Car Car) {
        Car.setId(Car.getId());
        return repository.save(Car);
    }

    @Override
    public List<Car> selectAll() {
        return repository.findAllByOrderByIdAsc();
    }

    @Override
    public Car select(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
    }

    @Override
    public Car update(Car newCar, int id) {
        return repository.findById(id)
                .map(car -> {
                    car.setModel(newCar.getModel());
                    car.setColor(newCar.getColor());
                    car.setMaxSpeed(newCar.getMaxSpeed());
                    return repository.save(car);
                })
                .orElseThrow(() -> new CarNotFoundException(id));
    }

    @Override
    public void delete(int id) {
        if (repository.findById(id).isPresent()){
            repository.deleteById(id);
        } else {
            throw new CarNotFoundException(id);
        }
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public List<Car> getAll() {
        return new ArrayList<>(repository.findAll(Sort.by(List.of(Sort.Order.desc("id")))));
    }
}