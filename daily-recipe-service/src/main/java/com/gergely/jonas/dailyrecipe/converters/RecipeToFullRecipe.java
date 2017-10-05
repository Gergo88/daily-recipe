package com.gergely.jonas.dailyrecipe.converters;

import com.gergely.jonas.dailyrecipe.dto.FindingsDTO;
import com.gergely.jonas.dailyrecipe.dto.FullRecipe;
import com.gergely.jonas.dailyrecipe.model.model.Findings;
import com.gergely.jonas.dailyrecipe.model.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ext-jonasg on 2017.10.04..
 */
@Component
public class RecipeToFullRecipe implements Converter<Recipe, FullRecipe> {

    private FindingsToFindingsDTO findingsToFindingsDTO;

    public RecipeToFullRecipe(FindingsToFindingsDTO findingsToFindingsDTO) {
        this.findingsToFindingsDTO = findingsToFindingsDTO;
    }

    @Nullable
    @Override
    @Synchronized
    public FullRecipe convert(Recipe recipe) {

        if (recipe == null) {
            return null;
        }

        FullRecipe fullRecipe = new FullRecipe();
        fullRecipe.setId(recipe.getId());
        fullRecipe.setComment(recipe.getComment());
        fullRecipe.setDescription(recipe.getDescription());
        fullRecipe.setName(recipe.getName());

        List<FindingsDTO> findingsDTOList = new ArrayList<>();
        for (Findings findings : recipe.getFindingsList()) {
            findingsDTOList.add(findingsToFindingsDTO.convert(findings));
        }

        fullRecipe.setFindingsList(findingsDTOList);

        return fullRecipe;
    }
}
