package br.com.projeto.api.dtos;

import java.util.List;

import br.com.projeto.api.models.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UnitDTO {

    private String description;
    private String room;
    private String floor;
    private List<Person> persons;
    private Person person;
}