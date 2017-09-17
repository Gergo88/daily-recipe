package com.gergely.jonas.dailyrecipe.model.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by ext-jonasg on 2017.09.08..
 */
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String comment;
    private String description;
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Findings> findingsList;

    public Recipe() {
    }

    public Recipe(String name, String comment, String description) {
        this.name = name;
        this.comment = comment;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", description='" + description + '\'' +
                ", findingsList=" + findingsList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        return id != null ? id.equals(recipe.id) : recipe.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Findings> getFindingsList() {
        return findingsList;
    }

    public void setFindingsList(List<Findings> findingsList) {
        this.findingsList = findingsList;
    }
}
