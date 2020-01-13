/**
 * 
 */
package com.br.lotohardapp;

import java.util.List;

/**
 * @author G0055135
 *
 */
public class Line {

	private int index;

	private List<Integer> numbers;

	private int priority;

	private int minSortedNumbers;

	private int maxSortedNumbers;

	private int countSortedNumbers;

	public Line(int index, int priority, int minSortedNumbers, int maxSortedNumbers) {
		this.index = index;
		this.priority = priority;
		this.minSortedNumbers = minSortedNumbers;
		this.maxSortedNumbers = maxSortedNumbers;
	}

	public Line(int index, int priority, int minSortedNumbers, int maxSortedNumbers, List<Integer> numbers) {
		this.index = index;
		this.priority = priority;
		this.numbers = numbers;
		this.minSortedNumbers = minSortedNumbers;
		this.maxSortedNumbers = maxSortedNumbers;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<Integer> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<Integer> numbers) {
		this.numbers = numbers;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getMinSortedNumbers() {
		return minSortedNumbers;
	}

	public void setMinSortedNumbers(int minSortedNumbers) {
		this.minSortedNumbers = minSortedNumbers;
	}

	public int getMaxSortedNumbers() {
		return maxSortedNumbers;
	}

	public void setMaxSortedNumbers(int maxSortedNumbers) {
		this.maxSortedNumbers = maxSortedNumbers;
	}

	public int getCountSortedNumbers() {
		return countSortedNumbers;
	}

	public void setCountSortedNumbers(int countSortedNumbers) {
		this.countSortedNumbers = countSortedNumbers;
	}

}
