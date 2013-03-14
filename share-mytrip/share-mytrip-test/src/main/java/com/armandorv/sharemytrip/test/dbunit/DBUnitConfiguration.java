package com.armandorv.sharemytrip.test.dbunit;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to set a dbunit source configuration.
 * A dbunit source requires a XML file name and a data source bean name.
 * 
 * @author armandorv
 *
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface DBUnitConfiguration {

	/**
	 * The name of the XML file.
	 */
	public String dataSet();

	/**
	 * The name of a data source bean bounded to the Spring context.
	 */
	public String dataSource();
}
