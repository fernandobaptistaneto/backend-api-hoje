package br.com.projeto.api.service;

import br.com.projeto.api.controller.dto.PersonDTO;
import br.com.projeto.api.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService extends GeneralService<Person> {

    Person register(Person person);

    Person edit(Long id, PersonDTO dto);

    String deletePerson(Long id);
}
