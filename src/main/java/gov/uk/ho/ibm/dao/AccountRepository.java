package gov.uk.ho.ibm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.uk.ho.ibm.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
