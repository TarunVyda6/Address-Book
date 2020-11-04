/**
 * 
 */
package com.addressbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonStreamParser;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

/**
 * @author Tarun vyda
 *
 */
public class AddressBookFileOperations
{
	private static String FILE_NAME = "AddressBook.txt";
	private static String CSV_FILE = "AddressBook.csv";
	private static String JSON_FILE = "AddressBook.json";

	/**
	 * reads address books from files
	 */
	public static void addressBookReadText(String type)
	{
		if (type.equals("txt"))
		{
			try
			{
				Files.lines(new File(FILE_NAME).toPath()).forEach(System.out::println);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		else if (type.equals("csv"))
		{
			try (Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE)))
			{
				CsvToBeanBuilder<ContactDetails> builder = new CsvToBeanBuilder<ContactDetails>(reader);
				CsvToBean<ContactDetails> csvToBean = builder.withType(ContactDetails.class)
						.withIgnoreLeadingWhiteSpace(true).build();
				List<ContactDetails> contacts = csvToBean.parse();
				System.out.println(contacts);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

	}

	/**
	 * @param addressBooksMap
	 * @param saves           the address books in required file format
	 */
	public static void addressBookWriteText(Map<String, Map<String, ContactDetails>> addressBooksMap, String fileType)
	{

		if (fileType.equals("TXT"))
		{
			StringBuffer personsDataToWrite = new StringBuffer();

			addressBooksMap.forEach((k, v) -> {
				String bookName = k.concat(System.getProperty("line.separator"));
				personsDataToWrite.append(bookName);
				v.values().forEach(i -> {
					personsDataToWrite.append(i.toString().concat(System.getProperty("line.separator")));
				});
			});

			try
			{
				Files.write(Paths.get(FILE_NAME), personsDataToWrite.toString().getBytes());
				System.out.println("Txt File Saved Successfully!");
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		else if (fileType.equals("CSV"))
		{

			List<ContactDetails> contacts = mapToListConverter(addressBooksMap);

			try (Writer writer = Files.newBufferedWriter(Paths.get(CSV_FILE)))
			{
				StatefulBeanToCsv<ContactDetails> beanToCsv = new StatefulBeanToCsvBuilder<ContactDetails>(writer)
						.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();
				beanToCsv.write(contacts);
				System.out.println("csv file successfully saved");
			}
			catch (Exception e)
			{
				e.printStackTrace();

			}

		}

	}

	/**
	 * @param addressBooksMap to list conversion
	 * @return list of contact details
	 */
	public static List<ContactDetails> mapToListConverter(Map<String, Map<String, ContactDetails>> addressBooksMap)
	{
		List<ContactDetails> contacts = new ArrayList<ContactDetails>();
		addressBooksMap.forEach((k, v) -> {
			contacts.addAll(v.values());
		});
		return contacts;
	}

}
