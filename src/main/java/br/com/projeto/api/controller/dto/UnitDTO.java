package br.com.projeto.api.controller.dto;

import java.util.List;

import br.com.projeto.api.model.Person;
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