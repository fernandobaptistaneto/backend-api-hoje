package br.com.projeto.api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.util.CollectionUtils.isEmpty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import br.com.projeto.api.dtos.PersonDTO;
import br.com.projeto.api.models.Person;
import br.com.projeto.api.models.Unit;
import br.com.projeto.api.repositories.PersonRepository;
import br.com.projeto.api.utils.Utils;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UnitService unitService;

    @Autowired
    private Utils utils;

    public Iterable<Person> listPerson() {
        return personRepository.findAll();
    }

    public ResponseEntity<?> registerPerson(@RequestBody PersonDTO personDTO) {
        try {
            Person person = new Person();
            person.setName(personDTO.getName());
            person.setType(personDTO.getType());
            person.setCpf(personDTO.getCpf());
            person.setEmail(personDTO.getEmail());
            person.setPhone(personDTO.getPhone());
            person.setSurname(personDTO.getSurname());

            List<Unit> units = personDTO.getUnits();
            // Caso o cadastro venha somente o ID da unidade
            if (units == null || isEmpty(units)) {
                units = unitService.findUnitsbyId(personDTO.getUnitIds());
            }

            person.setUnits(units);

            Person savedPerson = personRepository.save(person);

            return ResponseEntity.ok(savedPerson);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao cadastrar a pessoa: " + utils.getDetailsError(e.getMessage()));
        }
    }

    public Iterable<Person> listar() {
        return personRepository.findAll();
    }

    public ResponseEntity<?> editPerson(@RequestBody PersonDTO updatedPersonDTO) {
        try {

            Optional<Person> existingPersonOptional = personRepository.findByCPF(updatedPersonDTO.getCpf());

            if (!existingPersonOptional.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            Person existingPerson = existingPersonOptional.get();
            existingPerson.setName(updatedPersonDTO.getName());
            existingPerson.setType(updatedPersonDTO.getType());
            existingPerson.setEmail(updatedPersonDTO.getEmail());
            existingPerson.setPhone(updatedPersonDTO.getPhone());
            existingPerson.setSurname(updatedPersonDTO.getSurname());

            List<Unit> units = unitService.findUnitsbyId(updatedPersonDTO.getUnitIds());
            existingPerson.setUnits(units);

            Person updatedPerson = personRepository.save(existingPerson);
            return ResponseEntity.ok(updatedPerson);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao cadastrar a pessoa: " + utils.getDetailsError(e.getMessage()));
        }
    }

    public String deletePerson(@PathVariable UUID id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return "Pessoa excluída com sucesso!";
        } else {
            return "Pessoa não encontrada.";
        }
    }
}