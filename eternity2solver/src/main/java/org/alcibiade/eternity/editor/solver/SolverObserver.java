package org.alcibiade.eternity.editor.solver;

public interface SolverObserver {
	public void solverStarted();

	public void solverEnded(boolean success);
}
