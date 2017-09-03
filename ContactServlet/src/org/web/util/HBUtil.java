package org.web.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * HB Factory is class which provied hibernate session factory as static method
 * get() method returns SessionFactory Object, You can use a method as
 * <b>HBFactory.get()</b>
 * 
 * @author vijay
 *
 */
public class HBUtil {
	private static SessionFactory sessionFactory;

	/**
	 * This method returns sessionFactory as static service
	 * 
	 * @return SessionFactory Object
	 */
	public static SessionFactory get() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		return sessionFactory;
	}
}
