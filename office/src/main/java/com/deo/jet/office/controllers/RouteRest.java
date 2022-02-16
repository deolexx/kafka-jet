package com.deo.jet.office.controllers;

import com.deo.jet.common.bean.Route;
import com.deo.jet.office.service.PathService;
import com.deo.jet.office.service.WaitingRoutesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/routes")
public class RouteRest {
    private final WaitingRoutesService waitingRoutesService;
    private final PathService pathService;

    @PostMapping("route")
    public void addRoute(@RequestBody List<String> routeLocations) {
        Route route = pathService.convertLocationsToRoute(routeLocations);
        waitingRoutesService.add(route);
    }

}
