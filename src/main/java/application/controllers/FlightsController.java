package application.controllers;

import application.controllers.params.FlightEventSeatsParams;
import application.controllers.params.FlightsParams;
import application.controllers.params.ReserveParams;
import application.controllers.results.FlightEventSeatResult;
import application.model.Flight;
import application.model.FlightEvent;
import application.model.FlightEventSeat;
import application.services.FlightEventSeatsService;
import application.services.FlightEventsService;
import application.services.FlightsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Math.*;
import static org.apache.commons.lang3.StringUtils.leftPad;

@RestController
@RequestMapping("/flights")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api
public class FlightsController {
    private final FlightsService flightsService;
    private final FlightEventsService flightEventsService;
    private final FlightEventSeatsService flightEventSeatsService;

    @PostMapping(path ="/list", produces = "application/json")
    @ApiOperation(value = "get all flights", tags = "flights")
    public @ResponseBody List<Flight> list(@RequestBody FlightsParams params) {
        List<Flight> list = flightsService.list();
        if (params.getSearch()!=null && params.getSearch().isEmpty()==false) {
            String search = params.getSearch();
            list = list.stream().filter((flight)->
                    flight.getTo().toLowerCase().contains(search.toLowerCase()) ||
                    flight.getFid().toLowerCase().contains(search.toLowerCase())
            ).collect(Collectors.toList());
        }
        return list;
    }

    @PostMapping(path ="/my", produces = "application/json")
    @ApiOperation(value = "get all flights", tags = "flights")
    public @ResponseBody List<FlightEventSeatResult> list(@RequestBody FlightEventSeatsParams params) {
        List<FlightEventSeatResult> list = new ArrayList<>();
        String ip = params.getIp();
        if (ip!=null && ip.isEmpty()==false) {
            for (FlightEventSeat seat: flightEventSeatsService.findByIp(ip)) {
                FlightEventSeatResult one = new FlightEventSeatResult(seat);
                List<FlightEvent> flightEvents = flightEventsService.findBySeat(seat);
                if (flightEvents.size()>0) {
                    FlightEvent event = flightEvents.get(0);
                    one.setEvent(event);
                    List<Flight> flights = flightsService.findByEvent(event);
                    if (flights.size()>0) {
                        one.setFlight(flights.get(0));
                    }
                }
                list.add(one);
            }
        }
        return list;
    }

    @PostMapping(path ="/reserve", produces = "application/json")
    @ApiOperation(value = "reserve the seats", tags = "reservation")
    public @ResponseBody List<FlightEventSeat> reserve(@RequestBody ReserveParams params, HttpServletRequest request) {
        List<FlightEventSeat> result = new ArrayList<>();
        String ip = request.getRemoteAddr();
        Optional<Flight> flightOpt = flightsService.findByFId(params.getFid());
        if (flightOpt.isPresent()) {
            Flight flight = flightOpt.get();
            List<FlightEvent> events = flight.getEvents();
            for (FlightEvent ev: events) {
                if (ev.getId().equals(params.getEventId())) {
                    List<FlightEventSeat> seats = ev.getSeats();
                    for(ReserveParams.ReserveSeatParams paramSeat: params.getSeats()) {
                        for (FlightEventSeat s: seats) if (s.getSid().equals(paramSeat.getSeatId())) {
                            s.setUsername(paramSeat.getUsername());
                            s.setPassport(paramSeat.getPassport());
                            s.setComment(paramSeat.getComment());
                            s.setTicket(gen(6));
                            s.setIp(paramSeat.getIp()!=null && paramSeat.getIp().isEmpty()==false ? paramSeat.getIp() : ip);
                            result.add(s);
                            break;
                        }
                    }
                }
            }
            flightsService.save(flight);
        }
        return result;
    }

    public static String gen(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = length; i > 0; i -= 12) {
            int n = min(12, abs(i));
            sb.append(leftPad(Long.toString(round(random() * pow(36, n)), 36), n, '0'));
        }
        return sb.toString();
    }
}
