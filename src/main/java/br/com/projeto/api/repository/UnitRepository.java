package br.com.projeto.api.repository;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

import br.com.projeto.api.model.Unit;

public interface UnitRepository extends CrudRepository<Unit, UUID> {

}
