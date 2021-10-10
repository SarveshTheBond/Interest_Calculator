package com.bestdeals.models;

import io.swagger.annotations.ApiModelProperty;


/**
 * Created by Sarvesh
 */
public class SavingRequest {

    @ApiModelProperty(notes = "Client ID", required = true)
    private int bsb;

    @ApiModelProperty(notes = "The Unique Account no.", required = true)
    private int identification;

    @ApiModelProperty(notes = "The Balance amount for given month", required = true)
    private double balance;

    public SavingRequest() {
    }

	public int getBsb() {
		return bsb;
	}

	public void setBsb(int bsb) {
		this.bsb = bsb;
	}

	public int getIdentification() {
		return identification;
	}

	public void setIdentification(int identification) {
		this.identification = identification;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "SavingRequest [bsb=" + bsb + ", identification=" + identification + ", balance=" + balance + "]";
	}

   
}
