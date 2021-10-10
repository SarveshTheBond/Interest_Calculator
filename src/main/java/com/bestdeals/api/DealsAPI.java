package com.bestdeals.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bestdeals.models.AccountCloseRequest;
import com.bestdeals.models.AccountMonthlyResponse;
import com.bestdeals.models.AccountOpenRequest;
import com.bestdeals.models.DailyRequest;
import com.bestdeals.service.DealService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Created by Sarvesh
 */
@RestController
@RequestMapping("/bestDeals")
@Api(value="BestDeals Bank ", description="BestDeals Bank wants Returns calculator API")
public class DealsAPI {

	@Autowired
	DealService dealService;

	@ApiOperation(value = "Create New Account", response = String.class)
	@RequestMapping(value = "/deals/createAccount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.ALL_VALUE)
	public ResponseEntity<String> createAccount(@RequestBody AccountOpenRequest accountOpenRequest) {

		dealService.createAccount(accountOpenRequest);
		return new ResponseEntity<String>("Account Created Successfully", HttpStatus.OK);
	}

	@ApiOperation(value = "Saving EOD Balance for all account", response = String.class)
	@RequestMapping(value = "/deals/saveBalance", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.ALL_VALUE)
	public ResponseEntity<String> saveBalance(@RequestBody DailyRequest dailyRequest) {

		dealService.saveDayEndData(dailyRequest);
		return new ResponseEntity<String>("EOD Report completed Successfully", HttpStatus.OK);
	}

	@ApiOperation(value = "Get savings Data based on Account no.", response = AccountMonthlyResponse.class)

	@ApiResponses(value = {

			@ApiResponse(code = 200, message = "Successfully retrieved list"),

			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),

			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),

			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })

	@RequestMapping(value = "/deals/{accountId}", method = RequestMethod.GET, produces = "application/json")
	public AccountMonthlyResponse showTotalInterest(@PathVariable Integer accountId, Model model) {

		AccountMonthlyResponse accumulateClientReturns = dealService.getAccountMonthlyData(accountId);
		return accumulateClientReturns;
	}
	
	@ApiOperation(value = "Close Existing Account", response = String.class)
	@RequestMapping(value = "/deals/closeAccount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.ALL_VALUE)
	public ResponseEntity<String> closeAccount(@RequestBody AccountCloseRequest accountCloseRequest) {

		double totalInterest =  dealService.closeAccount(accountCloseRequest);
		//return totalInterest;
		return new ResponseEntity<String>("Account Closed Successfully", HttpStatus.OK);
	}
}
