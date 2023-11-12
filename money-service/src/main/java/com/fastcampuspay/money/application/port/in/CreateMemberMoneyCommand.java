package com.fastcampuspay.money.application.port.in;

import com.fastcampuspay.common.SelfValidating;
import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;

/**
 * 포트
 * Port는 application 입장에서 consumer, 또는 application에서 나가거나/들어오는 end point라고 볼 수 있다.
 * 포트는 내부 비즈니스 영역을 외부 영역에 노출한 API이고 인바운드(Inbound)/아웃바운드(Outbound) 포트로 구분
 * 인바운드 포트 - 내부 영역 사용을 위해 노출된 API
 * 아웃바운드 포트 - 내부 영역이 외부 영역을 사용하기 위한 API
 * */
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class CreateMemberMoneyCommand extends SelfValidating<CreateMemberMoneyCommand> {

    @NotNull
    private String membershipId;

    public CreateMemberMoneyCommand(String targetMembershipId) {
        this.membershipId = targetMembershipId;

        this.validateSelf();
    }
}
