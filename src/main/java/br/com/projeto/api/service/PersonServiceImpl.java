package br.com.projeto.api.service;

import br.com.projeto.api.controller.dto.PersonDTO;
import br.com.projeto.api.model.Person;
import br.com.projeto.api.model.Unit;
import br.com.projeto.api.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.CollectionUtils.isEmpty;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final UnitService unitService;

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person register(PersonDTO personDTO) {
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

        return savedPerson;
    }

    @Override
    public Person edit(Long id, PersonDTO updatedPersonDTO) {
        // TODO - Ainda vou analisar e refatorar isso daqui.
        Optional<Person> personOptional = personRepository.findById(id);

        Person existingPerson = personOptional.get();
        existingPerson.setName(updatedPersonDTO.getName());
        existingPerson.setType(updatedPersonDTO.getType());
        existingPerson.setEmail(updatedPersonDTO.getEmail());
        existingPerson.setPhone(updatedPersonDTO.getPhone());
        existingPerson.setSurname(updatedPersonDTO.getSurname());

        List<Unit> units = unitService.findUnitsbyId(updatedPersonDTO.getUnitIds());
        existingPerson.setUnits(units);

        return personRepository.save(existingPerson);
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