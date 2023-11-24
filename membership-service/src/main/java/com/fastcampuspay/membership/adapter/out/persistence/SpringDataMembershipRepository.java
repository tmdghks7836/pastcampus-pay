package com.fastcampuspay.membership.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataMembershipRepository extends JpaRepository<MemberShipJpaEntity, Long> {

    List<MemberShipJpaEntity> findAllByAddress(String address);
}
