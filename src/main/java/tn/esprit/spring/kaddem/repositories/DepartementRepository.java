package tn.esprit.spring.kaddem.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.kaddem.entities.Departement;

import java.util.Map;

@Repository
public interface DepartementRepository extends CrudRepository<Departement,Integer> {


    @Query("SELECT d.nomDepart, COUNT(e) FROM Departement d JOIN d.etudiants e GROUP BY d.nomDepart")
    Map<String, Long> countStudentsByDepartement();
}
