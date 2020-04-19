package com.cloudsre.services.transport_service.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudsre.services.transport_service.domain.Delay;
import com.cloudsre.services.transport_service.domain.Line;

import java.util.ArrayList;
import java.util.Date;
import com.cloudsre.services.transport_service.domain.Stop;
import com.cloudsre.services.transport_service.domain.Time;
import com.cloudsre.services.transport_service.domain.infrastructure.persistence.DelayRepository;
import com.cloudsre.services.transport_service.domain.infrastructure.persistence.LineRepository;
import com.cloudsre.services.transport_service.domain.infrastructure.persistence.StopRepository;
import com.cloudsre.services.transport_service.domain.infrastructure.persistence.TimeRepository;
import com.cloudsre.services.transport_service.exception.ResourceNotFoundException;
import com.cloudsre.services.transport_service.utils.DateUtils;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LineService {

	private static final Logger log = LoggerFactory.getLogger(LineService.class);

	@Autowired
	private LineRepository lineRepository;
	@Autowired
	private DelayRepository delayRepository;
	@Autowired
	private StopRepository stopRepository;
	@Autowired
	private TimeRepository timeRepository;

	private static final String TEMPLATE = "%s minutes delayed";

	public Optional<Line> getLine(long id) {
		return lineRepository.findById(id);
	}

	/* Find a vehicle for a given time and X & Y coordinates */
	public List<Line> getLinesByLocationAndTime(String date, int x, int y) {

		return lineRepository.findVehiclesByTimeAndCoordinates(DateUtils.converToTimeStamp(date), x, y);

	}

	/* Indicate if a given line is currently delayed */
	public String checkLineDelayation(String name) {

		Optional<Delay> optionalDelay = delayRepository.findByLineName(name);
		if (optionalDelay.isPresent()) {
			Long delayTime = optionalDelay.map(Delay::getDelay)
					.orElseThrow(() -> new ResourceNotFoundException("no delay row for this line"));
			return String.format(TEMPLATE, delayTime);

		}

		return String.format(TEMPLATE, 0);

	}

	/* Return the vehicle arriving next at a given stop */
	private Timestamp getactualArrivalTime(Date currentTime, Optional<Delay> optionalDelay) {
		if (optionalDelay.isPresent() && currentTime != null) {
			Long delayTime = optionalDelay.map(Delay::getDelay)
					.orElseThrow(() -> new ResourceNotFoundException("no delay row for this line"));

			return DateUtils.addMinutesDelayed(currentTime, delayTime);

		}

		return DateUtils.converToTimeStamp("00:00:00");

	}

	public List<Line> getNextVehicleArriving(String currentTime, int x, int y) {

		Optional<Stop> currentStop = stopRepository.getStopByCoordinates(x, y);

		List<Time> timesForStop = timeRepository.getTimeByStopId(currentStop.get().getStopId());


		List<Line> nextArrivalLines = new ArrayList<>();

		for (Time time : timesForStop) {

			Optional<Line> optionalTimeForLine = lineRepository.findByLineId(time.getLineId());
			Optional<Delay> optionalDelayForLine = delayRepository
					.findByLineName(optionalTimeForLine.get().getLineName());

			if (getactualArrivalTime(time.getTime(), optionalDelayForLine)
					.after(DateUtils.converToTimeStamp(currentTime))) {


				nextArrivalLines.add(optionalTimeForLine.get());
			} // if

		} // for

		return nextArrivalLines;

	}

}