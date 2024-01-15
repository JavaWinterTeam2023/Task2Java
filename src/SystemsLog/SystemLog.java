import static java.nio.file.StandardCopyOption.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;

public class SystemLog extends LogFileOperations{
	private Path filePath; 
	enum LogType {
		INFO,
		WARNING,
		ERROR
	}
	
	public SystemLog(Path filePath) {
		super(filePath);
	}
	
	private void updateLog(String message, LogType type) {
		LocalDateTime localTime = LocalDateTime.now();
		String logMessage = formatSystemLog(localTime, type, message);
		writeLogFile(this.getFilePath(), logMessage);
	}

	public void infoLog(String message) {
		LogType type = LogType.INFO;
		updateLog(message, type);
	}
	
	public void warningLog(String message) {
		LogType type = LogType.WARNING;
		updateLog(message, type);
	}
	
	public void errorLog(String message) {
		LogType type = LogType.ERROR;
		updateLog(message, type);
	}
	
	public String formatSystemLog(LocalDateTime logTime, LogType type, String message) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return String.format("[%s] %s\t %s\n", logTime.format(formatter), type, message);
		
	}
}
