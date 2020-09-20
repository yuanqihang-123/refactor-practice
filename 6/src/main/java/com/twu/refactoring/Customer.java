package com.twu.refactoring;

import java.util.ArrayList;
import java.util.Iterator;

public class Customer {
	private final double REGULAR_EXTRA_AMOUNT = 2;
	private final int REGULAR_RANT_DAY = 2;
	private final double REGULAR_EXTRA_RANT_RATE = 1.5;
	private final double NEW_RELEASE_EXTRA_RANT_RATE = 3;
	private final double CHILDREN_EXTRA_AMOUNT = 1.5;
	private final int CHILDREN_RANT_DAY = 3;
	private final double CHILDREN_EXTRA_RANT_RATE = 1.5;

	private final String name;
	private final ArrayList<Rental> rentalList = new ArrayList<Rental>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental arg) {
		rentalList.add(arg);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Iterator<Rental> rentals = rentalList.iterator();
		StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
		while (rentals.hasNext()) {
			double eachAmount = 0;
			Rental each = rentals.next();
			eachAmount = getEachAmount(eachAmount, each);

			frequentRenterPoints++;
			if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1) {
				frequentRenterPoints++;
			}

			result.append("\t").append(each.getMovie().getTitle())
					.append("\t").append(eachAmount)
					.append("\n");
			totalAmount += eachAmount;
		}

		result.append("Amount owed is ").append(totalAmount).append("\n");
		result.append("You earned ").append(frequentRenterPoints)
				.append(" frequent renter points");
		return result.toString();
	}

	private double getEachAmount(double eachAmount, Rental each) {
		switch (each.getMovie().getPriceCode()) {
			case Movie.REGULAR:
				eachAmount = getAmountCaseRegular(eachAmount, each);
				break;
			case Movie.NEW_RELEASE:
				eachAmount = getAmountCaseNewRelease(eachAmount, each);
				break;
			case Movie.CHILDRENS:
				eachAmount = getAmountCaseChildren(eachAmount, each);
				break;
		}
		return eachAmount;
	}

	private double getAmountCaseRegular(double eachAmount, Rental each) {
		eachAmount += REGULAR_EXTRA_AMOUNT;
		if (each.getDaysRented() > REGULAR_RANT_DAY) {
			eachAmount += (each.getDaysRented() - REGULAR_RANT_DAY) * REGULAR_EXTRA_RANT_RATE;
		}
		return eachAmount;
	}

	private double getAmountCaseNewRelease(double eachAmount, Rental each) {
		eachAmount += each.getDaysRented() * NEW_RELEASE_EXTRA_RANT_RATE;
		return eachAmount;
	}

	private double getAmountCaseChildren(double eachAmount, Rental each) {
		eachAmount += CHILDREN_EXTRA_AMOUNT;
		if (each.getDaysRented() > CHILDREN_RANT_DAY) {
			eachAmount += (each.getDaysRented() - CHILDREN_RANT_DAY) * CHILDREN_EXTRA_RANT_RATE;
		}
		return eachAmount;
	}

}