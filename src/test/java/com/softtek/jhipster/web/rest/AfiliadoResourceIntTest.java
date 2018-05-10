package com.softtek.jhipster.web.rest;

import com.softtek.jhipster.JhipsterAppApp;

import com.softtek.jhipster.domain.Afiliado;
import com.softtek.jhipster.repository.AfiliadoRepository;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.softtek.jhipster.web.rest.TestUtil.sameInstant;
import static com.softtek.jhipster.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AfiliadoResource REST controller.
 *
 * @see AfiliadoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterAppApp.class)
public class AfiliadoResourceIntTest {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDOPATERNO = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDOPATERNO = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDOMATERNO = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDOMATERNO = "BBBBBBBBBB";

    private static final Integer DEFAULT_NSS = 1;
    private static final Integer UPDATED_NSS = 2;

    private static final Long DEFAULT_SALARIO = 1L;
    private static final Long UPDATED_SALARIO = 2L;

    private static final Float DEFAULT_PESO = 1F;
    private static final Float UPDATED_PESO = 2F;

    private static final Double DEFAULT_DATO_DOUBLE = 1D;
    private static final Double UPDATED_DATO_DOUBLE = 2D;

    private static final BigDecimal DEFAULT_DATO_BIG_DECIMAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_DATO_BIG_DECIMAL = new BigDecimal(2);

    private static final LocalDate DEFAULT_FECHA_AFILIACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_AFILIACION = LocalDate.now(ZoneId.systemDefault());

    private static final Instant DEFAULT_DATO_INSTANT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATO_INSTANT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final ZonedDateTime DEFAULT_DATO_ZONE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATO_ZONE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_ACTIVO = false;
    private static final Boolean UPDATED_ACTIVO = true;

    private static final byte[] DEFAULT_FOTOGRAFIA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTOGRAFIA = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_FOTOGRAFIA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTOGRAFIA_CONTENT_TYPE = "image/png";

    @Autowired
    private AfiliadoRepository afiliadoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAfiliadoMockMvc;

    private Afiliado afiliado;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AfiliadoResource afiliadoResource = new AfiliadoResource(afiliadoRepository);
        this.restAfiliadoMockMvc = MockMvcBuilders.standaloneSetup(afiliadoResource)
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
    public static Afiliado createEntity(EntityManager em) {
        Afiliado afiliado = new Afiliado()
            .nombre(DEFAULT_NOMBRE)
            .apellidopaterno(DEFAULT_APELLIDOPATERNO)
            .apellidomaterno(DEFAULT_APELLIDOMATERNO)
            .nss(DEFAULT_NSS)
            .salario(DEFAULT_SALARIO)
            .peso(DEFAULT_PESO)
            .datoDouble(DEFAULT_DATO_DOUBLE)
            .datoBigDecimal(DEFAULT_DATO_BIG_DECIMAL)
            .fechaAfiliacion(DEFAULT_FECHA_AFILIACION)
            .datoInstant(DEFAULT_DATO_INSTANT)
            .datoZone(DEFAULT_DATO_ZONE)
            .activo(DEFAULT_ACTIVO)
            .fotografia(DEFAULT_FOTOGRAFIA)
            .fotografiaContentType(DEFAULT_FOTOGRAFIA_CONTENT_TYPE);
        return afiliado;
    }

    @Before
    public void initTest() {
        afiliado = createEntity(em);
    }

