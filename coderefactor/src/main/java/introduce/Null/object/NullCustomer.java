package introduce.Null.object;

public class NullCustomer extends CustomerRF{

	public boolean isNull() {
		return true;
	}
	
	public String getName() {
		return "occupant";
	}
	
	public BillingPlan getPlan() {
		return BillingPlan.basic();
	}
	
	public PaymentHistory getHistory() {
		return PaymentHistory.newNull();
	}
}
