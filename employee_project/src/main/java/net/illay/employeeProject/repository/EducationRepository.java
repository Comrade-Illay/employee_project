package net.illay.employeeProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.illay.employeeProject.model.Education;

public interface EducationRepository extends JpaRepository<Education, Long>{

	Page<Education> findAll(Pageable pageable);
	
}
