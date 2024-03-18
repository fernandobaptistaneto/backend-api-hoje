package br.com.projeto.api.repositories;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

import br.com.projeto.api.models.Unit;

public interface UnitRepository extends CrudRepository<Unit, UUID> {

}
