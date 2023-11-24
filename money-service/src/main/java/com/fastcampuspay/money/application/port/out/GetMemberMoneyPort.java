package com.fastcampuspay.money.application.port.out;


//밖에서 안으로 들어오는 정의

import com.fastcampuspay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.fastcampuspay.money.domain.membermoney.MemberMoney;

import java.util.List;

public interface GetMemberMoneyPort {

    MemberMoneyJpaEntity getMemberMoney (MemberMoney.MembershipId memberMoneyId);

    List<MemberMoneyJpaEntity> getMemberMoneyListByMembershipIds (List<String> membershipIds);

}
