package br.com.projeto.api.controller;

import br.com.projeto.api.controller.dto.UnitDTO;
import br.com.projeto.api.controller.dto.factory.UnitDTOFactory;
import br.com.projeto.api.model.Unit;
import br.com.projeto.api.service.UnitService;
import br.com.projeto.api.service.UnitServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/units")
public class UnitController extends AbstractController<UnitService, Unit> {

    private final UnitServiceImpl service;

    private final UnitDTOFactory unitDTOFactory;

    public UnitController(UnitServiceImpl service, UnitDTOFactory unitDTOFactory) {
        super(service);
        this.service = service;
        this.unitDTOFactory = unitDTOFactory;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody UnitDTO dto) {
        return execute((s) -> s.register(unitDTOFactory.toUnit(dto)), "cadastrar");
    }

    @GetMapping
    public List<Unit> findAll() {
        return service.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody UnitDTO dto) {
       return execute((s) -> s.edit(id, dto), "editar");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return execute((s) -> s.delete(id), "deletar");
    }
}