package com.example.application.papka.service;

import com.example.application.papka.model.Position;
import com.example.application.papka.repository.PositionRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PositionService {

    private PositionRepo positionRepo;

    public PositionService(PositionRepo positionRepo) {
        this.positionRepo = positionRepo;
    }

    public ArrayList<Position> getById(long id) {
        Optional<Position> positionModels= positionRepo.findById(id);
        ArrayList<Position> position = new ArrayList<>();
        positionModels.ifPresent(position::add);
        return position;
    }
}
