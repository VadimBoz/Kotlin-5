package ru.otus.cars

abstract class VazPlatform(override val color: String) : Car {
    // Положение руля. Доступно только внутри класса и наследникам
    protected var wheelAngle: Int = 0 // Положение руля

    override val tankMouth: TankMouth
        get() = engine.fuelSystem.tankMouth

    override fun startRefueling(volume: Int) {
        engine.fuelSystem.fillFuel(volume)
    }


    // Реализация интерфейса CarInput
    override fun wheelToRight(degrees: Int) { wheelAngle += degrees }
    // Реализация интерфейса CarInput
    override fun wheelToLeft(degrees: Int) { wheelAngle -= degrees }

    // Получить оборудование
    override fun getEquipment(): String = "Кузов, колеса, движок"

    // Абстрактное свойство двигателя
    abstract val engine: VazEngine

}

// Перечисление двигателей ВАЗ
sealed class VazEngine: Engine() {
    // Объем двигателя можно задать при производстве

    data class LADA_2107(override val volume: Int) : VazEngine() {
        override var fuelSystem: FuelSystem = FuelSystem(FuelType.PETROL).apply {
            tank = FuelSystem.Tank(50)
        }
    }

    data class SAMARA_2108(override val volume: Int) : VazEngine() {
        override var fuelSystem: FuelSystem = FuelSystem(FuelType.LPG).apply {
            tank = FuelSystem.Tank(70)
        }
    }

}


abstract class Engine() {
    abstract var fuelSystem: FuelSystem
    abstract val volume: Int
    var isStarted: Boolean = false

    fun startRefueling(volume: Int) {
        println("Starting refueling ...")
        fuelSystem.fillFuel(volume)
    }

    fun startEngine() {
        if (fuelSystem.tank.currentVolume > 0) {
            println("Starting Engine ...")
            isStarted = true
        } else throw IllegalStateException("The fuel is out")
    }

    fun stopEngine() {
        println("Stopping Engine ...")
        isStarted = false
    }

}




