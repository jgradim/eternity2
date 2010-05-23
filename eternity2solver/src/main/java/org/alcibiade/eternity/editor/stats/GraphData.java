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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GraphData {
	private Map<Integer, Map<String, List<Long>>> data;

	public GraphData() {
		data = new TreeMap<Integer, Map<String, List<Long>>>();
	}

	public void recordElapsed(int bestScore, double ratio, long elapsed) {
		String label = String.format("%.3f", ratio);
		if (!data.containsKey(bestScore)) {
			data.put(bestScore, new TreeMap<String, List<Long>>());
		}

		Map<String, List<Long>> ratioDurationsMap = data.get(bestScore);

		if (!ratioDurationsMap.containsKey(label)) {
			ratioDurationsMap.put(label, new ArrayList<Long>());
		}

		ratioDurationsMap.get(label).add(elapsed);
	}

	@Override
	public String toString() {
		StringBuilder text = new StringBuilder();

		text.append('\t');
		Map<String, List<Long>> headerMap = data.get(data.keySet().iterator().next());

		for (Map.Entry<String, List<Long>> ratioDurationEntry : headerMap.entrySet()) {
			String ratio = ratioDurationEntry.getKey();
			text.append(ratio);
			text.append('\t');
		}

		text.append('\n');

		for (Map.Entry<Integer, Map<String, List<Long>>> entry : data.entrySet()) {
			int bestScore = entry.getKey();

			text.append(bestScore);
			text.append('\t');
			Map<String, List<Long>> ratioDurationsMap = data.get(bestScore);

			for (Map.Entry<String, List<Long>> ratioDurationEntry : ratioDurationsMap.entrySet()) {
				List<Long> durations = ratioDurationEntry.getValue();

				long average = computeMedian(durations);

				text.append(average);
				text.append('\t');
			}

			text.append('\n');
		}

		return text.toString();
	}

	@SuppressWarnings("unused")
	private long computeAverage(List<Long> durations) {
		long sum = 0;

		for (long duration : durations) {
			sum += duration;
		}

		return sum / durations.size();
	}

	private long computeMedian(List<Long> durations) {
		long result = 0;

		if (!durations.isEmpty()) {
			List<Long> sotedDurations = new ArrayList<Long>(durations);
			Collections.sort(sotedDurations);
			result = sotedDurations.get(sotedDurations.size() / 2);
		}

		return result;
	}

}
