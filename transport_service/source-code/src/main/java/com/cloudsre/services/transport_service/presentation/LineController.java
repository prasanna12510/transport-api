package com.cloudsre.services.transport_service.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cloudsre.services.transport_service.application.LineService;
import com.cloudsre.services.transport_service.domain.Line;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/city-navigation/v1/lines")
@Api(tags = { "lines" })
public class LineController {

	@Autowired
	private LineService lineService;
	
	
	@ApiOperation(value = "Health check for all lines", response = String.class)
	@GetMapping("/lines/health")
    public ResponseEntity<String> health() {
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.setContentType(MediaType.APPLICATION_JSON);
	    return ResponseEntity.ok()
	      .headers(responseHeaders)
	      .body("{\"status\":\"All Lines are healthy and OK !!!\"}");
    }

	@RequestMapping(value = "", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get a list of all lines for specific time and place.")
	public @ResponseBody ResponseEntity<List<Line>> getAllLine(
			@ApiParam(value = "The line x coordinate", required = true) @RequestParam(value = "x", required = true) Integer x,
			@ApiParam(value = "The line y coordinate", required = true) @RequestParam(value = "y", required = true) Integer y,
			@ApiParam(value = "The timestamp", required = true) @RequestParam(value = "date", required = true) String date) {
		return ResponseEntity.ok(lineService.getLinesByLocationAndTime(date, x, y));
	}

	@RequestMapping(value = "check-delay", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Returns delay in minutes")
	public @ResponseBody Map<String,String> checkLineDelation(
			@ApiParam(value = "The line name", required = true) @RequestParam(value = "lineName", required = true) String name) {

		 Map<String, String> response = new HashMap<>();
	        response.put("status", lineService.checkLineDelayation(name));
	        return response;
	
	}
	
	@RequestMapping(value = "next-line", method = RequestMethod.GET, produces = { "application/json"})
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get a list of all lines ariving next in time")
	public @ResponseBody ResponseEntity<List<Line>> getArrivingNextLine(
			@ApiParam(value = "The line x coordinate", required = true) @RequestParam(value = "x", required = true) Integer x,
			@ApiParam(value = "The line y coordinate", required = true) @RequestParam(value = "y", required = true) Integer y,
			@ApiParam(value = "The timestamp", required = true) @RequestParam(value = "date", required = true) String date) {
		return ResponseEntity.ok(lineService.getNextVehicleArriving(date, x, y));
	}

}
