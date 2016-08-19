package extract.Interface;

public class TimeSheet {

	double charger(Billable emp, int days) {
		int base = emp.getRate() * days;
		if (emp.hasSpecialSkill())
			return base * 1.05;
		return base;
	}
}
