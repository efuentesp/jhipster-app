package com.softtek.jhipster.web.rest;

import com.softtek.jhipster.JhipsterAppApp;

import com.softtek.jhipster.domain.SubModulo;
import com.softtek.jhipster.repository.SubModuloRepository;
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
 * Test class for the SubModuloResource REST controller.
 *
 * @see SubModuloResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterAppApp.class)
public class SubModuloResourceIntTest {

    private static final Integer DEFAULT_ID_SUB_MODULO = 1;
    private static final Integer UPDATED_ID_SUB_MODULO = 2;

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
    private SubModuloRepository subModuloRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSubModuloMockMvc;

    private SubModulo subModulo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SubModuloResource subModuloResource = new SubModuloResource(subModuloRepository);
        this.restSubModuloMockMvc = MockMvcBuilders.standaloneSetup(subModuloResource)
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
    public static SubModulo createEntity(EntityManager em) {
        SubModulo subModulo = new SubModulo()
            .idSubModulo(DEFAULT_ID_SUB_MODULO)
            .idModulo(DEFAULT_ID_MODULO)
            .nombre(DEFAULT_NOMBRE)
            .estatus(DEFAULT_ESTATUS)
            .fechaCreacion(DEFAULT_FECHA_CREACION)
            .fechaModificacion(DEFAULT_FECHA_MODIFICACION);
        return subModulo;
    }

    @Before
    public void initTest() {
        subModulo = createEntity(em);
    }

