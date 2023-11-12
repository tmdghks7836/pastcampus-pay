package com.fastcampuspay.remittance.adapter.in.web;

import com.fastcampuspay.common.WebAdapter;
import com.fastcampuspay.remittance.application.port.in.FindRemittanceCommand;
import com.fastcampuspay.remittance.application.port.in.FindRemittanceUseCase;
import com.fastcampuspay.remittance.application.port.in.RequestRemittanceCommand;
import com.fastcampuspay.remittance.domain.RemittanceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
class FindRemittanceHistoryController {

	private final FindRemittanceUseCase findRemittanceUseCase;

	@GetMapping(path = "/remittance/{membershipId}")
	@ResponseStatus(HttpStatus.OK)
	List<RemittanceRequest> findRemittanceInfoByRemittanceId(@PathVariable String membershipId){

		FindRemittanceCommand command = FindRemittanceCommand.builder()
				.membershipId(membershipId)
				.build();

		return findRemittanceUseCase.findRemittanceHistory(command);
	}


/*	@GetMapping(path = "/com/fastcampuspay/remittance/history")
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<Object>  findRemittanceHistoryByMemberId(){


		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
	}

	// (API Aggregation, Banking + Money)
	@GetMapping(path = "/com/fastcampuspay/remittance/transferred-money")
	@ResponseStatus(HttpStatus.OK)
	ResponseEntity<Object>  findMoneyTransferringByRemittanceId(){


		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
	}*/
}
