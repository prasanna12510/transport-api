package com.cloudsre.services.transport_service.domain.infrastructure.persistence;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cloudsre.services.transport_service.domain.Stop;

public interface StopRepository extends JpaRepository<Stop, Long> {
	
	@Query(value = "select * from stop where x=:x and y=:y order by stop_id", nativeQuery = true)
	Optional<Stop> getStopByCoordinates(int x, int y);
	
	

}
