package com.springframework.recipeapp.repositories;

import com.springframework.recipeapp.domain.UnitofMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitofMeasure, Long> {

    Optional<UnitofMeasure> findByDescription(String description);
}
