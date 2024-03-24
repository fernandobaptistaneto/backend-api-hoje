package br.com.projeto.api.controller;

import br.com.projeto.api.controller.dto.PersonDTO;
import br.com.projeto.api.model.Person;
import br.com.projeto.api.service.PersonService;
import br.com.projeto.api.util.UtilService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Supplier;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/people")
@AllArgsConstructor
public class PersonController {

    private final PersonService service;
    private final UtilService utilService;

    @GetMapping
    public List<Person> findAll() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody PersonDTO dto) {
        return execute(() -> service.register(dto), "cadastrar");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id,
                                  @RequestBody PersonDTO dto) {
        return execute(() -> service.edit(id, dto), "editar");
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id) {
        return service.deletePerson(id);
    }

    private ResponseEntity<?> execute(Supplier<Person> personConsumer, String action) {
        try {
            return ok(personConsumer.get());
        } catch (Exception e) {
            return status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao %s a pessoa: %s".formatted(action, utilService.getDetailsError(e.getMessage())));
        }
    }
}