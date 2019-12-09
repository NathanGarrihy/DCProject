package com.shops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO {

	private DataSource mysqlDS;

	 // Constructor
	public DAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/shops";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}
	
public ArrayList<Store> loadStores() throws Exception {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		conn = mysqlDS.getConnection();
		
		String sql = "select * from product";
		
		stmt = conn.createStatement();
		
		rs = stmt.executeQuery(sql);
		
		ArrayList<Store> stores = new ArrayList<Store>();
		
		//	Processing of result set
		while(rs.next()) {
			Store s = new Store();
			s.setId(rs.getInt("ID"));
			s.setName(rs.getString("NAME"));
			s.setFounded(rs.getString("FOUNDED"));
		}
		return stores;
}

public void addStore(Store store) throws Exception {
	
	Connection conn = null;
	PreparedStatement stmt = null;
	
	conn = mysqlDS.getConnection();
	
	String sql = "insert into product values (?, ?)";
	
	stmt = conn.prepareStatement(sql);
	
	stmt.setInt(1, store.getId());
	stmt.setString(2,  store.getName());
	stmt.setString(3, store.getFounded());
	
}
}