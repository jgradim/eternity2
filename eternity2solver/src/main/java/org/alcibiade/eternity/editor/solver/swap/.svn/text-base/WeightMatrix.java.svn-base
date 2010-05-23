/* This file is part of Eternity II Editor.
 * 
 * Eternity II Editor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Eternity II Editor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Eternity II Editor.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Eternity II Editor project is hosted on SourceForge:
 * http://sourceforge.net/projects/eternityii/
 * and maintained by Yannick Kirschhoffer <alcibiade@alcibiade.org>
 */

package org.alcibiade.eternity.editor.solver.swap;

import java.util.Iterator;
import java.util.Random;

import org.alcibiade.eternity.editor.solver.RandomFactory;

public class WeightMatrix implements Iterable<Long> {
	private long[] weights;
	private Random rand;
	private int size;

	public WeightMatrix(int size) {
		assert size > 1;
		this.weights = new long[size * size];
		this.rand = RandomFactory.getRandom();
		this.size = size;
	}

	public long getWeight(int offset) {
		return weights[offset];
	}

	public long getWeight(int x, int y) {
		return weights[x + y * size];
	}

	public void setWeight(int offset, long weight) {
		assert weight >= 0;
		weights[offset] = weight;
	}

	public void setWeight(int x, int y, long weight) {
		assert weight >= 0;
		weights[x + y * size] = weight;
	}

	public void multiplyWeight(int offset, long ratio) {
		assert ratio >= 0;
		weights[offset] *= ratio;
	}

	public void addIfNotZero(WeightMatrix matrix) {
		for (int i = 0; i < weights.length; i++) {
			if (weights[i] != 0) {
				weights[i] += matrix.weights[i];
			}
		}
	}

	public void addToAll(long weight) {
		for (int i = 0; i < weights.length; i++) {
			weights[i] += weight;
		}
	}

	public void multiplyWeight(int x, int y, long ratio) {
		assert ratio >= 0;
		weights[x + y * size] *= ratio;
	}

	public long getTotalWeight() {
		long total = 0;

		for (long weight : weights) {
			total += weight;
		}

		return total;
	}

	public int pick() {
		long totalWeight = getTotalWeight();

		if (totalWeight < 1) {
			dumpMatrix();
			throw new ArithmeticException("Total weight of the matrix is < 1");
		}

		long abspos = Math.abs(rand.nextLong()) % totalWeight;

		// System.out.println(String.format("%d/%d avg: %d", abspos,
		// totalWeight,
		// totalWeight / weights.length));

		int pos = -1;

		while (abspos >= 0) {
			pos++;
			abspos -= weights[pos];
		}

		return pos;
	}

	public void dumpMatrix() {
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				System.out.print(String.format("%6d", weights[x + size * y]));
			}

			System.out.println("");
		}
	}

	public Iterator<Long> iterator() {
		return new ArrayIterator(weights);
	}
}
