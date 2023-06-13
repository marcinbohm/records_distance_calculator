package com.devskiller.race;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

record Race(List<Leg> legs) {

	Race() {
		this(new ArrayList<>());
	}

	/**
	 * Adds leg to the race
	 *
	 * @param leg to add
	 * @throws IllegalStartPointException when start point of the leg is different from the finish point of the last leg
	 */
	void addLeg(Leg leg) throws IllegalStartPointException {
		if (legs.isEmpty() || leg.startLocation().equals(legs.get(legs.size() - 1).finishLocation())) {
			legs.add(leg);
		} else {
			throw new IllegalStartPointException("Start point of the leg is different from the finish point of the last leg");
		}
	}

	/**
	 * @return number of legs in the race
	 */
	int getLegsCount() {
		return legs.size();
	}

	Duration getTotalDuration() {
		return legs.stream()
				.map(Leg::duration)
				.reduce(Duration.ZERO, Duration::plus);
	}

	Duration getAverageLegDuration() {
		if (legs.isEmpty()) {
			return Duration.ZERO;
		} else {
			Duration totalDuration = getTotalDuration();
			return totalDuration.dividedBy(legs.size());
		}
	}

	double getTotalDistance() {
		return legs.stream()
				.mapToDouble(Leg::distance)
				.sum();
	}

	double getAverageLegDistance() {
		if (legs.isEmpty()) {
			return 0.0;
		} else {
			double totalDistance = getTotalDistance();
			return totalDistance / legs.size();
		}
	}
}
