package com.fastcampuspay.common.event;

import lombok.Data;

@Data
public class RollbackFirmbankingFinishedEvent {

    private final String rollbackFirmbankingId;
    private final String membershipId;
    private final String firmbankingRequestId;

    public RollbackFirmbankingFinishedEvent(String rollbackFirmbankingId, String membershipId, String firmbankingRequestId) {
        this.rollbackFirmbankingId = rollbackFirmbankingId;
        this.membershipId = membershipId;
        this.firmbankingRequestId = firmbankingRequestId;
    }
}
