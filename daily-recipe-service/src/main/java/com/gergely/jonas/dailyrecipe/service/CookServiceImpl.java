package com.gergely.jonas.dailyrecipe.service;

import com.gergely.jonas.dailyrecipe.converters.*;
import com.gergely.jonas.dailyrecipe.dao.repository.FindingsRepository;
import com.gergely.jonas.dailyrecipe.dao.repository.IngredientRepository;
import com.gergely.jonas.dailyrecipe.dao.repository.RecipeRepository;
import com.gergely.jonas.dailyrecipe.dao.repository.UnitRepository;
import com.gergely.jonas.dailyrecipe.dto.FindingsDTO;
import com.gergely.jonas.dailyrecipe.dto.FullRecipe;
import com.gergely.jonas.dailyrecipe.dto.IngredientDTO;
import com.gergely.jonas.dailyrecipe.dto.UnitDTO;
import com.gergely.jonas.dailyrecipe.exception.RecipeNotFoundException;
import com.gergely.jonas.dailyrecipe.model.model.Findings;
import com.gergely.jonas.dailyrecipe.model.model.Ingredient;
import com.gergely.jonas.dailyrecipe.model.model.Recipe;
import com.gergely.jonas.dailyrecipe.model.model.Unit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
public class CookServiceImpl implements CookService {

    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;
    private UnitRepository unitRepository;
    private FindingsRepository findingsRepository;
    private UnitToUnitDTO unitToUnitDTO;
    private IngredientToIngredientDTO ingredientToIngredientDTO;
    private FullRecipeToRecipe fullRecipeToRecipe;
    private RecipeToFullRecipe recipeToFullRecipe;
    private FindingsDTOToFindings findingsDTOToFindings;

