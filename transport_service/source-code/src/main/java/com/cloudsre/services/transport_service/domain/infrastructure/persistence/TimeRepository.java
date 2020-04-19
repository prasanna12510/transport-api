package com.cloudsre.services.transport_service.domain.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cloudsre.services.transport_service.domain.Time;

public interface TimeRepository extends JpaRepository<Time, Long> {

	List<Time> getTimeByStopId(Long stopId);

	@Query(value = "SELECT t.* FROM time t JOIN stop s on  t.stop_id=s.stop_id "
			+ "where stop_id=(select stop_id from stop where x=:x and y=:y ) "
			+ "ORDER BY t.line_id", nativeQuery = true)
	List<Time> findTimeLinesByCoordinates(int x, int y);

}
