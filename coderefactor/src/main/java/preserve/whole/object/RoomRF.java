package preserve.whole.object;

public class RoomRF {

	TempRangeRF daysTempRange() {
		return new TempRangeRF();
	}
	
	boolean withinPlan(HeatingPlanRF plan) {
		return plan.withinRange(daysTempRange());
	}
}
