package com.fastcampuspay.money.adapter.out.persistence;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "member_money")
@Data
@NoArgsConstructor
public class MemberMoneyJpaEntity {

    @Id
    private String id;

    private String membershipId;

    private int balance;

    public MemberMoneyJpaEntity(String id, String membershipId, int balance) {
        this.id = id;
        this.membershipId = membershipId;
        this.balance = balance;
    }

    public void increaseMoney(int moneyAmount){

        balance += moneyAmount;
    }
}
