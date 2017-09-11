package com.gergely.jonas.dailyrecipe.service;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CookService {

    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;
    private UnitRepository unitRepository;
    private FindingsRepository findingsRepository;

    public CookService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, UnitRepository unitRepository, FindingsRepository findingsRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.unitRepository = unitRepository;
        this.findingsRepository = findingsRepository;
    }

    public void addRecipe(FullRecipe fullRecipe) {
        Recipe savedRecipe = recipeRepository.save(getRecipeFromFullRecipe(fullRecipe));
        List<Findings> findingsList = getFindigsListFromFullRecipe(fullRecipe);
        for(int i = 0; i<findingsList.size(); i++) {
            findingsList.get(i).setRecipe(savedRecipe);
        }
        System.out.println(findingsList.toString());
        findingsRepository.saveAll(findingsList);
    }

    private List<Findings> getFindigsListFromFullRecipe(FullRecipe fullRecipe) {
        List<Findings> findingsList = new ArrayList<>();
        for(FindingsDTO findingsDTO : fullRecipe.getFindingsList()) {
            findingsList.add(getFindingsFromFindingsDTO(findingsDTO));
        }
        return findingsList;
    }

    private List<FindingsDTO> getFindigsDTOListFromRecipe(Recipe recipe) {
        List<FindingsDTO> findingsDTOList = new ArrayList<>();
        for(Findings findings : recipe.getFindingsList()) {
            findingsDTOList.add(getFindingsDTOFromFindings(findings));
        }
        return findingsDTOList;
    }

    private FindingsDTO getFindingsDTOFromFindings(Findings findings) {
        FindingsDTO findingsDTO = new FindingsDTO();
        findingsDTO.setId(findings.getId());
        findingsDTO.setUnitDTO(getUnitDTOFromUnit(findings.getUnit()));
        findingsDTO.setIngredientDTO(getIngredientDTOFromIngredient(findings.getIngredient()));
        findingsDTO.setAmount(findings.getAmount());
        return findingsDTO;
    }


    private Findings getFindingsFromFindingsDTO(FindingsDTO findingsDTO) {
        Findings findings = new Findings();
        findings.setId(findingsDTO.getId());
        findings.setAmount(findingsDTO.getAmount());
        findings.setIngredient(getIngredientFromIngredientDTO(findingsDTO.getIngredientDTO()));
        findings.setUnit(getUnitFromUnitDTO(findingsDTO.getUnitDTO()));
        return findings;
    }

    private Unit getUnitFromUnitDTO(UnitDTO unitDTO) {
        Unit unit = new Unit();
        unit.setId(unitDTO.getId());
        unit.setName(unitDTO.getName());
        unit.setLongName(unitDTO.getLongName());
        return unit;
    }

    private Ingredient getIngredientFromIngredientDTO(IngredientDTO ingredientDTO) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientDTO.getId());
        ingredient.setName(ingredientDTO.getName());
        return ingredient;
    }

    public Iterable<FullRecipe> findAll() {
        return getIterableFullRecipeFromRecipe(recipeRepository.findAll());
    }

    private Iterable<FullRecipe> getIterableFullRecipeFromRecipe(Iterable<Recipe> recipes) {
        List<FullRecipe> fullRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            FullRecipe fullRecipe = new FullRecipe();
            fullRecipe.setId(recipe.getId());
            fullRecipe.setName(recipe.getName());
            fullRecipe.setComment(recipe.getComment());
            fullRecipe.setDescription(recipe.getDescription());
            fullRecipes.add(fullRecipe);
        }
        return fullRecipes;
    }

    private FullRecipe getFullRecipeFromRecipe(Optional<Recipe> recipe) {
        FullRecipe fullRecipe = new FullRecipe();
        fullRecipe.setId(recipe.orElse(null).getId());
        fullRecipe.setName(recipe.orElse(null).getName());
        fullRecipe.setComment(recipe.orElse(null).getComment());
        fullRecipe.setDescription(recipe.orElse(null).getDescription());
        fullRecipe.setFindingsList(getFindigsDTOListFromRecipe(recipe.orElse(new Recipe())));
        return fullRecipe;
    }

    private Recipe getRecipeFromFullRecipe(FullRecipe fullRecipe) {
        Recipe recipe = new Recipe();
        recipe.setId(fullRecipe.getId());
        recipe.setName(fullRecipe.getName());
        recipe.setComment(fullRecipe.getComment());
        recipe.setDescription(fullRecipe.getDescription());
        return recipe;
    }

    public FullRecipe findById(Long id) {
        return getFullRecipeFromRecipe(recipeRepository.findById(id));
    }

    public List<IngredientDTO> getAllIngredient() {
        List<IngredientDTO> ingredientDTOList = new ArrayList<>();
        for (Ingredient ingredient : ingredientRepository.findAll()) {
            ingredientDTOList.add(getIngredientDTOFromIngredient(ingredient));
        }
        return ingredientDTOList;
    }

    private IngredientDTO getIngredientDTOFromIngredient(Ingredient ingredient) {
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setId(ingredient.getId());
        ingredientDTO.setName(ingredient.getName());
        return  ingredientDTO;
    }

    public List<UnitDTO> getAllUnit() {
        List<UnitDTO> unitDTOList = new ArrayList<>();
        for (Unit unit : unitRepository.findAll()) {
            unitDTOList.add(getUnitDTOFromUnit(unit));
        }
        return unitDTOList;
    }

    private UnitDTO getUnitDTOFromUnit(Unit unit) {
        UnitDTO unitDTO = new UnitDTO();
        unitDTO.setId(unit.getId());
        unitDTO.setName(unit.getName());
        unitDTO.setLongName(unit.getLongName());
        return unitDTO;
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
}
