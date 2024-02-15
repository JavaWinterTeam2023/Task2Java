package SystemsLog;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EnergyManagementLog extends LogFileOperations{

	public EnergyManagementLog(Path filePath, Path dirPath) {
		super(filePath, dirPath);
	}
	
	public void EnergyConsumptionLog (String locationId, LocalDateTime startTime, 
			LocalDateTime endTime, String energySource, 
			double tranferredEnergy, double chargingPower) {
		String logEnergyMessage = formatEnergyMessage(locationId, energySource, 
				startTime, endTime, tranferredEnergy, chargingPower);
		writeLogFile(this.getFilePath(), logEnergyMessage);
//        try {
//			Files.write(this.getFilePath(), logByteEnergyMessage, StandardOpenOption.APPEND);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			System.err.println(e.getMessage());
//		}
	}
	
	private String formatEnergyMessage(String locationId, String energySource, 
			LocalDateTime startTime, LocalDateTime endTime,
			double tranferredEnergy, double chargingPower) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("[%s] \tCharging started at %s, ended at %s."
        		+ "\n\t\tEnergy source: %s."
        		+ "\n\t\tCharging power: %.2f kW.\n"
        		+ "\t\tEnergyTransferred: %.2f kWh.\n",
                locationId, startTime.format(formatter), endTime.format(formatter), 
                energySource, chargingPower, tranferredEnergy);
    }
}
