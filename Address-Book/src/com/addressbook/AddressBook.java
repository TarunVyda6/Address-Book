package com.addressbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author Tarun Vyda
 *
 */
public class AddressBook
{
	public static Map<String, Map<String, ContactDetails>> addressBooksMap = new HashMap<>();

	public static void main(String[] args)
	{
		System.out.println("Welcome to Address Book Program");
		Scanner scanner = new Scanner(System.in);
		addressBookOperation(scanner);
	}

	/**
	 * @param scanner
	 */
	private static void addressBookOperation(Scanner scanner)
	{
		System.out.println("Press 1 to Add an address book");
		System.out.println("Press 2 to View all address books");
		System.out.println("Press 3 to do operation in an address book");
		System.out.println("Press 4 to search by City in all address books");
		System.out.println("Press 5 to search by state in all address books");
		System.out.println("Press 6 to count persons by state");
		System.out.println("Press 7 to count person by city");
		System.out.println("Press 8 to write data into file");
		System.out.println("Press 9 to read data from file");
		System.out.println("press 0 to exit");
		Map<String, ContactDetails> addressBook = new HashMap<>();
		//
		int choice = Integer.parseInt(scanner.nextLine());

		switch (choice)
		{

			case 1:
				System.out.println("Enter Address book name");
				String bookName = scanner.nextLine();
				if (addressBooksMap.containsKey(bookName))
				{
					System.out.println("Book already exits!");
				}
				else
				{
					System.out.println("Book Added Successfully");
					addressBooksMap.put(bookName, addressBook);
				}
				addressBookOperation(scanner);
			case 2:
				if (addressBooksMap.isEmpty() || addressBooksMap == null)
				{
					System.out.println("No book to view");
				}
				else
				{
					for (Entry<String, Map<String, ContactDetails>> s : addressBooksMap.entrySet())
					{
						System.out.println(s);
					}
				}
				addressBookOperation(scanner);
			case 3:
				System.out.println("Enter Address book name");
				bookName = scanner.nextLine();
				if (addressBooksMap.containsKey(bookName))
				{
					addressBook = addressBooksMap.get(bookName);
					ContactsOperation(scanner, addressBook, bookName);
				}
				else
				{
					System.out.println("Contact does not exist");
					addressBookOperation(scanner);
				}
			case 4:
				System.out.println("Enter city name");
				String city = scanner.nextLine();
				System.out.println(searchPersonInCity(city));
				addressBookOperation(scanner);
			case 5:
				System.out.println("Enter state name");
				String state = scanner.nextLine();
				System.out.println(searchPersonInState(state));
				addressBookOperation(scanner);
			case 6:
				countPersonsByState();
				addressBookOperation(scanner);
			case 7:
				countPersonsByCity();
				addressBookOperation(scanner);
			case 8:
				if (addressBooksMap.isEmpty() || addressBooksMap == null)
				{
					System.out.println("No book to save");
				}
				else
				{
					System.out.println("Press 1 for .TXT file");
					System.out.println("Press 2 for .CSV file");
					System.out.println("Press 3 for .JSON file");
					int selection = Integer.parseInt(scanner.nextLine());
					if (selection == 1)
					{
						AddressBookFileOperations.addressBookWriteText(addressBooksMap, "TXT");
					}
					else if (selection == 2)
					{
						AddressBookFileOperations.addressBookWriteText(addressBooksMap, "CSV");

					}
					else if (selection == 3)
					{
						AddressBookFileOperations.addressBookWriteText(addressBooksMap, "JSON");
					}
					else
					{
						System.out.println("invalid choice.... going back to main menu");
					}
				}
				addressBookOperation(scanner);
			case 9:
				System.out.println("press 1 to read txt file");
				System.out.println("press 2 to read csv file");
				System.out.println("press 3 to read json file");
				int selection = Integer.parseInt(scanner.nextLine());
				if (selection == 1)
				{
					AddressBookFileOperations.addressBookReadText("txt");
				}

				else if (selection == 2)
				{
					AddressBookFileOperations.addressBookReadText("csv");
				}

				else if (selection == 3)
				{
					AddressBookFileOperations.addressBookReadText("json");
				}

				addressBookOperation(scanner);
			case 0:
				break;
			default:
				break;
		}
	}

