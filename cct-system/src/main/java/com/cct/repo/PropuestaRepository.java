package com.cct.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cct.model.Propuesta;

@Repository
public interface PropuestaRepository extends JpaRepository<Propuesta, Long>{

}
