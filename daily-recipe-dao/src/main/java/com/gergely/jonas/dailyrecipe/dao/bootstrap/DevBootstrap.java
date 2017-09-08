package com.gergely.jonas.dailyrecipe.dao.bootstrap;

import com.gergely.jonas.dailyrecipe.dao.repository.RecipeRepository;
import com.gergely.jonas.dailyrecipe.model.model.Recipe;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
/**
 * Created by ext-jonasg on 2017.09.08..
 */
@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private RecipeRepository recipeRepository;

    public DevBootstrap(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    private void initData(){
        Recipe recipe1 = new Recipe("name1", "comment1", "description1");
        Recipe recipe2 = new Recipe("name2", "comment2", "description2");
        Recipe recipe3 = new Recipe("name3", "comment3", "description3");

        recipeRepository.save(recipe1);
        recipeRepository.save(recipe2);
        recipeRepository.save(recipe3);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
