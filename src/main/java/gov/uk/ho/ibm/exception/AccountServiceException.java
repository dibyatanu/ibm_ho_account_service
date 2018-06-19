package gov.uk.ho.ibm.exception;

public class AccountServiceException extends RuntimeException {
	
	private static final long serialVersionUID = 5097345586309759471L;

	public AccountServiceException()
	{
		super();
	}
	
	public AccountServiceException(final String message)
	{
		super(message);
	}

}
