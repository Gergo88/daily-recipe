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
public class UnitToUnitDTO implements Converter<Unit, UnitDTO> {

    @Nullable
    @Override
    @Synchronized
    public UnitDTO convert(Unit unit) {

        if (unit == null) {
            return null;
        }

        UnitDTO unitDTO = new UnitDTO();
        unitDTO.setId(unit.getId());
        unitDTO.setName(unit.getName());
        unitDTO.setLongName(unit.getLongName());

        return unitDTO;
    }
}
