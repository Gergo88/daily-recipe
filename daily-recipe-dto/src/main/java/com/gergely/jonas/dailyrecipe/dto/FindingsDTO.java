package com.gergely.jonas.dailyrecipe.dto;

public class FindingsDTO {
    private Long id;
    private IngredientDTO ingredientDTO;
    private UnitDTO unitDTO;
    private String amount;
    private Long recipeId;

    public FindingsDTO() {
    }

    public FindingsDTO(Long id, IngredientDTO ingredientDTO, UnitDTO unitDTO, String amount) {
        this.id = id;
        this.ingredientDTO = ingredientDTO;
        this.unitDTO = unitDTO;
        this.amount = amount;
    }

    public FindingsDTO(IngredientDTO ingredientDTO, UnitDTO unitDTO, String amount) {
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

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FindingsDTO that = (FindingsDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (ingredientDTO != null ? !ingredientDTO.equals(that.ingredientDTO) : that.ingredientDTO != null)
            return false;
        if (unitDTO != null ? !unitDTO.equals(that.unitDTO) : that.unitDTO != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        return recipeId != null ? recipeId.equals(that.recipeId) : that.recipeId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ingredientDTO != null ? ingredientDTO.hashCode() : 0);
        result = 31 * result + (unitDTO != null ? unitDTO.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (recipeId != null ? recipeId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FindingsDTO{" +
                "id=" + id +
                ", ingredientDTO=" + ingredientDTO +
                ", unitDTO=" + unitDTO +
                ", amount='" + amount + '\'' +
                ", recipeID=" + recipeId +
                '}';
    }
}