    @Test
    @Transactional
    public void createSubModulo() throws Exception {
        int databaseSizeBeforeCreate = subModuloRepository.findAll().size();

        // Create the SubModulo
        restSubModuloMockMvc.perform(post("/api/sub-modulos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(subModulo)))
            .andExpect(status().isCreated());

        // Validate the SubModulo in the database
        List<SubModulo> subModuloList = subModuloRepository.findAll();
        assertThat(subModuloList).hasSize(databaseSizeBeforeCreate + 1);
        SubModulo testSubModulo = subModuloList.get(subModuloList.size() - 1);
        assertThat(testSubModulo.getIdSubModulo()).isEqualTo(DEFAULT_ID_SUB_MODULO);
        assertThat(testSubModulo.getIdModulo()).isEqualTo(DEFAULT_ID_MODULO);
        assertThat(testSubModulo.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testSubModulo.isEstatus()).isEqualTo(DEFAULT_ESTATUS);
        assertThat(testSubModulo.getFechaCreacion()).isEqualTo(DEFAULT_FECHA_CREACION);
        assertThat(testSubModulo.getFechaModificacion()).isEqualTo(DEFAULT_FECHA_MODIFICACION);
    }

    @Test
    @Transactional
    public void createSubModuloWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = subModuloRepository.findAll().size();

        // Create the SubModulo with an existing ID
        subModulo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSubModuloMockMvc.perform(post("/api/sub-modulos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(subModulo)))
            .andExpect(status().isBadRequest());

        // Validate the SubModulo in the database
        List<SubModulo> subModuloList = subModuloRepository.findAll();
        assertThat(subModuloList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdSubModuloIsRequired() throws Exception {
        int databaseSizeBeforeTest = subModuloRepository.findAll().size();
        // set the field null
        subModulo.setIdSubModulo(null);

        // Create the SubModulo, which fails.

        restSubModuloMockMvc.perform(post("/api/sub-modulos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(subModulo)))
            .andExpect(status().isBadRequest());

        List<SubModulo> subModuloList = subModuloRepository.findAll();
        assertThat(subModuloList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdModuloIsRequired() throws Exception {
        int databaseSizeBeforeTest = subModuloRepository.findAll().size();
        // set the field null
        subModulo.setIdModulo(null);

        // Create the SubModulo, which fails.

        restSubModuloMockMvc.perform(post("/api/sub-modulos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(subModulo)))
            .andExpect(status().isBadRequest());

        List<SubModulo> subModuloList = subModuloRepository.findAll();
        assertThat(subModuloList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = subModuloRepository.findAll().size();
        // set the field null
        subModulo.setNombre(null);

        // Create the SubModulo, which fails.

        restSubModuloMockMvc.perform(post("/api/sub-modulos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(subModulo)))
            .andExpect(status().isBadRequest());

        List<SubModulo> subModuloList = subModuloRepository.findAll();
        assertThat(subModuloList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSubModulos() throws Exception {
        // Initialize the database
        subModuloRepository.saveAndFlush(subModulo);

        // Get all the subModuloList
        restSubModuloMockMvc.perform(get("/api/sub-modulos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subModulo.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubModulo").value(hasItem(DEFAULT_ID_SUB_MODULO)))
            .andExpect(jsonPath("$.[*].idModulo").value(hasItem(DEFAULT_ID_MODULO)))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].estatus").value(hasItem(DEFAULT_ESTATUS.booleanValue())))
            .andExpect(jsonPath("$.[*].fechaCreacion").value(hasItem(DEFAULT_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].fechaModificacion").value(hasItem(DEFAULT_FECHA_MODIFICACION.toString())));
    }

    @Test
    @Transactional
    public void getSubModulo() throws Exception {
        // Initialize the database
        subModuloRepository.saveAndFlush(subModulo);

        // Get the subModulo
        restSubModuloMockMvc.perform(get("/api/sub-modulos/{id}", subModulo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(subModulo.getId().intValue()))
            .andExpect(jsonPath("$.idSubModulo").value(DEFAULT_ID_SUB_MODULO))
            .andExpect(jsonPath("$.idModulo").value(DEFAULT_ID_MODULO))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.estatus").value(DEFAULT_ESTATUS.booleanValue()))
            .andExpect(jsonPath("$.fechaCreacion").value(DEFAULT_FECHA_CREACION.toString()))
            .andExpect(jsonPath("$.fechaModificacion").value(DEFAULT_FECHA_MODIFICACION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSubModulo() throws Exception {
        // Get the subModulo
        restSubModuloMockMvc.perform(get("/api/sub-modulos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSubModulo() throws Exception {
        // Initialize the database
        subModuloRepository.saveAndFlush(subModulo);
        int databaseSizeBeforeUpdate = subModuloRepository.findAll().size();

        // Update the subModulo
        SubModulo updatedSubModulo = subModuloRepository.findOne(subModulo.getId());
        // Disconnect from session so that the updates on updatedSubModulo are not directly saved in db
        em.detach(updatedSubModulo);
        updatedSubModulo
            .idSubModulo(UPDATED_ID_SUB_MODULO)
            .idModulo(UPDATED_ID_MODULO)
            .nombre(UPDATED_NOMBRE)
            .estatus(UPDATED_ESTATUS)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .fechaModificacion(UPDATED_FECHA_MODIFICACION);

        restSubModuloMockMvc.perform(put("/api/sub-modulos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSubModulo)))
            .andExpect(status().isOk());

        // Validate the SubModulo in the database
        List<SubModulo> subModuloList = subModuloRepository.findAll();
        assertThat(subModuloList).hasSize(databaseSizeBeforeUpdate);
        SubModulo testSubModulo = subModuloList.get(subModuloList.size() - 1);
        assertThat(testSubModulo.getIdSubModulo()).isEqualTo(UPDATED_ID_SUB_MODULO);
        assertThat(testSubModulo.getIdModulo()).isEqualTo(UPDATED_ID_MODULO);
        assertThat(testSubModulo.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testSubModulo.isEstatus()).isEqualTo(UPDATED_ESTATUS);
        assertThat(testSubModulo.getFechaCreacion()).isEqualTo(UPDATED_FECHA_CREACION);
        assertThat(testSubModulo.getFechaModificacion()).isEqualTo(UPDATED_FECHA_MODIFICACION);
    }

    @Test
    @Transactional
    public void updateNonExistingSubModulo() throws Exception {
        int databaseSizeBeforeUpdate = subModuloRepository.findAll().size();

        // Create the SubModulo

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSubModuloMockMvc.perform(put("/api/sub-modulos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(subModulo)))
            .andExpect(status().isCreated());

        // Validate the SubModulo in the database
        List<SubModulo> subModuloList = subModuloRepository.findAll();
        assertThat(subModuloList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSubModulo() throws Exception {
        // Initialize the database
        subModuloRepository.saveAndFlush(subModulo);
        int databaseSizeBeforeDelete = subModuloRepository.findAll().size();

        // Get the subModulo
        restSubModuloMockMvc.perform(delete("/api/sub-modulos/{id}", subModulo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SubModulo> subModuloList = subModuloRepository.findAll();
        assertThat(subModuloList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubModulo.class);
        SubModulo subModulo1 = new SubModulo();
        subModulo1.setId(1L);
        SubModulo subModulo2 = new SubModulo();
        subModulo2.setId(subModulo1.getId());
        assertThat(subModulo1).isEqualTo(subModulo2);
        subModulo2.setId(2L);
        assertThat(subModulo1).isNotEqualTo(subModulo2);
        subModulo1.setId(null);
        assertThat(subModulo1).isNotEqualTo(subModulo2);
    }
}
