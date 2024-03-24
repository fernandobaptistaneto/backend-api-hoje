package br.com.projeto.api.service;

import br.com.projeto.api.model.Unit;
import br.com.projeto.api.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UnitService {

    @Autowired
    private UnitRepository unitRepository;

    public Unit cadastrar(Unit unit) {
        return unitRepository.save(unit);
    }

    public Iterable<Unit> listar() {
        return unitRepository.findAll();
    }

    public Unit editar(Unit unit) {
        return unitRepository.save(unit);
    }

    public Unit findById(UUID id) {
        return unitRepository.findById(id).orElse(null);
    }

    public List<Unit> findUnitsbyId(List<String> personUnits) {
        if (personUnits != null) {
            // Busca as unidades com base nos IDs fornecidos
            List<Unit> units = new ArrayList<>();
            for (String unitId : personUnits) {
                Unit unit = unitRepository.findById(UUID.fromString(unitId)).orElse(null);
                if (unit != null) {
                    units.add(unit);
                }
            }
            return units;
        } else {
            return null;
        }
    }
}