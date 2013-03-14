package com.armandorv.sharemytrip.test;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

/**
 * Class to explore the spring context in runtime.
 * 
 * @author armandorv
 * 
 */
public class SpringContextLogger {

    /* Set this constants to the correct files configuration. */

    private static final String BEANS_FILES_PATTERN = "*/*.xml";

    private static final String PROPERTIES_FILES_PATTERN = "*.properties";

    private static final String FILES_PATTERN = "*.xml";

    private static Logger log = Logger.getLogger(SpringContextLogger.class);

    private static SpringContextLogger INTANCE = new SpringContextLogger();

    private static ApplicationContext applicationContext;

    /* *********************************************************** */

    private SpringContextLogger() {
    }

    public static SpringContextLogger getLogger(ApplicationContext context) {

	if (context == null) {
	    throw new IllegalArgumentException("EL contexto no debe ser null.");
	}
	applicationContext = context;

	return INTANCE;
    }

    public void contextBeans() {

	log.info("********************");

	String[] beans = applicationContext.getBeanDefinitionNames();

	for (String o : beans) {

	    log.info("________________________");
	    log.info("BEAN = " + o + "|  Type  = "
		    + applicationContext.getType(o));

	    String[] aliases = applicationContext.getAliases(o);

	    if (aliases != null && aliases.length > 0) {

		for (String a : aliases) {
		    log.info("\tAliased as: " + a);
		}
	    }
	}
	log.info("********************");

    }

    public void contextBeansFiles() throws IOException {
	log.info("********************");

	Resource[] resources = applicationContext
		.getResources(BEANS_FILES_PATTERN);

	for (Resource resource : resources) {
	    log.info(" Resource " + resource.getFilename());
	}
    }

    public void contextFiles() throws IOException {
	log.info("********************");

	Resource[] resources = applicationContext.getResources(FILES_PATTERN);

	for (Resource resource : resources) {
	    log.info(" Resource " + resource.getFilename());
	}
    }

    public void contextPropertiesFiles() throws IOException {
	log.info("********************");

	Resource[] resources = applicationContext
		.getResources(PROPERTIES_FILES_PATTERN);

	for (Resource resource : resources) {
	    log.info(" Resource " + resource.getFilename());
	}
    }

    public void contextBeansNumber() {
	log.info("*** Number of Beans = {} *** + \n"
		+ applicationContext.getBeanDefinitionCount());
    }

}