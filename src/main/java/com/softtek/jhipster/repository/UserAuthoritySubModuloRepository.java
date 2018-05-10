package com.softtek.jhipster.repository;

import com.softtek.jhipster.domain.UserAuthoritySubModulo;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the UserAuthoritySubModulo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserAuthoritySubModuloRepository extends JpaRepository<UserAuthoritySubModulo, Long> {

}
