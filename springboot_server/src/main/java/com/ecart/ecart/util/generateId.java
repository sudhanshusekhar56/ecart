package com.ecart.ecart.util;

import java.util.Random;

public class generateId {
	public static int getId(int n) {
		 if (n <= 0) {
	            throw new IllegalArgumentException("Length of the ID must be greater than 0");
	        }

	        // Minimum value for n digits
	        int min = (int) Math.pow(10, n - 1);
	        // Maximum value for n digits
	        int max = (int) Math.pow(10, n) - 1;

	        // Generate random number between min and max
	        Random random = new Random();
	        return random.nextInt((max - min) + 1) + min;
	}
}
