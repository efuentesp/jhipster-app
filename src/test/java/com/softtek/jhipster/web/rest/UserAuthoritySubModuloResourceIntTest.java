package com.softtek.jhipster.web.rest;

import com.softtek.jhipster.JhipsterAppApp;

import com.softtek.jhipster.domain.UserAuthoritySubModulo;
import com.softtek.jhipster.repository.UserAuthoritySubModuloRepository;
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
 * Test class for the UserAuthoritySubModuloResource REST controller.
 *
 * @see UserAuthoritySubModuloResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterAppApp.class)
public class UserAuthoritySubModuloResourceIntTest {

    private static final Integer DEFAULT_ID_SUB_MODULO = 1;
    private static final Integer UPDATED_ID_SUB_MODULO = 2;

    private static final Integer DEFAULT_ID_USER_AUTHORITY = 1;
    private static final Integer UPDATED_ID_USER_AUTHORITY = 2;

    private static final Boolean DEFAULT_ESTATUS = false;
    private static final Boolean UPDATED_ESTATUS = true;

    private static final LocalDate DEFAULT_FECHA_CREACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_CREACION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FECHA_MODIFICACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_MODIFICACION = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private UserAuthoritySubModuloRepository userAuthoritySubModuloRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUserAuthoritySubModuloMockMvc;

    private UserAuthoritySubModulo userAuthoritySubModulo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserAuthoritySubModuloResource userAuthoritySubModuloResource = new UserAuthoritySubModuloResource(userAuthoritySubModuloRepository);
        this.restUserAuthoritySubModuloMockMvc = MockMvcBuilders.standaloneSetup(userAuthoritySubModuloResource)
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
    public static UserAuthoritySubModulo createEntity(EntityManager em) {
        UserAuthoritySubModulo userAuthoritySubModulo = new UserAuthoritySubModulo()
            .idSubModulo(DEFAULT_ID_SUB_MODULO)
            .idUserAuthority(DEFAULT_ID_USER_AUTHORITY)
            .estatus(DEFAULT_ESTATUS)
            .fechaCreacion(DEFAULT_FECHA_CREACION)
            .fechaModificacion(DEFAULT_FECHA_MODIFICACION);
        return userAuthoritySubModulo;
    }