	private static List<ContactDetails> searchPersonInState(String state)
	{

		ArrayList<ContactDetails> allContacts = new ArrayList<ContactDetails>();
		for (Map<String, ContactDetails> book : addressBooksMap.values())
		{
			allContacts.addAll(book.values());
		}
		return allContacts.stream().filter(contacts -> contacts.getState().equals(state)).collect(Collectors.toList());
	}

	private static List<ContactDetails> searchPersonInCity(String city)
	{
		ArrayList<ContactDetails> allContacts = new ArrayList<ContactDetails>();
		for (Map<String, ContactDetails> book : addressBooksMap.values())
		{
			allContacts.addAll(book.values());
		}

		return allContacts.stream().filter(contacts -> contacts.getCity().equals(city)).collect(Collectors.toList());

	}

	public static Map<String, List<ContactDetails>> personsByState = new TreeMap<>();

	// uc9
	private static Map<String, List<ContactDetails>> viewPersonsByState()
	{
		ArrayList<ContactDetails> stateDetails = new ArrayList<>();
		for (Map<String, ContactDetails> book : addressBooksMap.values())
		{
			stateDetails.addAll(book.values());
		}
		stateDetails.stream()
				.forEach(contact -> personsByCity.put(contact.getState(), searchPersonInState(contact.getState())));
		return personsByCity;
	}

	public static Map<String, List<ContactDetails>> personsByCity = new TreeMap<>();

	private static Map<String, List<ContactDetails>> viewPersonsByCity()
	{
		ArrayList<ContactDetails> cityDetails = new ArrayList<>();
		for (Map<String, ContactDetails> book : addressBooksMap.values())
		{
			cityDetails.addAll(book.values());
		}
		cityDetails.stream()
				.forEach(contact -> personsByCity.put(contact.getCity(), searchPersonInCity(contact.getCity())));
		return personsByCity;
	}

	// uc10
	public static void countPersonsByState()
	{
		Set<String> countByState = viewPersonsByState().keySet();

		countByState.stream().forEach(state -> System.out
				.println(state + " has " + viewPersonsByState().get(state).stream().count() + " persons."));
	}

	public static void countPersonsByCity()
	{
		Set<String> countByCity = viewPersonsByCity().keySet();

		countByCity.stream().forEach(city -> System.out
				.println(city + " has " + viewPersonsByCity().get(city).stream().count() + " persons."));
	}

	/**
	 * @param scanner
	 * @param addressBook
	 */
	private static void ContactsOperation(Scanner scanner, Map<String, ContactDetails> addressBook, String bookName)
	{
		System.out.println("Press 1 to Add a new contact");
		System.out.println("Press 2 to Edit an existing contact");
		System.out.println("Press 3 to Delete contact");
		System.out.println("Press 4 to View All contact");
		System.out.println("Press 5 to View All contact sorted by name");
		System.out.println("Press 6 for View All contact sorted by city");
		System.out.println("Press 7 for View All contact sorted by state");
		System.out.println("Press 8 for View All contact sorted by zip code");
		System.out.println("Press 9 for main menu");
		int choice = Integer.parseInt(scanner.nextLine());

		switch (choice)
		{
			case 1:
				addContact(scanner, addressBook, bookName);
				break;
			case 2:
				editContact(scanner, addressBook, bookName);
				break;
			case 3:
				deleteContact(scanner, addressBook, bookName);
				break;
			case 4:
				viewAllContacts(scanner, addressBook, bookName);
				break;
			case 5:
				viewAllContactsSortedByName(scanner, addressBook, bookName);
				break;
			case 6:
				viewAllContactsSortedByCity(scanner, addressBook, bookName);
				break;
			case 7:
				viewAllContactsSortedByState(scanner, addressBook, bookName);
				break;
			case 8:
				viewAllContactsSortedByZip(scanner, addressBook, bookName);
				break;
			case 9:
				addressBookOperation(scanner);
				break;
			default:
				System.out.println("Incorrect Selection");
		}

	}

