package com.softtek.jhipster.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.softtek.jhipster.domain.Afiliado;

import com.softtek.jhipster.repository.AfiliadoRepository;
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
 * REST controller for managing Afiliado.
 */
@RestController
@RequestMapping("/api")
public class AfiliadoResource {

    private final Logger log = LoggerFactory.getLogger(AfiliadoResource.class);

    private static final String ENTITY_NAME = "afiliado";

    private final AfiliadoRepository afiliadoRepository;

    public AfiliadoResource(AfiliadoRepository afiliadoRepository) {
        this.afiliadoRepository = afiliadoRepository;
    }

    /**
     * POST  /afiliados : Create a new afiliado.
     *
     * @param afiliado the afiliado to create
     * @return the ResponseEntity with status 201 (Created) and with body the new afiliado, or with status 400 (Bad Request) if the afiliado has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/afiliados")
    @Timed
    public ResponseEntity<Afiliado> createAfiliado(@Valid @RequestBody Afiliado afiliado) throws URISyntaxException {
        log.debug("REST request to save Afiliado : {}", afiliado);
        if (afiliado.getId() != null) {
            throw new BadRequestAlertException("A new afiliado cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Afiliado result = afiliadoRepository.save(afiliado);
        return ResponseEntity.created(new URI("/api/afiliados/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /afiliados : Updates an existing afiliado.
     *
     * @param afiliado the afiliado to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated afiliado,
     * or with status 400 (Bad Request) if the afiliado is not valid,
     * or with status 500 (Internal Server Error) if the afiliado couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/afiliados")
    @Timed
    public ResponseEntity<Afiliado> updateAfiliado(@Valid @RequestBody Afiliado afiliado) throws URISyntaxException {
        log.debug("REST request to update Afiliado : {}", afiliado);
        if (afiliado.getId() == null) {
            return createAfiliado(afiliado);
        }
        Afiliado result = afiliadoRepository.save(afiliado);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, afiliado.getId().toString()))
            .body(result);
    }

    /**
     * GET  /afiliados : get all the afiliados.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of afiliados in body
     */
    @GetMapping("/afiliados")
    @Timed
    public ResponseEntity<List<Afiliado>> getAllAfiliados(Pageable pageable) {
        log.debug("REST request to get a page of Afiliados");
        Page<Afiliado> page = afiliadoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/afiliados");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /afiliados/:id : get the "id" afiliado.
     *
     * @param id the id of the afiliado to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the afiliado, or with status 404 (Not Found)
     */
    @GetMapping("/afiliados/{id}")
    @Timed
    public ResponseEntity<Afiliado> getAfiliado(@PathVariable Long id) {
        log.debug("REST request to get Afiliado : {}", id);
        Afiliado afiliado = afiliadoRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(afiliado));
    }

    /**
     * DELETE  /afiliados/:id : delete the "id" afiliado.
     *
     * @param id the id of the afiliado to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/afiliados/{id}")
    @Timed
    public ResponseEntity<Void> deleteAfiliado(@PathVariable Long id) {
        log.debug("REST request to delete Afiliado : {}", id);
        afiliadoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
