package com.softtek.jhipster.repository;

import com.softtek.jhipster.domain.SubModulo;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the SubModulo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubModuloRepository extends JpaRepository<SubModulo, Long> {

}
