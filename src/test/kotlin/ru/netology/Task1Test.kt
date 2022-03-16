package ru.netology

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Task1Test {

    @Test
    fun `calculateCommission for VK_PAY`() {
        assertEquals(
            1,
            calculateCommission(VK_PAY, 1000_00, 0, 0)
        )
    }

    @Test
    fun `calculateCommission for MasterCard`() {
        assertEquals(
            0,
            calculateCommission(MASTER_CARD, 1000_00, 0, 0)
        )
    }

    @Test
    fun `calculateCommission for Maestro`() {
        assertEquals(
            0,
            calculateCommission(MAESTRO, 1000_00, 0, 0)
        )
    }

    @Test
    fun `calculateCommission for Visa`() {
        assertEquals(
            35_00,
            calculateCommission(VISA, 1000_00, 0, 0)
        )
    }

    @Test
    fun `calculateCommission for Mir`() {
        assertEquals(
            35_00,
            calculateCommission(MIR, 1000_00, 0, 0)
        )
    }
    
    @Test
    fun `calculateCommission if result is null`() {
        assertNull(
            calculateCommission(MASTER_CARD, 1000_00, 600_001_00, 0)
        )
    }

    @Test
    fun `calculateCommission by default parameters`() {
        assertEquals(
            0,
            calculateCommission(currentTransfer = 1000_00)
        )
    }

    @Test
    fun `transferIsAvailableTest with VK_PAY and currentTransfer equals 150k`(
    ) {
        assertTrue(
            transferIsAvailable(VK_PAY, 15_000_00, 0, 0)
        )
    }

    @Test
    fun `transferIsAvailableTest with VK_PAY and currentTransfer more 150k`(
    ) {
        assertFalse(
            transferIsAvailable(VK_PAY, 15_001_00, 0, 0)
        )
    }

    @Test
    fun `transferIsAvailableTest with VK_PAY and transfersPerMonth equals 40k`(
    ) {
        assertTrue(
            transferIsAvailable(VK_PAY, 1000_00, 0, 40_000_00)
        )
    }

    @Test
    fun `transferIsAvailableTest with VK_PAY and transfersPerMonth more 40k`(
    ) {
        assertFalse(
            transferIsAvailable(VK_PAY, 1000_00, 0, 40_001_00)
        )
    }

    @Test
    fun `transferIsAvailableTest with MASTER_CARD and transfersPerDay equals 150k`(
    ) {
        assertTrue(
            transferIsAvailable(MASTER_CARD, 0, 150_000_00, 0)
        )
    }

    @Test
    fun `transferIsAvailableTest with MAESTRO and transfersPerDay more 150k`(
    ) {
        assertFalse(
            transferIsAvailable(MAESTRO, 0, 150_001_00, 0)
        )
    }

    @Test
    fun `transferIsAvailableTest with VISA and transfersPerMonth equals 600k`(
    ) {
        assertTrue(
            transferIsAvailable(VISA, 0, 0, 600_000_00)
        )
    }

    @Test
    fun `transferIsAvailableTest with MIR and transfersPerMonth more 600k`(
    ) {
        assertFalse(
            transferIsAvailable(MIR, 0, 0, 600_001_00)
        )
    }

    @Test
    fun `commissionForMasterCardAndMaestro if transfer less 300`() {
        assertEquals(
            21_79,
            commissionForMasterCardAndMaestro(299_00)
        )
    }

    @Test
    fun `commissionForMasterCardAndMaestro if transfer equals 300`() {
        assertEquals(
            0,
            commissionForMasterCardAndMaestro(300_00)
        )
    }

    @Test
    fun `commissionForMasterCardAndMaestro if transfer equals 75k`() {
        assertEquals(
            0,
            commissionForMasterCardAndMaestro(75_000_00)
        )
    }

    @Test
    fun `commissionForMasterCardAndMaestro if transfer more 75k`() {
        assertEquals(
            470_00,
            commissionForMasterCardAndMaestro(75_001_00)
        )
    }

    @Test
    fun `commissionForVisaAndMir equals min commission`() {
        assertEquals(
            35_00,
            commissionForVisaAndMir(1000_00)
        )
    }

    @Test
    fun `commissionForVisaAndMir more min commission`() {
        assertEquals(
            36_00,
            commissionForVisaAndMir(4800_00)
        )
    }

}