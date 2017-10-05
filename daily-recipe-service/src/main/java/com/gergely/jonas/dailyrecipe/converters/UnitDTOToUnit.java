package com.gergely.jonas.dailyrecipe.converters;

import com.gergely.jonas.dailyrecipe.dto.UnitDTO;
import com.gergely.jonas.dailyrecipe.model.model.Unit;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by ext-jonasg on 2017.10.03..
 */
@Component
public class UnitDTOToUnit implements Converter<UnitDTO, Unit> {
    @Nullable
    @Override
    @Synchronized
    public Unit convert(UnitDTO unitDTO) {

        if (unitDTO == null) {
            return null;
        }

        Unit unit = new Unit();
        unit.setId(unitDTO.getId());
        unit.setName(unitDTO.getName());
        unit.setLongName(unitDTO.getLongName());

        return unit;
    }
}
