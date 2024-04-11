package br.com.projeto.api.service;

import br.com.projeto.api.controller.dto.UnitDTO;
import br.com.projeto.api.model.Unit;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UnitService extends GeneralService<Unit> {

    Optional<Unit> findById(Long id);

    Unit register(Unit unit);

    Unit edit(Long id, UnitDTO unitDTO);

    Unit delete(Long id);
}
