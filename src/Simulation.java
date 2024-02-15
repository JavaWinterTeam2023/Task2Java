
import java.nio.file.*;
import java.time.LocalDateTime;

import SystemsLog.*;

public class Simulation {
	public static void main(String[] args) {
		Path dirPath = Path.of("./log");
		Path logCharging = Paths.get("ChargingEvents.log") ;
		Path logEnergy = Paths.get("Energy.log");
		Path logSystem = Paths.get("System.log");
		
        ChargingStationLog eventLogger = new ChargingStationLog(logCharging, dirPath);
        EnergyManagementLog energyLogger = new EnergyManagementLog(logEnergy, dirPath);
        SystemLog systemLogger = new SystemLog(logSystem, dirPath);
        
        eventLogger.createLogFile(logCharging);
        energyLogger.createLogFile(logEnergy);
        systemLogger.createLogFile(logSystem);
        
        // Simulated charging event data
        String locationId = "Station 1";
        LocalDateTime startTime = LocalDateTime.of(2023, 11, 19, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 11, 19, 12, 0);
        String energySource = "Solar";
        double energyTransferred = 30.5; // kWh
        double chargingPower = 7.2; // kW
        
        String systemInfoMessage = "System start successfully";
        String systemWarningMessage = "Weak connection to station 1";
        String systemErrorMessage = "Failed to connect to database";
        systemLogger.infoLog(systemInfoMessage);
        systemLogger.warningLog(systemWarningMessage);
        systemLogger.errorLog(systemErrorMessage);
        
        eventLogger.logChargingEvent(locationId, startTime, endTime, chargingPower);
        energyLogger.EnergyConsumptionLog(locationId, startTime, endTime, energySource, 
        		energyTransferred, chargingPower);
        eventLogger.archieveLogFile();
	}
}
