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

import org.alcibiade.eternity.editor.log.SolverLog;

public class ScoreStats {
	private int scoreMin;
	private int scoreMax;
	private int scoreBest;
	private long scoreSum;
	private long blockStart;
	private int scoreItems;
	private int blockSize;
	private SolverLog log;

	public ScoreStats(SolverLog log, int blockSize) {
		this.scoreBest = Integer.MIN_VALUE;
		this.blockSize = blockSize;
		this.log = log;
		reset();
	}

	private void reset() {
		scoreMin = Integer.MAX_VALUE;
		scoreMax = Integer.MIN_VALUE;
		scoreSum = 0;
		scoreItems = 0;
		blockStart = System.currentTimeMillis();
	}

	public void recordScore(int score) {
		if (score < scoreMin) {
			scoreMin = score;
		}

		if (score > scoreMax) {
			scoreMax = score;

			if (score > scoreBest) {
				scoreBest = score;
			}
		}

		scoreSum += score;
		scoreItems += 1;

		if (scoreItems == blockSize) {
			long now = System.currentTimeMillis();
			log.logMessage(String.format(" >> Scores: %3d < %03d < %3d < %03d   %dit/s", scoreMin,
					scoreSum / blockSize, scoreMax, scoreBest, blockSize * 1000L
							/ (now - blockStart)));
			reset();
		}

	}

}
