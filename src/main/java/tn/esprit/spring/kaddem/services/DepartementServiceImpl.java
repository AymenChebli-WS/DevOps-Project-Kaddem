package tn.esprit.spring.kaddem.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DepartementServiceImpl implements IDepartementService {
	private static final Logger logger = LogManager.getLogger(DepartementServiceImpl.class);

	@Autowired
	DepartementRepository departementRepository;

	public List<Departement> retrieveAllDepartements() {
		logger.info("Retrieving all departments");
		List<Departement> departements = (List<Departement>) departementRepository.findAll();
		logger.debug("Retrieved departments: {}", departements);
		return departements;
	}

	public Departement addDepartement(Departement d) {
		logger.info("Adding new department: {}", d);
		Departement savedDepartement = departementRepository.save(d);
		logger.info("Added department: {}", savedDepartement);
		return savedDepartement;
	}

	public Departement updateDepartement(Departement d) {
		logger.info("Updating department: {}", d);
		Departement updatedDepartement = departementRepository.save(d);
		logger.info("Updated department: {}", updatedDepartement);
		return updatedDepartement;
	}

	public Departement retrieveDepartement(Integer idDepart) {
		logger.info("Retrieving department with ID: {}", idDepart);
		Departement departement = departementRepository.findById(idDepart).orElse(null);
		logger.debug("Retrieved department: {}", departement);
		return departement;
	}

	public void deleteDepartement(Integer idDepartement) {
		logger.info("Deleting department with ID: {}", idDepartement);
		Departement d = retrieveDepartement(idDepartement);
		if (d != null) {
			departementRepository.delete(d);
			logger.info("Deleted department: {}", d);
		} else {
			logger.warn("Attempted to delete a department that does not exist: ID {}", idDepartement);
		}
	}

	public Map<String, Long> getDepartmentStatistics() {
		logger.info("Retrieving department statistics");
		Map<String, Long> statistics = departementRepository.countStudentsByDepartement();
		logger.debug("Department statistics: {}", statistics);
		return statistics;
	}
}
