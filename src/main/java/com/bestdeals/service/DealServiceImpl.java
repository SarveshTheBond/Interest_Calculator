package com.bestdeals.service;

import java.text.DecimalFormat;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bestdeals.models.Account;
import com.bestdeals.models.AccountCloseRequest;
import com.bestdeals.models.AccountMonthlyResponse;
import com.bestdeals.models.AccountOpenRequest;
import com.bestdeals.models.DailyRequest;
import com.bestdeals.models.SavingRequest;
import com.bestdeals.repository.DealsRepository;

/**
 * Created by Sarvesh
 */
@Service
public class DealServiceImpl implements DealService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DealServiceImpl.class);

	@Autowired
	private DealsRepository dealsRepository;

	@Override
	public void createAccount(AccountOpenRequest accountOpenRequest) {
		dealsRepository.createAccount(accountOpenRequest.getIdentification(), accountOpenRequest);

	}

	@Override
	public void saveDayEndData(DailyRequest dailyRequest) {
		// TODO Auto-generated method stub
		int reportDay = dailyRequest.getBalanceDate().getDate();
		int reportYear = dailyRequest.getBalanceDate().getYear();
		int reportMonth = dailyRequest.getBalanceDate().getMonth();
		String epochString = String.valueOf(reportYear).concat(String.valueOf(reportMonth))
				.concat(String.valueOf(reportDay));
		int epochMonth = Integer.valueOf(epochString);
		Double balance;
		int accountNo;
		int accountOpenDay;
		double interest;
		SavingRequest[] savingRequest = dailyRequest.getSavingRequest();
		for (SavingRequest r : savingRequest) {
			balance = r.getBalance();
			accountNo = r.getIdentification();
			Account accountData = getAccountOpenDate(accountNo);
			if (null != accountData && accountData.getStatus().equalsIgnoreCase("Active")) {
				accountOpenDay = accountData.getOpeningDate().getDate();
				interest = computeSimpleInterest(balance, reportDay - accountOpenDay);

				dealsRepository.saveAccount(accountNo, epochMonth, interest);
			} else {
				LOGGER.info(
						"Account no. " + accountNo + " is not Created and hence skippingf this for Saving Calculation");
			}

		}

	}

	/**
	 * getAccountOpenDate method used to get the date when account is opened
	 * 
	 * @param int     accountId
	 * @param returns AccountOpenRequest
	 */
	private Account getAccountOpenDate(int accountId) {
		return dealsRepository.getAccount(accountId);
	}

	/**
	 * computeSimpleInterest method calculates the Simple interest for a given day
	 * with assumption of 10% return
	 * 
	 * @param int     accountId
	 * @param returns AccountOpenRequest
	 */

	private double computeSimpleInterest(double balance, int time) {
		return (balance * 0.1) / 365;
	}

	@Override
	public AccountMonthlyResponse getAccountMonthlyData(int accountNo) {
		LOGGER.info("Get Monthly Savings Data for account " + accountNo);

		Map<Integer, Double> interestMap = dealsRepository.getAccountInterestReport(accountNo);
		Account accountData = getAccountOpenDate(accountNo);
		double totalInterest = computeTotalInterest(interestMap);
		AccountMonthlyResponse response = new AccountMonthlyResponse(accountNo, totalInterest,accountData.getStatus(), interestMap);
		return response;
	}

	/**
	 * computeTotalInterest method adds up the total interest for a given account
	 * 
	 * @param Map<Integer, Double> interestMap
	 * @param returns      double
	 */
	private double computeTotalInterest(Map<Integer, Double> interestMap) {
		double totalInterest = 0.0;
		if(null != interestMap) {
		for (Map.Entry<Integer, Double> entry : interestMap.entrySet())
			totalInterest = totalInterest + entry.getValue();
		}
		DecimalFormat f = new DecimalFormat("##.00");
		String fmtInterest = f.format(totalInterest);

		return Double.valueOf(fmtInterest);
	}

	@Override
	public double closeAccount(AccountCloseRequest accountOpenRequest) {
		
		Map<Integer, Double> interestMap = dealsRepository.getAccountInterestReport(accountOpenRequest.getIdentification());
		double totalInterest = computeTotalInterest(interestMap);
		dealsRepository.closeAccount(accountOpenRequest);
		
		return totalInterest;
	}
}
