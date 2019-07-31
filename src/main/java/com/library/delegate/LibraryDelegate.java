package com.library.delegate;

import javax.ws.rs.core.Response;

import com.library.Dao.ILibraryDao;
import com.library.Dao.LibraryDaoImpl;
import com.library.pojo.AccountDetails;
import com.library.pojo.LibraryTransactionStatus;
import com.library.pojo.PaymentPojoClass;

public class LibraryDelegate {

	public Response libraryPayment(PaymentPojoClass paymentpojo) {
		
		ILibraryDao dao=new LibraryDaoImpl();
		AccountDetails acountdetails=dao.libraryPayment(paymentpojo);
		if(acountdetails == null) {
			System.out.println("Invalid Card number entered!! We don't have entered card details!!");
		}
		if(!acountdetails.getCvv().equals(paymentpojo.getCvv())) {
			System.out.println("Invalid cvv entered!!");
		}
		if(!acountdetails.getExpiry().equals(paymentpojo.getCvv())) {
			System.out.println("Invalid expiry entered!!");
		}
		if(acountdetails.getAmount() < paymentpojo.getAmount()){
			System.out.println("Invalid amount entered!!");
		}
		LibraryTransactionStatus trans = new LibraryTransactionStatus();
		double remainingBalance = acountdetails.getAmount() - paymentpojo.getAmount();
		//update balance to database table(account_details)
		
		boolean isUpdateBalance = dao.updateBalance(acountdetails.getCardNumber(), remainingBalance);
		if(isUpdateBalance) {
			trans.setAvailableBalance(remainingBalance);
			trans.setStatus("Success");
			trans.setTransactionId(12345);			
		} else {
			trans.setAvailableBalance(acountdetails.getAmount());
			trans.setStatus("Failed");
			trans.setTransactionId(12346);
		}
			
		return Response.status(200).entity(trans).build();
	}
	
}
