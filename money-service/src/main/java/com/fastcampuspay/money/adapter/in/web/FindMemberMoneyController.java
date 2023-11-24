package com.fastcampuspay.money.adapter.in.web;

import com.fastcampuspay.common.WebAdapter;
import com.fastcampuspay.money.application.port.in.*;
import com.fastcampuspay.money.domain.membermoney.MemberMoney;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class FindMemberMoneyController {

    private final FindMemberMoneyUseCase findMemberMoneyUseCase;

    @PostMapping("/money/member-money")
    @ResponseStatus(HttpStatus.OK)
    List<MemberMoney> findMemberMoneyListByMembershipIds(@RequestBody FindMemberMoneyListByMembershipIdsRequest request) {

        FindMemberMoneyByMembershipIdsCommand command = new FindMemberMoneyByMembershipIdsCommand(
                request.getMembershipIds()
        );

        return findMemberMoneyUseCase.findAllMemberMoneyByMembershipIds(command);
    }
}
