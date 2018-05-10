package com.softtek.jhipster.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.softtek.jhipster.domain.SubModulo;

import com.softtek.jhipster.repository.SubModuloRepository;
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
 * REST controller for managing SubModulo.
 */
@RestController
@RequestMapping("/api")
public class SubModuloResource {

    private final Logger log = LoggerFactory.getLogger(SubModuloResource.class);

    private static final String ENTITY_NAME = "subModulo";

    private final SubModuloRepository subModuloRepository;

    public SubModuloResource(SubModuloRepository subModuloRepository) {
        this.subModuloRepository = subModuloRepository;
    }

    /**
     * POST  /sub-modulos : Create a new subModulo.
     *
     * @param subModulo the subModulo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new subModulo, or with status 400 (Bad Request) if the subModulo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sub-modulos")
    @Timed
    public ResponseEntity<SubModulo> createSubModulo(@Valid @RequestBody SubModulo subModulo) throws URISyntaxException {
        log.debug("REST request to save SubModulo : {}", subModulo);
        if (subModulo.getId() != null) {
            throw new BadRequestAlertException("A new subModulo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SubModulo result = subModuloRepository.save(subModulo);
        return ResponseEntity.created(new URI("/api/sub-modulos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sub-modulos : Updates an existing subModulo.
     *
     * @param subModulo the subModulo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated subModulo,
     * or with status 400 (Bad Request) if the subModulo is not valid,
     * or with status 500 (Internal Server Error) if the subModulo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sub-modulos")
    @Timed
    public ResponseEntity<SubModulo> updateSubModulo(@Valid @RequestBody SubModulo subModulo) throws URISyntaxException {
        log.debug("REST request to update SubModulo : {}", subModulo);
        if (subModulo.getId() == null) {
            return createSubModulo(subModulo);
        }
        SubModulo result = subModuloRepository.save(subModulo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, subModulo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sub-modulos : get all the subModulos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of subModulos in body
     */
    @GetMapping("/sub-modulos")
    @Timed
    public ResponseEntity<List<SubModulo>> getAllSubModulos(Pageable pageable) {
        log.debug("REST request to get a page of SubModulos");
        Page<SubModulo> page = subModuloRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sub-modulos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /sub-modulos/:id : get the "id" subModulo.
     *
     * @param id the id of the subModulo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the subModulo, or with status 404 (Not Found)
     */
    @GetMapping("/sub-modulos/{id}")
    @Timed
    public ResponseEntity<SubModulo> getSubModulo(@PathVariable Long id) {
        log.debug("REST request to get SubModulo : {}", id);
        SubModulo subModulo = subModuloRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(subModulo));
    }

    /**
     * DELETE  /sub-modulos/:id : delete the "id" subModulo.
     *
     * @param id the id of the subModulo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sub-modulos/{id}")
    @Timed
    public ResponseEntity<Void> deleteSubModulo(@PathVariable Long id) {
        log.debug("REST request to delete SubModulo : {}", id);
        subModuloRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