    @Before
    public void initTest() {
        userAuthoritySubModulo = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserAuthoritySubModulo() throws Exception {
        int databaseSizeBeforeCreate = userAuthoritySubModuloRepository.findAll().size();

        // Create the UserAuthoritySubModulo
        restUserAuthoritySubModuloMockMvc.perform(post("/api/user-authority-sub-modulos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userAuthoritySubModulo)))
            .andExpect(status().isCreated());

        // Validate the UserAuthoritySubModulo in the database
        List<UserAuthoritySubModulo> userAuthoritySubModuloList = userAuthoritySubModuloRepository.findAll();
        assertThat(userAuthoritySubModuloList).hasSize(databaseSizeBeforeCreate + 1);
        UserAuthoritySubModulo testUserAuthoritySubModulo = userAuthoritySubModuloList.get(userAuthoritySubModuloList.size() - 1);
        assertThat(testUserAuthoritySubModulo.getIdSubModulo()).isEqualTo(DEFAULT_ID_SUB_MODULO);
        assertThat(testUserAuthoritySubModulo.getIdUserAuthority()).isEqualTo(DEFAULT_ID_USER_AUTHORITY);
        assertThat(testUserAuthoritySubModulo.isEstatus()).isEqualTo(DEFAULT_ESTATUS);
        assertThat(testUserAuthoritySubModulo.getFechaCreacion()).isEqualTo(DEFAULT_FECHA_CREACION);
        assertThat(testUserAuthoritySubModulo.getFechaModificacion()).isEqualTo(DEFAULT_FECHA_MODIFICACION);
    }

    @Test
    @Transactional
    public void createUserAuthoritySubModuloWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userAuthoritySubModuloRepository.findAll().size();

        // Create the UserAuthoritySubModulo with an existing ID
        userAuthoritySubModulo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserAuthoritySubModuloMockMvc.perform(post("/api/user-authority-sub-modulos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userAuthoritySubModulo)))
            .andExpect(status().isBadRequest());

        // Validate the UserAuthoritySubModulo in the database
        List<UserAuthoritySubModulo> userAuthoritySubModuloList = userAuthoritySubModuloRepository.findAll();
        assertThat(userAuthoritySubModuloList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdSubModuloIsRequired() throws Exception {
        int databaseSizeBeforeTest = userAuthoritySubModuloRepository.findAll().size();
        // set the field null
        userAuthoritySubModulo.setIdSubModulo(null);

        // Create the UserAuthoritySubModulo, which fails.

        restUserAuthoritySubModuloMockMvc.perform(post("/api/user-authority-sub-modulos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userAuthoritySubModulo)))
            .andExpect(status().isBadRequest());

        List<UserAuthoritySubModulo> userAuthoritySubModuloList = userAuthoritySubModuloRepository.findAll();
        assertThat(userAuthoritySubModuloList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdUserAuthorityIsRequired() throws Exception {
        int databaseSizeBeforeTest = userAuthoritySubModuloRepository.findAll().size();
        // set the field null
        userAuthoritySubModulo.setIdUserAuthority(null);

        // Create the UserAuthoritySubModulo, which fails.

        restUserAuthoritySubModuloMockMvc.perform(post("/api/user-authority-sub-modulos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userAuthoritySubModulo)))
            .andExpect(status().isBadRequest());

        List<UserAuthoritySubModulo> userAuthoritySubModuloList = userAuthoritySubModuloRepository.findAll();
        assertThat(userAuthoritySubModuloList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUserAuthoritySubModulos() throws Exception {
        // Initialize the database
        userAuthoritySubModuloRepository.saveAndFlush(userAuthoritySubModulo);

        // Get all the userAuthoritySubModuloList
        restUserAuthoritySubModuloMockMvc.perform(get("/api/user-authority-sub-modulos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userAuthoritySubModulo.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubModulo").value(hasItem(DEFAULT_ID_SUB_MODULO)))
            .andExpect(jsonPath("$.[*].idUserAuthority").value(hasItem(DEFAULT_ID_USER_AUTHORITY)))
            .andExpect(jsonPath("$.[*].estatus").value(hasItem(DEFAULT_ESTATUS.booleanValue())))
            .andExpect(jsonPath("$.[*].fechaCreacion").value(hasItem(DEFAULT_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].fechaModificacion").value(hasItem(DEFAULT_FECHA_MODIFICACION.toString())));
    }

    @Test
    @Transactional
    public void getUserAuthoritySubModulo() throws Exception {
        // Initialize the database
        userAuthoritySubModuloRepository.saveAndFlush(userAuthoritySubModulo);

        // Get the userAuthoritySubModulo
        restUserAuthoritySubModuloMockMvc.perform(get("/api/user-authority-sub-modulos/{id}", userAuthoritySubModulo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userAuthoritySubModulo.getId().intValue()))
            .andExpect(jsonPath("$.idSubModulo").value(DEFAULT_ID_SUB_MODULO))
            .andExpect(jsonPath("$.idUserAuthority").value(DEFAULT_ID_USER_AUTHORITY))
            .andExpect(jsonPath("$.estatus").value(DEFAULT_ESTATUS.booleanValue()))
            .andExpect(jsonPath("$.fechaCreacion").value(DEFAULT_FECHA_CREACION.toString()))
            .andExpect(jsonPath("$.fechaModificacion").value(DEFAULT_FECHA_MODIFICACION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUserAuthoritySubModulo() throws Exception {
        // Get the userAuthoritySubModulo
        restUserAuthoritySubModuloMockMvc.perform(get("/api/user-authority-sub-modulos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserAuthoritySubModulo() throws Exception {
        // Initialize the database
        userAuthoritySubModuloRepository.saveAndFlush(userAuthoritySubModulo);
        int databaseSizeBeforeUpdate = userAuthoritySubModuloRepository.findAll().size();

        // Update the userAuthoritySubModulo
        UserAuthoritySubModulo updatedUserAuthoritySubModulo = userAuthoritySubModuloRepository.findOne(userAuthoritySubModulo.getId());
        // Disconnect from session so that the updates on updatedUserAuthoritySubModulo are not directly saved in db
        em.detach(updatedUserAuthoritySubModulo);
        updatedUserAuthoritySubModulo
            .idSubModulo(UPDATED_ID_SUB_MODULO)
            .idUserAuthority(UPDATED_ID_USER_AUTHORITY)
            .estatus(UPDATED_ESTATUS)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .fechaModificacion(UPDATED_FECHA_MODIFICACION);

        restUserAuthoritySubModuloMockMvc.perform(put("/api/user-authority-sub-modulos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserAuthoritySubModulo)))
            .andExpect(status().isOk());

        // Validate the UserAuthoritySubModulo in the database
        List<UserAuthoritySubModulo> userAuthoritySubModuloList = userAuthoritySubModuloRepository.findAll();
        assertThat(userAuthoritySubModuloList).hasSize(databaseSizeBeforeUpdate);
        UserAuthoritySubModulo testUserAuthoritySubModulo = userAuthoritySubModuloList.get(userAuthoritySubModuloList.size() - 1);
        assertThat(testUserAuthoritySubModulo.getIdSubModulo()).isEqualTo(UPDATED_ID_SUB_MODULO);
        assertThat(testUserAuthoritySubModulo.getIdUserAuthority()).isEqualTo(UPDATED_ID_USER_AUTHORITY);
        assertThat(testUserAuthoritySubModulo.isEstatus()).isEqualTo(UPDATED_ESTATUS);
        assertThat(testUserAuthoritySubModulo.getFechaCreacion()).isEqualTo(UPDATED_FECHA_CREACION);
        assertThat(testUserAuthoritySubModulo.getFechaModificacion()).isEqualTo(UPDATED_FECHA_MODIFICACION);
    }

    @Test
    @Transactional
    public void updateNonExistingUserAuthoritySubModulo() throws Exception {
        int databaseSizeBeforeUpdate = userAuthoritySubModuloRepository.findAll().size();

        // Create the UserAuthoritySubModulo

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUserAuthoritySubModuloMockMvc.perform(put("/api/user-authority-sub-modulos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userAuthoritySubModulo)))
            .andExpect(status().isCreated());

        // Validate the UserAuthoritySubModulo in the database
        List<UserAuthoritySubModulo> userAuthoritySubModuloList = userAuthoritySubModuloRepository.findAll();
        assertThat(userAuthoritySubModuloList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUserAuthoritySubModulo() throws Exception {
        // Initialize the database
        userAuthoritySubModuloRepository.saveAndFlush(userAuthoritySubModulo);
        int databaseSizeBeforeDelete = userAuthoritySubModuloRepository.findAll().size();

        // Get the userAuthoritySubModulo
        restUserAuthoritySubModuloMockMvc.perform(delete("/api/user-authority-sub-modulos/{id}", userAuthoritySubModulo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserAuthoritySubModulo> userAuthoritySubModuloList = userAuthoritySubModuloRepository.findAll();
        assertThat(userAuthoritySubModuloList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserAuthoritySubModulo.class);
        UserAuthoritySubModulo userAuthoritySubModulo1 = new UserAuthoritySubModulo();
        userAuthoritySubModulo1.setId(1L);
        UserAuthoritySubModulo userAuthoritySubModulo2 = new UserAuthoritySubModulo();
        userAuthoritySubModulo2.setId(userAuthoritySubModulo1.getId());
        assertThat(userAuthoritySubModulo1).isEqualTo(userAuthoritySubModulo2);
        userAuthoritySubModulo2.setId(2L);
        assertThat(userAuthoritySubModulo1).isNotEqualTo(userAuthoritySubModulo2);
        userAuthoritySubModulo1.setId(null);
        assertThat(userAuthoritySubModulo1).isNotEqualTo(userAuthoritySubModulo2);
    }
}
