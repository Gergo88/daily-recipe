package com.gergely.jonas.dailyrecipe.dto;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class FullRecipe {
    private Long id;
    @NotNull
    @NotEmpty
    private String name;
    private String comment;
    private String description;
    private List<FindingsDTO> findingsList;
    private MultipartFile image;

    public FullRecipe() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FullRecipe that = (FullRecipe) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
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
                ", image=" + image +
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
