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

package org.alcibiade.eternity.editor.model;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.alcibiade.eternity.editor.gui.GridScoreRecorderObserver;

public class GridScoreRecorder extends Thread implements GridObserver {

	private static final int HISTORYSIZE = 128;
	private static final int INTERVALMS = 300;

	private static final long serialVersionUID = 1L;
	private GridModel gridmodel;

	private Map<Long, Integer> scorehistory;
	private int maxscorevalue;

	private Object updatelock = new Object();

	private Set<GridScoreRecorderObserver> observers;

	public GridScoreRecorder(GridModel grid) {
		super("ScoreRecorder");
		this.setDaemon(true);
		gridmodel = grid;
		grid.addGridObserver(this);
		scorehistory = new TreeMap<Long, Integer>();

		observers = new HashSet<GridScoreRecorderObserver>();
		maxscorevalue = gridmodel.countConnections();
	}

	public void addObserver(GridScoreRecorderObserver observer) {
		observers.add(observer);
	}

	public void removeObserver(GridScoreRecorderObserver observer) {
		observers.remove(observer);
	}

	private void notifyObservers() {
		for (GridScoreRecorderObserver observer : observers) {
			observer.notifyRecorderUpdated();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.alcibiade.eternity.editor.model.GridObserver#gridSizeUpdated(int)
	 */
	public void gridSizeUpdated(int size) {
		synchronized (updatelock) {
			maxscorevalue = gridmodel.countConnections();
			recordSample();
			notifyObservers();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.alcibiade.eternity.editor.model.GridObserver#gridUpdated()
	 */
	public void gridUpdated() {
		// Do nothing here
	}

	public Map<Long, Integer> getHistory() {
		return scorehistory;
	}

	public int getMaxValue() {
		return maxscorevalue;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (updatelock) {
				recordSample();
				notifyObservers();
			}

			try {
				sleep(INTERVALMS);
			} catch (InterruptedException e) {
				// Nothing to do here.
			}
		}
	}

	private void recordSample() {
		long now = System.currentTimeMillis();
		int score = gridmodel.countPairs();

		scorehistory.put(now, score);

		if (scorehistory.size() > HISTORYSIZE) {
			scorehistory.remove(scorehistory.keySet().iterator().next());
		}
	}
}
