package com.library.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.library.delegate.LibraryDelegate;
import com.library.pojo.PaymentPojoClass;

@Path("/")
public class PaymentLibrary {
	
	@POST
	@Path("/libraryPayment")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response libraryPayment(PaymentPojoClass paymentpojo) {
		
		LibraryDelegate librarydelegate=new LibraryDelegate();
		
		librarydelegate.libraryPayment(paymentpojo);
		System.out.println(paymentpojo.getCardNumber());
	
		String s="sucess";
		return Response.status(200).entity(s).build();
	}

}
