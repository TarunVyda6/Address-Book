package com.addressbook;

import com.opencsv.bean.CsvBindByName;

/**
 * @author Tarun Vyda
 *
 */
public class ContactDetails
{
	@CsvBindByName(column = "Address Book Name")
	private String addressBookName;
	@CsvBindByName(column = "First Name")
	private String firstName;
	@CsvBindByName(column = "Last Name")
	private String lastName;
	@CsvBindByName(column = "Address")
	private String address;
	@CsvBindByName(column = "City")
	private String city;
	@CsvBindByName(column = "State")
	private String state;
	@CsvBindByName(column = "ZIP")
	private String zip;
	@CsvBindByName(column = "Ph Number")
	private String phoneNumber;
	@CsvBindByName(column = "Email")
	private String email;

	/**
	 * @param addressBookName the addressBookName to set
	 */
	public void setAddressBookName(String addressBookName)
	{
		this.addressBookName = addressBookName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @return the address
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}

	/**
	 * @return the city
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city)
	{
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState()
	{
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state)
	{
		this.state = state;
	}

	/**
	 * @return the zip
	 */
	public String getZip()
	{
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip)
	{
		this.zip = zip;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * @return the addressBookName
	 */
	public String getAddressBookName()
	{
		return addressBookName;
	}

	@Override
	public String toString()
	{
		return "ContactDetails [addressBookName=" + addressBookName + ", firstName=" + firstName + ", lastName="
				+ lastName + ", address=" + address + ", city=" + city + ", state=" + state + ", zip=" + zip
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + "]";
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		ContactDetails other = (ContactDetails) obj;
		if (firstName == null)
		{
			if (other.firstName != null) return false;
		}
		else if (!firstName.equals(other.firstName)) return false;
		if (lastName == null)
		{
			if (other.lastName != null) return false;
		}
		else if (!lastName.equals(other.lastName)) return false;
		return true;
	}

}
