package br.com.projeto.api.model;

import br.com.projeto.api.controller.PersistentObject;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(name = "units", uniqueConstraints = {
        @UniqueConstraint(name = "uk_uuid__unit", columnNames = "uuid")
})
@Getter
@Setter
public class Unit extends PersistentObject {

    @Column(updatable = false, unique = true, nullable = false)
    private UUID uuid = UUID.randomUUID();

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String room;

    @Column(nullable = false)
    private String floor;

    @OneToMany(mappedBy = "unit", cascade = ALL, orphanRemoval = true)
    @ToString.Exclude
    @JsonIgnore
    private List<PersonUnit> unitPersons;

}
