package ca.sheridancollege.mridhasu.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.mridhasu.beans.Contact;

import lombok.*;

@Repository
@AllArgsConstructor
public class ContactsRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	public void testConnection() {
		System.out.println("Hello from contact repo.");
	}
	
//	public void testAddDrink() {
//		MapSqlParameterSource params = new MapSqlParameterSource();
//		String query = "INSERT INTO contacts VALUES ('Sumits Drink', 'orange juice', 1.5, 'pineapple', 1.5, 'mix properly')";
//		//jdbc.query -> get information
//		//jdbc.update -> change information
//		jdbc.update(query, params);
//		System.out.println("Drink added.");
//	}
	
	public void addContact(Contact c) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		String query = "INSERT INTO contacts (name, phno, addr, email) VALUES (:na, :ph, :ad, :em)";
		params.addValue("na", c.getName());
		params.addValue("ph", c.getPhno());
		params.addValue("ad", c.getAddr());
		params.addValue("em", c.getEmail());
		jdbc.update(query, params);
		System.out.println("Contact added.");
	}
	
//	public ArrayList<Drinks> getDrinks() {
//		ArrayList<Drinks> ds = new ArrayList<Drinks>();
//		MapSqlParameterSource params = new MapSqlParameterSource();
//		String query = "SELECT * FROM easy_drinks";
//		List<Map <String, Object>> rows = jdbc.queryForList(query, params);
//		for(Map<String, Object> row : rows) {
//			Drinks d = new Drinks();
//			d.setId((int)row.get("id"));
//			d.setDrinkName((String)row.get("drink_name"));
//			d.setMain1((String)row.get("main1"));
//			d.setAmount1((double)row.get("amount1"));
//			d.setMain2((String)row.get("main2"));
//			d.setAmount2((double)row.get("amount2"));
//			d.setDirections((String)row.get("directions"));
//			ds.add(d);
//		}
//		return ds;
//	}
	
	public ArrayList<Contact> getContacts () {
		MapSqlParameterSource params = new MapSqlParameterSource();
		String query = "SELECT * FROM contacts";
		ArrayList<Contact> contacts = (ArrayList<Contact>)jdbc.query(query, params, new BeanPropertyRowMapper<Contact>(Contact.class));
		return contacts;
	}
	
	public Contact getContactByID(int id) {
		Contact c = new Contact();
		MapSqlParameterSource params = new MapSqlParameterSource();
		String query = "SELECT * FROM contacts where id=:cid";
		params.addValue("cid", id);
		List<Map <String, Object>> rows = jdbc.queryForList(query, params);
		for(Map<String, Object> row : rows) {
			c.setId((int)row.get("id"));
			c.setName((String)row.get("name"));
			c.setPhno((String)row.get("phno"));
			c.setAddr((String)row.get("addr"));
			c.setEmail((String)row.get("email"));
		}
		return c;
	}
	
	public void editContact(Contact c) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		String query = "UPDATE contacts SET name=:na, phno=:ph, addr=:ad, email=:em where id=:cid";
		params.addValue("cid", c.getId());
		params.addValue("na", c.getName());
		params.addValue("ph", c.getPhno());
		params.addValue("ad", c.getAddr());
		params.addValue("em", c.getEmail());
		jdbc.update(query, params);
		System.out.println("Contact updated!!");
	}
	
	public void deleteContact(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		String query = "DELETE FROM contacts WHERE id=:cid";
		params.addValue("cid", id);
		jdbc.update(query, params);
		System.out.println("Contact deleted!!");
	}
}

