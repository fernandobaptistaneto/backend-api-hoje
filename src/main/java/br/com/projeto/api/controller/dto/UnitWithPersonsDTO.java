package br.com.projeto.api.controller.dto;

import br.com.projeto.api.model.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UnitWithPersonsDTO {

    private String description;
    private String room;
    private String floor;
    private List<Person> persons;
}