package replace.delegation.with.inheritance;

public class EmployeeRF extends Person{

	
	public String toString() {
		return "Emp: " + getLastName();
	}
}
