package main.com.bridgelabz.addressbooksystem;

import java.sql.Connection;
<<<<<<< HEAD
import java.sql.DriverManager;
=======
import java.sql.Date;
>>>>>>> address_book_system_uc20
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD

public class AddressBookDBService {
=======
import org.checkerframework.checker.units.qual.C;

import java.sql.DriverManager;

public class AddressBookDBService {
	
>>>>>>> address_book_system_uc20
	private PreparedStatement contactStatement;
	
	private Connection getConnection() throws SQLException {
		String jdbcURL="jdbc:mysql://localhost:3306/addressBookService?useSSL=false";
		String userName="root";
		String password="abcd1234";
		Connection connection;
		System.out.println("Connecting to database"+ jdbcURL);
		connection=DriverManager.getConnection(jdbcURL,userName,password);
		System.out.println("Connection is successfull"+ connection);
		return connection;
	}
	
<<<<<<< HEAD
	public List<Contact> readData(String name) throws SQLException{
		String sql = String.format("select c.first_name, c.last_name, c.phone_number, c.email, a.address, a.city,a.state, a.zipCode"
				+ " from contact c, address	 a, addressbook ab, contact_type t"
				+ " where c.contact_id=t.contact_id and ab.addressbook_id=t.addressbook_id and c.contact_id=a.contact_id"
				+ " and ab.addressbook_name='%s';",name);
		//String sql=String.format(sqlQuery, name);
		List<Contact> contactList= new ArrayList<>();
		try(Connection connection =this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			contactList= this.getcontactData(result);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}

=======
>>>>>>> address_book_system_uc20
	private List<Contact> getcontactData(ResultSet resultSet) {
		List<Contact> contactList = new ArrayList<>();
		try {
			while(resultSet.next()) {
<<<<<<< HEAD
=======
				int id=resultSet.getInt("contact_id");
>>>>>>> address_book_system_uc20
				String firstName=resultSet.getString("first_name");
				String lastName=resultSet.getString("last_name");
				long phoneNumber=resultSet.getLong("phone_number");
				String email=resultSet.getString("email");
				String address=resultSet.getString("address");
				String city=resultSet.getString("city");
				String state=resultSet.getString("state");
				int zipCode=resultSet.getInt("zipCode");
<<<<<<< HEAD
				contactList.add(new Contact(firstName,lastName,new Address(address,city,state,zipCode),phoneNumber,email)); 		
=======
				contactList.add(new Contact(id,firstName,lastName,new Address(id,address,city,state,zipCode),phoneNumber,email)); 		
>>>>>>> address_book_system_uc20
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}
<<<<<<< HEAD

	public int updateContactEmail(String firstName, String email) {
		String sql = String.format("update contact set email= '%s' where first_name='%s';",email,firstName);
		try(Connection connection =this.getConnection()) {
			Statement statement = connection.createStatement();
			return statement.executeUpdate(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Contact getContactFromDB(String firstName) {
		Contact contact=null;
		if(contactStatement==null)
			this.prepareStatementForContact();
		try {
			contactStatement.setString(1, firstName);
			ResultSet result = contactStatement.executeQuery();
			contact=getcontactData(result).get(0);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return contact;
	}
	
	private void prepareStatementForContact() {
		try {
			Connection connection =this.getConnection();
			String sql= "select c.first_name, c.last_name, c.phone_number, c.email, a.address, a.city,a.state, a.zipCode"
					+ " from contact c, address	 a"
					+ " where c.contact_id=a.contact_id and c.first_name=?";
			contactStatement=connection.prepareStatement(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public int getContactBasedOnCity(String city) {
		List<Contact> listOfContacts=new ArrayList<Contact>();
		String sql= String.format("select c.first_name, c.last_name, c.phone_number, c.email, a.address, a.city,a.state, a.zipCode"
								+ " from contact c, address	 a"
							+   " where c.contact_id=a.contact_id and city='%s'", city);
=======
	
	public List<Contact> getContactData(String firstName) {
		List<Contact> contactList = new ArrayList<>();	
		String sql=String.format("select c.contact_id, c.first_name, c.last_name, c.phone_number, c.email, a.address, a.city,a.state, a.zipCode"
						         + " from contact c, address a"
								+ " where c.contact_id=a.contact_id and c.first_name='%s';", firstName);
		try(Connection connection =this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			contactList= this.getcontactData(result);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}
	
	public List<Contact> readFromDB() throws SQLException{
		String sql = String.format("select c.contact_id, c.first_name, c.last_name, c.phone_number, c.email, a.address, a.city,a.state, a.zipCode"
				+ " from contact c, address	 a"
				+ " where c.contact_id=a.contact_id");
		List<Contact> contactList= new ArrayList<>();
		try(Connection connection =this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			contactList= this.getcontactData(result);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}

	public int updateContactEmail(String firstName, String newEmail) {
		String sql = String.format("update contact set email= '%s' where first_name='%s';",newEmail,firstName);
		try(Connection connection =this.getConnection()) {
			Statement statement = connection.createStatement();
			return statement.executeUpdate(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Contact> getContactsOnGivenDateRange(LocalDate startDate, LocalDate endDate) {
		List<Contact> listOfContacts=new ArrayList<>();
		String sql=String.format("select c.contact_id, c.first_name, c.last_name, c.phone_number, c.email, a.address, a.city,a.state, a.zipCode"
				+ "  from contact c, address a"
				+ " where c.contact_id=a.contact_id and c.date_added between '%s' and '%s';"
				,Date.valueOf(startDate),Date.valueOf(endDate));
		try(Connection connection =this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			listOfContacts= this.getcontactData(result);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return listOfContacts;
	}

	public List<Contact> getContactBasedOnCity(String city) {
		List<Contact> listOfContacts=new ArrayList<Contact>();
		String sql= String.format("select c.contact_id, c.first_name, c.last_name, c.phone_number, c.email, a.address, a.city,a.state, a.zipCode"
								+ " from contact c, address	 a "
							+   " where c.contact_id=a.contact_id and a.city='%s';", city);
>>>>>>> address_book_system_uc20
		try(Connection connection =this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			listOfContacts= this.getcontactData(result);
		}catch(SQLException e) {
			e.printStackTrace();
		}
<<<<<<< HEAD
		return listOfContacts.size();
	}

	public int getContactBasedOnState(String state) {
		List<Contact> listOfContacts=new ArrayList<Contact>();
		String sql= String.format("select c.first_name, c.last_name, c.phone_number, c.email, a.address, a.city,a.state, a.zipCode"
								+ " from contact c, address	 a"
							+   " where c.contact_id=a.contact_id and state='%s'", state);
=======
		return listOfContacts;
		
	}

	public List<Contact> getContactBasedOnState(String state) {
		List<Contact> listOfContacts=new ArrayList<Contact>();
		String sql= String.format("select c.contact_id, c.first_name, c.last_name, c.phone_number, c.email, a.address, a.city,a.state, a.zipCode"
								+ " from contact c, address	 a "
							+   " where c.contact_id=a.contact_id and a.state='%s';", state);
>>>>>>> address_book_system_uc20
		try(Connection connection =this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			listOfContacts= this.getcontactData(result);
		}catch(SQLException e) {
			e.printStackTrace();
		}
<<<<<<< HEAD
		return listOfContacts.size();
	}
	
	public Contact addNewContactToContacts(int contactId, String firstName, String lastName, long phoneNumber, String email, int addressId, String dateAdded, int addressBookId) {
		
=======
		return listOfContacts;
	}

	public Contact addContact(int contactId, String firstName, String lastName, Address address, long phoneNumber, String emailId,
			LocalDate dateAdded) {
>>>>>>> address_book_system_uc20
		int id = -1;
		Connection connection = null;
		Contact contact = null;
		
		try {
			connection = this.getConnection();
			connection.setAutoCommit(false);
		}
		catch(SQLException exception) {
			exception.printStackTrace();
		}
		try (Statement statement = connection.createStatement()){
			
<<<<<<< HEAD
			String sql = String.format("insert into contact (contact_id, first_name, last_name, phone_number, email) values ('%s', '%s', '%s', '%s', '%s')", contactId, firstName, lastName, phoneNumber, email);
=======
			String sql = String.format("INSERT INTO contact (contact_id, first_name, last_name, phone_number, email, date_added) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')", contactId, firstName, lastName, phoneNumber, emailId, dateAdded);
>>>>>>> address_book_system_uc20
			
			int rowAffected = statement.executeUpdate(sql, statement.RETURN_GENERATED_KEYS);
			if(rowAffected == 1) {
				ResultSet resultSet = statement.getGeneratedKeys();
				if(resultSet.next())
					id = resultSet.getInt(1);
			}
<<<<<<< HEAD
			contact = new Contact(id, firstName, lastName, phoneNumber, email);
=======
			contact= new Contact(id, firstName, lastName, phoneNumber, emailId);
>>>>>>> address_book_system_uc20
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
		}
		
		try(Statement statement = connection.createStatement()){
<<<<<<< HEAD

			String sqlQuery = String.format("insert into contact_type VALUES ('%s', '%s')",contactId, addressBookId);
			int rowAffected = statement.executeUpdate(sqlQuery);
			if (rowAffected == 1) {
				contact = new Contact(contactId, firstName, lastName, phoneNumber, email);
=======
			String address1=address.getAddress();
			String city=address.getCity();
			String state=address.getState();
			int zip=address.getZipCode();

			String sqlQuery = String.format("INSERT INTO address VALUES ('%s', '%s','%s','%s','%s')",contactId, address1,city,state,zip);
			int rowAffected = statement.executeUpdate(sqlQuery);
			if (rowAffected == 1) {
				contact = new Contact(contactId, firstName, lastName,address, phoneNumber, emailId,dateAdded);
>>>>>>> address_book_system_uc20
			}			
		}
		catch(SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
		}
		
		try {
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(connection != null)
				try {
					connection.close();
				} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return contact;
	}

<<<<<<< HEAD
=======
	
>>>>>>> address_book_system_uc20

}
