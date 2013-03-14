package com.armandorv.sharemytrip.business.test;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.armandorv.sharemytrip.test.dbunit.DBUnitExportCommand;

/**
 * Generate a db unit file from the data base using the datasource loaded in the
 * spring context.
 * 
 * @author armandorv
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:share-mytrip-infrastructure-context.xml")
public class GenerateXmlFiles {

	private static Logger log = Logger.getLogger(GenerateXmlFiles.class);

	@Autowired
	private DataSource dataSource;

	private DBUnitExportCommand exportCommand;

	@Before
	public void setUp() {
		exportCommand = new DBUnitExportCommand(dataSource,
				"src/test/resources/share-mytrip-dbunit.xml");
	}

	@Test
	public void generateFiles() {
		try {
			String succesMessage = exportCommand.execute();
			log.info(succesMessage);

		} catch (Exception e) {
			log.error("Error exporting data base : " + e.getMessage());
		}
	}
}