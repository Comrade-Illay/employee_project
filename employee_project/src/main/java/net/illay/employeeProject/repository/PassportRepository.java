package net.illay.employeeProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.illay.employeeProject.model.Passport;

public interface PassportRepository  extends JpaRepository<Passport, Long>{
	
	Page<Passport> findAll(Pageable pageable);

}