    @Test
    @Transactional
    public void createAfiliado() throws Exception {
        int databaseSizeBeforeCreate = afiliadoRepository.findAll().size();

        // Create the Afiliado
        restAfiliadoMockMvc.perform(post("/api/afiliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(afiliado)))
            .andExpect(status().isCreated());

        // Validate the Afiliado in the database
        List<Afiliado> afiliadoList = afiliadoRepository.findAll();
        assertThat(afiliadoList).hasSize(databaseSizeBeforeCreate + 1);
        Afiliado testAfiliado = afiliadoList.get(afiliadoList.size() - 1);
        assertThat(testAfiliado.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testAfiliado.getApellidopaterno()).isEqualTo(DEFAULT_APELLIDOPATERNO);
        assertThat(testAfiliado.getApellidomaterno()).isEqualTo(DEFAULT_APELLIDOMATERNO);
        assertThat(testAfiliado.getNss()).isEqualTo(DEFAULT_NSS);
        assertThat(testAfiliado.getSalario()).isEqualTo(DEFAULT_SALARIO);
        assertThat(testAfiliado.getPeso()).isEqualTo(DEFAULT_PESO);
        assertThat(testAfiliado.getDatoDouble()).isEqualTo(DEFAULT_DATO_DOUBLE);
        assertThat(testAfiliado.getDatoBigDecimal()).isEqualTo(DEFAULT_DATO_BIG_DECIMAL);
        assertThat(testAfiliado.getFechaAfiliacion()).isEqualTo(DEFAULT_FECHA_AFILIACION);
        assertThat(testAfiliado.getDatoInstant()).isEqualTo(DEFAULT_DATO_INSTANT);
        assertThat(testAfiliado.getDatoZone()).isEqualTo(DEFAULT_DATO_ZONE);
        assertThat(testAfiliado.isActivo()).isEqualTo(DEFAULT_ACTIVO);
        assertThat(testAfiliado.getFotografia()).isEqualTo(DEFAULT_FOTOGRAFIA);
        assertThat(testAfiliado.getFotografiaContentType()).isEqualTo(DEFAULT_FOTOGRAFIA_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createAfiliadoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = afiliadoRepository.findAll().size();

        // Create the Afiliado with an existing ID
        afiliado.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAfiliadoMockMvc.perform(post("/api/afiliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(afiliado)))
            .andExpect(status().isBadRequest());

        // Validate the Afiliado in the database
        List<Afiliado> afiliadoList = afiliadoRepository.findAll();
        assertThat(afiliadoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = afiliadoRepository.findAll().size();
        // set the field null
        afiliado.setNombre(null);

        // Create the Afiliado, which fails.

        restAfiliadoMockMvc.perform(post("/api/afiliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(afiliado)))
            .andExpect(status().isBadRequest());

        List<Afiliado> afiliadoList = afiliadoRepository.findAll();
        assertThat(afiliadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkApellidopaternoIsRequired() throws Exception {
        int databaseSizeBeforeTest = afiliadoRepository.findAll().size();
        // set the field null
        afiliado.setApellidopaterno(null);

        // Create the Afiliado, which fails.

        restAfiliadoMockMvc.perform(post("/api/afiliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(afiliado)))
            .andExpect(status().isBadRequest());

        List<Afiliado> afiliadoList = afiliadoRepository.findAll();
        assertThat(afiliadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNssIsRequired() throws Exception {
        int databaseSizeBeforeTest = afiliadoRepository.findAll().size();
        // set the field null
        afiliado.setNss(null);

        // Create the Afiliado, which fails.

        restAfiliadoMockMvc.perform(post("/api/afiliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(afiliado)))
            .andExpect(status().isBadRequest());

        List<Afiliado> afiliadoList = afiliadoRepository.findAll();
        assertThat(afiliadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAfiliados() throws Exception {
        // Initialize the database
        afiliadoRepository.saveAndFlush(afiliado);

        // Get all the afiliadoList
        restAfiliadoMockMvc.perform(get("/api/afiliados?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(afiliado.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].apellidopaterno").value(hasItem(DEFAULT_APELLIDOPATERNO.toString())))
            .andExpect(jsonPath("$.[*].apellidomaterno").value(hasItem(DEFAULT_APELLIDOMATERNO.toString())))
            .andExpect(jsonPath("$.[*].nss").value(hasItem(DEFAULT_NSS)))
            .andExpect(jsonPath("$.[*].salario").value(hasItem(DEFAULT_SALARIO.intValue())))
            .andExpect(jsonPath("$.[*].peso").value(hasItem(DEFAULT_PESO.doubleValue())))
            .andExpect(jsonPath("$.[*].datoDouble").value(hasItem(DEFAULT_DATO_DOUBLE.doubleValue())))
            .andExpect(jsonPath("$.[*].datoBigDecimal").value(hasItem(DEFAULT_DATO_BIG_DECIMAL.intValue())))
            .andExpect(jsonPath("$.[*].fechaAfiliacion").value(hasItem(DEFAULT_FECHA_AFILIACION.toString())))
            .andExpect(jsonPath("$.[*].datoInstant").value(hasItem(DEFAULT_DATO_INSTANT.toString())))
            .andExpect(jsonPath("$.[*].datoZone").value(hasItem(sameInstant(DEFAULT_DATO_ZONE))))
            .andExpect(jsonPath("$.[*].activo").value(hasItem(DEFAULT_ACTIVO.booleanValue())))
            .andExpect(jsonPath("$.[*].fotografiaContentType").value(hasItem(DEFAULT_FOTOGRAFIA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].fotografia").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTOGRAFIA))));
    }

    @Test
    @Transactional
    public void getAfiliado() throws Exception {
        // Initialize the database
        afiliadoRepository.saveAndFlush(afiliado);

        // Get the afiliado
        restAfiliadoMockMvc.perform(get("/api/afiliados/{id}", afiliado.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(afiliado.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.apellidopaterno").value(DEFAULT_APELLIDOPATERNO.toString()))
            .andExpect(jsonPath("$.apellidomaterno").value(DEFAULT_APELLIDOMATERNO.toString()))
            .andExpect(jsonPath("$.nss").value(DEFAULT_NSS))
            .andExpect(jsonPath("$.salario").value(DEFAULT_SALARIO.intValue()))
            .andExpect(jsonPath("$.peso").value(DEFAULT_PESO.doubleValue()))
            .andExpect(jsonPath("$.datoDouble").value(DEFAULT_DATO_DOUBLE.doubleValue()))
            .andExpect(jsonPath("$.datoBigDecimal").value(DEFAULT_DATO_BIG_DECIMAL.intValue()))
            .andExpect(jsonPath("$.fechaAfiliacion").value(DEFAULT_FECHA_AFILIACION.toString()))
            .andExpect(jsonPath("$.datoInstant").value(DEFAULT_DATO_INSTANT.toString()))
            .andExpect(jsonPath("$.datoZone").value(sameInstant(DEFAULT_DATO_ZONE)))
            .andExpect(jsonPath("$.activo").value(DEFAULT_ACTIVO.booleanValue()))
            .andExpect(jsonPath("$.fotografiaContentType").value(DEFAULT_FOTOGRAFIA_CONTENT_TYPE))
            .andExpect(jsonPath("$.fotografia").value(Base64Utils.encodeToString(DEFAULT_FOTOGRAFIA)));
    }

    @Test
    @Transactional
    public void getNonExistingAfiliado() throws Exception {
        // Get the afiliado
        restAfiliadoMockMvc.perform(get("/api/afiliados/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAfiliado() throws Exception {
        // Initialize the database
        afiliadoRepository.saveAndFlush(afiliado);
        int databaseSizeBeforeUpdate = afiliadoRepository.findAll().size();

        // Update the afiliado
        Afiliado updatedAfiliado = afiliadoRepository.findOne(afiliado.getId());
        // Disconnect from session so that the updates on updatedAfiliado are not directly saved in db
        em.detach(updatedAfiliado);
        updatedAfiliado
            .nombre(UPDATED_NOMBRE)
            .apellidopaterno(UPDATED_APELLIDOPATERNO)
            .apellidomaterno(UPDATED_APELLIDOMATERNO)
            .nss(UPDATED_NSS)
            .salario(UPDATED_SALARIO)
            .peso(UPDATED_PESO)
            .datoDouble(UPDATED_DATO_DOUBLE)
            .datoBigDecimal(UPDATED_DATO_BIG_DECIMAL)
            .fechaAfiliacion(UPDATED_FECHA_AFILIACION)
            .datoInstant(UPDATED_DATO_INSTANT)
            .datoZone(UPDATED_DATO_ZONE)
            .activo(UPDATED_ACTIVO)
            .fotografia(UPDATED_FOTOGRAFIA)
            .fotografiaContentType(UPDATED_FOTOGRAFIA_CONTENT_TYPE);

        restAfiliadoMockMvc.perform(put("/api/afiliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAfiliado)))
            .andExpect(status().isOk());

        // Validate the Afiliado in the database
        List<Afiliado> afiliadoList = afiliadoRepository.findAll();
        assertThat(afiliadoList).hasSize(databaseSizeBeforeUpdate);
        Afiliado testAfiliado = afiliadoList.get(afiliadoList.size() - 1);
        assertThat(testAfiliado.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testAfiliado.getApellidopaterno()).isEqualTo(UPDATED_APELLIDOPATERNO);
        assertThat(testAfiliado.getApellidomaterno()).isEqualTo(UPDATED_APELLIDOMATERNO);
        assertThat(testAfiliado.getNss()).isEqualTo(UPDATED_NSS);
        assertThat(testAfiliado.getSalario()).isEqualTo(UPDATED_SALARIO);
        assertThat(testAfiliado.getPeso()).isEqualTo(UPDATED_PESO);
        assertThat(testAfiliado.getDatoDouble()).isEqualTo(UPDATED_DATO_DOUBLE);
        assertThat(testAfiliado.getDatoBigDecimal()).isEqualTo(UPDATED_DATO_BIG_DECIMAL);
        assertThat(testAfiliado.getFechaAfiliacion()).isEqualTo(UPDATED_FECHA_AFILIACION);
        assertThat(testAfiliado.getDatoInstant()).isEqualTo(UPDATED_DATO_INSTANT);
        assertThat(testAfiliado.getDatoZone()).isEqualTo(UPDATED_DATO_ZONE);
        assertThat(testAfiliado.isActivo()).isEqualTo(UPDATED_ACTIVO);
        assertThat(testAfiliado.getFotografia()).isEqualTo(UPDATED_FOTOGRAFIA);
        assertThat(testAfiliado.getFotografiaContentType()).isEqualTo(UPDATED_FOTOGRAFIA_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingAfiliado() throws Exception {
        int databaseSizeBeforeUpdate = afiliadoRepository.findAll().size();

        // Create the Afiliado

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAfiliadoMockMvc.perform(put("/api/afiliados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(afiliado)))
            .andExpect(status().isCreated());

        // Validate the Afiliado in the database
        List<Afiliado> afiliadoList = afiliadoRepository.findAll();
        assertThat(afiliadoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAfiliado() throws Exception {
        // Initialize the database
        afiliadoRepository.saveAndFlush(afiliado);
        int databaseSizeBeforeDelete = afiliadoRepository.findAll().size();

        // Get the afiliado
        restAfiliadoMockMvc.perform(delete("/api/afiliados/{id}", afiliado.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Afiliado> afiliadoList = afiliadoRepository.findAll();
        assertThat(afiliadoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Afiliado.class);
        Afiliado afiliado1 = new Afiliado();
        afiliado1.setId(1L);
        Afiliado afiliado2 = new Afiliado();
        afiliado2.setId(afiliado1.getId());
        assertThat(afiliado1).isEqualTo(afiliado2);
        afiliado2.setId(2L);
        assertThat(afiliado1).isNotEqualTo(afiliado2);
        afiliado1.setId(null);
        assertThat(afiliado1).isNotEqualTo(afiliado2);
    }
}
