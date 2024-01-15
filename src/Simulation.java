
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class ChargingStation {
    private List<Car> chargingSpots;
    private List<String> energySources;
    private int chargingTime; // in milliseconds
    private int maxWaitTime; // in milliseconds
    private String logFile;

    public ChargingStation(List<String> energySources, int numberOfSpots, int chargingTime, int maxWaitTime, String logFile) {
        this.energySources = energySources;
        this.chargingSpots = new ArrayList<>(numberOfSpots);
        this.chargingTime = chargingTime;
        this.maxWaitTime = maxWaitTime;
        this.logFile = logFile;

        for (int i = 0; i < numberOfSpots; i++) {
            chargingSpots.add(null); // Initialize spots as empty
        }
    }

    public boolean chargeCar(Car car) {
        long startTime = System.currentTimeMillis();

        while (true) {
            for (int i = 0; i < chargingSpots.size(); i++) {
                if (chargingSpots.get(i) == null) {
                    chargingSpots.set(i, car);
                    logCharging(car, i);
                    simulateChargingTime();
                    return true;
                }
            }

            if (System.currentTimeMillis() - startTime > maxWaitTime) {
                logWaitedTooLong(car);
                return false;
            }

            simulateWeatherConditions();
        }
    }

    private void logCharging(Car car, int spot) {
        String logMessage = "Car " + car.getName() + " is charging at station with energy source " + energySources.get(spot);
        writeLog(logMessage);
    }

    private void logWaitedTooLong(Car car) {
        String logMessage = "Car " + car.getName() + " waited too long. Moving to another station.";
        writeLog(logMessage);
    }

    private void writeLog(String logMessage) {
        try (Writer writer = new BufferedWriter(new FileWriter(logFile, true))) {
            writer.write(logMessage + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void simulateChargingTime() {
        try {
            Thread.sleep(chargingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void simulateWeatherConditions() {
        Random random = new Random();
        int randomIndex = random.nextInt(energySources.size());
        String logMessage = "Weather conditions change at station. Switching to energy source " + energySources.get(randomIndex);
        writeLog(logMessage);
    }
}

class CarChargingSimulation {
    public static void main(String[] args) {
        List<String> energySources = List.of("Solar", "Wind", "Hydro");
        ChargingStation chargingStation = new ChargingStation(energySources, 5, 2000, 5000, "charging_log.txt");

        for (int i = 0; i < 10; i++) {
            Car car = new Car("Car" + i);
            chargingStation.chargeCar(car);
        }
    }
}

class Car {
    private String name;

    public Car(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
