import java.io.*;
import java.util.Scanner;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Maze 
{
	final static int xtiles = 28; // 28 tiles wide
	final static int ytiles = 31; // 31 tiles high
	final static int WIDTH = 840; // pixel width
	final static int HEIGHT = 930; // pixel height
	final static int CELLSIZE = 30; // cell side length (square)
	
	final static int EMPTY = 0;
	final static int WALL = 1;
	final static int PELLET = 2;
	
	String filename;
	public int grid [][] = new int [HEIGHT][WIDTH]; //  pixel grid
	int maze [][]; // cell grid
	
	public Maze(String file)
	{
		filename = file; 
		maze = new int [ytiles][xtiles]; // empty maze
	}
	
	public void initMaze() throws FileNotFoundException, IOException //  reads from txt file and fills 2d array
	{
		Scanner fin = new Scanner(new File(filename));
		for(int r = 0; r < ytiles; r++)
		{
			String inline = fin.nextLine(); // text file is read by line
			for(int c = 0; c < xtiles; c++) 
			{
				if(inline.charAt(c) == 'e') // empty space
				{
					maze[r][c] = EMPTY;
				}
				if(inline.charAt(c) == 'w') // wall
				{
					maze[r][c] = WALL;
				}
				if(inline.charAt(c) == 'p') // pellet
				{
					maze[r][c] = PELLET;
				}
			}
		}
		fin.close();
		
		// test if the array is initialized properly
		
//		for(int i = 0; i < ytiles; i++) 
//		{
//			for(int j = 0; j < xtiles; j++)
//				System.out.print(maze[i][j]);
//			System.out.println();
//		}
	}
	
	public void loadmaze(GraphicsContext gc) // draw map based on 2d array
	{
		for(int r = 0; r < ytiles; r++)
			for(int c = 0; c < xtiles; c++)
			{
				int val = maze[r][c];
				if(val == EMPTY)
				{
					drawEmpty(r, c, gc);
				}
				if(val == WALL)
				{
					drawWall(r, c, gc);
				}
				if(val == PELLET)
				{
					drawPellet(r, c, gc);
				}
			}
	}
	
	private void drawWall(int r, int c, GraphicsContext gc) // r and c are the row and column of the cell
	{
		int x = c * CELLSIZE; // top left x coordinate of cell
		int y = r * CELLSIZE; // top left y coordinate of cell
		gc.setFill(Color.BLUE);
		gc.fillRect(x, y, CELLSIZE, CELLSIZE);
	}
	
	private void drawPellet(int r, int c, GraphicsContext gc) // r and c are the row and column of the cell
	{
		int x = c * CELLSIZE; // top left x coordinate of cell
		int y = r * CELLSIZE; // top left y coordinate of cell
		gc.setFill(Color.BLACK);
		gc.fillRect(x, y, CELLSIZE, CELLSIZE);
		gc.setFill(Color.YELLOW);
		int midx = x + (CELLSIZE/2); // midpoint x of a cell
		int midy = y + (CELLSIZE/2); // midpoint y of a cell
		gc.fillRect(midx - 5, midy - 5, 10, 10);
	}
	
	private void drawEmpty(int r, int c, GraphicsContext gc) // r and c are the row and column of the cell
	{
		int x = c * CELLSIZE; // top left x coordinate of cell
		int y = r * CELLSIZE; // top left y coordinate of cell
		gc.setFill(Color.BLACK);
		gc.fillRect(x, y, CELLSIZE, CELLSIZE);
	}
	
	public void render(GraphicsContext gc) // maintain maze DO LATER!!!!
	{
		
	}
	
	public static void main (String args[]) throws FileNotFoundException, IOException // testing
	{

        Canvas canvas = new Canvas( 600, 500 ); // 600 pixels wide, 500 pixels tall
        GraphicsContext gc = canvas.getGraphicsContext2D();
		Maze maze = new Maze("level1.txt");
		maze.initMaze();
		maze.loadmaze(gc);
	}
}
