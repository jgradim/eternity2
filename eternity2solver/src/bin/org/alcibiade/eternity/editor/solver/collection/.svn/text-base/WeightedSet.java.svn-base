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

package org.alcibiade.eternity.editor.solver.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

import org.alcibiade.eternity.editor.solver.RandomFactory;

public class WeightedSet<T extends Comparable<T>> {
	// Items in decreasing weight order
	private SortedSet<WeightedItem> items = new TreeSet<WeightedItem>();

	public void put(T item, int weight) {
		items.add(new WeightedItem(item, weight));
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}

	public int size() {
		return items.size();
	}

	public T getFirst() {
		return items.first().getItem();
	}

	public T pickRandomBest() {
		int bestWeight = items.first().getWeight();
		List<T> bestItems = new ArrayList<T>();

		for (WeightedItem item : items) {
			if (item.getWeight() == bestWeight) {
				bestItems.add(item.getItem());
			} else {
				break;
			}
		}

		Random random = RandomFactory.getRandom();
		int randIndex = random.nextInt(bestItems.size());
		T result = bestItems.get(randIndex);
		return result;
	}

	public T pickRandom() {
		T result = null;

		Random random = RandomFactory.getRandom();
		int totalWeight = computeTotalWeight();
		int target = random.nextInt(totalWeight);

		for (WeightedItem item : items) {
			target -= item.getWeight();
			result = item.getItem();

			if (target < 0) {
				break;
			}
		}

		return result;
	}

	private int computeTotalWeight() {
		int total = 0;

		for (WeightedItem item : items) {
			total += item.getWeight();
		}

		return total;
	}

	private class WeightedItem implements Comparable<WeightedItem> {
		private T item;

		private int weight;

		public WeightedItem(T item, int weight) {
			super();
			this.item = item;
			this.weight = weight;
		}

		public T getItem() {
			return item;
		}

		public int getWeight() {
			return weight;
		}

		@Override
		public int compareTo(WeightedItem o) {
			int result = o.weight - weight;

			if (result == 0) {
				result = item.compareTo(o.item);
			}

			return result;
		}
	}

	@Override
	public String toString() {
		StringBuilder text = new StringBuilder();
		text.append("WeightedSet{");
		for (WeightedItem item : items) {
			text.append(item.getItem());
			text.append(":");
			text.append(item.getWeight());
			text.append(", ");
		}
		text.append("}");
		return text.toString();
	}
}
