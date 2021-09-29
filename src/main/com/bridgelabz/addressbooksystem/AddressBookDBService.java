package main.com.bridgelabz.addressbooksystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class AddressBookDBService {
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

	private List<Contact> getcontactData(ResultSet resultSet) {
		List<Contact> contactList = new ArrayList<>();
		try {
			while(resultSet.next()) {
				String firstName=resultSet.getString("first_name");
				String lastName=resultSet.getString("last_name");
				long phoneNumber=resultSet.getLong("phone_number");
				String email=resultSet.getString("email");
				String address=resultSet.getString("address");
				String city=resultSet.getString("city");
				String state=resultSet.getString("state");
				int zipCode=resultSet.getInt("zipCode");
				contactList.add(new Contact(firstName,lastName,new Address(address,city,state,zipCode),phoneNumber,email)); 		
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}

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
		try(Connection connection =this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			listOfContacts= this.getcontactData(result);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return listOfContacts.size();
	}

	public int getContactBasedOnState(String state) {
		List<Contact> listOfContacts=new ArrayList<Contact>();
		String sql= String.format("select c.first_name, c.last_name, c.phone_number, c.email, a.address, a.city,a.state, a.zipCode"
								+ " from contact c, address	 a"
							+   " where c.contact_id=a.contact_id and state='%s'", state);
		try(Connection connection =this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			listOfContacts= this.getcontactData(result);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return listOfContacts.size();
	}
	
	public Contact addNewContactToContacts(int contactId, String firstName, String lastName, long phoneNumber, String email, int addressId, String dateAdded, int addressBookId) {
		
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
			
			String sql = String.format("insert into contact (contact_id, first_name, last_name, phone_number, email) values ('%s', '%s', '%s', '%s', '%s')", contactId, firstName, lastName, phoneNumber, email);
			
			int rowAffected = statement.executeUpdate(sql, statement.RETURN_GENERATED_KEYS);
			if(rowAffected == 1) {
				ResultSet resultSet = statement.getGeneratedKeys();
				if(resultSet.next())
					id = resultSet.getInt(1);
			}
			contact = new Contact(id, firstName, lastName, phoneNumber, email);
			
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

			String sqlQuery = String.format("insert into contact_type VALUES ('%s', '%s')",contactId, addressBookId);
			int rowAffected = statement.executeUpdate(sqlQuery);
			if (rowAffected == 1) {
				contact = new Contact(contactId, firstName, lastName, phoneNumber, email);
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


}
