package com.gergely.jonas.dailyrecipe.converters;

import com.gergely.jonas.dailyrecipe.dto.FindingsDTO;
import com.gergely.jonas.dailyrecipe.dto.FullRecipe;
import com.gergely.jonas.dailyrecipe.model.model.Findings;
import com.gergely.jonas.dailyrecipe.model.model.Recipe;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ext-jonasg on 2017.10.04..
 */
@Component
@Slf4j
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
        if (fullRecipe.getImage() != null) {
            try {

                Byte[] byteObjects = new Byte[fullRecipe.getImage().getBytes().length];

                int i = 0;

                for (byte b : fullRecipe.getImage().getBytes()) {
                    byteObjects[i++] = b;
                }

                recipe.setImage(byteObjects);
            }  catch (IOException e) {
                log.error("Error occurred while parsing the image!", e);
            }
        }

        List<Findings> findingsList = new ArrayList<>();
        for (FindingsDTO findingsDTO : fullRecipe.getFindingsList()) {
            findingsList.add(findingsDTOToFindings.convert(findingsDTO));
        }
        recipe.setFindingsList(findingsList);

        return recipe;
    }
}
