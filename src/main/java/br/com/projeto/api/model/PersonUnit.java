package br.com.projeto.api.model;

import br.com.projeto.api.controller.PersistentObject;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "persons_units")
@Getter
@Setter
@NoArgsConstructor
public class PersonUnit extends PersistentObject {

    @ManyToOne
    private Person person;

    @ManyToOne
    private Unit unit;

    public PersonUnit(Unit unit, Person person) {
        this.unit = unit;
        this.person = person;
    }
}
