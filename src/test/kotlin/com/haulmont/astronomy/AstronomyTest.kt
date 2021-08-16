package com.haulmont.astronomy

import com.haulmont.astronomy.model.basemodel.BaseEntity
import com.haulmont.astronomy.model.basemodel.tableName
import com.haulmont.astronomy.model.Coordinates
import com.haulmont.astronomy.model.CustomerGrade
import com.haulmont.astronomy.model.*
import com.haulmont.astronomy.repo.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ActiveProfiles
import java.io.FileDescriptor
import java.math.BigDecimal
import kotlin.reflect.KClass

@SpringBootTest
@ActiveProfiles("test")
class AstronomyTest(
    @Autowired val atmosphereRepository: AtmosphereRepository,
    @Autowired val atmosphericGasRepository: AtmosphericGasRepository,
    @Autowired val carrierRepository: CarrierRepository,
    @Autowired val companyRepository: CompanyRepository,
    @Autowired val discountsRepository: DiscountsRepository,
    @Autowired val gasRepository: GasRepository,
    @Autowired val individualRepository: IndividualRepository,
    @Autowired val moonRepository: MoonRepository,
    @Autowired val planetRepository: PlanetRepository,
    @Autowired val spaceportRepository: SpaceportRepository,
    @Autowired val waybillRepository: WaybillRepository,
    @Autowired val waybillItemRepository: WaybillItemRepository,
    @Autowired val jdbcTemplate: JdbcTemplate
) {

    @BeforeEach
    internal fun setUp() {
        val oxygen = Gas().apply {
            name = "Oxygen"
        }
        val nitrogen = Gas().apply {
            name = "Nitrogen"
        }
        gasRepository.saveAll(mutableListOf(oxygen, nitrogen))

        val atmosphere = Atmosphere().apply {
            description = "Atmosphere in Test"
            pressure = 100.0
        }
        atmosphereRepository.save(atmosphere)

        val oxygenGas = AtmosphericGas().apply {
            volume = 4.0
            gas = oxygen
            this.atmosphere = atmosphere
        }
        val nitrogenGas = AtmosphericGas().apply {
            volume = 2.0
            gas = nitrogen
            this.atmosphere = atmosphere
        }
        atmosphericGasRepository.saveAll(mutableListOf(oxygenGas, nitrogenGas))


        val planet = Planet().also {
            it.name = "Planet"
            it.mass = 100.0
            it.picture = FileDescriptor.`in`
            it.semiMajorAxis = 2.0
            it.orbitalPeriod = 4.0
            it.rotationPeriod = 6.0
            it.atmosphere = atmosphere
            it.rings = true
        }
        planetRepository.save(planet)

        val moon = Moon().also {
            it.name = "Moon"
            it.mass = 65.0
            it.picture = FileDescriptor.out
            it.planet = planet
            it.atmosphere = atmosphere
        }
        moonRepository.save(moon)

        val discounts = Discounts().apply {
            value = BigDecimal.valueOf(5)
            grade = CustomerGrade.HIGH
        }
        discountsRepository.save(discounts)

        val individual = Individual().apply {
            name = "Ivan"
            email = "ivanov@gmail.com"
            grade = CustomerGrade.MIDDLE
            firstName = name
            lastName = "Ivanov"
        }
        individualRepository.save(individual)

        val company = Company().apply {
            name = "Haulmont"
            email = "haulmont@gmail.com"
            grade = CustomerGrade.PREMIUM
            registrationId = "123456"
            companyType = "Type"
        }
        companyRepository.save(company)

        val waybillItem = WaybillItem().apply {
            number = 1
            name = "item"
            weight = 2.5
            dimensions?.apply {
                length = 1.0
                width = 2.0
                height = 3.0
            }
        }
        waybillItemRepository.save(waybillItem)

    }

    @AfterEach
    internal fun tearDown() {
        jdbcTemplate.also {
            it.deleteFrom(AtmosphericGas::class)
            it.deleteFrom(Moon::class)
            it.deleteFrom(Planet::class)
            it.deleteFrom(Atmosphere::class)
            it.deleteFrom(Carrier::class)
            it.deleteFrom(Company::class)
            it.deleteFrom(Discounts::class)
            it.deleteFrom(Gas::class)
            it.deleteFrom(Individual::class)
            it.deleteFrom(Spaceport::class)
            it.deleteFrom(Waybill::class)
            it.deleteFrom(WaybillItem::class)
        }
    }

    @Test
    internal fun testAtmosphereRepo() {
        val allAtmospheres = atmosphereRepository.findAll()
        val count = allAtmospheres.size
        assertEquals(1, count)

        val atmosphere = allAtmospheres.first()
        val gases = atmosphereRepository.findAllGasesInAtmosphere(atmosphere)
        assertEquals(2, gases.size)
    }

    @Test
    internal fun testAtmosphericGasRepo() {
        val totalCount = atmosphericGasRepository.count()
        assertEquals(2, totalCount)

        val atmosphericGas = atmosphericGasRepository.findByGas_NameLike("Oxy")
        assertNotNull(atmosphericGas)

        var count = atmosphericGasRepository.countByVolumeEquals(4.0)
        assertEquals(1, count)

        count = atmosphericGasRepository.countByVolumeEquals(3.0)
        assertEquals(0, count)
    }

    @Test
    internal fun testCarrierAndSpaceportAndWaybillRepos() {
        initSpaceportAndCarrierAndWaybill()

        try {
            val spaceports = spaceportRepository.findByIsDefaultEqualsOrderByNameAsc(true)
            assertEquals(1, spaceports.size)
            assertEquals("Space", spaceports.first().name)

            assertNotNull(carrierRepository.findByName("Vostok"))

            assertEquals(1, waybillRepository.findByTotalWeightGreaterThan(10.0).size)
        } finally {
            destroySpaceportAndCarrierAndWaybill()
        }

    }

    @Test
    internal fun testCompanyRepo() {
        val count = companyRepository.count()
        assertEquals(1, count)

        val company = companyRepository.findByCompanyType("Type")
        assertNotNull(company)
    }

    @Test
    internal fun testDiscountsRepo() {
        val allDiscounts = discountsRepository.findAll()
        val count = allDiscounts.size
        assertEquals(1, count)

        val discounts = allDiscounts.first()
        assertEquals(CustomerGrade.HIGH, discounts.grade)

        assertTrue(discountsRepository.existsByValueBetween(BigDecimal.ONE, BigDecimal.TEN))
    }

    @Test
    internal fun testGasRepo() {
        val gas = gasRepository.findByNameIs("Nitrogen")
        assertNotNull(gas)

        val allGases = gasRepository.findAll()
        assertEquals("Oxygen", allGases.first().name)
        assertEquals(2, allGases.size)
    }

    @Test
    internal fun testIndividualRepo() {
        val count = individualRepository.count()
        assertEquals(1, count)

        val individual = individualRepository.findByFirstNameAndEmail("Ivan", "ivanov@gmail.com")
        assertNotNull(individual)

        assertEquals(CustomerGrade.MIDDLE, individual?.grade)
    }

    @Test
    internal fun testMoonRepo() {
        val allMoons = moonRepository.findAll()
        val size = allMoons.size
        assertEquals(1, size)

        val moon = allMoons.first()
        assertEquals(moon, moonRepository.findByName("Moon"))

        val moons = moonRepository.findByPlanet_RingsEquals(false)
        assertEquals(0, moons.size)
    }

    @Test
    internal fun testPlanetRepo() {
        val size = planetRepository.findAll().size
        assertEquals(1, size)

        val atmosphericGases = planetRepository.getAtmosphericGasesByName("Planet")
        assertEquals(2, atmosphericGases.size)
    }

    @Test
    internal fun testWaybillItemRepo() {
        val count = waybillItemRepository.count()
        assertEquals(1, count)

        assertTrue(waybillItemRepository.existsByNumber(1))
        assertFalse(waybillItemRepository.existsByNumber(2))

        assertNotNull(waybillItemRepository.findByNameOrderByWeightAsc("item"))
    }

    private fun initSpaceportAndCarrierAndWaybill() {
        val spaceport = Spaceport().also {
            it.name = "Space"
            it.planet = planetRepository.findByName("Planet")
            it.moon = moonRepository.findByName("Moon")
            it.isDefault = true
            it.coordinates = Coordinates().apply {
                latitude = 123.45
                longitude = 543.21
            }
        }

        val carrier = Carrier().apply {
            name = "Vostok"
        }

        spaceport.carriers = mutableListOf(carrier)
        carrier.spaceports = mutableListOf(spaceport)

        val waybill = Waybill().also {
            it.reference = "Reference"
            it.shipper = individualRepository.findByFirstNameAndEmail("Ivan", "ivanov@gmail.com")
            it.consignee = companyRepository.findByCompanyType("Type").first()
            it.departurePort = spaceport
            it.destinationPort = spaceport
            it.carrier = carrier
            it.items = waybillItemRepository.findByNameOrderByWeightAsc("item") as MutableList<WaybillItem>
            it.totalWeight = 25.0
            it.totalCharge = BigDecimal(50)
        }

        spaceportRepository.save(spaceport)
        carrierRepository.save(carrier)
        waybillRepository.save(waybill)
    }

    private fun destroySpaceportAndCarrierAndWaybill() {
        val spaceport = spaceportRepository.findByName("Space")
        val carrier = carrierRepository.findByName("Vostok")
        val waybill = waybillRepository.findByReference("Reference")

        spaceport?.carriers = mutableListOf()
        spaceport?.moon = null
        spaceport?.planet = null
        carrier?.spaceports = mutableListOf()
        waybill?.carrier = null
        waybill?.departurePort = null
        waybill?.destinationPort = null
        waybill?.items = mutableListOf()

        spaceportRepository.save(spaceport!!)
        carrierRepository.save(carrier!!)
        waybillRepository.save(waybill!!)
    }
}

private fun JdbcTemplate.deleteFrom(entityClass: KClass<out BaseEntity>) {
    this.execute("delete from ${entityClass.tableName()}")
}