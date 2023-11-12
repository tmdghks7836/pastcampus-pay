package com.fastcampuspay.money.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataMemberMoneyRepository extends JpaRepository<MemberMoneyJpaEntity, String> {

    Optional<MemberMoneyJpaEntity> findFirstByMembershipId(String membershipId);
}
