package com.deo.jet.office.service;

import com.deo.jet.common.bean.Route;
import com.deo.jet.common.bean.RoutePath;
import com.deo.jet.common.bean.RoutePoint;
import com.deo.jet.office.provider.AirportsProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
@RequiredArgsConstructor
public class WaitingRoutesService {
    private final AirportsProvider airportsProvider;
    private final Lock lock = new ReentrantLock(true);
    private List<Route> list = new ArrayList<>();

    public List<Route> list() {
        return list;
    }

    public void add(Route route) {
        try {
            lock.lock();
            list.add(route);
        } finally {
            lock.unlock();
        }
    }

    public void remove(Route route) {
        try {
            lock.lock();
            list.removeIf(route::equals);
        } finally {
            lock.unlock();
        }
    }

    public Route convertLocationsToRoute(List<String> locations) {
        Route route = new Route();
        List<RoutePath> path = new ArrayList<>();
        List<RoutePoint> points = new ArrayList<>();

        locations.forEach(location -> {
            airportsProvider.getPorts().stream()
                    .filter(airPort -> airPort.getName().equals(location))
                    .findFirst()
                    .ifPresent(airPort -> points.add(new RoutePoint(airPort)));
        });

        for (int i = 0; i < points.size() - 1; i++) {
            path.add(new RoutePath());
        }
        path.forEach(row -> {
            int ind = path.indexOf(row);
            if (row.getFrom() == null) {
                row.setFrom(points.get(ind));
                if (points.size() > ind + 1) {
                    row.setTo(points.get(ind + 1));
                } else {
                    row.setTo(points.get(ind));
                }
            }
        });
        route.setPath(path);

        return route;
    }
}
