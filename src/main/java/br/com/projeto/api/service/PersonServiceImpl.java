package br.com.projeto.api.service;

import br.com.projeto.api.controller.dto.PersonDTO;
import br.com.projeto.api.controller.dto.factory.PersonDTOFactory;
import br.com.projeto.api.model.Person;
import br.com.projeto.api.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    private final PersonDTOFactory personDTOFactory;

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }
    @Override
    public Person register(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person edit(Long id, PersonDTO updatedPersonDTO) {
        Optional<Person> existingPersonOptional = personRepository.findById(id);
        if (existingPersonOptional.isPresent()) {
            Person existingPerson = existingPersonOptional.get();
            var savedPerson = personDTOFactory.updatePerson(existingPerson, updatedPersonDTO);
            return personRepository.save(savedPerson);
        } else {
            throw new EntityNotFoundException("Pessoa não encontrada!");
        }
    }

    @Override
    public String deletePerson(Long id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return "Pessoa excluída com sucesso!";
        } else {
            return "Pessoa não encontrada.";
        }
    }
}