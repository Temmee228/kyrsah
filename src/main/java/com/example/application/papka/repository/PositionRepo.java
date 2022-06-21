package com.example.application.papka.repository;

import com.example.application.papka.model.Position;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PositionRepo extends PagingAndSortingRepository<Position, Long> {
}
