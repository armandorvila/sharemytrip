package com.armandorv.sharemytrip.business.service.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.armandorv.sharemytrip.business.exception.BusinessException;

@Aspect
@Order(0)
@Component
public class ThrowsBusinessExceptionAspect {

	private Logger log = Logger.getLogger(ThrowsBusinessExceptionAspect.class);

	@Around("Pointcuts.businessExceptionPointcut()")
	public Object executeCatchAndThrow(ProceedingJoinPoint joinPoint)
			throws Throwable {
		try {

			return joinPoint.proceed();

		} catch (BusinessException e) {
			log.error(message(joinPoint, e));
			throw e;

		} catch (Exception e) {
			log.error(message(joinPoint, e));
			throw new BusinessException(message(joinPoint, e), e);
		}

	}

	private String message(ProceedingJoinPoint joinPoint, Exception e) {
		String mess = "Error executing " + joinPoint.getSignature() + "Error "
				+ e.getMessage();
		return mess;
	}
}
