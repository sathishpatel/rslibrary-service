package com.library.Dao;

import com.library.pojo.AccountDetails;
import com.library.pojo.PaymentPojoClass;

public interface ILibraryDao {
	
	public AccountDetails libraryPayment(PaymentPojoClass paymentpojo);

	public boolean updateBalance(String cardNumber, double remainingBalance);
}
