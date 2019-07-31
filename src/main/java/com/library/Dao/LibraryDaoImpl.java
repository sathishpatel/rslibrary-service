package com.library.Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.library.pojo.AccountDetails;
import com.library.pojo.PaymentPojoClass;

public class LibraryDaoImpl implements ILibraryDao {

	public AccountDetails libraryPayment(PaymentPojoClass paymentpojo) {
		// get balance details from database
		try {
			Configuration configure = new Configuration().configure("hibernate.cfg.xml");
			SessionFactory sf = configure.buildSessionFactory();
			Session session = sf.openSession();

			Query query = session.createQuery("from AccountDetails where cardNumber = :cardNumber");
			query.setParameter("cardNumber", paymentpojo.getCardNumber());
			List<AccountDetails> list = query.list();
			session.close();
			if (!list.isEmpty()) {
				System.out.println("Balance in account : " + list.get(0).getAmount());
				return list.get(0);
			}
		} catch (Exception e) {

		}

		return null;
	}

	public boolean updateBalance(String cardNumber, double remainingBalance) {
		
		Configuration configure = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sf = configure.buildSessionFactory();
		Session session = sf.openSession();
		
		Query query = session.createQuery("update AccountDetails set amount = :amount" + " where cardNumber = :cardNumber");
		query.setParameter("amount", remainingBalance);
		query.setParameter("cardNumber", cardNumber);
		int result = query.executeUpdate();
		session.beginTransaction().commit();
		session.close();

		return false;
	}

}
