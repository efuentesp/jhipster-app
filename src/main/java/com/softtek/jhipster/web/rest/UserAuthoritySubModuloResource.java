package com.softtek.jhipster.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.softtek.jhipster.domain.UserAuthoritySubModulo;

import com.softtek.jhipster.repository.UserAuthoritySubModuloRepository;
import com.softtek.jhipster.web.rest.errors.BadRequestAlertException;
import com.softtek.jhipster.web.rest.util.HeaderUtil;
import com.softtek.jhipster.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing UserAuthoritySubModulo.
 */
@RestController
@RequestMapping("/api")
public class UserAuthoritySubModuloResource {

    private final Logger log = LoggerFactory.getLogger(UserAuthoritySubModuloResource.class);

    private static final String ENTITY_NAME = "userAuthoritySubModulo";

    private final UserAuthoritySubModuloRepository userAuthoritySubModuloRepository;

    public UserAuthoritySubModuloResource(UserAuthoritySubModuloRepository userAuthoritySubModuloRepository) {
        this.userAuthoritySubModuloRepository = userAuthoritySubModuloRepository;
    }

    /**
     * POST  /user-authority-sub-modulos : Create a new userAuthoritySubModulo.
     *
     * @param userAuthoritySubModulo the userAuthoritySubModulo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userAuthoritySubModulo, or with status 400 (Bad Request) if the userAuthoritySubModulo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-authority-sub-modulos")
    @Timed
    public ResponseEntity<UserAuthoritySubModulo> createUserAuthoritySubModulo(@Valid @RequestBody UserAuthoritySubModulo userAuthoritySubModulo) throws URISyntaxException {
        log.debug("REST request to save UserAuthoritySubModulo : {}", userAuthoritySubModulo);
        if (userAuthoritySubModulo.getId() != null) {
            throw new BadRequestAlertException("A new userAuthoritySubModulo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserAuthoritySubModulo result = userAuthoritySubModuloRepository.save(userAuthoritySubModulo);
        return ResponseEntity.created(new URI("/api/user-authority-sub-modulos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-authority-sub-modulos : Updates an existing userAuthoritySubModulo.
     *
     * @param userAuthoritySubModulo the userAuthoritySubModulo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userAuthoritySubModulo,
     * or with status 400 (Bad Request) if the userAuthoritySubModulo is not valid,
     * or with status 500 (Internal Server Error) if the userAuthoritySubModulo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-authority-sub-modulos")
    @Timed
    public ResponseEntity<UserAuthoritySubModulo> updateUserAuthoritySubModulo(@Valid @RequestBody UserAuthoritySubModulo userAuthoritySubModulo) throws URISyntaxException {
        log.debug("REST request to update UserAuthoritySubModulo : {}", userAuthoritySubModulo);
        if (userAuthoritySubModulo.getId() == null) {
            return createUserAuthoritySubModulo(userAuthoritySubModulo);
        }
        UserAuthoritySubModulo result = userAuthoritySubModuloRepository.save(userAuthoritySubModulo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userAuthoritySubModulo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-authority-sub-modulos : get all the userAuthoritySubModulos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of userAuthoritySubModulos in body
     */
    @GetMapping("/user-authority-sub-modulos")
    @Timed
    public ResponseEntity<List<UserAuthoritySubModulo>> getAllUserAuthoritySubModulos(Pageable pageable) {
        log.debug("REST request to get a page of UserAuthoritySubModulos");
        Page<UserAuthoritySubModulo> page = userAuthoritySubModuloRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-authority-sub-modulos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /user-authority-sub-modulos/:id : get the "id" userAuthoritySubModulo.
     *
     * @param id the id of the userAuthoritySubModulo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userAuthoritySubModulo, or with status 404 (Not Found)
     */
    @GetMapping("/user-authority-sub-modulos/{id}")
    @Timed
    public ResponseEntity<UserAuthoritySubModulo> getUserAuthoritySubModulo(@PathVariable Long id) {
        log.debug("REST request to get UserAuthoritySubModulo : {}", id);
        UserAuthoritySubModulo userAuthoritySubModulo = userAuthoritySubModuloRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userAuthoritySubModulo));
    }

    /**
     * DELETE  /user-authority-sub-modulos/:id : delete the "id" userAuthoritySubModulo.
     *
     * @param id the id of the userAuthoritySubModulo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-authority-sub-modulos/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserAuthoritySubModulo(@PathVariable Long id) {
        log.debug("REST request to delete UserAuthoritySubModulo : {}", id);
        userAuthoritySubModuloRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
