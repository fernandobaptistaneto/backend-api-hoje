package br.com.projeto.api.repository;

import br.com.projeto.api.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    // Testando Query feita na mão.
    // @Query("SELECT DISTINCT p FROM Person p INNER JOIN FETCH p.units")
    // List<Person> findAllWithUnits();

    @Query("SELECT p FROM Person p WHERE p.cpf = :cpf")
    Optional<Person> findByCPF(@Param("cpf") String cpf);

}
