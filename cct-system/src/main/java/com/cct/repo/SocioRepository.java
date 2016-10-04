package com.cct.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cct.model.Socio;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Long>{

}
