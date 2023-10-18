package it.dedagroup.project_cea.repository;




import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.dedagroup.project_cea.model.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long>{
	public Optional<Administrator> findByCondominiums_Id(long id);
}
