package gov.uk.ho.ibm.api;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

import gov.uk.ho.ibm.model.Account;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class AccountApiIT {

	@LocalServerPort
	private int port;
	private TestRestTemplate testRestTemplate;
	private static final String url="/rest/account/json";
	
	@Before
	public void setUp()
	{
		testRestTemplate= new TestRestTemplate();
	}
	@Test
	public void test_endpoints() throws JsonProcessingException {
		final Account account= Account.builder().firstName("Dibyatanu").secondName("deb").accountNumber(123).build();
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		final HttpEntity<Account> entity = new HttpEntity<Account>(account, headers);
		final ResponseEntity<String> responsePost=testRestTemplate.exchange(buildURL(url), HttpMethod.POST, entity,String.class);
		 assertThat("unexpected status code",responsePost.getStatusCode(), is(equalTo(HttpStatus.ACCEPTED)));
	
		 final ResponseEntity<String> responseDelete= testRestTemplate.exchange(buildURL(url+"/4"), HttpMethod.DELETE, null,String.class);
		 assertThat("unexpected status code",responseDelete.getStatusCode(), is(equalTo(HttpStatus.ACCEPTED)));
		 
		 final ParameterizedTypeReference<List<Account>> responseType = new ParameterizedTypeReference<List<Account>>() {};
		 final ResponseEntity<List<Account>> responseGet= testRestTemplate.exchange(buildURL(url), HttpMethod.GET, null,responseType);
		 assertThat("unexpected status code",responseGet.getStatusCode(), is(equalTo(HttpStatus.OK)));
	}
	
	private String buildURL(final String uri)
	{
	   return "http://localhost:"+port+uri;
	}
	
	

}
