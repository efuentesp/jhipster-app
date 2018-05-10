package com.softtek.jhipster.repository;

import com.softtek.jhipster.domain.Modulo;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Modulo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Long> {

}
