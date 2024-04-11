package br.com.projeto.api.controller;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.NONE;

@MappedSuperclass
@Getter
public abstract class PersistentObject {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(updatable = false)
    @Setter(NONE)
    private Long id;

}
