package com.netchus.mapper;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class ConnectionTests {
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:oracle:sql://115.68.29.167:22/netchannel"; 
	private static final String USER = "root"; 
	private static final String PW = "sptcosjf@1824"; 
	
	@Test 
	public void testConnection() throws Exception{ 
		System.out.println(Class.forName(DRIVER));
	try(Connection conn = DriverManager.getConnection(URL, USER, PW)){ 
		System.out.println(conn); 
	// 콘솔창에서 연결정보를 출력하여 확인한다. 
	} catch (Exception e) { 
		// TODO: handle exception 
		} 
		
			}
		}


