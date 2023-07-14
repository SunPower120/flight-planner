package io.codelex.flightplanner.Controllers;

import io.codelex.flightplanner.Flight.Flight;
import io.codelex.flightplanner.Services.AdminService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin-api")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/flights")
    public Flight createFlight(@Valid @RequestBody Flight flight) {
        return adminService.createFlight(flight);
    }

    @GetMapping("/flights")
    public List<Flight> getFlights() {
        return adminService.getFlights();
    }

    @DeleteMapping("/flights/{id}")
    public void deleteFlight(@PathVariable Long id) {
        adminService.deleteFlight(id);
    }

    @GetMapping("/flights/{id}")
    public Flight fetchFlight(@PathVariable Long id) {
        return adminService.fetchFlight(id);
    }
}