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
public class FindingsToFindingsDTO implements Converter<Findings, FindingsDTO> {

    private IngredientToIngredientDTO ingredientToIngredientDTO;
    private UnitToUnitDTO unitToUnitDTO;

    public FindingsToFindingsDTO(IngredientToIngredientDTO ingredientToIngredientDTO, UnitToUnitDTO unitToUnitDTO) {
        this.ingredientToIngredientDTO = ingredientToIngredientDTO;
        this.unitToUnitDTO = unitToUnitDTO;
    }

    @Nullable
    @Override
    @Synchronized
    public FindingsDTO convert(Findings findings) {

        if (findings == null) {
            return null;
        }

        FindingsDTO findingsDTO = new FindingsDTO();
        findingsDTO.setId(findings.getId());
        findingsDTO.setAmount(findings.getAmount());
        findingsDTO.setIngredientDTO(ingredientToIngredientDTO.convert(findings.getIngredient()));
        findingsDTO.setUnitDTO(unitToUnitDTO.convert(findings.getUnit()));

        return findingsDTO;
    }
}
