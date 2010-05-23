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

package org.alcibiade.eternity.editor.stats;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Collector implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int SCREEN_WIDTH = 50;

	private List<Long> durations = new ArrayList<Long>();
	private List<Long> iterations = new ArrayList<Long>();

	private String name;
	private Collector reference;

	public Collector(String name) {
		this(name, null);
	}

	public Collector(String name, Collector reference) {
		this.name = name;
		this.reference = reference;
	}

	public synchronized void recordExecution(long millis, long iterations) {
		this.durations.add(millis);
		this.iterations.add(iterations);

		if (durations.size() > 1 && durations.size() % SCREEN_WIDTH == 1) {
			System.out.println();
		}

		System.out.print(".");
		System.out.flush();
	}

	public synchronized void recordExecutions(Collector collector) {
		this.durations.addAll(collector.durations);
		this.iterations.addAll(collector.iterations);
	}

	public synchronized void displayResults() {
		System.out.println("");
		System.out.println(name + " - " + computeStats(iterations, "it") + " - "
				+ computeStats(durations, "ms") + referenceStats());
	}

	private String referenceStats() {
		String result = "";

		if (reference != null) {
			long myGlobalIndex = getGlobalIndex();
			long refGlobalIndex = reference.getGlobalIndex();

			double ratio = 100. * myGlobalIndex / refGlobalIndex;
			result = String.format("  -  %5.1f%%", ratio);
		}

		return result;
	}

	private long getGlobalIndex() {
		long v = durations.get(iterations.size() / 2);
		return v;
	}

	private String computeStats(List<Long> data, String unit) {
		Collections.sort(data);

		String text = formatLong(data.get(0), unit) + "/"
				+ formatLong(data.get(data.size() / 2), unit) + "/"
				+ formatLong(data.get(data.size() - 1), unit);
		return text;
	}

	private static String formatLong(long value, String unit) {
		String result = Long.toString(value) + unit;

		while (result.length() < 9) {
			result = " " + result;
		}

		return result;
	}

}
