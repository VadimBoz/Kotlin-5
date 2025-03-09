package ru.otus.cars


object FillingStation: FillingFuel {
    override fun fillingFuel(car: Car, volume: Int) {
        when (car.tankMouth) {
            is TankMouth.PetrolMouth -> {
                car.tankMouth.setConnectToNozzle()
                car.startRefueling(volume)
                println("Filling fuel for Petrol car with volume $volume")
                car.tankMouth.setDisconnectFromNozzle()
            }
            is TankMouth.LpgMouth -> {
                car.tankMouth.setConnectToNozzle()
                car.startRefueling(volume)
                println("Filling fuel for Gas car with volume $volume")
                car.tankMouth.setDisconnectFromNozzle()
            }
            else -> throw Exception("Fueling module not found")
        }
    }
}

interface FillingFuel {
    fun fillingFuel(car: Car, volume: Int)
}







