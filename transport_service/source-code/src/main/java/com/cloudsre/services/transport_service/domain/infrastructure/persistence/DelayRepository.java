package com.cloudsre.services.transport_service.domain.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cloudsre.services.transport_service.domain.Delay;

public interface DelayRepository extends JpaRepository<Delay, Long> {

	Optional<Delay> findByLineName(String lineName);

}
