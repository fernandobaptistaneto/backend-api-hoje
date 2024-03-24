package br.com.projeto.api.controller;

import br.com.projeto.api.model.Unit;
import br.com.projeto.api.service.UnitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
public class UnitController {

    @Autowired
    private UnitService unitService;

    @PostMapping("/unit")
    public Unit cadastrar(@RequestBody Unit unit) {
        return unitService.cadastrar(unit);
    }

    @GetMapping("/units")
    public Iterable<Unit> listar() {
        return unitService.listar();
    }

    @PutMapping("/unit")
    public Unit editar(@RequestBody Unit unit) {
        return unitService.editar(unit);
    }

    @GetMapping("/unit/{id}")
    public Unit buscar(@PathVariable UUID id) {
        return unitService.findById(id);
    }
}