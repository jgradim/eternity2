package org.alcibiade.eternity.editor.solver.diamonds;

import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.solver.ClusterManager;
import org.alcibiade.eternity.editor.solver.EternitySolver;
import org.alcibiade.eternity.editor.solver.RandomFactory;

public class DiamondsAdapter extends EternitySolver {

  private TabuleiroLosangos diamondsBoard;
  
  private GridModel problemGrid;
	private GridModel solutionGrid;

	private long iterationsLimit = -1;

	protected long iterations = 0;
  
  public DiamondsAdapter(GridModel grid, GridModel solutionGrid, ClusterManager clusterManager) {
		super(clusterManager);
		this.problemGrid = grid;
		this.solutionGrid = solutionGrid;
		problemGrid.copyTo(solutionGrid);
		
		diamondsBoard = new TabuleiroLosangos(Boards.Tab12x12);
		
		/*
		TabuleiroLosangos tab = new TabuleiroLosangos(Tab12x12);
		
		long inicio = System.currentTimeMillis();
		
		
		if (tab.solve(1, 0))
		{
			long fim = System.currentTimeMillis();
			tab.tabuleiro.dump();
			System.out.println("Iterations: " + tab.iterations);
			System.out.println("Elapsed time = " + (fim-inicio)/1000.0 + "seconds.");
		}
		*/
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
