package com.cloudsre.services.transport_service.domain.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cloudsre.services.transport_service.domain.Time;

public interface TimeRepository extends JpaRepository<Time, Long> {

	List<Time> getTimeByStopId(Long stopId);

}
