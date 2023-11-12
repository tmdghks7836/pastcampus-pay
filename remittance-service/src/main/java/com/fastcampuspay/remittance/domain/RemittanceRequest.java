package com.fastcampuspay.remittance.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RemittanceRequest {

	@Getter private final String remittanceRequestId;

	@Getter private final String remittanceFromMembershipId;

	@Getter private final String toBankName;

	@Getter private final String toBankAccountNumber;

	@Getter private int remittanceType;

	@Getter	private int amount;

	public static RemittanceRequest generateRemittanceRequest(
            RemittanceRequestId remittanceRequestId,
            RemittanceFromMembershipId remittanceFromMembershipId,
            ToBankName toBankName,
            ToBankAccountNumber toBankAccountNumber,
            RemittanceType remittanceType,
            Amount amount) {
		return new RemittanceRequest(
				remittanceRequestId.value,
                remittanceFromMembershipId.value,
                toBankName.value,
                toBankAccountNumber.value,
                remittanceType.value,
                amount.value
		);
	}

    public static class RemittanceRequestId {
        public RemittanceRequestId(String value) {
            this.value = value;
        }
        String value;
    }

	public static class RemittanceFromMembershipId {
		public RemittanceFromMembershipId(String value) {
			this.value = value;
		}
		String value;
	}

    public static class ToBankName {
        public ToBankName(String value) {
            this.value = value;
        }
        String value;
    }

    public static class ToBankAccountNumber {
        public ToBankAccountNumber(String value) {
            this.value = value;
        }
        String value;
    }

    public static class RemittanceType {
        public RemittanceType(int value) {
            this.value = value;
        }
        int value;
    }

    public static class Amount {
        public Amount(int value) {
            this.value = value;
        }
        int value;
    }
}
