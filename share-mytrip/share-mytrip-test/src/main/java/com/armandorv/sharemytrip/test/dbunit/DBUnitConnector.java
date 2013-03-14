package com.armandorv.sharemytrip.test.dbunit;

import java.io.InputStream;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

/**
 * Class to make the dbunit related work.
 * 
 * @author armandorv
 *
 */
public class DBUnitConnector extends DataSourceBasedDBTestCase {

	private static Logger log = Logger.getLogger(DBUnitConnector.class);

	private String xmlDataFile;

	private DataSource dataSource;

	private IDataSet dataset;

	public DBUnitConnector(String xmlDataFile, DataSource dataSource) {
		super();
		this.xmlDataFile = xmlDataFile;
		this.dataSource = dataSource;
	}

	@Override
	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public IDataSet getDataSet() throws Exception {

		if (dataset == null) {
			log.debug("Loading data file : " + xmlDataFile);

			InputStream is = this.getClass().getClassLoader().getResourceAsStream(xmlDataFile);

			FlatXmlDataSetBuilder dataSetBuilder = new FlatXmlDataSetBuilder();
			dataSetBuilder.setColumnSensing(true);
			dataset = dataSetBuilder.build(is);
		}

		return dataset;
	}

	public IDatabaseConnection getDBUnitConnection() throws Exception {
		return super.getConnection();
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
