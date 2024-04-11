package br.com.projeto.api.controller.dto;

import java.util.List;
import java.util.UUID;

import br.com.projeto.api.model.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UnitDTO {

    private String description;
    private String room;
    private String floor;
}