package br.com.projeto.api.controller.dto.factory;

import br.com.projeto.api.controller.dto.UnitDTO;
import br.com.projeto.api.model.Unit;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UnitDTOFactory {

    public List<UnitDTO> toDto(List<Unit> unitList) {
        return unitList.stream()
                .map(p -> new UnitDTO(
                        p.getDescription(),
                        p.getRoom(),
                        p.getFloor()
                )).toList();
    }

    public Unit toUnit(UnitDTO dto) {
        var unit = new Unit();
        unit.setDescription(dto.getDescription());
        unit.setFloor(dto.getFloor());
        unit.setRoom(dto.getRoom());
        return unit;
    }
}
