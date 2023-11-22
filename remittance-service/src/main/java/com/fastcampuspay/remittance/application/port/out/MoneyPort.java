package com.fastcampuspay.remittance.application.port.out;


import com.fastcampuspay.remittance.adapter.out.service.money.MoneyInfo;

public interface MoneyPort {

	MoneyInfo getMoneyInfo(String membershipId);

	boolean requestMoneyRecharging(String membershipId, int amount);

	boolean requestMoneyIncrease(String membershipId, int amount);

	boolean requestMoneyDecrease(String membershipId, int amount);
}