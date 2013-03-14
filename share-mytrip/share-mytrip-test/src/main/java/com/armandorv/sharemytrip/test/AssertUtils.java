package com.armandorv.sharemytrip.test;

import java.lang.reflect.Method;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Assert;

/**
 * Clase de utilidad para ejecutar assertos.
 * 
 * @author armandorv
 *
 */
public class AssertUtils {

	private static Logger log = Logger.getLogger(AssertUtils.class);

	private AssertUtils() {
	}

	/**
	 * Assert that any element in the collection is not null.
	 *
	 * @param collection to check any element, collection is checked too.
	 */
	public static <T> void assertNotNullAny(Collection<T> collection) {
		Assert.assertNotNull("Collection is null.", collection);

		for (T element : collection) {
			Assert.assertNotNull("Any element is null.", element);
		}
	}

	public static <T> void assertNotNullAnyProperty(T object) {
		AssertProperties(object);
	}

	public static <T> void assertNotNullAnyProperty(Collection<T> collection) {

		for (T t : collection) {
			AssertProperties(t);
		}
	}

	private static void AssertProperties(Object object) {

		for (Method method : object.getClass().getMethods()) {

			if (method.getName().startsWith("get")) {
				try {
					Assert.assertNotNull("Propery " + method.getName() + " is null.", method.invoke(object));
				} catch (Exception e) {
					log.error("Property " + method + " caused an error.");
				}

			}
		}
	}
}
