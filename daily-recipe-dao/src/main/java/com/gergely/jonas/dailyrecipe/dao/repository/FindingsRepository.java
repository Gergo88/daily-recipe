package com.gergely.jonas.dailyrecipe.dao.repository;

import com.gergely.jonas.dailyrecipe.model.model.Findings;
import com.gergely.jonas.dailyrecipe.model.model.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FindingsRepository extends CrudRepository<Findings, Long> {
    List<Findings> findAllByRecipe(Recipe recipe);
}
