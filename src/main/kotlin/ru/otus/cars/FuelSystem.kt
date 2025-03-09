package ru.otus.cars

class FuelSystem(val fuelType: FuelType) {
    lateinit var tank: Tank

    var tankMouth: TankMouth =  when (fuelType) {
        FuelType.PETROL -> TankMouth.PetrolMouth()
        FuelType.LPG -> TankMouth.LpgMouth()
    }

    fun fillFuel(volume: Int) {
        if (!tankMouth.connectToNozzle)  throw IllegalStateException("tankMouth not connected to Nozzle")
            tank.fill(volume)

    }

    class Tank(val capacity: Int) {
        var currentVolume: Int = 0
            private set

        fun fill(volume: Int) {
            if (currentVolume + volume <= capacity)
                currentVolume += volume
            else throw IllegalArgumentException("volume must be in range [0,${capacity - currentVolume}])")
        }
    }
}


sealed class TankMouth {
    var connectToNozzle: Boolean = false

    class PetrolMouth(): TankMouth()
    class LpgMouth(): TankMouth()

    fun setConnectToNozzle() {
        connectToNozzle = true
        println("connect To Nozzle")
    }

    fun setDisconnectFromNozzle() {
        connectToNozzle = false
        println("disconnect from Nozzle, the fueling is completed")
    }
}

