package SystemsLog;
import static java.nio.file.StandardCopyOption.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;

public class LogFileOperations {
	private Path filePath; 
	private Path fileName;
	private Path fileDirectory;
	private Path archieveDirectory;
	Path archieveFolder = Path.of("archieve");
	
	public LogFileOperations(Path file, Path dir) {
		this.filePath = dir.resolve(file);
		this.fileName = filePath.getFileName();
		this.fileDirectory = dir;
		this.archieveDirectory = dir.resolve(archieveFolder);
	}

	public Path getFilePath() {
		return filePath;
	}

	public void setFilePath(Path filePath) {
		this.filePath = filePath;
	}

	public void createLogFile(Path file) {
		try {
			Files.createFile(filePath);
			System.out.println("File created: " + filePath.getFileName() 
					+ " in folder: " + filePath.getParent()); 
		} catch(FileAlreadyExistsException e) {
			System.err.format("File named %s" +
			        " already exists%n", file);
		} catch (IOException x) {
		    // Some other sort of failure, such as permissions.
		    System.err.format("Create File error: %s%n", x);
		}
	}
	
	public void writeLogFile(Path file, String message) {
		byte[] byteMessage = message.getBytes();
		try {
			Files.write(file, byteMessage, StandardOpenOption.APPEND);
		} catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void deleteLogFile(Path file) {
		try {
		    Files.delete(file);
		} catch (NoSuchFileException x) {
		    System.err.format("%s: no such" + " file or directory%n", file);
		} catch (DirectoryNotEmptyException x) {
		    System.err.format("%s not empty%n", file);
		} catch (IOException x) {
		    // File permission problems are caught here.
		    System.err.println(x);
		}
	}
	
	public void moveLogFile (Path sourcePath, Path destinationPath) throws IOException {
		Files.move(sourcePath, destinationPath, REPLACE_EXISTING);
	}
	
	public void archieveLogFile () {
		String timestamp = LocalDateTime.now().format(
				DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
		Path archieveFile = Paths.get(fileName.toString() + "_" + timestamp);
		Path archievePath = this.archieveDirectory.resolve(archieveFile);
		try {
			Files.copy(filePath, archievePath, REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error copy: " + e.getMessage());
		}

		try {
            Files.newOutputStream(filePath).close();
            System.out.println("File: " + filePath.getFileName() + " archieved. "
            		+ "Succeeded to delete contents of the file");
        } catch (IOException e) {
            System.err.println("Failed to delete contents of the file: " + e.getMessage());
        }
		
	}
}
