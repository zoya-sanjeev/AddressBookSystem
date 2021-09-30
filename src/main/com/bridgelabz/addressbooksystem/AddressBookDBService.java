package main.com.bridgelabz.addressbooksystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;

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
	
	private List<Contact> getcontactData(ResultSet resultSet) {
		List<Contact> contactList = new ArrayList<>();
		try {
			while(resultSet.next()) {
				int id=resultSet.getInt("contact_id");
				String firstName=resultSet.getString("first_name");
				String lastName=resultSet.getString("last_name");
				long phoneNumber=resultSet.getLong("phone_number");
				String email=resultSet.getString("email");
				String address=resultSet.getString("address");
				String city=resultSet.getString("city");
				String state=resultSet.getString("state");
				int zipCode=resultSet.getInt("zipCode");
				contactList.add(new Contact(id,firstName,lastName,new Address(id,address,city,state,zipCode),phoneNumber,email)); 		
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}
	
	public List<Contact> readFromDB(String name) throws SQLException{
		String sql = String.format("select c.contact_id, c.first_name, c.last_name, c.phone_number, c.email, a.address, a.city,a.state, a.zipCode"
				+ " from contact c, address	 a, addressbook ab, contact_type t"
				+ " where c.contact_id=t.contact_id and ab.addressbook_id=t.addressbook_id and c.contact_id=a.contact_id"
				+ " and ab.addressbook_name='%s';",name);
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

}