    public CookServiceImpl(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, UnitRepository unitRepository, FindingsRepository findingsRepository, UnitToUnitDTO unitToUnitDTO, IngredientToIngredientDTO ingredientToIngredientDTO, FullRecipeToRecipe fullRecipeToRecipe, RecipeToFullRecipe recipeToFullRecipe, FindingsDTOToFindings findingsDTOToFindings) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.unitRepository = unitRepository;
        this.findingsRepository = findingsRepository;
        this.unitToUnitDTO = unitToUnitDTO;
        this.ingredientToIngredientDTO = ingredientToIngredientDTO;
        this.fullRecipeToRecipe = fullRecipeToRecipe;
        this.recipeToFullRecipe = recipeToFullRecipe;
        this.findingsDTOToFindings = findingsDTOToFindings;
    }

    @Transactional
    @Override
    public void addRecipe(FullRecipe fullRecipe) {
        log.info("Recipe to save: " + fullRecipe.toString());
        fullRecipe.getFindingsList().removeIf(finding -> finding == null || finding.getAmount() == null || finding.getIngredientDTO() == null || finding.getUnitDTO() == null || (new Long(0L).equals(finding.getUnitDTO().getId())) || (new Long(0L).equals(finding.getIngredientDTO().getId())) || ("".equals(finding.getAmount())));
        List<FindingsDTO> findingsDTOListToSave = fullRecipe.getFindingsList();
        fullRecipe.setFindingsList(new ArrayList<>());
        Recipe savedRecipe;
        if (fullRecipe.getId() == null) {
            savedRecipe = recipeRepository.save(fullRecipeToRecipe.convert(fullRecipe));
            Long recipeId = savedRecipe.getId();
            findingsDTOListToSave.forEach(findingsDTO -> findingsDTO.setRecipeId(recipeId));
            saveFindingsToRecipe(findingsDTOListToSave, savedRecipe);
        } else {
            List<Findings> oldFindingsList = recipeRepository.findById(fullRecipe.getId()).orElse(null).getFindingsList();
            deleteDeletedFindings(findingsDTOListToSave, oldFindingsList);
            savedRecipe = fullRecipeToRecipe.convert(fullRecipe);
            savedRecipe.setImage(recipeRepository.findById(fullRecipe.getId()).orElse(new Recipe()).getImage());
            savedRecipe.setFindingsList(new ArrayList<>());
            savedRecipe = recipeRepository.save(savedRecipe);
            Long recipeId = savedRecipe.getId();
            findingsDTOListToSave.forEach(findingsDTO -> findingsDTO.setRecipeId(recipeId));
            saveFindingsToRecipe(findingsDTOListToSave, savedRecipe);
        }
    }

    private void deleteDeletedFindings(List<FindingsDTO> newFindingsList, List<Findings> oldFindingsList) {
        List<Long> oldFindingIds = new ArrayList<>();
        List<Long> newFindingIds = new ArrayList<>();
        oldFindingsList.forEach(finding -> oldFindingIds.add(finding.getId()));
        newFindingsList.forEach(findingsDTO -> newFindingIds.add(findingsDTO.getId()));
        oldFindingIds.forEach(aLong -> {
            if (!newFindingIds.contains(aLong)) {
                findingsRepository.deleteById(aLong);
            }
        });
    }

    private void saveFindingsToRecipe(List<FindingsDTO> findingsDTOList, Recipe recipe) {
        List<Findings> findingsList = new ArrayList<>();
        findingsDTOList.forEach(findingsDTO -> {
            findingsDTO.setRecipeId(recipe.getId());
            findingsList.add(findingsDTOToFindings.convert(findingsDTO));
        });
        findingsRepository.saveAll(findingsList);
    }

    public Set<FullRecipe> findAll() {
        Set<FullRecipe> fullRecipes = new HashSet<>();
        recipeRepository.findAll().forEach(recipe -> fullRecipes.add(recipeToFullRecipe.convert(recipe)));
        return fullRecipes;
    }

    public FullRecipe findById(Long id) {
        FullRecipe fullRecipe = recipeToFullRecipe.convert(recipeRepository.findById(id).orElseThrow( () -> new RecipeNotFoundException("Recipe does not exist with id: " + id)));
        if (fullRecipe != null && fullRecipe.getFindingsList() != null && fullRecipe.getFindingsList().size() == 0) {
            fullRecipe.getFindingsList().add(getNewFindigsDTO());
        }
        return fullRecipe;
    }

    @Override
    public Byte[] getRecipeImageById(Long id) {
        return recipeRepository.findById(id).orElse(new Recipe()).getImage();
    }

    @Override
    public void deletePictureOfRecipe(Long recipId) {
        Recipe recipe = recipeRepository.findById(recipId).orElseThrow( () -> new RecipeNotFoundException("Recipe does not exist with id: " + recipId));
        recipe.setImage(null);
        recipeRepository.save(recipe);
    }

    @Override
    public void uploadPictureForRecipe(FullRecipe fullRecipe) {
        Recipe recipeWithNewImage = fullRecipeToRecipe.convert(fullRecipe);
        Recipe recipeToSave = recipeRepository.findById(fullRecipe.getId()).orElseThrow( () -> new RecipeNotFoundException("Recipe does not exist with id: " + fullRecipe.getId()));
        recipeToSave.setImage(recipeWithNewImage.getImage());
        recipeRepository.save(recipeToSave);
    }

    public List<IngredientDTO> getAllIngredient() {
        List<IngredientDTO> ingredientDTOList = new ArrayList<>();
        for (Ingredient ingredient : ingredientRepository.findAll()) {
            ingredientDTOList.add(ingredientToIngredientDTO.convert(ingredient));
        }
        return ingredientDTOList;
    }


    public List<UnitDTO> getAllUnit() {
        List<UnitDTO> unitDTOList = new ArrayList<>();
        for (Unit unit : unitRepository.findAll()) {
            unitDTOList.add(unitToUnitDTO.convert(unit));
        }
        return unitDTOList;
    }

    public FindingsDTO getNewFindigsDTO() {
        FindingsDTO findingsDTO = new FindingsDTO();
        IngredientDTO ingredientDTO = new IngredientDTO();
        findingsDTO.setIngredientDTO(ingredientDTO);
        findingsDTO.getIngredientDTO().setId(0L);
        UnitDTO unitDTO = new UnitDTO();
        unitDTO.setId(0L);
        findingsDTO.setUnitDTO(unitDTO);
        return findingsDTO;
    }

    @Override
    public void deleteRecipeById(Long idToDelete) {
        recipeRepository.deleteById(idToDelete);
    }
}
