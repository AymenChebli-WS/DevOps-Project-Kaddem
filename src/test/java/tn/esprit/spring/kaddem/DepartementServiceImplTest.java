package tn.esprit.spring.kaddem;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;

import java.util.*;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class DepartementServiceImplTest {

    @InjectMocks
    private DepartementServiceImpl departementService;

    @Mock
    private DepartementRepository departementRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testAddDepartement() {
        // Création Département (Arrange)
        Departement depart = new Departement();
        depart.setNomDepart("IT Depart");

        // Ajout Département (Act)
        Mockito.when(departementRepository.save(Mockito.any(Departement.class))).thenReturn(depart);
        Departement result = departementService.addDepartement(depart);

        // Vérifier le client (Assert)
        Assertions.assertNotNull(result);
        Assertions.assertEquals("IT Depart", result.getNomDepart());

        // Vérifier l'interactions avec mock
        Mockito.verify(departementRepository).save(Mockito.any(Departement.class));
    }

    @Test
    void testRetrieveAllDepartements() {
        // Création de la liste des départements (Arrange)
        Departement depart1 = new Departement(1, "IT Depart");
        Departement depart2 = new Departement(2, "HR Depart");
        List<Departement> departements = Arrays.asList(depart1, depart2);

        // Configuration du mock (Act)
        Mockito.when(departementRepository.findAll()).thenReturn(departements);

        // Récupération de la liste des départements (Act)
        List<Departement> result = departementService.retrieveAllDepartements();

        // Vérifications (Assert)
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("IT Depart", result.get(0).getNomDepart());
        Assertions.assertEquals("HR Depart", result.get(1).getNomDepart());

        // Vérifier l'interaction avec le mock
        Mockito.verify(departementRepository).findAll();
    }

    @Test
    void testUpdateDepartement() {
        // Création d'un département (Arrange)
        Departement depart = new Departement(1, "IT Depart");

        // Configuration du mock pour la mise à jour (Act)
        Mockito.when(departementRepository.save(Mockito.any(Departement.class))).thenReturn(depart);

        // Mise à jour du département (Act)
        Departement result = departementService.updateDepartement(depart);

        // Vérifications (Assert)
        Assertions.assertNotNull(result);
        Assertions.assertEquals("IT Depart", result.getNomDepart());

        // Vérifier l'interaction avec le mock
        Mockito.verify(departementRepository).save(Mockito.any(Departement.class));
    }

    @Test
    void testRetrieveDepartement() {
        // Création d'un département (Arrange)
        Departement depart = new Departement(1, "IT Depart");

        // Configuration du mock pour le findById (Act)
        Mockito.when(departementRepository.findById(1)).thenReturn(Optional.of(depart));

        // Récupération du département par ID (Act)
        Departement result = departementService.retrieveDepartement(1);

        // Vérifications (Assert)
        Assertions.assertNotNull(result);
        Assertions.assertEquals("IT Depart", result.getNomDepart());

        // Vérifier l'interaction avec le mock
        Mockito.verify(departementRepository).findById(1);
    }

    @Test
    void testDeleteDepartement() {
        // Création d'un département (Arrange)
        Departement depart = new Departement(1, "IT Depart");

        // Configuration du mock pour le findById (Act)
        Mockito.when(departementRepository.findById(1)).thenReturn(Optional.of(depart));

        // Suppression du département (Act)
        departementService.deleteDepartement(1);

        // Vérifier l'interaction avec le mock (Assert)
        Mockito.verify(departementRepository).delete(depart);
        Mockito.verify(departementRepository).findById(1);
    }

    @Test
    void testGetDepartmentStatistics() {
        Map<String, Long> statistics = new HashMap<>();
        statistics.put("IT Depart", 5L);
        statistics.put("HR Depart", 3L);

        Mockito.when(departementRepository.countStudentsByDepartement()).thenReturn(statistics);

        Map<String, Long> result = departementService.getDepartmentStatistics();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(5L, result.get("IT Depart"));
        Assertions.assertEquals(3L, result.get("HR Depart"));
    }
}
