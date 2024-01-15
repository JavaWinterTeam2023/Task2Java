import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChargingStationLog extends LogFileOperations {
    public ChargingStationLog(Path filePath) {
        super(filePath);
    }

    public void logChargingEvent(String locationId, LocalDateTime startTime, LocalDateTime endTime,
                                  double chargingPower) {
        try {
            String logMessage = formatChargingEvent(locationId, startTime, endTime, chargingPower);
            byte[] logByteMessage = logMessage.getBytes();
            Files.write(this.getFilePath(), logByteMessage, StandardOpenOption.APPEND);
        } catch (IOException e) {
            // Consider logging or handling the exception in an appropriate way.
            System.err.println("Error writing to charging event log: " + e.getMessage());
        }
    }

    private String formatChargingEvent(String locationId, LocalDateTime startTime, LocalDateTime endTime,
                                       double chargingPower) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("[%s] Charging started at %s, ended at %s. Charging rate: %.2f kW\n",
                locationId, startTime.format(formatter), endTime.format(formatter), chargingPower);
    }
}

