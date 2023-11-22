package com.fastcampuspay.payment.adapter.in.web;


import com.fastcampuspay.common.WebAdapter;
import com.fastcampuspay.payment.application.port.in.RequestPaymentCommand;
import com.fastcampuspay.payment.application.port.in.RequestPaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestPaymentController {

    private final RequestPaymentUseCase requestPaymentUsecase;

    @PostMapping("/payment/request")
    void requestPayment(PaymentRequest request){

        requestPaymentUsecase.requestPayment(
                new RequestPaymentCommand(
                        request.getRequestMembershipId(),
                        request.getRequestPrice(),
                        request.getFranchiseId(),
                        request.getFranchiseFeeRate()
                )
        );
    }

}
