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
import com.gergely.jonas.dailyrecipe.model.model.Findings;
import com.gergely.jonas.dailyrecipe.model.model.Ingredient;
import com.gergely.jonas.dailyrecipe.model.model.Recipe;
import com.gergely.jonas.dailyrecipe.model.model.Unit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
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
    public void addRecipe(FullRecipe fullRecipe) {
        System.out.println(fullRecipe.toString());
        List<FindingsDTO> findingsDTOList = clearList(fullRecipe.getFindingsList());
        fullRecipe.setFindingsList(new ArrayList<>());
        Recipe savedRecipe = recipeRepository.save(fullRecipeToRecipe.convert(fullRecipe));
        System.out.println(savedRecipe.toString());
        deleteAllFindingsById(savedRecipe);
        saveFindingsToRecipe(findingsDTOList, savedRecipe);

    }

    private void saveFindingsToRecipe(List<FindingsDTO> findingsDTOList, Recipe recipe) {
        List<Findings> findingsList = new ArrayList<>();
        for (FindingsDTO findingsDTO : findingsDTOList) {
            findingsDTO.setRecipeId(recipe.getId());
            findingsList.add(findingsDTOToFindings.convert(findingsDTO));
        }
        findingsRepository.saveAll(findingsList);
    }

    private List<FindingsDTO> clearList(List<FindingsDTO> listToClear) {

        List<FindingsDTO> clearedList = new ArrayList<>();

        for (FindingsDTO findingsDTO : listToClear) {
            if (findingsDTO.getAmount() != null && findingsDTO.getIngredientDTO() != null && findingsDTO.getUnitDTO() != null && !(new Long(0L).equals(findingsDTO.getUnitDTO().getId())) && !(new Long(0L).equals(findingsDTO.getIngredientDTO().getId())) && !("".equals(findingsDTO.getAmount()))) {
                clearedList.add(findingsDTO);
            }
        }

        return clearedList;
    }

    private void deleteAllFindingsById(Recipe recipe) {
        List<Findings> findingsList = findingsRepository.findAllByRecipe(recipe);
        for (Findings findings : findingsList) {
            findingsRepository.deleteById(findings.getId());
        }
    }

    public Set<FullRecipe> findAll() {
        Set<FullRecipe> fullRecipes = new HashSet<>();
        recipeRepository.findAll().forEach(recipe -> fullRecipes.add(recipeToFullRecipe.convert(recipe)));
        return fullRecipes;
    }

    public FullRecipe findById(Long id) {
        FullRecipe fullRecipe = recipeToFullRecipe.convert(recipeRepository.findById(id).orElse(null));
        if (fullRecipe.getFindingsList().size() == 0) {
            fullRecipe.getFindingsList().add(getNewFindigsDTO());
        }
        return fullRecipe;
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
