package com.bestdeals.models;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;


/**
 * Created by Sarvesh
 */
public class Account {

    private int bsb;

    private int identification;

    private Date openingDate;
   
    private Date closingDate;
    
    private String status;


    public Account() {
    }


	public Account(int bsb, int identification, Date openingDate, Date closingDate, String status) {
		super();
		this.bsb = bsb;
		this.identification = identification;
		this.openingDate = openingDate;
		this.closingDate = closingDate;
		this.status = status;
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


	public Date getOpeningDate() {
		return openingDate;
	}


	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}


	public Date getClosingDate() {
		return closingDate;
	}


	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Account [bsb=" + bsb + ", identification=" + identification + ", openingDate=" + openingDate
				+ ", closingDate=" + closingDate + ", status=" + status + "]";
	}

	

   
}
