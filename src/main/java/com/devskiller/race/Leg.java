package com.devskiller.race;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

record Leg(Location startLocation, Location finishLocation, Duration duration, double distance) {

    Leg(Location startLocation, LocalDateTime startLocalTime, Location finishLocation, LocalDateTime finishLocalTime) {
        this(startLocation, finishLocation,
                calculateDuration(startLocation, startLocalTime, finishLocation, finishLocalTime),
                DistanceCalculator.betweenPoints(startLocation.point(), finishLocation.point()));
    }

    private static Duration calculateDuration(Location startLocation, LocalDateTime startLocalTime,
                                              Location finishLocation, LocalDateTime finishLocalTime) {
        ZonedDateTime startZonedDateTime = ZonedDateTime.of(startLocalTime, startLocation.zone());
        ZonedDateTime finishZonedDateTime = ZonedDateTime.of(finishLocalTime, finishLocation.zone());

        return Duration.between(startZonedDateTime, finishZonedDateTime).abs();
    }

}
