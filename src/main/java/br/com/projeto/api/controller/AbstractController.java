package br.com.projeto.api.controller;

import br.com.projeto.api.service.GeneralService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Function;

import static org.springframework.http.ResponseEntity.ok;

abstract class AbstractController<T extends GeneralService, E extends PersistentObject> {

    protected T service;

    AbstractController(T generalService) {
        this.service = generalService;
    }

    protected ResponseEntity<?> execute(Function<T, E> entityConsumer, String action) {
        try {
            return ok(entityConsumer.apply(service));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao %s a pessoa: %s".formatted(action,
                            e.getMessage()));
        }
    }
}
