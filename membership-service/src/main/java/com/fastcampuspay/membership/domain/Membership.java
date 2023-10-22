package com.fastcampuspay.membership.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Membership {

    @Getter private final String membershipId;

    @Getter private final String name;

    @Getter  private final String email;

    @Getter private final String address;

    @Getter  private final boolean isValid;

    @Getter  private final boolean isCorp;

    public static Membership generateMember(MembershipId membershipId,
                                            MembershipName membershipName,
                                            MembershipEmail membershipEmail,
                                            MembershipAddress membershipAddress,
                                            MembershipIsValid membershipIsValid,
                                            MembershipIsCorp membershipIsCorp){

        return new Membership(
                membershipId.membershipId,
                membershipName.membershipName,
                membershipEmail.membershipEmail,
                membershipAddress.membershipAddress,
                membershipIsValid.membershipIsValid,
                membershipIsCorp.membershipIsCorp);
    }


    // member 정의
    // 오염이 되면 안되는 클래스, 고객정보, 핵심 도메인
    @Getter
    public static class MembershipId{

        public MembershipId(String value){
            this.membershipId = value;
        }

        String membershipId;
    }

    @Getter
    public static class MembershipName{

        public MembershipName(String value){
            this.membershipName = value;
        }

        String membershipName;
    }

    @Getter
    public static class MembershipEmail{

        public MembershipEmail(String value){
            this.membershipEmail = value;
        }

        String membershipEmail;
    }

    @Getter
    public static class MembershipAddress{

        public MembershipAddress(String value){
            this.membershipAddress = value;
        }

        String membershipAddress;
    }

    @Getter
    public static class MembershipIsValid{

        public MembershipIsValid(boolean value){
            this.membershipIsValid = value;
        }

        boolean membershipIsValid;
    }

    @Getter
    public static class MembershipIsCorp{

        public MembershipIsCorp(boolean value){
            this.membershipIsCorp = value;
        }

        boolean membershipIsCorp;
    }
}
