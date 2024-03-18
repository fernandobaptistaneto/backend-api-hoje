package br.com.projeto.api.dtos;

import br.com.projeto.api.models.Person;
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