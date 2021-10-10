package com.bestdeals.service;

import org.springframework.stereotype.Service;

import com.bestdeals.models.AccountCloseRequest;
import com.bestdeals.models.AccountMonthlyResponse;
import com.bestdeals.models.AccountOpenRequest;
import com.bestdeals.models.DailyRequest;

/**
 * Created by Sarvesh
 */
@Service
public interface DealService {

	/**
     * createAccount method used to persist new Account in System
     * @param AccountOpenRequest accountOpenRequest
     * @param returns void
     */
	void createAccount(AccountOpenRequest accountOpenRequest);
	
	/**
     * saveDayEndData method used to persist interest procured for given day when EOD report is received
     * @param DailyRequest dailyRequest
     * @param returns void
     */
	void saveDayEndData(DailyRequest dailyRequest);
	
	/**
     * getAccountMonthlyData method used to get the persisted Account Data based on account no
     * @param int accountNo
     * @param returns AccountMonthlyResponse
     */
	AccountMonthlyResponse getAccountMonthlyData(int accountNo);
	
	/**
     * closeAccount method used to close an Account and display total interest Procured
     * @param AccountCloseRequest accountOpenRequest
     * @param returns double
     */
	double closeAccount(AccountCloseRequest accountOpenRequest);

}
