package org.alcibiade.eternity.editor.solver.diamonds;

//import org.alcibiade.eternity.editor.solver.diamonds.Peca;

import java.util.Random;

public class Tabuleiro {
	
	Random generator = new Random(System.currentTimeMillis());
	Peca[][] pecas; //[lines][columns]
	int nLines;
	int nColumns;
	
	Tabuleiro(int nLines, int nColumns)
	{
		this.pecas = new Peca[nLines][nColumns];
		this.nLines = nLines;
		this.nColumns = nColumns;		
	}
	
	public void setPecas(Peca[][] ps)
	{
		for(int i = 0; i < nLines; i++)
		{
			for(int j = 0; j < nColumns; j++)
			{
				this.pecas[i][j] = new Peca(ps[i][j].getNorth() ,ps[i][j].getEast() ,ps[i][j].getSouth(), ps[i][j].getWest());
				this.pecas[i][j].setCoordinates(i, j);
			}
		}
	}
	
	public void scramble()
	{
		for(int i = 0; i < nLines; i++)
		{
			for(int j = 0; j < nColumns; j++)
			{
				pecas[i][j].rotate(generator.nextInt(4));
				swap(i, j, generator.nextInt(nLines), generator.nextInt(nColumns));
				pecas[i][j].rotate(generator.nextInt(4));
			}
		}
	}
	
	public void swap(int l1, int c1, int l2, int c2)
	{
		if(!pecas[l1][c1].equals(pecas[l2][c2]))
		{
			Peca p1 = new Peca(pecas[l1][c1].getNorth(), pecas[l1][c1].getEast(), pecas[l1][c1].getSouth(), pecas[l1][c1].getWest());
			Peca p2 = new Peca(pecas[l2][c2].getNorth(), pecas[l2][c2].getEast(), pecas[l2][c2].getSouth(), pecas[l2][c2].getWest());
			
			p1.setCoordinates(l2, c2);
			p2.setCoordinates(l1, c1);
			
			pecas[p1.getLine()][p1.getColumn()] = p1;
			pecas[p2.getLine()][p2.getColumn()] = p2;
		}
		else
		{
			//mesma peça, do nothing
		}
	}
	
	public void dump()
	{
		System.out.println("--TABULEIRO--");
		for(int i = 0; i < nLines; i++)
		{
			for(int j = 0; j < nColumns; j++)
			{
				System.out.print(" ------");
			}
			System.out.println("|");
			
			for(int j = 0; j < nColumns; j++)
			{
				if(pecas[i][j].getNorth() > 9)
					System.out.print("|  " + pecas[i][j].getNorth() + "  ");
				else
					System.out.print("|   " + pecas[i][j].getNorth() + "  ");
			}
			System.out.println("|");
			
			for(int j = 0; j < nColumns; j++)
			{
				if(pecas[i][j].getWest() > 9)
					System.out.print("|" + pecas[i][j].getWest() + "  ");
				else
					System.out.print("| " + pecas[i][j].getWest() + "  ");
					
				if(pecas[i][j].getEast() > 9)
					System.out.print(pecas[i][j].getEast());
				else
					System.out.print(" " + pecas[i][j].getEast());
			}
			System.out.println("|");
			
			for(int j = 0; j < nColumns; j++)
			{
				if(pecas[i][j].getSouth() > 9)
					System.out.print("|  " + pecas[i][j].getSouth() + "  ");
				else
					System.out.print("|   " + pecas[i][j].getSouth() + "  ");
			}
			System.out.println("|");
			
		}
		
		for(int j = 0; j < nColumns; j++)
		{
			System.out.print(" ------");
		}
		System.out.println(" ");
		
		System.out.println("--FIM TABULEIRO--");
	}
	
	public void dumpToClipboard()
	{
		for(int i = 0; i < nLines; i++)
		{
			for(int j = 0; j < nColumns; j++)
			{
				System.out.print(pecas[i][j].getNorth() + " ");
				System.out.print(pecas[i][j].getEast() + " ");
				System.out.print(pecas[i][j].getSouth() + " ");
				System.out.print(pecas[i][j].getWest() + " ");
			}
		}
		System.out.println();
	}
	
	public String dumpToString() {
	  String board = "";
	  for(int i = 0; i < nLines; i++)
		{
			for(int j = 0; j < nColumns; j++)
			{
				board += pecas[i][j].getNorth() + " ";
				board += pecas[i][j].getEast() + " ";
				board += pecas[i][j].getSouth() + " ";
				board += pecas[i][j].getWest() + " ";
			}
		}
		return board;
	}

}
