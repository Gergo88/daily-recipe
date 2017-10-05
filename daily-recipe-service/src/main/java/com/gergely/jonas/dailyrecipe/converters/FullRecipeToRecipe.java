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
public class FullRecipeToRecipe implements Converter<FullRecipe, Recipe> {

    private FindingsDTOToFindings findingsDTOToFindings;

    public FullRecipeToRecipe(FindingsDTOToFindings findingsDTOToFindings) {
        this.findingsDTOToFindings = findingsDTOToFindings;
    }

    @Nullable
    @Override
    @Synchronized
    public Recipe convert(FullRecipe fullRecipe) {

        if (fullRecipe == null) {
            return null;
        }

        Recipe recipe = new Recipe();
        recipe.setId(fullRecipe.getId());
        recipe.setComment(fullRecipe.getComment());
        recipe.setDescription(fullRecipe.getDescription());
        recipe.setName(fullRecipe.getName());

        List<Findings> findingsList = new ArrayList<>();
        for (FindingsDTO findingsDTO : fullRecipe.getFindingsList()) {
            findingsList.add(findingsDTOToFindings.convert(findingsDTO));
        }
        recipe.setFindingsList(findingsList);

        return recipe;
    }
}
