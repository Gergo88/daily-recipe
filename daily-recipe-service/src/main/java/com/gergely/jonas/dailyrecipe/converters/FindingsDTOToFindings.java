package com.gergely.jonas.dailyrecipe.converters;

import com.gergely.jonas.dailyrecipe.dto.FindingsDTO;
import com.gergely.jonas.dailyrecipe.model.model.Findings;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by ext-jonasg on 2017.10.04..
 */
@Component
public class FindingsDTOToFindings implements Converter<FindingsDTO, Findings> {

    private IngredientDTOToIngredient ingredientDTOToIngredient;
    private UnitDTOToUnit unitDTOToUnit;

    public FindingsDTOToFindings(IngredientDTOToIngredient ingredientDTOToIngredient, UnitDTOToUnit unitDTOToUnit) {
        this.ingredientDTOToIngredient = ingredientDTOToIngredient;
        this.unitDTOToUnit = unitDTOToUnit;
    }

    @Nullable
    @Override
    @Synchronized
    public Findings convert(FindingsDTO findingsDTO) {

        if (findingsDTO == null) {
            return null;
        }
        Findings findings = new Findings();
        findings.setId(findingsDTO.getId());
        findings.setAmount(findingsDTO.getAmount());
        findings.setIngredient(ingredientDTOToIngredient.convert(findingsDTO.getIngredientDTO()));
        findings.setUnit(unitDTOToUnit.convert(findingsDTO.getUnitDTO()));

        return findings;
    }
}
