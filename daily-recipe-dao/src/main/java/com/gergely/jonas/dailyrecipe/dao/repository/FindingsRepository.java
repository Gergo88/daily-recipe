package com.gergely.jonas.dailyrecipe.dao.repository;

import com.gergely.jonas.dailyrecipe.model.model.Findings;
import com.gergely.jonas.dailyrecipe.model.model.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FindingsRepository extends CrudRepository<Findings, Long> {
    public void deleteByRecipe(Recipe recipe);
    public void deleteByRecipeId(Long recipeId);
    public List<Findings> findAllByRecipe(Recipe recipe);
}
