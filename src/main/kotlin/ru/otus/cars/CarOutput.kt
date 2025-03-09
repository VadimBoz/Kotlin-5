package ru.otus.cars

/**
 * Следит за машиной
 */
interface CarOutput {
    /**
     * Скажи текущую скорость
     */
    fun getCurrentSpeed(): Int

    fun getFuelLevel(): Int

    fun getStatusEngine(): Boolean   // start or stop engine

    fun getTypeFuel(): FuelType
}