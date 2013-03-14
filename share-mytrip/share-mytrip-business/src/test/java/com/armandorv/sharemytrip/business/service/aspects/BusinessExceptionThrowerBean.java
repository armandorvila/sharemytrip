package com.armandorv.sharemytrip.business.service.aspects;

import org.springframework.stereotype.Component;

import com.armandorv.sharemytrip.business.service.aspects.ThrowsBusinessException;

@ThrowsBusinessException
@Component
public class BusinessExceptionThrowerBean {

	public void throwAnyException() {
		throw new RuntimeException(
				"RuntimeException that would be caught by the business exception aspect.");
	}
}
