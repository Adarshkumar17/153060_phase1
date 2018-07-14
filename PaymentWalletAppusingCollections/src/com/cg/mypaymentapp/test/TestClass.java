package com.cg.mypaymentapp.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.service.WalletService;
import com.cg.mypaymentapp.service.WalletServiceImpl;

public class TestClass {

	static WalletService service;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception 
	{
		Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Adarsh", "8555824058",new Wallet(new BigDecimal(9000)));
		 Customer cust2=new Customer("Shaolin", "9989070165",new Wallet(new BigDecimal(6000)));
		 Customer cust3=new Customer("Ganesh", "9493054988",new Wallet(new BigDecimal(7000)));
				
		 data.put("8555824058", cust1);
		 data.put("9989070165", cust2);	
		 data.put("9493054988", cust3);	
			service= new WalletServiceImpl(data);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=InvalidInputException.class)
	public void testCreateAccount1() 
	{
		service.createAccount(null, "9942221102", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount2() 
	{
		service.createAccount("", "9942221102", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount3() 
	{
		service.createAccount("syed", "999", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount4() 
	{
		service.createAccount("syed", "", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount5() 
	{
		service.createAccount("", "", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount6() 
	{
		service.createAccount("Adarsh", "8555824058", new BigDecimal(9000));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount7() 
	{
		service.createAccount("Adarsh", "8555824058", new BigDecimal(-100));
	}
	
	
	@Test
	public void testCreateAccount8() 
	{
		Customer actual=service.createAccount("Kamal", "9698495659", new BigDecimal(5000));
		Customer expected=new Customer("Kamal", "9698495659", new Wallet(new BigDecimal(5000)));
		
		assertEquals(expected, actual);
	}
	
	
	@Test
	public void testCreateAccount9() 
	{
		Customer actual=service.createAccount("Swathi", "8754922472", new BigDecimal(0));
		Customer expected=new Customer("Swathi", "8754922472", new Wallet(new BigDecimal(0)));
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testCreateAccount10() 
	{
		Customer actual=service.createAccount("Swathi", "8754922472", new BigDecimal(5000.75));
		Customer expected=new Customer("Swathi", "8754922472", new Wallet(new BigDecimal(5000.75)));
		
		assertEquals(expected, actual);
	}


	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance11() 
	{
		service.showBalance(null);		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance12() 
	{
		service.showBalance("");		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance13() 
	{
		service.showBalance("12345");		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance14() 
	{
		service.showBalance("9900112218");		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance15() 
	{
		service.showBalance("9900112134");		
	}
	
	
	@Test
	public void testShowBalance16() 
	{
		Customer customer=service.showBalance("8555824058");
		BigDecimal expectedResult=new BigDecimal(9000);
		BigDecimal obtainedResult=customer.getWallet().getBalance();
		
		assertEquals(expectedResult, obtainedResult);
		
	}

	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer17() 
	{
		service.fundTransfer("9948484810", "9493054988", new BigDecimal(5000));		
	}
	
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer18() 
	{
		service.fundTransfer("9493054988", "9493054988", new BigDecimal(5000));		
	}

	
	@Test(expected=InsufficientBalanceException.class)
	public void testFundTransfer19() 
	{
		service.fundTransfer("8555824058", "9493054988", new BigDecimal(12000));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer20() 
	{
		service.fundTransfer("8555824058", "9493054988", new BigDecimal(0));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer21() 
	{
		service.fundTransfer("8555824058", "", new BigDecimal(0));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer22() 
	{
		service.fundTransfer("", "9493054988", new BigDecimal(500));		
	}
	
	
	@Test
	public void testFundTransfer23() 
	{
		Customer customer=service.fundTransfer("8555824058", "9493054988", new BigDecimal(500));
		BigDecimal expected=customer.getWallet().getBalance();
		BigDecimal actual=new BigDecimal(8500);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFundTransfer24() 
	{
		Customer customer=service.fundTransfer("8555824058", "9493054988", new BigDecimal(550.50));
		BigDecimal expected=customer.getWallet().getBalance();
		BigDecimal actual=new BigDecimal(8449.50);
		
		assertEquals(expected, actual);
	}
	
	
	@Test(expected=InsufficientBalanceException.class)
	public void testFundTransfer25() 
	{
		Customer customer=service.fundTransfer("8555824058", "9493054988", new BigDecimal(15000));	
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer26() 
	{
		service.fundTransfer("", "9493054988", new BigDecimal(-100));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer27() 
	{
		service.fundTransfer("", "", new BigDecimal(500));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer28() 
	{
		service.fundTransfer(null, null, new BigDecimal(0));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer29() 
	{
		service.fundTransfer("9493054988", null, new BigDecimal(50));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer30() 
	{
		service.fundTransfer("9493054988", "9989070165", new BigDecimal(0));		
	}

	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount31() 
	{
		service.depositAmount(null, new BigDecimal(500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount32() 
	{
		service.depositAmount("", new BigDecimal(500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount33() 
	{
		service.depositAmount("9942221102", new BigDecimal(500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount34() 
	{
		service.depositAmount("9942221102", new BigDecimal(0));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount35() 
	{
		service.depositAmount("9493054988", new BigDecimal(-1000));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount36() 
	{
		service.depositAmount("9493054988", new BigDecimal(200000));
	}
	

	
	@Test(expected=InsufficientBalanceException.class)
	public void testWithdrawAmount37() 
	{
		service.withdrawAmount("8555824058", new BigDecimal(15000));	
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testWithdrawAmount38() 
	{
		service.withdrawAmount("8555824058", new BigDecimal(0));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testWithdrawAmount39() 
	{
		service.withdrawAmount("8754922472", new BigDecimal(5000));	
	}

}
