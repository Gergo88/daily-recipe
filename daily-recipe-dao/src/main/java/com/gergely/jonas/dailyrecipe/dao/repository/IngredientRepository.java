package com.gergely.jonas.dailyrecipe.dao.repository;

import com.gergely.jonas.dailyrecipe.model.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
