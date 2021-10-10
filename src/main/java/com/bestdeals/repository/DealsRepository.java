package com.bestdeals.repository;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.bestdeals.models.Account;
import com.bestdeals.models.AccountCloseRequest;
import com.bestdeals.models.AccountOpenRequest;

/**
 * Created by Sarvesh
 */

@Repository
public class DealsRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(DealsRepository.class);


    private static final Map<Integer, Account> accountRepo;
    private static final Map<Integer, Map<Integer,Double>> interestRepo;

    static {
    	accountRepo = new ConcurrentHashMap<Integer, Account>();
    	interestRepo = new ConcurrentHashMap<Integer, Map<Integer,Double>>();
    }

    /**
     * createAccount method used to persist new Account in System
     * @param Integer accountId
     * @param AccountOpenRequest accountOpenRequest
     * @param returns void
     */
    public void createAccount(int accountId, AccountOpenRequest accountOpenRequest) {

        LOGGER.info("Create Account "+accountId+ "with Values "+accountOpenRequest.toString());

        if (accountRepo.get(accountId) == null) {
        	Account account = new Account(accountId, accountId, accountOpenRequest.getOpeningDate(), null, "Active");
			/*
			 * account.setBsb(accountId); account.setIdentification(accountId);
			 * account.setOpeningDate(accountOpenRequest.getOpeningDate());
			 * account.setStatus("Active");
			 */
        	accountRepo.put(accountId, account);
        } else {
        	accountRepo.put(accountId, accountRepo.get(accountId));
        }
        LOGGER.info("Account "+accountId+ "persisted Successfully");
    }
    
    /**
     * saveAccount method used to persist interest procured for given day
     * @param int accountId
     * @param int epochMonth
     * @param double interest
     * @param returns void
     */
	public void saveAccount(int accountId, int epochMonth, double interest) {
		Map<Integer, Double> interestMap;
		DecimalFormat f = new DecimalFormat("##.00");
		String fmtInterest = f.format(interest);

		LOGGER.info("Saving Account " + accountId + " for epoch month " + epochMonth);

		if (interestRepo.get(accountId) == null) {
			interestMap = new ConcurrentHashMap<Integer, Double>();
			interestMap.put(epochMonth, Double.valueOf(fmtInterest));
			LOGGER.info("First saving Interest for account " + accountId + " is enered ");
		} else {
			interestMap = interestRepo.get(accountId);
			interestMap.put(epochMonth, Double.valueOf(fmtInterest));
		}
		interestRepo.put(accountId, interestMap);
		
		LOGGER.info("Interest Saved for AccountID " + accountId + " for epoch month " + epochMonth + " with interest amount"+interest);
	}

	 /**
     * getAccount method used to get the persisted Account Data based on account Id
     * @param int accountId
     * @param returns AccountOpenRequest
     */
    public Account getAccount(int accountId) {

        return  accountRepo.get(accountId);
    }
    
    /**
     * getAccountInterestReport method used to get all the interest Procured for a given account
     * @param int accountId
     * @param returns Map<Integer, Double>
     */
   public Map<Integer, Double> getAccountInterestReport(int accountId) {

	   return interestRepo.get(accountId);
   }
   
   /**
    * closeAccount method used to close an Existing Account
    * @param Integer accountId
    * @param AccountOpenRequest accountOpenRequest
    * @param returns void
    */
   public void closeAccount(AccountCloseRequest accountCloseRequest) {

	   int accountId = accountCloseRequest.getIdentification();
       LOGGER.info("Closing Account "+accountId);

       if (accountRepo.get(accountId) == null) {
    	   LOGGER.info("Closing Account "+accountId+ " DOES NOT EXISTS");
       } else {
    	   Account account = accountRepo.get(accountId);
    	   account.setStatus("Closed");
    	   account.setClosingDate(accountCloseRequest.getClosingDate());
    	   accountRepo.put(accountId,account);
       }
       LOGGER.info("Account "+accountId+ "Closed Successfully");
   }
}
