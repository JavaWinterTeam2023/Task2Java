package Model;
import java.util.ArrayList;
import java.util.List;

public class ChargingStation {
	private int Id;
	private String SName;
	private String Scode;
	private int YearOfConstruction;
	private List<User> Users;

	public ChargingStation() {
		Users=new ArrayList<User>();
	}
	
	public int GetId() {
		return Id;
	}
	public void SetId(int id) {
		this.Id=id;
	}
	public String GetSName() {
		return SName;
	}
	public void SetSName(String sName) {
		this.SName=sName;
	}
	public String GetScode() {
		return Scode;
	}
	public void SetScode(String sCode) {
		this.Scode=sCode;
	}
	public int GetYearOfConstruction() {
		return YearOfConstruction;
	}
	public void SetYearOfConstruction(int yConstruction) {
		this.YearOfConstruction=yConstruction;
	}
}
