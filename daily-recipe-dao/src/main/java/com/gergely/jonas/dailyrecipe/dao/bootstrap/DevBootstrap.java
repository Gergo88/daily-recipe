package com.gergely.jonas.dailyrecipe.dao.bootstrap;

import com.gergely.jonas.dailyrecipe.dao.repository.FindingsRepository;
import com.gergely.jonas.dailyrecipe.dao.repository.IngredientRepository;
import com.gergely.jonas.dailyrecipe.dao.repository.RecipeRepository;
import com.gergely.jonas.dailyrecipe.dao.repository.UnitRepository;
import com.gergely.jonas.dailyrecipe.model.model.Findings;
import com.gergely.jonas.dailyrecipe.model.model.Ingredient;
import com.gergely.jonas.dailyrecipe.model.model.Recipe;
import com.gergely.jonas.dailyrecipe.model.model.Unit;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ext-jonasg on 2017.09.08..
 */
@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private RecipeRepository recipeRepository;
    private UnitRepository unitRepository;
    private IngredientRepository ingredientRepository;
    private FindingsRepository findingsRepository;

    public DevBootstrap(RecipeRepository recipeRepository, UnitRepository unitRepository, IngredientRepository ingredientRepository, FindingsRepository findingsRepository) {
        this.recipeRepository = recipeRepository;
        this.unitRepository = unitRepository;
        this.ingredientRepository = ingredientRepository;
        this.findingsRepository = findingsRepository;
    }

    private void initData() {

        List<Unit> units = new ArrayList<>();
        Unit evokanal = new Unit("evőkanál", "evőkanál");
        Unit g = new Unit("g", "gramm");
        units.add(new Unit("dkg", "dekagramm"));
        units.add(new Unit("kg", "kilogramm"));
        units.add(new Unit("csipet", "csipet"));
        units.add(new Unit("kiskanál", "kiskanál"));
        units.add(evokanal);
        units.add(g);

        unitRepository.saveAll(units);

        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient krumpli = new Ingredient("krumpli");
        ingredients.add(krumpli);
        Ingredient sargarepa = new Ingredient("sárgarépa");
        ingredients.add(sargarepa);
        ingredients.add(new Ingredient("zöldség"));
        ingredients.add(new Ingredient("disznó comb"));
        ingredients.add(new Ingredient("só"));
        ingredients.add(new Ingredient("bors"));

        ingredientRepository.saveAll(ingredients);

        Recipe recipe1 = new Recipe("name1", "comment1", "description1", null);
        Recipe recipe2 = new Recipe("name2", "comment2", "description2", null);
        Recipe recipe3 = new Recipe("name3", "comment3", "description3", null);

        recipeRepository.save(recipe1);
        recipeRepository.save(recipe2);
        recipeRepository.save(recipe3);

        Findings findings1 = new Findings(recipe1, krumpli, g, "1");
        Findings findings2 = new Findings(recipe1, sargarepa, g, "100");

        findingsRepository.save(findings1);
        findingsRepository.save(findings2);




    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
