package com.fastcampuspay.money.adapter.out.persistence;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "member_money")
@Data
@NoArgsConstructor
public class MemberMoneyJpaEntity {

    @Id
    @GeneratedValue
    private Long memberMoneyId;

    private String memberId;

    private int balance;

    public MemberMoneyJpaEntity(String memberId, int balance) {
        this.memberId = memberId;
        this.balance = balance;
    }

    public void increaseMoney(int moneyAmount){

        balance += moneyAmount;
    }
}
