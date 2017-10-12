package com.gergely.jonas.dailyrecipe.service;

import com.gergely.jonas.dailyrecipe.dto.FindingsDTO;
import com.gergely.jonas.dailyrecipe.dto.FullRecipe;
import com.gergely.jonas.dailyrecipe.dto.IngredientDTO;
import com.gergely.jonas.dailyrecipe.dto.UnitDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by ext-jonasg on 2017.10.03..
 */
public interface CookService {

    void addRecipe(FullRecipe fullRecipe);

    Iterable<FullRecipe> findAll();

    FullRecipe findById(Long id);

    List<IngredientDTO> getAllIngredient();

    List<UnitDTO> getAllUnit();

    FindingsDTO getNewFindigsDTO();

    void deleteRecipeById(Long idToDelete);

    Byte[] getRecipeImageById(Long id);
}
