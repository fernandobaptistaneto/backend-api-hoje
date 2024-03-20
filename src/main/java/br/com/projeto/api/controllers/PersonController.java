package br.com.projeto.api.controllers;

import br.com.projeto.api.dtos.PersonDTO;
import br.com.projeto.api.models.Person;
import br.com.projeto.api.services.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    public ResponseEntity<?> listPerson() {
        Iterable<Person> listPersons = personService.listPerson();
        if (listPersons == null || !listPersons.iterator().hasNext()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listPersons);
    }

    @PostMapping("/person")
    public ResponseEntity<?> registerPerson(@RequestBody PersonDTO personDTO) {
        return personService.registerPerson(personDTO);
    }

    @PutMapping("/person")
    public ResponseEntity<?> editPerson(@RequestBody PersonDTO updatedPersonDTO) {
        return personService.editPerson(updatedPersonDTO);
    }

    @DeleteMapping("/person/{id}")
    public String deletePerson(@PathVariable UUID id) {
        return personService.deletePerson(id);
    }
}