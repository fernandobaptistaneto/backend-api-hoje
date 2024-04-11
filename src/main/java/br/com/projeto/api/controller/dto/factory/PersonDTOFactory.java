package br.com.projeto.api.controller.dto.factory;

import br.com.projeto.api.controller.dto.PersonDTO;
import br.com.projeto.api.model.Person;
import br.com.projeto.api.model.PersonUnit;
import br.com.projeto.api.service.UnitService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.CollectionUtils.isEmpty;

@Component
public class PersonDTOFactory {

    private final UnitService unitService;

    public PersonDTOFactory(UnitService unitService) {
        this.unitService = unitService;
    }

    public List<PersonDTO> toDto(List<Person> personList) {
        return personList.stream().map(p -> {
            var dto = new PersonDTO();
            dto.setName(p.getName());
            dto.setType(p.getType());
            dto.setCpf(p.getCpf());
            dto.setEmail(p.getEmail());
            dto.setPhone(p.getPhone());
            dto.setSurname(p.getSurname());
            if (!p.getPersonUnits().isEmpty()) {
                dto.setUnits(p.getPersonUnits()
                        .stream()
                        .map(PersonUnit::getUnit)
                        .toList());
            }
            return dto;
        }).toList();
    }

    public Person toPerson(PersonDTO dto) {
        var person = new Person();
        person.setName(dto.getName());
        person.setType(dto.getType());
        person.setCpf(dto.getCpf());
        person.setEmail(dto.getEmail());
        person.setPhone(dto.getPhone());
        person.setSurname(dto.getSurname());

        person.setPersonUnits(createPersonUnits(dto.getUnitsId(), person));
        return person;
    }

    public Person updatePerson(Person existingPerson, PersonDTO updatedPerson) {
        existingPerson.setName(updatedPerson.getName());
        existingPerson.setType(updatedPerson.getType());
        existingPerson.setCpf(updatedPerson.getCpf());
        existingPerson.setEmail(updatedPerson.getEmail());
        existingPerson.setPhone(updatedPerson.getPhone());
        existingPerson.setSurname(updatedPerson.getSurname());
        if (!isEmpty(updatedPerson.getUnitsId())) {
            existingPerson.getPersonUnits().addAll(createPersonUnits(updatedPerson.getUnitsId(), existingPerson));
        } else {
            existingPerson.getPersonUnits().clear();
        }

        return existingPerson;
    }

    private List<PersonUnit> createPersonUnits(List<Long> unitIds, Person person) {
        if (!isEmpty(unitIds)) {
            return unitIds.stream()
                    .map(unitService::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .map(u -> new PersonUnit(u, person))
                    .toList();
        }
        return List.of();
    }
}
