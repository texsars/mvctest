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
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public abstract class AbstractDbUnitTestCase {
	protected static IDatabaseConnection dbConn;
	protected static IDataSet dataSet;
	protected static File backupFile; 
	
	@BeforeClass
	public static void init() throws DataSetException, IOException, SQLException {
		ApplicationContext ac =  new FileSystemXmlApplicationContext("classpath:service-context.xml");
		DataSource dataSource = (DataSource)ac.getBean("dataSource");
		Connection conn = null;
		conn = dataSource.getConnection();

		EstablishDbUnitConnection(conn, new File("src/test/resources/t_user.xml"));
		
		Backup();
	}
	
	protected static void EstablishDbUnitConnection(Connection conn, File file) throws DataSetException, IOException {
		dbConn = new DatabaseConnection(conn);
		dataSet = new FlatXmlDataSet(file);
	}
	
	protected static void Backup() throws SQLException, DataSetException, IOException {
		IDataSet realDataSet = dbConn.createDataSet();
		backupFile = File.createTempFile("backup", "xml");
		FlatXmlDataSet.write(realDataSet, new FileWriter(backupFile));
	}
	
	protected static void BackupOneTable(String tableName) throws SQLException, IOException, DataSetException {
		String[] tableNames = new String[]{tableName};
		BackupCustomTable(tableNames);
	}
	
	protected static void BackupCustomTable(String[] tableNames) throws SQLException, DataSetException, IOException {
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
	
	protected static void Resume() throws DatabaseUnitException, SQLException, IOException {
		IDataSet realDataSet = new FlatXmlDataSet(backupFile);
		DatabaseOperation.CLEAN_INSERT.execute(dbConn, realDataSet);
	}
	
	@Before
	public void setUp() throws DatabaseUnitException, SQLException {
		PrepareForTest();
	}
	
	@AfterClass
	public static void destroy() throws DatabaseUnitException, SQLException, IOException {
		Resume();
	}
}
