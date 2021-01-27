package com.clinicals.api.util;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JpaUtil {

	private static final Logger log = LoggerFactory.getLogger(JpaUtil.class);

	private JpaUtil() {

	}

	public static <T> T getNullOrValue(T lazy) {
		try {
			Hibernate.initialize(lazy);
			return lazy;
		} catch (EntityNotFoundException rte) {
			log.trace("getNullOrValue : Kopplad entitet saknas : {}", rte.getMessage());
			return null;
		} catch (Throwable t) {
			log.error("getNullOrValue : Exception vid hämtning av lazy-värde", t);
			return null;
		}

	}
}
