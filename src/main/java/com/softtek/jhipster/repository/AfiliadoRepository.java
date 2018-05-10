package com.softtek.jhipster.repository;

import com.softtek.jhipster.domain.Afiliado;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Afiliado entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AfiliadoRepository extends JpaRepository<Afiliado, Long> {

}
