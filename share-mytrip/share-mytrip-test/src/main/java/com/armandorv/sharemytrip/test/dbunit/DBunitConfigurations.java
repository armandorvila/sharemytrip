package com.armandorv.sharemytrip.test.dbunit;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Annotation to group several sources for dbunit test.
 *  
 * @author armandorv
 *
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface DBunitConfigurations {

	/**
	 * Array of dbunit sources.
	 */
	public DBUnitConfiguration[] value();
}
