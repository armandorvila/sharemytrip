package com.armandorv.sharemytrip.test.dbunit;

import java.io.FileOutputStream;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlWriter;

/**
 * Class to export the content of any relational data base to a dbunit XML file.
 * 
 * @author armandorv
 * 
 */
public class DBUnitExportCommand {

	private static Logger log = Logger.getLogger(DBUnitExportCommand.class);

	private DataSource dataSource;

	private String xml;

	public DBUnitExportCommand(DataSource dataSource, String xml) {
		super();
		this.dataSource = dataSource;
		this.xml = xml;
	}

	public String execute() throws Exception {

		exportFullDB(dataSource, xml);

		return "Data base exported [ xml =" + xml + "]";
	}

	private void exportFullDB(DataSource datasource, String fileName)
			throws Exception {
		IDatabaseConnection connection = null;

		try {
			connection = new DatabaseConnection(datasource.getConnection());

			DatabaseSequenceFilter filter = new DatabaseSequenceFilter(
					connection);
			IDataSet datasetAll = new FilteredDataSet(filter,
					connection.createDataSet());
			QueryDataSet partialDataSet = new QueryDataSet(connection);

			String[] listTableNames = filter.getTableNames(datasetAll);

			for (int i = 0; i < listTableNames.length; i++) {

				final String tableName = listTableNames[i];
				partialDataSet.addTable(tableName);
			}

			// Specify the location of the flat file(XML)
			FlatXmlWriter datasetWriter = new FlatXmlWriter(
					new FileOutputStream(fileName));

			// Export the data
			datasetWriter.write(partialDataSet);

			log.info("Se ha completado el export a xml en fichero " + fileName);

		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

}