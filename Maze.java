import java.io.*;
import java.util.Scanner;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Maze 
{
	final static int mazeWidth = 28; // 28 tiles wide
	final static int mazeHeight = 31; // 31 tiles high
	final static int WIDTH = 840;
	final static int HEIGHT = 930;
	final static int CELLSIZE = 30;
	String filename;
	char grid [][];
	
	public Maze(String file)
	{
		filename = file; 
		grid = new char [mazeHeight][mazeWidth]; // empty grid
	}
	
	public void initGrid() throws FileNotFoundException, IOException //  reads from txt file and fills 2d array
	{
		Scanner fin = new Scanner(new File(filename));
		for(int r = 0; r < mazeHeight; r++)
		{
			String inline = fin.nextLine(); // text file is read by line
			for(int c = 0; c < mazeWidth; c++) 
			{
				grid[r][c] = inline.charAt(c);
			}
		}
		fin.close();
		
		// test if the array is initialized properly
		
//		for(int i = 0; i < mazeHeight; i++) 
//		{
//			for(int j = 0; j < mazeWidth; j++)
//				System.out.print(grid[i][j]);
//			System.out.println();
//		}
	}
	
	public void loadGrid(GraphicsContext gc)
	{
		for(int r = 0; r < mazeHeight; r++)
			
	}
	
	public void render(GraphicsContext gc) // maintain maze
	{
		
	}
	
	public static void main (String args[]) throws FileNotFoundException, IOException
	{
		Maze maze = new Maze("level1.txt");
		maze.initGrid();
	}
}
