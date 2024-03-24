package br.com.projeto.api.service;

import br.com.projeto.api.controller.dto.PersonDTO;
import br.com.projeto.api.model.Person;

import java.util.List;

public interface PersonService {

    List<Person> findAll();

    Person register(PersonDTO personDTO);

    Person edit(Long id, PersonDTO updatedPersonDTO);

    String deletePerson(Long id);
}
