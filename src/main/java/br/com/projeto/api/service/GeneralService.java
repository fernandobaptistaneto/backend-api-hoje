package br.com.projeto.api.service;

import br.com.projeto.api.controller.PersistentObject;
import br.com.projeto.api.model.Unit;

import java.util.List;

public interface GeneralService<E extends PersistentObject> {

    List<E> findAll();

}
