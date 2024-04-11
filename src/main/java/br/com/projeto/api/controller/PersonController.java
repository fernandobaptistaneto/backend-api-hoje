package br.com.projeto.api.controller;

import br.com.projeto.api.controller.dto.PersonDTO;
import br.com.projeto.api.controller.dto.factory.PersonDTOFactory;
import br.com.projeto.api.model.Person;
import br.com.projeto.api.service.PersonService;
import br.com.projeto.api.service.PersonServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController extends AbstractController<PersonService, Person> {
//    private final PersonServiceImpl service;
    private final PersonDTOFactory personDTOFactory;

    PersonController(PersonServiceImpl service, PersonDTOFactory personDTOFactory) {
        super(service);
        this.service = service;
        this.personDTOFactory = personDTOFactory;
    }

    @GetMapping
    public List<PersonDTO> findAll() {
        return personDTOFactory.toDto(service.findAll());
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody PersonDTO dto) {
        return execute((s) -> s.register(personDTOFactory.toPerson(dto)), "cadastrar");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody PersonDTO dto) {
        return execute((s) -> s.edit(id, dto), "editar");
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id) {
        return service.deletePerson(id);
    }
}