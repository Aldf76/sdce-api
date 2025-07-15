package com.sgce.sgce_api.controller;

import com.sgce.sgce_api.model.dashboard.DashboardDTO;
import com.sgce.sgce_api.service.DashboardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "http://localhost:5173") // Permite chamadas do front-end local (evita erro de CORS durante o desenvolvimento)
public class DashboardController {

    //teste
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }


    @GetMapping(produces = "application/json")
    public DashboardDTO obterResumo() {
        return dashboardService.obterResumo();
    }
}