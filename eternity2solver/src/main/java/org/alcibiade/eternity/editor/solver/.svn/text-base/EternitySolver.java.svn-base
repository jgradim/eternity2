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

package org.alcibiade.eternity.editor.solver;

import java.util.HashSet;
import java.util.Set;

public abstract class EternitySolver extends Thread {

	protected static final long SLOWMOTION_DELAY = 100;
	protected boolean interrupted = false;
	protected boolean slowmotion = false;
	protected ClusterManager clusterManager;

	private Set<SolverObserver> observers;

	public EternitySolver(ClusterManager clusterManager) {
		this.setDaemon(true);
		this.setName(getSolverName() + "-" + getId());
		this.clusterManager = clusterManager;
		this.observers = new HashSet<SolverObserver>();
	}

	public void addSolverObserver(SolverObserver observer) {
		this.observers.add(observer);
	}

	public void removeSolverObserver(SolverObserver observer) {
		this.observers.remove(observer);
	}

	protected ClusterManager getClusterManager() {
		return clusterManager;
	}

	@Override
	public void interrupt() {
		interrupted = true;
	}

	@Override
	public void start() {
		super.start();
	}

	public void setSlowMotion(boolean sm) {
		slowmotion = sm;
	}

	public abstract String getSolverName();

	public abstract long getIterations();

	protected void notifyStart() {
		for (SolverObserver observer : observers) {
			observer.solverStarted();
		}
	}

	protected void notifyEnd(boolean success) {
		for (SolverObserver observer : observers) {
			observer.solverEnded(success);
		}
	}
}
