package com.gergely.jonas.dailyrecipe.converters;

import com.gergely.jonas.dailyrecipe.dto.IngredientDTO;
import com.gergely.jonas.dailyrecipe.model.model.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by ext-jonasg on 2017.10.04..
 */
@Component
public class IngredientDTOToIngredient implements Converter<IngredientDTO, Ingredient> {

    @Nullable
    @Override
    @Synchronized
    public Ingredient convert(IngredientDTO ingredientDTO) {

        if (ingredientDTO == null) {
            return null;
        }

        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientDTO.getId());
        ingredient.setName(ingredientDTO.getName());

        return ingredient;
    }
}
