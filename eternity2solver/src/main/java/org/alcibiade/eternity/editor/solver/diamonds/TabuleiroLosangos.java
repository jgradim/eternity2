package org.alcibiade.eternity.editor.solver.diamonds;

import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.model.QuadsFormatException;
import org.alcibiade.eternity.editor.solver.ClusterManager;

import java.util.ArrayList;
import java.util.Hashtable;

public class TabuleiroLosangos {
	
	int[][] losangos;
	int depth = 0;
	Contador[][][][] pecas;
	// N E S W = number
	
	long iterations = 0;
	
	int nLosangos;
	
	int[] cCores; // quantos losangos ha de cada padrao
	ArrayList<Peca> listaPecas= new ArrayList<Peca>();
	Tabuleiro tabuleiro;
	int nLinhas;      // número de linhas
	int nColunasMin;  // n colunas nas linhas com menos losangos
	int nColunasMax;  // n colunas nas linhas com mais losangos
	
	GridModel grid; // Eternity II Editor board format
	ClusterManager clusterManager;
	
	TabuleiroLosangos(Peca[][] tabuleiro)
	{
		this.tabuleiro = new Tabuleiro(tabuleiro.length, tabuleiro[0].length);
		this.tabuleiro.setPecas(tabuleiro);
		
		this.tabuleiro.scramble();
		
		// "retirar" as peças para um monte = peças livres para usar
		for(int i = 0; i < tabuleiro.length; i++)
		{
			for(int j = 0; j < tabuleiro[i].length; j++)
			{
				listaPecas.add(new Peca(tabuleiro[i][j].getNorth(), tabuleiro[i][j].getEast(), tabuleiro[i][j].getSouth(), tabuleiro[i][j].getWest()));
				listaPecas.get(listaPecas.size() - 1).setCoordinates(i, j);
			}
		}
		
		this.nLinhas = tabuleiro.length + (tabuleiro.length - 1);
		this.nColunasMax = tabuleiro[0].length;
		this.nColunasMin = this.nColunasMax - 1;
		
		losangos = new int[nLinhas][];
		
		for(int i = 0; i < nLinhas; i++)
		{
			if(i%2 == 0)
				losangos[i] = new int[nColunasMin];
			else
				losangos[i] = new int[nColunasMax];
		}
		
		inicializaTab(tabuleiro);
	}
	
	public void setGridModel(GridModel grid) { this.grid = grid; }
	public void setClusterManager(ClusterManager clusterManager) { this.clusterManager = clusterManager; }
	
	private void inicializaTab(Peca[][] tab)
	{		
		nLosangos = 0;
		int nCores = 0;
		cCores = new int[100];  // array de contadores de padrões, indexado para o inteiro correspondente ao padrão
		
		// inicialização dos contadores de padrões
		for (int i = 0; i < tabuleiro.nLines; i++)
		{
			for (int j = 0; j < tabuleiro.nColumns; j++)
			{
				cCores[tabuleiro.pecas[i][j].getNorth()]++;
				cCores[tabuleiro.pecas[i][j].getEast()]++;
				cCores[tabuleiro.pecas[i][j].getSouth()]++;
				cCores[tabuleiro.pecas[i][j].getWest()]++;
				nCores = Math.max(nCores,
              						Math.max(tabuleiro.pecas[i][j].getNorth(), Math.max(tabuleiro.pecas[i][j].getEast(),
							          	Math.max(tabuleiro.pecas[i][j].getSouth(), tabuleiro.pecas[i][j].getWest()))));
			}
		}
		
	  nCores++;
	  for (int i = 0; i < nCores; i++)
	  {
		  cCores[i] /= 2;
		  nLosangos += cCores[i];
	  }
	  nLosangos -= cCores[0]; // remover as bordas do tabuleiro, não contam para losangos
	
	  System.out.println("Numero total de losangos: " + nLosangos);
	  System.out.println("Numero de losangos de cada cor (cor - numero): ");
	  for(int i = 0; i < nCores; i++)
	  {
		  System.out.println(i + " - " + cCores[i]);
	  }
		
		pecas = new Contador[nCores][nCores][nCores][nCores];
		Contador cont;
		
		for(int i = 0; i < listaPecas.size(); i++)
		{
			cont = pecas[listaPecas.get(i).getNorth()][listaPecas.get(i).getEast()][listaPecas.get(i).getSouth()][listaPecas.get(i).getWest()];
			if(cont != null)
				cont.inc();
			else
			{
				cont = new Contador();
				cont.inc();
				pecas[listaPecas.get(i).getNorth()][listaPecas.get(i).getEast()][listaPecas.get(i).getSouth()][listaPecas.get(i).getWest()] = cont;
				pecas[listaPecas.get(i).getWest()][listaPecas.get(i).getNorth()][listaPecas.get(i).getEast()][listaPecas.get(i).getSouth()] = cont;
				pecas[listaPecas.get(i).getSouth()][listaPecas.get(i).getWest()][listaPecas.get(i).getNorth()][listaPecas.get(i).getEast()] = cont;
				pecas[listaPecas.get(i).getEast()][listaPecas.get(i).getSouth()][listaPecas.get(i).getWest()][listaPecas.get(i).getNorth()] = cont;
			}
		}
		
		for(int i = 0; i < nLinhas; i++)
		{
			for(int j = 0; j < losangos[i].length; j++)
			{
				losangos[i][j] = -1;  // ainda nao tem cor
			}
		}
	}
	
