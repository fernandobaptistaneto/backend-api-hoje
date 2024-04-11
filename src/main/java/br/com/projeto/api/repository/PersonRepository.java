package br.com.projeto.api.repository;

import br.com.projeto.api.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    // Testando Query feita na mão.
    // @Query("SELECT DISTINCT p FROM Person p INNER JOIN FETCH p.units")
    // List<Person> findAllWithUnits();

    Optional<Person> findByCpf(String cpf);

    Optional<Person> findByEmailOrCpfOrderByEmail(String email, String cpf);

}