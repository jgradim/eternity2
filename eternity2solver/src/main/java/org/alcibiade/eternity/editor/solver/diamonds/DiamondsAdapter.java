package org.alcibiade.eternity.editor.solver.diamonds;

import java.util.ArrayList;
import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.solver.ClusterManager;
import org.alcibiade.eternity.editor.solver.EternitySolver;

import org.alcibiade.eternity.editor.model.QuadsFormatException;

public class DiamondsAdapter extends EternitySolver {

	private GridModel problemGrid;
	private GridModel solutionGrid;

	private long iterationsLimit = -1;

	protected long iterations = 0;
  
	public DiamondsAdapter(GridModel grid, GridModel solutionGrid, ClusterManager clusterManager) {
		super(clusterManager);
		this.problemGrid = grid;
		this.solutionGrid = solutionGrid;
	}
	
	@Override
	public void run() {
		notifyStart();
		clusterManager.showStartMessage();

		ArrayList<GridModel> features = this.problemGrid.getFeatures();
		for(GridModel g : features) {
			System.out.print(g.toQuadString());
			System.out.println("----");
		}
		
		clusterManager.showStats(1);
		notifyEnd(true);
	}
  
	@Override
	public long getIterations() {
		return iterations;
	}

	@Override
	public String getSolverName() {
		return "";
	}
}
