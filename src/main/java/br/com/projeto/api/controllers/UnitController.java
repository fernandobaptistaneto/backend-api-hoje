package br.com.projeto.api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.models.Unit;
import br.com.projeto.api.repositories.UnitRepository;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin(origins = "*")
public class UnitController {

    @Autowired
    private UnitRepository unitRepository;

    @PostMapping("/unit")
    public Unit cadastrar(@RequestBody Unit c) {
        return unitRepository.save(c);
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

    @GetMapping("/units")
    public Iterable<Unit> listar() {
        return unitRepository.findAll();
    }

    @PutMapping("/unit")
    public Unit editar(@RequestBody Unit c) {
        return unitRepository.save(c);
    }

}
