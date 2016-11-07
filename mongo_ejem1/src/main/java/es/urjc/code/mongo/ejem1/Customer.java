package es.urjc.code.mongo.ejem1;

import org.springframework.data.annotation.Id;

public class Customer {

	@Id
	private String id;

	private String firstName;
	private String lastName;

	protected Customer() {
		// Used by SpringData
	}

	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public String toString() {
		return String.format("Customer[id=%s, firstName='%s', lastName='%s']",
				id, firstName, lastName);
	}
}