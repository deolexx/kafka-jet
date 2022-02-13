package com.deo.jet.common.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoutePoint {
    private String name;
    private double x;
    private double y;

    public RoutePoint(AirPort airPort ) {
        this.x = airPort.getX();
        this.y = airPort.getY();
        this.name = airPort.getName();
    }
}