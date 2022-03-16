package ru.netology

const val VK_PAY = "VK Pay"
const val MASTER_CARD = "Master Card"
const val MAESTRO = "Maestro"
const val VISA = "Visa"
const val MIR = "Мир"

fun main() {
    val commission = calculateCommission(MIR, 11000_00)
    if (commission != null) println("Комиссия составила: ${commission / 100} руб. ${commission % 100} коп.")
    else println("Не удалось посчитать комиссию")
}

fun calculateCommission(
    senderType: String = VK_PAY,
    currentTransfer: Int,
    transfersPerDay: Int = 0,
    transfersPerMonth: Int = 0
) = if (transferIsAvailable(senderType, currentTransfer, transfersPerDay, transfersPerMonth)) when (senderType) {
    VK_PAY -> 0
    MASTER_CARD, MAESTRO -> commissionForMasterCardAndMaestro(currentTransfer)
    VISA, MIR -> commissionForVisaAndMir(currentTransfer)
    else -> null
} else null

fun transferIsAvailable(
    senderType: String,
    currentTransfer: Int,
    transfersPerDay: Int,
    transfersPerMonth: Int
): Boolean {
    val cardsMaxPerDay = 150000_00
    val cardsMaxPerMonth = 600000_00
    val vkMaxTransfer = 15000_00
    val vkMaxPerMonth = 40000_00
    return when {
        senderType == VK_PAY && currentTransfer <= vkMaxTransfer && transfersPerMonth <= vkMaxPerMonth -> true
        senderType != VK_PAY && transfersPerDay <= cardsMaxPerDay && transfersPerMonth <= cardsMaxPerMonth -> true
        else -> false
    }
}

fun commissionForMasterCardAndMaestro(currentTransfer: Int): Int {
    val transferRangeForPromo = 300_00..75_000_00
    val commissionPercent = 0.006
    val fixCommission = 20_00
    return if (currentTransfer in transferRangeForPromo) 0 else (currentTransfer * commissionPercent).toInt() + fixCommission
}

fun commissionForVisaAndMir(currentTransfer: Int): Int {
    val commissionPercent = 0.0075
    val minCommission = 35_00
    val commission = (currentTransfer * commissionPercent).toInt()
    return if (commission < minCommission) minCommission else commission
}