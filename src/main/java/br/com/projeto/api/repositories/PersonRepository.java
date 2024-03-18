package br.com.projeto.api.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.projeto.api.models.Person;

public interface PersonRepository extends CrudRepository<Person, UUID> {

    // Testando Query feita na mão.
    // @Query("SELECT DISTINCT p FROM Person p INNER JOIN FETCH p.units")
    // List<Person> findAllWithUnits();

    @Query("SELECT p FROM Person p WHERE p.cpf = :cpf")
    Optional<Person> findByCPF(@Param("cpf") String cpf);

}
