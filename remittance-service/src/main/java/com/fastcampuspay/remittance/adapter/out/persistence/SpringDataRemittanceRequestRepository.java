package com.fastcampuspay.remittance.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataRemittanceRequestRepository extends JpaRepository<RemittanceRequestJpaEntity, Long> {
}
