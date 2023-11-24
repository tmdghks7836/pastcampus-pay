package com.fastcampuspay.money.aggregation.application.port.out;


//밖에서 안으로 들어오는 정의

import com.fastcampuspay.money.aggregation.domain.membermoney.MemberMoney;

import java.util.List;

public interface GetMoneySumPort {

    List<MemberMoney> getMemberMoneyList(List<String> membershipIds);

}
