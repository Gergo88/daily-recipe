package com.gergely.jonas.dailyrecipe.dto;

public class FindingsDTO {
    private Long id;
    private FullRecipe recipe;
    private IngredientDTO ingredientDTO;
    private UnitDTO unitDTO;
    private String amount;

    public FindingsDTO() {
    }

    public FindingsDTO(Long id, FullRecipe recipe, IngredientDTO ingredientDTO, UnitDTO unitDTO, String amount) {
        this.id = id;
        this.recipe = recipe;
        this.ingredientDTO = ingredientDTO;
        this.unitDTO = unitDTO;
        this.amount = amount;
    }

    public FindingsDTO(FullRecipe recipe, IngredientDTO ingredientDTO, UnitDTO unitDTO, String amount) {
        this.recipe = recipe;
        this.ingredientDTO = ingredientDTO;
        this.unitDTO = unitDTO;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FullRecipe getRecipe() {
        return recipe;
    }

    public void setRecipe(FullRecipe recipe) {
        this.recipe = recipe;
    }

    public IngredientDTO getIngredientDTO() {
        return ingredientDTO;
    }

    public void setIngredientDTO(IngredientDTO ingredientDTO) {
        this.ingredientDTO = ingredientDTO;
    }

    public UnitDTO getUnitDTO() {
        return unitDTO;
    }

    public void setUnitDTO(UnitDTO unitDTO) {
        this.unitDTO = unitDTO;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
