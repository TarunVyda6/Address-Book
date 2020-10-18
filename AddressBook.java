package com.addressbook;
import java.util.*;
public class AddressBook 
{
	public static Map<String, Map<String, ContactDetails>> addressBooksMap = new HashMap<>();
	public static void main(String[] args) 
	{
		System.out.println("Welcome to Address Book Program");
		Scanner scanner = new Scanner(System.in);
		addressBookOperation(scanner);
	}
	
	private static void addressBookOperation(Scanner scanner)
	{
		Map<String, ContactDetails> addressBook = new HashMap<>();
		System.out.println("Enter Address book name");
		String bookName = scanner.nextLine();
		if ( addressBooksMap.containsKey ( bookName ) ) 
		{
			System.out.println("Book already exits!");
		} 
		else 
		{
			System.out.println("Book Added Successfully");
			addressBooksMap.put( bookName, addressBook );
		}
	
		
		if ( addressBooksMap.containsKey ( bookName ) ) 
		{
			addressBook=addressBooksMap.get( bookName);
			ContactsOperation ( scanner, addressBook );
		}
		else
		{
			System.out.println("Contact does not exist");
		}
	}
	
	
	private static void ContactsOperation ( Scanner scanner, Map<String, ContactDetails> addressBook )
	{
		addContact(scanner, addressBook);
		
	}
	
	private static void addContact(Scanner scanner, Map<String, ContactDetails> addressBook)
	{
		System.out.println("Enter firstName");
		String firstName = scanner.nextLine();
		System.out.println("Enter lastName");
		String lastName = scanner.nextLine();
		System.out.println("Enter address");
		String address = scanner.nextLine();
		System.out.println("Enter city ");
		String city = scanner.nextLine();
		System.out.println("Enter state ");
		String state = scanner.nextLine();
		System.out.println("Enter zip ");
		String zip = scanner.nextLine();
		System.out.println("Enter phoneNumber");
		String phoneNumber = scanner.nextLine();
		System.out.println("Enter email");
		String email = scanner.nextLine();

		ContactDetails add = new ContactDetails();
		add.setFirstName(firstName);
		add.setLastName(lastName);
		add.setAddress(address);
		add.setCity(city);
		add.setState(state);
		add.setZip(zip);
		add.setPhoneNumber(phoneNumber);
		add.setEmail(email);

		if (addressBook.containsKey(firstName + lastName)) 
		{
			System.out.println("Name already exits!");
		}
		else 
		{
			System.out.println("Contact Added Successfully");
			addressBook.put(firstName + lastName, add);
		}
		
		int inp = Integer.parseInt(scanner.nextLine());
		if (inp == 0) 
		{
			addressBookOperation(scanner);
		}
		else if (inp == 1) 
		{
			ContactsOperation(scanner,addressBook);
		}
		else if (inp == 2) 
		{
			addContact(scanner,addressBook);
		}
	}
	
}