	// uc12
	private static void viewAllContactsSortedByState(Scanner scanner, Map<String, ContactDetails> addressBook,
			String bookName)
	{

		ArrayList<ContactDetails> contactList = new ArrayList<>();
		System.out.println("Sorting current address book by state: ");
		for (Map<String, ContactDetails> book : addressBooksMap.values())
		{
			contactList.addAll(book.values());
		}
		contactList.stream().sorted((a, b) -> a.getState().compareTo(b.getState())).forEachOrdered(System.out::println);
		System.out.println("Press 0 for main menu");
		System.out.println("Press 1 for submain menu");
		int input = Integer.parseInt(scanner.nextLine());
		if (input == 0)
		{
			addressBookOperation(scanner);
		}
		else if (input == 1)
		{
			ContactsOperation(scanner, addressBook, bookName);
		}
		else
		{
			System.out.println("invalid input going back to main menu");
			addressBookOperation(scanner);
		}
	}

	private static void viewAllContactsSortedByCity(Scanner scanner, Map<String, ContactDetails> addressBook,
			String bookName)
	{

		ArrayList<ContactDetails> contactList = new ArrayList<>();
		System.out.println("Sorting current address book by state: ");
		for (Map<String, ContactDetails> book : addressBooksMap.values())
		{
			contactList.addAll(book.values());
		}
		contactList.stream().sorted((a, b) -> a.getCity().compareTo(b.getCity())).forEachOrdered(System.out::println);
		System.out.println("Press 0 for main menu");
		System.out.println("Press 1 for submain menu");
		int input = Integer.parseInt(scanner.nextLine());
		if (input == 0)
		{
			addressBookOperation(scanner);
		}
		else if (input == 1)
		{
			ContactsOperation(scanner, addressBook, bookName);
		}
		else
		{
			System.out.println("invalid input going back to main menu");
			addressBookOperation(scanner);
		}

	}

	private static void viewAllContactsSortedByZip(Scanner scanner, Map<String, ContactDetails> addressBook,
			String bookName)
	{

		ArrayList<ContactDetails> contactList = new ArrayList<>();
		System.out.println("Sorting current address book by zip code: ");
		for (Map<String, ContactDetails> book : addressBooksMap.values())
		{
			contactList.addAll(book.values());
		}
		contactList.stream().sorted((a, b) -> a.getZip().compareTo(b.getZip())).forEachOrdered(System.out::println);
		System.out.println("Press 0 for main menu");
		System.out.println("Press 1 for submain menu");
		int input = Integer.parseInt(scanner.nextLine());
		if (input == 0)
		{
			addressBookOperation(scanner);
		}
		else if (input == 1)
		{
			ContactsOperation(scanner, addressBook, bookName);
		}
		else
		{
			System.out.println("invalid input going back to main menu");
			addressBookOperation(scanner);
		}

	}

	/**
	 * @param scanner
	 * @param addressBook
	 * @param bookName
	 */
	private static void viewAllContacts(Scanner scanner, Map<String, ContactDetails> addressBook, String bookName)
	{
		if (addressBook.isEmpty() || addressBook == null)
		{
			System.out.println("No contacts to view");
			ContactsOperation(scanner, addressBook, bookName);
		}
		else
		{
			for (Entry<String, ContactDetails> set : addressBook.entrySet())
			{
				System.out.println(set);
			}
		}

	}

	/**
	 * @param scanner
	 * @param addressBook
	 * @param bookName
	 */

	// uc11
	private static void viewAllContactsSortedByName(Scanner scanner, Map<String, ContactDetails> addressBook,
			String bookName)
	{
		if (addressBook.isEmpty() || addressBook == null)
		{
			System.out.println("No contacts to view");
			ContactsOperation(scanner, addressBook, bookName);
		}
		else
		{
			Map<String, ContactDetails> sortedAddressBook = new TreeMap<>();

			sortedAddressBook.putAll(addressBook);
			for (Entry<String, ContactDetails> set : sortedAddressBook.entrySet())
			{
				System.out.println(set);
			}
			ContactsOperation(scanner, addressBook, bookName);
		}
	}

