package com.bestdeals.models;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;


/**
 * Created by Sarvesh
 */
public class DailyRequest {

    @ApiModelProperty(notes = "The End of Month Data", required = true)
    private Date balanceDate;

    @ApiModelProperty(notes = "The Month End Account Data", required = true)
    private SavingRequest[] savingRequest;

    public DailyRequest() {
    }

	public Date getBalanceDate() {
		return balanceDate;
	}

	public void setBalanceDate(Date balanceDate) {
		this.balanceDate = balanceDate;
	}

	public SavingRequest[] getSavingRequest() {
		return savingRequest;
	}

	public void setSavingRequest(SavingRequest[] savingRequest) {
		this.savingRequest = savingRequest;
	}

	@Override
	public String toString() {
		return "MonthlyRequest [balanceDate=" + balanceDate + ", savingRequest=" + savingRequest + "]";
	}
   
}
