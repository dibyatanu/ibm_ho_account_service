package gov.uk.ho.ibm.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gov.uk.ho.ibm.model.Account;
import gov.uk.ho.ibm.service.AccountService;

@RestController
@RequestMapping("/rest/account/json")
public class AccountApi {
	
	private AccountService accountService;
	
	@Autowired
	public AccountApi(final AccountService accountService)
	{
		this.accountService=accountService;
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE ,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addAccount(@RequestBody final Account account)
	{
		accountService.addAccount(account);
		return new ResponseEntity<String>("{\"message\":\"account has been sucessfully added\"}", HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(value="/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteAccount(@PathVariable("id") final Long id)
	{
		accountService.deleteAccount(id);
		return new ResponseEntity<String>("{\"message\":\"account sucessfully deleted\"}", HttpStatus.ACCEPTED);
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<List<Account>> getAccounts()
	{
		return new ResponseEntity<List<Account>>(accountService.getAccounts(), HttpStatus.OK);
	}
	
	

}
