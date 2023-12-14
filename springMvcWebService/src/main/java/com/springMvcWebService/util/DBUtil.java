package com.springMvcWebService.util;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


public class DBUtil
{
	
	public DBUtil() {
		
	}
//	
	public DataSource getDataSource() throws NamingException, RemoteException {

		
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			dataSource.setUrl("jdbc:mysql://37.148.210.196:3306/mydb?useUnicode=true&characterEncoding=UTF-8");
			dataSource.setUsername("root");
			dataSource.setPassword("Mfm040761");
			return dataSource;
	}
	
	public static JdbcTemplate getJdbcTemplate() {
		try {
			DBUtil dbUtil = new DBUtil();
			return new JdbcTemplate(dbUtil.getDataSource());
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	public static Connection getConnection()
//	{
//		Connection conn = null;
//		try
//		{
//			if (inPortal)
//			{
//				InitialContext context = new InitialContext();
//				final DataSourceManager dsm = (DataSourceManager) context.lookup("dbpool");
//				String lookUpName = dsm.getSystemDataSourceDescriptor().getDataSourceName();
//				DataSource ds = (DataSource) context.lookup("jdbc/" + lookUpName);
//				conn = ds.getConnection();
//			}
//			else
//			{
//				String driverName = "com.mysql.jdbc.Driver"; // MySQL MM JDBC
//																// driver
//				Class.forName(driverName);
//				String url = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8"; // a
//																															// JDBC
//																															// url
//				String username = "root";
//				String passxxx = "mysql";
//				conn = DriverManager.getConnection(url, username, passxxx);
//			}
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		return conn;
//	}
	
	public static void close(Connection con, PreparedStatement stmt,
			ResultSet rset)
	{
		close(con);
		close(stmt);
		close(rset);
	}
	
	public static void close(Connection con, Statement stmt, ResultSet rset)
	{
		close(con);
		close(stmt);
		close(rset);
	}
	
	public static void close(Connection con, PreparedStatement stmt)
	{
		close(con);
		close(stmt);
	}
	
	public static void close(Connection con, Statement stmt)
	{
		close(con);
		close(stmt);
	}
	
	public static void close(Connection resource)
	{
		try
		{
			if (resource != null)
			{
				resource.close();
				resource = null;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement resource)
	{
		try
		{
			if (resource != null)
			{
				resource.close();
				resource = null;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void close(Statement resource)
	{
		try
		{
			if (resource != null)
			{
				resource.close();
				resource = null;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet resource)
	{
		try
		{
			if (resource != null)
			{
				resource.close();
				resource = null;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static long nextLongId(JdbcTemplate jdbcTemplate ,String table, String col)
	{
//		long maxid = jdbcTemplate.queryForLong("select max(" + col + ") as maxid from " + table + "");
		Long maxId = jdbcTemplate.queryForObject("select max(" + col + ") as maxid from " + table + "", Long.class);
		if(maxId == null)
			maxId = 0l;
		maxId += 1;
		return maxId;
	}
	
	public static int nextVersion(JdbcTemplate jdbcTemplate, String table, String col, String wherecol, String whereVal)
	{
//		int maxid = jdbcTemplate.queryForInt("select max(" + col + ") as maxid from " + table + " where " + wherecol + "=" + whereVal);
		Integer maxId = jdbcTemplate.queryForObject("select max(" + col + ") as maxid from " + table + " where " + wherecol + "=" + whereVal, Integer.class);
		if(maxId == null)
			maxId = 0;
		maxId += 1;
		return maxId;
	}
	
	public static void setString(PreparedStatement stmt, int index, String value)
			throws SQLException
	{
		if (value == null || value.trim().length() == 0) stmt.setNull(index, java.sql.Types.VARCHAR);
		else
			stmt.setString(index, value.trim());
	}
	
	public static void setInteger(PreparedStatement stmt, int index,
			Integer value) throws SQLException
	{
		if (value == null) stmt.setNull(index, java.sql.Types.INTEGER);
		else
			stmt.setInt(index, value);
	}
	
	public static void setDouble(PreparedStatement stmt, int index, Double value)
			throws SQLException
	{
		if (value == null) stmt.setNull(index, java.sql.Types.DOUBLE);
		else
			stmt.setDouble(index, value);
	}
	
	public static void setTimestamp(PreparedStatement stmt, int index,
			Date value) throws SQLException
	{
		if (value == null) stmt.setNull(index, java.sql.Types.TIMESTAMP);
		else
			stmt.setTimestamp(index, new Timestamp(value.getTime()));
	}
	
	public static void setLong(PreparedStatement stmt, int index, Long value)
			throws SQLException
	{
		if (value == null) stmt.setNull(index, java.sql.Types.BIGINT);
		else
			stmt.setLong(index, value);
	}
	
	public static void setShort(PreparedStatement stmt, int index, Integer value)
			throws SQLException
	{
		if (value == null) stmt.setNull(index, java.sql.Types.SMALLINT);
		else
			stmt.setShort(index, value.shortValue());
	}
	
	public static void setBoolean(PreparedStatement stmt, int index, Boolean value) throws SQLException
	{
		if (value == null) stmt.setNull(index, java.sql.Types.BOOLEAN);
		else
		{
			if (value) stmt.setString(index, "X");
			else{
				stmt.setNull(index, java.sql.Types.VARCHAR);
			}
		}
	}
	
	public static void setBytes(PreparedStatement stmt, int index, byte[] value) throws SQLException
	{
		if (value == null) stmt.setNull(index, java.sql.Types.BINARY);
		else
			stmt.setBytes(index, value);
	}
		
	
}
