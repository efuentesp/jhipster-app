package com.softtek.jhipster.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.softtek.jhipster.domain.Modulo;

import com.softtek.jhipster.repository.ModuloRepository;
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
 * REST controller for managing Modulo.
 */
@RestController
@RequestMapping("/api")
public class ModuloResource {

    private final Logger log = LoggerFactory.getLogger(ModuloResource.class);

    private static final String ENTITY_NAME = "modulo";

    private final ModuloRepository moduloRepository;

    public ModuloResource(ModuloRepository moduloRepository) {
        this.moduloRepository = moduloRepository;
    }

    /**
     * POST  /modulos : Create a new modulo.
     *
     * @param modulo the modulo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new modulo, or with status 400 (Bad Request) if the modulo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/modulos")
    @Timed
    public ResponseEntity<Modulo> createModulo(@Valid @RequestBody Modulo modulo) throws URISyntaxException {
        log.debug("REST request to save Modulo : {}", modulo);
        if (modulo.getId() != null) {
            throw new BadRequestAlertException("A new modulo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Modulo result = moduloRepository.save(modulo);
        return ResponseEntity.created(new URI("/api/modulos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /modulos : Updates an existing modulo.
     *
     * @param modulo the modulo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated modulo,
     * or with status 400 (Bad Request) if the modulo is not valid,
     * or with status 500 (Internal Server Error) if the modulo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/modulos")
    @Timed
    public ResponseEntity<Modulo> updateModulo(@Valid @RequestBody Modulo modulo) throws URISyntaxException {
        log.debug("REST request to update Modulo : {}", modulo);
        if (modulo.getId() == null) {
            return createModulo(modulo);
        }
        Modulo result = moduloRepository.save(modulo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, modulo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /modulos : get all the modulos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of modulos in body
     */
    @GetMapping("/modulos")
    @Timed
    public ResponseEntity<List<Modulo>> getAllModulos(Pageable pageable) {
        log.debug("REST request to get a page of Modulos");
        Page<Modulo> page = moduloRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/modulos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /modulos/:id : get the "id" modulo.
     *
     * @param id the id of the modulo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the modulo, or with status 404 (Not Found)
     */
    @GetMapping("/modulos/{id}")
    @Timed
    public ResponseEntity<Modulo> getModulo(@PathVariable Long id) {
        log.debug("REST request to get Modulo : {}", id);
        Modulo modulo = moduloRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(modulo));
    }

    /**
     * DELETE  /modulos/:id : delete the "id" modulo.
     *
     * @param id the id of the modulo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/modulos/{id}")
    @Timed
    public ResponseEntity<Void> deleteModulo(@PathVariable Long id) {
        log.debug("REST request to delete Modulo : {}", id);
        moduloRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
