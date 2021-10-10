package com.bestdeals.models;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;


/**
 * Created by Sarvesh
 */
public class AccountCloseRequest {

    @ApiModelProperty(notes = "Client ID", required = true)
    private int bsb;

    @ApiModelProperty(notes = "The Unique Account no.", required = true)
    private int identification;

    @ApiModelProperty(notes = "The date of CLosing an account", required = true)
    private Date closingDate;

    public AccountCloseRequest() {
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

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	@Override
	public String toString() {
		return "AccountOpenRequest [bsb=" + bsb + ", identification=" + identification + ", openingDate=" + closingDate
				+ "]";
	}

   
}
