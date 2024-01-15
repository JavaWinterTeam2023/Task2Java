package Model;
import java.sql.Time;

public class TimeSlot {
	
	private int Id;
	private Time StartTime;
	private Time EndTime;
	
	public TimeSlot() {
		
	}
	
	public int GetId() {
		return Id;
	}
	public void setId(int id) {
		this.Id=id;
	}
	public Time GetStartTime() {
		return StartTime;
	}
	public void setStartTime(Time Stime) {
		this.StartTime=Stime;
	}
	public Time GetEndTime() {
		return EndTime;
	}
	public void setEndTime(Time eTime) {
		this.EndTime=eTime;
	}

}
