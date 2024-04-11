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
@Table(name = "person", uniqueConstraints = {
                @UniqueConstraint(name = "uk_email__person", columnNames = "email"),
                @UniqueConstraint(name = "uk_cpf__person", columnNames = "cpf"),
                @UniqueConstraint(name = "uk_uuid__person", columnNames = "uuid")
})
@Getter
@Setter
public class Person extends PersistentObject {

        @Column(updatable = false, unique = true, nullable = false)
        private UUID uuid = UUID.randomUUID();

        @Column(nullable = false)
        private String name;

        @Column(nullable = false)
        private String type;

        @Column(nullable = false, unique = true)
        private String cpf;

        @Column(nullable = false, unique = true)
        private String email;

        private String phone;

        private String surname;

        @OneToMany(mappedBy = "person", cascade = ALL, orphanRemoval = true)
        @ToString.Exclude
        @JsonIgnore
        private List<PersonUnit> personUnits;

}
