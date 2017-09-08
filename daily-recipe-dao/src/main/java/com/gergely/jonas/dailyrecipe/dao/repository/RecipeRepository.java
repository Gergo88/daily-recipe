package com.gergely.jonas.dailyrecipe.dao.repository;

import com.gergely.jonas.dailyrecipe.model.model.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ext-jonasg on 2017.09.08..
 */
public interface RecipeRepository  extends CrudRepository<Recipe, Long>{
}
