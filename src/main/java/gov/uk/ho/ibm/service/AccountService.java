package gov.uk.ho.ibm.service;

import java.util.List;

import javax.security.auth.login.AccountException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.uk.ho.ibm.dao.AccountRepository;
import gov.uk.ho.ibm.exception.AccountServiceException;
import gov.uk.ho.ibm.model.Account;

@Service
public class AccountService {
	
	private final AccountRepository accountRepository;
	
	@Autowired
	public AccountService(final AccountRepository accountRepository)
	{
		this.accountRepository=accountRepository;
	}
	
	public void deleteAccount(final Long Id)
	{
		try {
		accountRepository.deleteById(Id);
		} catch(RuntimeException e)
		{
			throw new AccountServiceException("account id "+Id+ " does not exists");
		}
	}
	
	public Account addAccount(final Account account)
	{
		return accountRepository.save(account);
	}
	
	public List<Account> getAccounts()
	{
		return accountRepository.findAll();
	}

}
