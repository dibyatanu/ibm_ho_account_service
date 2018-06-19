package gov.uk.ho.ibm.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

import gov.uk.ho.ibm.IbmHoAccountServiceApplication;
import gov.uk.ho.ibm.dao.AccountRepository;
import gov.uk.ho.ibm.model.Account;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { IbmHoAccountServiceApplication.class })
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class})
@Transactional
public class AccountServiceIT {

	@Autowired
	private AccountService accountService;
	@Autowired
	private  AccountRepository accountRepository;
	
	

	@Test
	public void addAccount_should_add_account() {
		
		//arrange
		final Account account= Account.builder().firstName("Dibyatanu").secondName("deb").accountNumber(123).build();
		
		//act
		final Account savedAccount=accountService.addAccount(account);
		
		//assert
		assertThat("Unexpected account number", savedAccount.getAccountNumber(), equalTo(123));
		assertThat("Unexpected first name", savedAccount.getFirstName(), equalTo("Dibyatanu"));
		assertThat("Unexpected second name ", savedAccount.getSecondName(), equalTo("deb"));
		assertThat("Unexpected id ", savedAccount.getId(), is(notNullValue()));
	}
	@Test
	public void deleteAccount_should_delete_account()
	{
		//arrange
		final Account account= Account.builder().firstName("Dibyatanu").secondName("deb").accountNumber(123).build();
		final Account savedAccount=accountService.addAccount(account);
	
		//act
		accountService.deleteAccount(savedAccount.getId());
		
		//assert
		assertFalse(accountRepository.existsById(savedAccount.getId()));
	}
	
	@Test
	public void getAccounts_should_get_all_accounts()
	{
		//arrange
	   final Account account= Account.builder().firstName("Dibyatanu").secondName("deb").accountNumber(123).build();
	   accountService.addAccount(account);
	   
	  //act
	 final  List<Account> accounts=accountService.getAccounts();
	 
	 //assert
		assertThat(accounts.size(), equalTo(1));
	}

}
