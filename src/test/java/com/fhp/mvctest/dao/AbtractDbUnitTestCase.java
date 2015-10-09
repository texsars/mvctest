package com.fhp.mvctest.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public abstract class AbtractDbUnitTestCase {
	private static IDatabaseConnection dbConn;
	private static IDataSet dataSet;
	
	@BeforeClass
	public static void init() throws DataSetException, IOException, SQLException {
		ApplicationContext ac =  new FileSystemXmlApplicationContext("classpath:service-context.xml");
		DataSource dataSource = (DataSource)ac.getBean("dataSource");
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbConn = new DatabaseConnection(conn);
//		IDataSet dataSet = new FlatXmlDataSet(new InputSource(TestDbUnit.class.getResourceAsStream("src/test/resources/t_user.xml")));
		dataSet = new FlatXmlDataSet(new File("src/test/resources/t_user.xml"));
		
		/*Backup data from database.*/
		IDataSet realDataSet = dbConn.createDataSet();
		FlatXmlDataSet.write(realDataSet, new FileWriter(new File("src/test/resources/real_data.xml")));
	}
	
	protected void EstablishConnection(Connection conn, File file) {
		
	}
}
