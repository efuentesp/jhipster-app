package com.softtek.jhipster.web.rest;

import com.softtek.jhipster.JhipsterAppApp;

import com.softtek.jhipster.domain.Modulo;
import com.softtek.jhipster.repository.ModuloRepository;
import com.softtek.jhipster.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.softtek.jhipster.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ModuloResource REST controller.
 *
 * @see ModuloResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterAppApp.class)
public class ModuloResourceIntTest {

    private static final Integer DEFAULT_ID_MODULO = 1;
    private static final Integer UPDATED_ID_MODULO = 2;

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ESTATUS = false;
    private static final Boolean UPDATED_ESTATUS = true;

    private static final LocalDate DEFAULT_FECHA_CREACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_CREACION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FECHA_MODIFICACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_MODIFICACION = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private ModuloRepository moduloRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restModuloMockMvc;

    private Modulo modulo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ModuloResource moduloResource = new ModuloResource(moduloRepository);
        this.restModuloMockMvc = MockMvcBuilders.standaloneSetup(moduloResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Modulo createEntity(EntityManager em) {
        Modulo modulo = new Modulo()
            .idModulo(DEFAULT_ID_MODULO)
            .nombre(DEFAULT_NOMBRE)
            .estatus(DEFAULT_ESTATUS)
            .fechaCreacion(DEFAULT_FECHA_CREACION)
            .fechaModificacion(DEFAULT_FECHA_MODIFICACION);
        return modulo;
    }

    @Before
    public void initTest() {
        modulo = createEntity(em);
    }

    @Test
    @Transactional
    public void createModulo() throws Exception {
        int databaseSizeBeforeCreate = moduloRepository.findAll().size();

        // Create the Modulo
        restModuloMockMvc.perform(post("/api/modulos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modulo)))
            .andExpect(status().isCreated());

        // Validate the Modulo in the database
        List<Modulo> moduloList = moduloRepository.findAll();
        assertThat(moduloList).hasSize(databaseSizeBeforeCreate + 1);
        Modulo testModulo = moduloList.get(moduloList.size() - 1);
        assertThat(testModulo.getIdModulo()).isEqualTo(DEFAULT_ID_MODULO);
        assertThat(testModulo.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testModulo.isEstatus()).isEqualTo(DEFAULT_ESTATUS);
        assertThat(testModulo.getFechaCreacion()).isEqualTo(DEFAULT_FECHA_CREACION);
        assertThat(testModulo.getFechaModificacion()).isEqualTo(DEFAULT_FECHA_MODIFICACION);
    }

    @Test
    @Transactional
    public void createModuloWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = moduloRepository.findAll().size();

        // Create the Modulo with an existing ID
        modulo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restModuloMockMvc.perform(post("/api/modulos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modulo)))
            .andExpect(status().isBadRequest());

        // Validate the Modulo in the database
        List<Modulo> moduloList = moduloRepository.findAll();
        assertThat(moduloList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdModuloIsRequired() throws Exception {
        int databaseSizeBeforeTest = moduloRepository.findAll().size();
        // set the field null
        modulo.setIdModulo(null);

        // Create the Modulo, which fails.

        restModuloMockMvc.perform(post("/api/modulos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modulo)))
            .andExpect(status().isBadRequest());

        List<Modulo> moduloList = moduloRepository.findAll();
        assertThat(moduloList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = moduloRepository.findAll().size();
        // set the field null
        modulo.setNombre(null);

        // Create the Modulo, which fails.

        restModuloMockMvc.perform(post("/api/modulos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modulo)))
            .andExpect(status().isBadRequest());

        List<Modulo> moduloList = moduloRepository.findAll();
        assertThat(moduloList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllModulos() throws Exception {
        // Initialize the database
        moduloRepository.saveAndFlush(modulo);

        // Get all the moduloList
        restModuloMockMvc.perform(get("/api/modulos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(modulo.getId().intValue())))
            .andExpect(jsonPath("$.[*].idModulo").value(hasItem(DEFAULT_ID_MODULO)))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].estatus").value(hasItem(DEFAULT_ESTATUS.booleanValue())))
            .andExpect(jsonPath("$.[*].fechaCreacion").value(hasItem(DEFAULT_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].fechaModificacion").value(hasItem(DEFAULT_FECHA_MODIFICACION.toString())));
    }

    @Test
    @Transactional
    public void getModulo() throws Exception {
        // Initialize the database
        moduloRepository.saveAndFlush(modulo);

        // Get the modulo
        restModuloMockMvc.perform(get("/api/modulos/{id}", modulo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(modulo.getId().intValue()))
            .andExpect(jsonPath("$.idModulo").value(DEFAULT_ID_MODULO))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.estatus").value(DEFAULT_ESTATUS.booleanValue()))
            .andExpect(jsonPath("$.fechaCreacion").value(DEFAULT_FECHA_CREACION.toString()))
            .andExpect(jsonPath("$.fechaModificacion").value(DEFAULT_FECHA_MODIFICACION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingModulo() throws Exception {
        // Get the modulo
        restModuloMockMvc.perform(get("/api/modulos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateModulo() throws Exception {
        // Initialize the database
        moduloRepository.saveAndFlush(modulo);
        int databaseSizeBeforeUpdate = moduloRepository.findAll().size();

        // Update the modulo
        Modulo updatedModulo = moduloRepository.findOne(modulo.getId());
        // Disconnect from session so that the updates on updatedModulo are not directly saved in db
        em.detach(updatedModulo);
        updatedModulo
            .idModulo(UPDATED_ID_MODULO)
            .nombre(UPDATED_NOMBRE)
            .estatus(UPDATED_ESTATUS)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .fechaModificacion(UPDATED_FECHA_MODIFICACION);

        restModuloMockMvc.perform(put("/api/modulos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedModulo)))
            .andExpect(status().isOk());

        // Validate the Modulo in the database
        List<Modulo> moduloList = moduloRepository.findAll();
        assertThat(moduloList).hasSize(databaseSizeBeforeUpdate);
        Modulo testModulo = moduloList.get(moduloList.size() - 1);
        assertThat(testModulo.getIdModulo()).isEqualTo(UPDATED_ID_MODULO);
        assertThat(testModulo.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testModulo.isEstatus()).isEqualTo(UPDATED_ESTATUS);
        assertThat(testModulo.getFechaCreacion()).isEqualTo(UPDATED_FECHA_CREACION);
        assertThat(testModulo.getFechaModificacion()).isEqualTo(UPDATED_FECHA_MODIFICACION);
    }

    @Test
    @Transactional
    public void updateNonExistingModulo() throws Exception {
        int databaseSizeBeforeUpdate = moduloRepository.findAll().size();

        // Create the Modulo

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restModuloMockMvc.perform(put("/api/modulos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(modulo)))
            .andExpect(status().isCreated());

        // Validate the Modulo in the database
        List<Modulo> moduloList = moduloRepository.findAll();
        assertThat(moduloList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteModulo() throws Exception {
        // Initialize the database
        moduloRepository.saveAndFlush(modulo);
        int databaseSizeBeforeDelete = moduloRepository.findAll().size();

        // Get the modulo
        restModuloMockMvc.perform(delete("/api/modulos/{id}", modulo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Modulo> moduloList = moduloRepository.findAll();
        assertThat(moduloList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Modulo.class);
        Modulo modulo1 = new Modulo();
        modulo1.setId(1L);
        Modulo modulo2 = new Modulo();
        modulo2.setId(modulo1.getId());
        assertThat(modulo1).isEqualTo(modulo2);
        modulo2.setId(2L);
        assertThat(modulo1).isNotEqualTo(modulo2);
        modulo1.setId(null);
        assertThat(modulo1).isNotEqualTo(modulo2);
    }
}
