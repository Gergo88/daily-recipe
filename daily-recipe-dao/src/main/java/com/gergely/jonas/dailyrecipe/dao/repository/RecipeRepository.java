package com.gergely.jonas.dailyrecipe.dao.repository;

import com.gergely.jonas.dailyrecipe.model.model.Findings;
import com.gergely.jonas.dailyrecipe.model.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ext-jonasg on 2017.09.08..
 */
@Repository
public interface RecipeRepository  extends CrudRepository<Recipe, Long>{

}
