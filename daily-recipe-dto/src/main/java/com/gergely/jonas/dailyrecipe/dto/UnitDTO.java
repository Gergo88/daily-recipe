package com.gergely.jonas.dailyrecipe.dto;

public class UnitDTO {
    private Long id;
    private String name;
    private String longName;

    public UnitDTO() {
    }

    public UnitDTO(Long id, String name, String longName) {
        this.id = id;
        this.name = name;
        this.longName = longName;
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

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }
}
