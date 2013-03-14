package com.armandorv.sharemytrip.test.dbunit;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * Listener que carga y descarga los datos de dbunit antes y después de cada
 * método de test, obteniendo la configuración de las anotaciones
 * 
 * @DBUnitConfiguration que posea la clase.
 * 
 *                      Obsérvese que @DBUnitConfigurations{
 *                                           {@DBUnitConfiguration(dataSource="X",
 *  dataSet="Y")}
 *                      equivale a @DBUnitConfiguration(dataSource="X",
 *                      dataSet="Y") y que si @DBUnitConfigurations y @DBUnitConfiguration
 *                      son configuradas, @DBUnitConfiguration será ignorada.
 * 
 *                      Importante : asegurarse de que existe en el contexto de
 *                      Spring sobre el que se ejecuta el test, el bean de
 *                      nombre X / dataSource="X" y que el tipo de X sea
 *                      assignable a javax.sql.DataSource.
 * 
 *                      Importante : al configurarse listener, se desactivan los
 *                      tres listeners por defecto que añade Spring
 *                      "DependencyInjectionTestExecutionListener, DirtiesContextTestExecutionListener, TransactionalTestExecutionListener"
 *                      , será necesario al menos
 *                      DependencyInjectionTestExecutionListener.
 * 
 * @author armandorv
 * 
 */
public class DBUnitTestExecutionListener extends AbstractTestExecutionListener {

	private List<DBUnitConnector> connectors = new ArrayList<DBUnitConnector>();

	@Override
	public void prepareTestInstance(TestContext testContext) throws Exception {

		DBUnitConfiguration[] configurations = getConfigurations(testContext);

		for (DBUnitConfiguration configuration : configurations) {
			DBUnitConnector connector = new DBUnitConnector(
					configuration.dataSet(), getDataSource(
							configuration.dataSource(), testContext));
			connectors.add(connector);
		}
	}

	private DataSource getDataSource(String name, TestContext testContext) {

		if (!testContext.getApplicationContext().containsBean(name)) {
			throw new IllegalArgumentException("Error getting data source.");
		}

		return testContext.getApplicationContext().getBean(name,
				DataSource.class);
	}

	private DBUnitConfiguration[] getConfigurations(TestContext testContext) {

		DBunitConfigurations sources = testContext.getTestClass()
				.getAnnotation(DBunitConfigurations.class);

		DBUnitConfiguration[] sourcesArray = {};

		if (sources != null) {
			sourcesArray = sources.value();
		}

		else {
			DBUnitConfiguration source = testContext.getTestClass()
					.getAnnotation(DBUnitConfiguration.class);

			if (source != null) {
				sourcesArray = new DBUnitConfiguration[1];
				sourcesArray[0] = source;
			}
		}

		if (sourcesArray.length == 0) {
			throw new IllegalArgumentException(
					"At least a dbunit source must be configured.");
		}

		return sourcesArray;
	}

	@Override
	public void beforeTestMethod(TestContext testContext) throws Exception {
		for (DBUnitConnector connector : connectors) {
			connector.setUp();
		}
	}

	@Override
	public void afterTestMethod(TestContext testContext) throws Exception {
		for (DBUnitConnector connector : connectors) {
			connector.tearDown();
		}
	}

}
