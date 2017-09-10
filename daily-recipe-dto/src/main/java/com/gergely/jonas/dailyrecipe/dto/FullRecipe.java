package com.gergely.jonas.dailyrecipe.dto;

import java.util.ArrayList;
import java.util.List;

public class FullRecipe {
    private Long id;
    private String name;
    private String comment;
    private String description;
    private List<FindingsDTO> findingsList;

    public FullRecipe() {
    }

    public FullRecipe(Long id, String name, String comment, String description, List<FindingsDTO> findingsList, List<String> szovegList) {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.description = description;
        this.findingsList = findingsList;
    }

    public List<FindingsDTO> getFindingsList() {
        if (findingsList == null) {
            findingsList = new ArrayList<>();
        }
        return findingsList;
    }

    @Override
    public String toString() {
        return "FullRecipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", description='" + description + '\'' +
                ", findingsList=" + findingsList +
                '}';
    }

    public void setFindingsList(List<FindingsDTO> findingsList) {
        this.findingsList = findingsList;
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
}
