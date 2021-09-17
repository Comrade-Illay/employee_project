package net.illay.employeeProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.illay.employeeProject.model.Position;

public interface PositionRepository extends JpaRepository<Position, Long>{

	Page<Position> findAll(Pageable pageable);
}
