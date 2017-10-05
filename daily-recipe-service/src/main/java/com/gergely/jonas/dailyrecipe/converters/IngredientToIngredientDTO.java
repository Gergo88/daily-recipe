package com.gergely.jonas.dailyrecipe.converters;

import com.gergely.jonas.dailyrecipe.dto.IngredientDTO;
import com.gergely.jonas.dailyrecipe.model.model.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by ext-jonasg on 2017.10.03..
 */
@Component
public class IngredientToIngredientDTO implements Converter<Ingredient, IngredientDTO> {

    @Nullable
    @Override
    @Synchronized
    public IngredientDTO convert(Ingredient ingredient) {

        if (ingredient == null) {
            return null;
        }

        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setId(ingredient.getId());
        ingredientDTO.setName(ingredient.getName());

        return ingredientDTO;
    }
}
