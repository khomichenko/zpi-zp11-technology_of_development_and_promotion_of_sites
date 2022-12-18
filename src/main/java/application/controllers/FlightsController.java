package application.controllers;

import application.model.Flight;
import application.services.FlightsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api
public class FlightsController {
    private final FlightsService service;

    @GetMapping(path ="/", produces = "application/json")
    @ApiOperation(value = "get all flights", tags = "flights")
    public @ResponseBody List<Flight> list() {
        return service.list();
    }
}
