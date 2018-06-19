package gov.uk.ho.ibm.service;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import gov.uk.ho.ibm.dao.AccountRepository;
import gov.uk.ho.ibm.model.Account;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
     @InjectMocks
	 private AccountService accountService;
     @Mock
	 private  AccountRepository accountRepository;
	
     @Test
	public void addAccount_should_call_save() {
		//arrange
	    final Account account= Account.builder().firstName("Dibyatanu").secondName("deb").accountNumber(123).build();
		when(accountRepository.save(account)).thenReturn(account);
		
		//act
		accountService.addAccount(account);
		
		//assert
		verify(accountRepository).save(ArgumentMatchers.eq(account));
		
     }
    
     @Test
     public void deleteAccount_should_call_delete()
     {
    	//arrange
    	 doNothing().when(accountRepository).deleteById(anyLong());
    	 
    	 //act
    	 accountService.deleteAccount(1L);
    	 
    	 //arrange
    	 verify(accountRepository).deleteById(ArgumentMatchers.eq(1L));
    	
     }
     
     @Test
     public void getAccounts_should_call_findall()
     {
    	//arrange
    	 when(accountRepository.findAll()).thenReturn(Collections.emptyList());
    	 
    	//act
    	 accountService.getAccounts();
    	
    	 //arrange
    	verify(accountRepository).findAll();
     }

}
