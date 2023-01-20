package il.ac.telhai.ds.misc;

import il.ac.telhai.ds.graph.Weighted;

import java.util.Date;

public class WeightedFriendship implements Weighted {
	Date start;
	Double weight;

	public WeightedFriendship(Date start, double weight) {
		this.start = start;
		this.weight= weight;
	}

	@Override
	public String toString() {
		return start.toString();
	}

	public double getWeight() {
		return weight;
	}
}