	public boolean usaPeca(int Linha, int Coluna)
	{		
		//Top left corner
		if(Linha == 0 && Coluna == 0)
		{
			if(pecas[0][losangos[0][0]][losangos[1][0]][0] != null && pecas[0][losangos[0][0]][losangos[1][0]][0].getValor() > 0)
			{
				tabuleiro.pecas[Linha][Coluna] = new Peca(0,losangos[0][0],losangos[1][0],0);
				pecas[0][losangos[0][0]][losangos[1][0]][0].dec();
				return true;
			}
		}
			
			
		//top right corner
		else if(Linha == 0 && Coluna == (tabuleiro.nColumns-1))
		{
			if(pecas[0][0][losangos[1][nColunasMax-1]][losangos[0][nColunasMin-1]] != null && pecas[0][0][losangos[1][nColunasMax-1]][losangos[0][nColunasMin-1]].getValor() > 0)
			{
				tabuleiro.pecas[Linha][Coluna] = new Peca(0,0,losangos[1][nColunasMax-1],losangos[0][nColunasMin-1]);
				pecas[0][0][losangos[1][nColunasMax-1]][losangos[0][nColunasMin-1]].dec();
				return true;
			}
		}
		
		//Bottom left corner
		else if(Linha == (tabuleiro.nLines-1) && Coluna == 0)
		{
			if(pecas[losangos[nLinhas-2][0]][losangos[nLinhas-1][0]][0][0] != null && pecas[losangos[nLinhas-2][0]][losangos[nLinhas-1][0]][0][0].getValor() > 0)
			{
				tabuleiro.pecas[Linha][Coluna] = new Peca(losangos[nLinhas-2][0],losangos[nLinhas-1][0],0,0);
				pecas[losangos[nLinhas-2][0]][losangos[nLinhas-1][0]][0][0].dec();
				return true;
			}
		}
			
			
		//Bottom right corner
		else if(Linha == (tabuleiro.nLines-1) && Coluna == (tabuleiro.nColumns-1))
		{
			if(pecas[losangos[nLinhas-2][nColunasMax-1]][0][0][losangos[nLinhas-1][nColunasMin-1]] != null && pecas[losangos[nLinhas-2][nColunasMax-1]][0][0][losangos[nLinhas-1][nColunasMin-1]].getValor() > 0)
			{
				tabuleiro.pecas[Linha][Coluna] = new Peca(losangos[nLinhas-2][nColunasMax-1],0,0,losangos[nLinhas-1][nColunasMin-1]);
				pecas[losangos[nLinhas-2][nColunasMax-1]][0][0][losangos[nLinhas-1][nColunasMin-1]].dec();
				return true;
			}
		}
			
			
		//Top border
		else if(Linha == 0)
		{
			if(pecas[0][losangos[0][Coluna]][losangos[1][Coluna]][losangos[0][Coluna-1]] != null && pecas[0][losangos[0][Coluna]][losangos[1][Coluna]][losangos[0][Coluna-1]].getValor() > 0)
			{
				tabuleiro.pecas[Linha][Coluna] = new Peca(0,losangos[0][Coluna],losangos[1][Coluna],losangos[0][Coluna-1]);
				pecas[0][losangos[0][Coluna]][losangos[1][Coluna]][losangos[0][Coluna-1]].dec();
				return true;
			}
		}
			
				
		//Bottom border
		else if(Linha == (tabuleiro.nLines-1))
		{
			if(pecas[losangos[nLinhas-2][Coluna]][losangos[nLinhas-1][Coluna]][0][losangos[nLinhas-1][Coluna-1]] != null && pecas[losangos[nLinhas-2][Coluna]][losangos[nLinhas-1][Coluna]][0][losangos[nLinhas-1][Coluna-1]].getValor() > 0)
			{
				tabuleiro.pecas[Linha][Coluna] = new Peca(losangos[nLinhas-2][Coluna],losangos[nLinhas-1][Coluna],0,losangos[nLinhas-1][Coluna-1]);
				pecas[losangos[nLinhas-2][Coluna]][losangos[nLinhas-1][Coluna]][0][losangos[nLinhas-1][Coluna-1]].dec();
				return true;
			}
		}


		//Left border
		else if(Coluna == 0)
		{
			if(pecas[losangos[(Linha*2) - 1][0]][losangos[Linha*2][0]][losangos[Linha*2+1][0]][0] != null && pecas[losangos[(Linha*2) - 1][0]][losangos[Linha*2][0]][losangos[Linha*2+1][0]][0].getValor() > 0)
			{
				tabuleiro.pecas[Linha][Coluna] = new Peca(losangos[(Linha*2) - 1][0],losangos[Linha*2][0],losangos[Linha*2+1][0],0);
				pecas[losangos[(Linha*2) - 1][0]][losangos[Linha*2][0]][losangos[Linha*2+1][0]][0].dec();
				return true;
			}
		}
			
		//Right border
		else if(Coluna == (tabuleiro.nColumns - 1))
		{
			if(pecas[losangos[(Linha*2)-1][Coluna]][0][losangos[Linha*2 + 1][Coluna]][losangos[Linha*2][Coluna-1]] != null && pecas[losangos[(Linha*2)-1][Coluna]][0][losangos[Linha*2 + 1][Coluna]][losangos[Linha*2][Coluna-1]].getValor() > 0)
			{
				tabuleiro.pecas[Linha][Coluna] = new Peca(losangos[(Linha*2)-1][Coluna],0,losangos[Linha*2 + 1][Coluna],losangos[Linha*2][Coluna-1]);
				pecas[losangos[(Linha*2)-1][Coluna]][0][losangos[Linha*2 + 1][Coluna]][losangos[Linha*2][Coluna-1]].dec();
				return true;
			}
		}
		
		/*Everything*/ else
		{
			if(pecas[losangos[(Linha*2) - 1][Coluna]][losangos[Linha*2][Coluna]][losangos[(Linha*2) + 1][Coluna]][losangos[Linha*2][Coluna-1]] != null && pecas[losangos[(Linha*2) - 1][Coluna]][losangos[Linha*2][Coluna]][losangos[(Linha*2) + 1][Coluna]][losangos[Linha*2][Coluna-1]].getValor() > 0)
			{
				tabuleiro.pecas[Linha][Coluna] = new Peca(losangos[(Linha*2) - 1][Coluna],losangos[Linha*2][Coluna],losangos[(Linha*2) + 1][Coluna],losangos[Linha*2][Coluna-1]);
				pecas[losangos[(Linha*2) - 1][Coluna]][losangos[Linha*2][Coluna]][losangos[(Linha*2) + 1][Coluna]][losangos[Linha*2][Coluna-1]].dec();
				return true;
			}
		}
		
		return false;
	}
	
