package com.fastcampuspay.money.application.port.in;


import com.fastcampuspay.money.domain.membermoney.MemberMoney;

import java.util.List;

public interface FindMemberMoneyUseCase {

    List<MemberMoney> findAllMemberMoneyByMembershipIds(FindMemberMoneyByMembershipIdsCommand command);
}
