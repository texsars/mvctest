package com.fhp.mvctest.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.transaction.annotation.Propagation;

public abstract class AbtractDbUnitTestCase {
	protected static IDatabaseConnection dbConn;
	protected static IDataSet dataSet;
	protected static File backupFile; 
	
	@BeforeClass
	public static void init() throws DataSetException, IOException, SQLException {
		ApplicationContext ac =  new FileSystemXmlApplicationContext("classpath:service-context.xml");
		DataSource dataSource = (DataSource)ac.getBean("dataSource");
		Connection conn = null;

		conn = dataSource.getConnection();

		dbConn = new DatabaseConnection(conn);
//		IDataSet dataSet = new FlatXmlDataSet(new InputSource(TestDbUnit.class.getResourceAsStream("src/test/resources/t_user.xml")));
		dataSet = new FlatXmlDataSet(new File("src/test/resources/t_user.xml"));
		
		/*Backup data from database.*/
		IDataSet realDataSet = dbConn.createDataSet();
		FlatXmlDataSet.write(realDataSet, new FileWriter(new File("src/test/resources/real_data.xml")));
	}
	
	protected void EstablishConnection(Connection conn, File file) throws DataSetException, IOException {
		dbConn = new DatabaseConnection(conn);
		dataSet = new FlatXmlDataSet(new File("src/test/resources/t_user.xml"));
	}
	
	protected void Backup() throws SQLException, DataSetException, IOException {
		IDataSet realDataSet = dbConn.createDataSet();
		backupFile = File.createTempFile("backup", "xml");
		FlatXmlDataSet.write(realDataSet, new FileWriter(backupFile));
	}
	
	protected void BackupOneTable(String tableName) throws SQLException, IOException, DataSetException {
		String[] tableNames = new String[]{tableName};
		BackupCustomTable(tableNames);
	}
	
	protected void BackupCustomTable(String[] tableNames) throws SQLException, DataSetException, IOException {
		QueryDataSet queryDataSet = new QueryDataSet(dbConn);
		for(String tableName : tableNames) {
			queryDataSet.addTable(tableName);
		}
		backupFile = File.createTempFile("backup", "xml");
		FlatXmlDataSet.write(queryDataSet, new FileWriter(backupFile));
	}
	protected void PrepareForTest() throws DatabaseUnitException, SQLException {
		DatabaseOperation.CLEAN_INSERT.execute(dbConn, dataSet);
	}
	
	protected void Resume() throws DatabaseUnitException, SQLException, IOException {
		IDataSet realDataSet = new FlatXmlDataSet(backupFile);
		DatabaseOperation.CLEAN_INSERT.execute(dbConn, realDataSet);
	}
	
	@Before
	protected void setUp() throws DatabaseUnitException, SQLException {
		PrepareForTest();
	}
	
	@AfterClass
	protected void destroy() throws DatabaseUnitException, SQLException, IOException {
		Resume();
	}
}
