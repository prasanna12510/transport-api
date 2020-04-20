package com.cloudsre.services.transport_service.domain.infrastructure.persistence;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cloudsre.services.transport_service.domain.Line;

public interface LineRepository extends JpaRepository<Line, Long> {

	@Query(value = "SELECT l.* FROM line l JOIN time t on  l.line_id=t.line_id "
			+ "where stop_id=(select stop_id from stop where x=:x and y=:y ) "
			+ "and time =:time ORDER BY l.line_id", nativeQuery = true)
	List<Line> findLinesByTimeAndCoordinates(Timestamp time, int x, int y);
	
	//return the lineobject by LineId not primaryKey id
	Optional<Line> findByLineId(Long lineId);

}