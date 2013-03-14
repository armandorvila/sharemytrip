package com.armandorv.sharemytrip.business.service.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Pointcuts {

	/**
	 * Point cut that include all methods annotated with ThrowsBusinessException
	 * or which are within any class annotated with ThrowsBusinessException.
	 */
	@Pointcut("@within(com.armandorv.sharemytrip.business.service.aspects.ThrowsBusinessException)"
			+ "|| @annotation(com.armandorv.sharemytrip.business.service.aspects.ThrowsBusinessException)")
	public void businessExceptionPointcut() {
	}

}
