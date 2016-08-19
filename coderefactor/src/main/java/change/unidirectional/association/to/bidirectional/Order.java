/**
 * 
 */
package change.unidirectional.association.to.bidirectional;

/**
 * @author meng.sun
 *
 */
public class Order {

	Customer _customer;

	public Customer getCustomer() {
		return _customer;
	}

	public void setCustomer(Customer customer) {
		_customer = customer;
	}
	
}
