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
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Consumer;

@Service
public class CookServiceImpl implements CookService{

    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;
    private UnitRepository unitRepository;
    private FindingsRepository findingsRepository;
    private UnitToUnitDTO unitToUnitDTO;
    private IngredientToIngredientDTO ingredientToIngredientDTO;
    private FullRecipeToRecipe fullRecipeToRecipe;
    private RecipeToFullRecipe recipeToFullRecipe;

    public CookServiceImpl(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, UnitRepository unitRepository, FindingsRepository findingsRepository, UnitToUnitDTO unitToUnitDTO, IngredientToIngredientDTO ingredientToIngredientDTO, FullRecipeToRecipe fullRecipeToRecipe, RecipeToFullRecipe recipeToFullRecipe) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.unitRepository = unitRepository;
        this.findingsRepository = findingsRepository;
        this.unitToUnitDTO = unitToUnitDTO;
        this.ingredientToIngredientDTO = ingredientToIngredientDTO;
        this.fullRecipeToRecipe = fullRecipeToRecipe;
        this.recipeToFullRecipe = recipeToFullRecipe;
    }

    public void addRecipe(FullRecipe fullRecipe) {

        fullRecipe.setFindingsList(clearList(fullRecipe.getFindingsList()));
        System.out.println(fullRecipe.toString());
        Recipe savedRecipe = recipeRepository.save(fullRecipeToRecipe.convert(fullRecipe));
        List<Findings> findingsList = fullRecipeToRecipe.convert(fullRecipe).getFindingsList();
        for (Findings aFindingsList : findingsList) {
            aFindingsList.setRecipe(savedRecipe);
        }
        deleteAllFindingsById(savedRecipe);
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
        return recipeToFullRecipe.convert(recipeRepository.findById(id).orElse(null));
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
