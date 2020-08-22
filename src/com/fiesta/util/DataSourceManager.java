package com.fiesta.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceManager {
	private DataSource ds;

	private static DataSourceManager instance = new DataSourceManager();
	private DataSourceManager() {
		try {
			InitialContext ic = new InitialContext();
			ds = (DataSource)ic.lookup("java:comp/env/jdbc/mysql");
		} catch (NamingException e) {
			System.out.println("DataSourceManager :: " + e);
		}
	}
	public static DataSourceManager getInstance() {
		return instance;
	}

	public DataSource getConnection() {
		return ds;
	}
}