package com.bestdeals.models;

import java.util.Map;

/**
 * Created by Sarvesh
 */
public class AccountMonthlyResponse {

    private int accountId;
    
	private double totalInterest;
    
    private String status;

	Map<Integer, Double> interestMap;

	public AccountMonthlyResponse(int accountId,double totalInterest, String status, Map<Integer, Double> interestMap) {
		super();
		this.accountId = accountId;
		this.interestMap = interestMap;
		this.totalInterest = totalInterest;
		this.status= status;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public Map<Integer, Double> getInterestMap() {
		return interestMap;
	}

	public void setInterestMap(Map<Integer, Double> interestMap) {
		this.interestMap = interestMap;
	}

	public double getTotalInterest() {
		return totalInterest;
	}

	public void setTotalInterest(double totalInterest) {
		this.totalInterest = totalInterest;
	}
	
	 public String getStatus() {
			return status;
		}

	public void setStatus(String status) {
		  this.status = status;
		}
    
}
