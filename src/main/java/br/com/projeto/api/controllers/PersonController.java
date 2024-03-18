package br.com.projeto.api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.dtos.PersonDTO;
import br.com.projeto.api.dtos.UnitDTO;
import br.com.projeto.api.models.Person;
import br.com.projeto.api.models.Unit;
import br.com.projeto.api.repositories.PersonRepository;
import br.com.projeto.api.repositories.UnitRepository;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin(origins = "*")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UnitController unitController;

    @PostMapping("/person")
    public ResponseEntity<?> registerPerson(@RequestBody PersonDTO personDTO) {
        try {
            Person person = new Person();
            person.setName(personDTO.getName());
            person.setType(personDTO.getType());
            person.setCpf(personDTO.getCpf());
            person.setEmail(personDTO.getEmail());
            person.setPhone(personDTO.getPhone());
            person.setSurname(personDTO.getSurname());

            List<Unit> units;
            // Caso o cadastro venha somente o ID da unidade
            units = unitController.findUnitsbyId(personDTO.getUnitIds());

            person.setUnits(units);

            Person savedPerson = personRepository.save(person);

            return ResponseEntity.ok(savedPerson);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao cadastrar a pessoa: " + getDetailsError(e.getMessage()));
        }
    }

    private String getDetailsError(String msgError) {
        String mensagemErro = msgError;

        String padrao = "\\[.*?\\];";

        // Remove a parte específica da mensagem de erro
        String mensagemFormatada = mensagemErro.replaceAll(padrao, "");

        System.out.println("Detalhe do erro: " + mensagemFormatada);
        return mensagemFormatada;
    }

    @GetMapping("/persons")
    public Iterable<Person> listar() {
        return personRepository.findAll();
    }

    @PutMapping("/person")
    public ResponseEntity<?> editPerson(@RequestBody Person updatedPerson) {
        try {

            Optional<Person> existingPersonOptional = personRepository.findById(updatedPerson.getId());

            if (!existingPersonOptional.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            Person updateSave = personRepository.save(updatedPerson);
            return ResponseEntity.ok(updateSave);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao cadastrar a pessoa: " + getDetailsError(e.getMessage()));
        }
    }

    @DeleteMapping("/person/{id}")
    public String deletePerson(@PathVariable UUID id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return "Pessoa excluída com sucesso!";
        } else {
            return "Pessoa não encontrada.";
        }
    }
}