	/**
	 * @param scanner
	 * @param addressBook
	 * @param bookName
	 */
	private static void editContact(Scanner scanner, Map<String, ContactDetails> addressBook, String bookName)
	{
		System.out.println("Please select name from the below list to edit contact details of the individual.");
		viewAllContacts(scanner, addressBook, bookName);
		System.out.println("Enter the first name and last name of the person to edit");
		System.out.println("Enter First Name");
		String firstName = scanner.nextLine();
		System.out.println("Enter Last Name");
		String lastName = scanner.nextLine();
		if (addressBook.containsKey(firstName + lastName))
		{
			ContactDetails editObj = addressBook.get(firstName + lastName);
			System.out.println(
					"Press 1 to edit address, 2 to edit city, 3 to edit state, 4 to edit zip, 5 to edit phone number, 6 to edit email, 0 to cancel editing");
			int response = Integer.parseInt(scanner.nextLine());
			System.out.println("Enter new value");
			String editVar = scanner.nextLine();
			switch (response)
			{
				case 0:
					break;
				case 1:
					editObj.setAddress(editVar);
					break;
				case 2:
					editObj.setCity(editVar);
					break;
				case 3:
					editObj.setState(editVar);
					break;
				case 4:
					editObj.setZip(editVar);
					break;
				case 5:
					editObj.setPhoneNumber(editVar);
					break;
				case 6:
					editObj.setEmail(editVar);
					break;
				default:
					System.out.println("Incorrect selection");
			}
			addressBook.put(firstName + lastName, editObj);
			System.out.println("Contact details edited successfully");
		}
		else
		{
			System.out.println("Contact doest not exist");
		}
		System.out.println("Press 0 for main menu");
		System.out.println("Press 1 for submain menu");
		System.out.println("Press 2 to edit another contact");
		int inp = Integer.parseInt(scanner.nextLine());
		if (inp == 0)
		{
			addressBookOperation(scanner);
		}
		else if (inp == 1)
		{
			ContactsOperation(scanner, addressBook, bookName);
		}
		else if (inp == 2)
		{
			editContact(scanner, addressBook, bookName);
		}

	}

	/**
	 * @param scanner
	 * @param addressBook
	 */
	private static void addContact(Scanner scanner, Map<String, ContactDetails> addressBook, String bookName)
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
		add.setAddressBookName(bookName);

		ArrayList<ContactDetails> contactDetailsList = new ArrayList<ContactDetails>(addressBook.values());

		if (contactDetailsList.stream().anyMatch(contact -> contact.equals(add)))
		{
			System.out.println("Name already exits!");
		}
		else
		{
			System.out.println("Contact Added Successfully");
			addressBook.put(firstName + lastName, add);
		}
		System.out.println("Press 0 for main menu");
		System.out.println("Press 1 for submain menu");
		System.out.println("Press 2 to add another contact");
		int inp = Integer.parseInt(scanner.nextLine());
		if (inp == 0)
		{
			addressBookOperation(scanner);
		}
		else if (inp == 1)
		{
			ContactsOperation(scanner, addressBook, bookName);
		}
		else if (inp == 2)
		{
			addContact(scanner, addressBook, bookName);
		}
	}

	/**
	 * @param scanner
	 * @param addressBook
	 * @param bookName
	 */
	private static void deleteContact(Scanner scanner, Map<String, ContactDetails> addressBook, String bookName)
	{
		System.out.println("Please select name from the below list to delete contact details of the individual.");
		viewAllContacts(scanner, addressBook, bookName);
		System.out.println("Enter the first name and last name of the person to delete contact");
		System.out.println("Enter First Name");
		String firstName = scanner.nextLine();
		System.out.println("Enter Last Name");
		String lastName = scanner.nextLine();
		if (addressBook.containsKey(firstName + lastName))
		{
			System.out.println("Press 1 to confirm delete, press any other number to cancel");
			int response = Integer.parseInt(scanner.nextLine());
			if (response == 1)
			{
				addressBook.remove(firstName + lastName);
				System.out.println("Deleted contact successfully.");
			}
			else
			{
				System.out.println("Operation cancelled");
			}
		}
		else
		{
			System.out.println("Contact does not exist");
		}
		System.out.println("Press 0 for main menu");
		System.out.println("Press 1 for submain menu");
		System.out.println("Press 2 to delete another contact");
		int inp = Integer.parseInt(scanner.nextLine());
		if (inp == 0)
		{
			addressBookOperation(scanner);
		}
		else if (inp == 1)
		{
			ContactsOperation(scanner, addressBook, bookName);
		}
		else if (inp == 2)
		{
			deleteContact(scanner, addressBook, bookName);
		}
	}

}