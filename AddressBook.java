package com.addressbook;

import java.util.*;
import java.util.Map.Entry;

/**
 * @author Tarun Vyda
 *
 */
public class AddressBook {
	public static Map<String, Map<String, ContactDetails>> addressBooksMap = new HashMap<>();

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");
		Scanner scanner = new Scanner(System.in);
		addressBookOperation(scanner);
	}

	/**
	 * @param scanner
	 */
	private static void addressBookOperation(Scanner scanner) {
		System.out.println("Press 1 to Add an address book");
		System.out.println("Press 2 to View all address book");
		System.out.println("Press 3 to do operation in an address book");
		Map<String, ContactDetails> addressBook = new HashMap<>();
		//
		int res = Integer.parseInt(scanner.nextLine());

		if (res == 1) {
			System.out.println("Enter Address book name");
			String bookName = scanner.nextLine();
			if (addressBooksMap.containsKey(bookName)) {
				System.out.println("Book already exits!");
			} else {
				System.out.println("Book Added Successfully");
				addressBooksMap.put(bookName, addressBook);
			}
			addressBookOperation(scanner);
		} else if (res == 2) {
			if (addressBooksMap.isEmpty() || addressBooksMap == null) {
				System.out.println("No book to view");
			} else {
				for (Entry<String, Map<String, ContactDetails>> s : addressBooksMap.entrySet()) {
					System.out.println(s);
				}
			}
			addressBookOperation(scanner);
		} else if (res == 3) {
			System.out.println("Enter Address book name");
			String bookName = scanner.nextLine();
			if (addressBooksMap.containsKey(bookName)) {
				addressBook = addressBooksMap.get(bookName);
				ContactsOperation(scanner, addressBook);
			} else {
				System.out.println("Contact does not exist");
				addressBookOperation(scanner);
			}
		} else {
			addressBookOperation(scanner);
		}
	}

	/**
	 * @param scanner
	 * @param addressBook
	 */
	private static void ContactsOperation(Scanner scanner, Map<String, ContactDetails> addressBook) {
		System.out.println("Press 1 to Add a new contact");
		System.out.println("Press 2 to Edit an existing contact");
		System.out.println("Press 3 to Delete contact");
		System.out.println("Press 4 to View All contact");
		System.out.println("Press 5 for main menu");
		int choice = Integer.parseInt(scanner.nextLine());

		switch (choice) {
		case 1:
			addContact(scanner, addressBook);
			break;
		case 2:
			editContact(scanner, addressBook);
			break;
		case 3:
			deleteContact(scanner, addressBook);
			break;
		case 4:
			viewAllContacts(scanner, addressBook, "contactop");
			break;
		case 5:
			addressBookOperation(scanner);
			break;
		default:
			System.out.println("Incorrect Selection");
		}

	}

	/**
	 * @param scanner
	 * @param addressBook
	 */
	private static void viewAllContacts(Scanner scanner, Map<String, ContactDetails> addressBook, String operation) {
		if (addressBook.isEmpty() || addressBook == null) {
			System.out.println("No contacts to view");
			ContactsOperation(scanner, addressBook);
		} else {
			for (Entry<String, ContactDetails> s : addressBook.entrySet()) {
				System.out.println(s);
			}
		}
		if (operation == "contactop") {
			ContactsOperation(scanner, addressBook);
		}
	}

	/**
	 * @param scanner
	 * @param addressBook
	 */
	private static void editContact(Scanner scanner, Map<String, ContactDetails> addressBook) {
		System.out.println("Please select name from the below list to edit contact details of the individual.");
		viewAllContacts(scanner, addressBook, "editContactop");
		System.out.println("Enter the first name and last name of the person to edit");
		System.out.println("Enter First Name");
		String firstName = scanner.nextLine();
		System.out.println("Enter Last Name");
		String lastName = scanner.nextLine();
		if (addressBook.containsKey(firstName + lastName)) {
			// result="Name already exits!";
			ContactDetails editObj = addressBook.get(firstName + lastName);
			System.out.println(
					"Press 1 to edit address, 2 to edit city, 3 to edit state, 4 to edit zip, 5 to edit phone number, 6 to edit email, 0 to cancel editing");
			int response = Integer.parseInt(scanner.nextLine());
			System.out.println("Enter new value");
			String editVar = scanner.nextLine();
			switch (response) {
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
		} else {
			System.out.println("Contact doest not exist");
		}
		System.out.println("Press 0 for main menu");
		System.out.println("Press 1 for submain menu");
		System.out.println("Press 2 to edit another contact");
		int inp = Integer.parseInt(scanner.nextLine());
		if (inp == 0) {
			addressBookOperation(scanner);
		} else if (inp == 1) {
			ContactsOperation(scanner, addressBook);
		} else if (inp == 2) {
			editContact(scanner, addressBook);
		}

	}

	/**
	 * @param scanner
	 * @param addressBook
	 */
	private static void addContact(Scanner scanner, Map<String, ContactDetails> addressBook) {
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

		ArrayList<ContactDetails> contactDetailsList = new ArrayList<ContactDetails>(addressBook.values());

		if (contactDetailsList.stream().anyMatch(contact -> contact.equals(add))) {
			System.out.println("Name already exits!");
		} else {
			System.out.println("Contact Added Successfully");
			addressBook.put(firstName + lastName, add);
		}
		System.out.println("Press 0 for main menu");
		System.out.println("Press 1 for submain menu");
		System.out.println("Press 2 to add another contact");
		int inp = Integer.parseInt(scanner.nextLine());
		if (inp == 0) {
			addressBookOperation(scanner);
		} else if (inp == 1) {
			ContactsOperation(scanner, addressBook);
		} else if (inp == 2) {
			addContact(scanner, addressBook);
		}
	}

	/**
	 * @param scanner
	 * @param addressBook
	 */
	private static void deleteContact(Scanner scanner, Map<String, ContactDetails> addressBook) {
		System.out.println("Please select name from the below list to delete contact details of the individual.");
		viewAllContacts(scanner, addressBook, "deleteContactop");
		System.out.println("Enter the first name and last name of the person to delete contact");
		System.out.println("Enter First Name");
		String firstName = scanner.nextLine();
		System.out.println("Enter Last Name");
		String lastName = scanner.nextLine();
		if (addressBook.containsKey(firstName + lastName)) {
			System.out.println("Press 1 to confirm delete, press any other number to cancel");
			int response = Integer.parseInt(scanner.nextLine());
			if (response == 1) {
				addressBook.remove(firstName + lastName);
				System.out.println("Deleted contact successfully.");
			} else {
				System.out.println("Operation cancelled");
			}
		} else {
			System.out.println("Contact does not exist");
		}
		System.out.println("Press 0 for main menu");
		System.out.println("Press 1 for submain menu");
		System.out.println("Press 2 to delete another contact");
		int inp = Integer.parseInt(scanner.nextLine());
		if (inp == 0) {
			addressBookOperation(scanner);
		} else if (inp == 1) {
			ContactsOperation(scanner, addressBook);
		} else if (inp == 2) {
			deleteContact(scanner, addressBook);
		}
	}

}