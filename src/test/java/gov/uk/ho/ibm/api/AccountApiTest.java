package gov.uk.ho.ibm.api;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import gov.uk.ho.ibm.model.Account;
import gov.uk.ho.ibm.service.AccountService;

@RunWith(MockitoJUnitRunner.class)
public class AccountApiTest {

	@InjectMocks
	private AccountApi accountApi;
	@Mock
	private AccountService accountService;

	@Test
	public void addAccount_should_call_add_account() {
		// arrange
		final Account account = Account.builder().firstName("Dibyatanu").secondName("deb").accountNumber(123).build();
		when(accountService.addAccount(account)).thenReturn(account);

		// act
		final ResponseEntity<String> response = accountApi.addAccount(account);

		// assert
		verify(accountService).addAccount(ArgumentMatchers.eq(account));
		assertThat("unexpected status code",response.getStatusCode(), is(equalTo(HttpStatus.ACCEPTED)));
		assertThat("unexpected response body ",response.getBody(), is(equalTo("{\"message\":\"account has been sucessfully added\"}")));
	}
	
	@Test
	public void deleteAccount_should_call_delete_account()
	{
		// arrange
		doNothing().when(accountService).deleteAccount(anyLong());
		
		// act
		final ResponseEntity<String> response = accountApi.deleteAccount(1L);
	    
	    //assert
	    verify(accountService).deleteAccount(ArgumentMatchers.eq(1L));
	    assertThat("unexpected status code",response.getStatusCode(), is(equalTo(HttpStatus.ACCEPTED)));
	    assertThat("unexpected response body ",response.getBody(), is(equalTo("{\"message\":\"account sucessfully deleted\"}")));

	}
	
	@Test
	public void getAccounts_should_call_get_accounts()
	{
		//arrange 
		when(accountService.getAccounts()).thenReturn(Collections.emptyList());
		
		// act
	   final ResponseEntity<List<Account>> response = accountApi.getAccounts();
	   
	   verify(accountService).getAccounts();
	   assertThat("unexpected status code",response.getStatusCode(), is(equalTo(HttpStatus.OK)));
	}

}