	public void libertaPeca(int Linha, int Coluna)
	{
		Peca temp = tabuleiro.pecas[Linha][Coluna];
		pecas[temp.getNorth()][temp.getEast()][temp.getSouth()][temp.getWest()].inc();
		tabuleiro.pecas[Linha][Coluna] = null;
	}
	
	// args: linha e coluna do losango que se vai colocar (NOTA: pode-se colocar 2 ou 1 losango/chamada da função solve)
	public boolean solve(int lLosango, int cLosango)
	{
		iterations++;
		
		//-------------
		try {	
  		grid.fromQuadString(this.tabuleiro.dumpToString()); // update grid
	  	clusterManager.submitSolution(grid);      // show solution
		} catch(QuadsFormatException e) {
		  e.printStackTrace();
		}
		//-------------
		
		int cDepth = (lLosango/2)*nColunasMax + (lLosango - (lLosango/2))*nColunasMin + cLosango; // dogma
		if(cDepth > depth)
		{
			depth = cDepth;
			//System.out.println("Best Depth = " + depth + "/" + (nLosangos + tabuleiro.nLines));
		}
		
		// se estás a usar o ultimo losango, so tens uma peça livre
		if(lLosango == nLinhas && cLosango == nColunasMin)
			return usaPeca(tabuleiro.nLines - 1, tabuleiro.nColumns - 1); // devolve true se conseguir coloca-la
		
		int nCores = cCores.length;
		
		for(int cor = 1; cor < nCores; cor++)
		{
			if(cCores[cor] > 0) // ainda ha losangos desta cor para colocar?
			{
				if(lLosango == nLinhas)   // BEGIN: ultima linha de losangos
				{
					losangos[lLosango-1][cLosango] = cor;
					cCores[cor]--;
					
					if(usaPeca((lLosango-1)/2,cLosango)) // usaPeca refere-se sempre ao tabuleiro de peças quadradas, e não de losangos
					{
						if(solve(lLosango, cLosango+1))
						{
							//System.out.println("Colocada Peca em (" + (lLosango-1)/2 + "," + cLosango + ")");
							return true;
						}
						
						libertaPeca((lLosango-1)/2,cLosango);
					}
					
					
					losangos[lLosango-1][cLosango] = -1; // refaz o que
					cCores[cor]++;                       // tinha feito
					
				}   // END: ultima linha de losangos
				
				else if(cLosango == nColunasMin)       // BEGIN: ultima coluna de losangos NOTA: nColunasMin == nColunasMax - 1
				{
					losangos[lLosango][cLosango] = cor;
					cCores[cor]--;
					
					if(usaPeca((lLosango-1)/2,cLosango))
					{
						if(solve(lLosango+2, 0))
						{
							//System.out.println("Colocada Peca em (" + (lLosango-1)/2 + "," + cLosango + ")");
							return true;
						}
						
						libertaPeca((lLosango-1)/2,cLosango);
					}
					
					
					losangos[lLosango][cLosango] = -1;
					cCores[cor]++;
				}                                      // END: ultima coluna de losangos
				
				else    // BEGIN: Situações normais a.k.a. colocar 2 losangos
				{
					losangos[lLosango][cLosango] = cor;
					cCores[cor]--;
					
					for(int cor2 = 1; cor2 < nCores; cor2++) // experimentar todas as combinações de cores = permutar todas as cores entre os 2 losangos
					{
						if(cCores[cor2] > 0)
						{
							losangos[lLosango-1][cLosango] = cor2;
							cCores[cor2]--;
							
							if(usaPeca((lLosango-1)/2,cLosango))
							{
								if(solve(lLosango, cLosango+1))
								{
									//System.out.println("Colocada Peca em (" + (lLosango-1)/2 + "," + cLosango + ")");
									return true;
								}
								
								libertaPeca((lLosango-1)/2,cLosango);
							}
							
							
							
							losangos[lLosango-1][cLosango] = -1;
							cCores[cor2]++;
						}
						
					}
					
					losangos[lLosango][cLosango] = -1;
					cCores[cor]++;
				}
			}
		}
		
		return false;
	}

}