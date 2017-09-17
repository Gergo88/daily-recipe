package com.gergely.jonas.dailyrecipe.model.model;

import javax.persistence.*;

@Entity
public class Findings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ingredient_id")
    private Ingredient ingredient;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="unit_id")
    private Unit unit;
    private String amount;

    public Findings() {
    }

    public Findings(Recipe recipe, Ingredient ingredient, Unit unit, String amount) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.unit = unit;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Findings{" +
                "id=" + id +
                ", recipe=" + recipe +
                ", ingredient=" + ingredient +
                ", unit=" + unit +
                ", amount='" + amount + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Findings findings = (Findings) o;

        return id != null ? id.equals(findings.id) : findings.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
