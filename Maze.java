import java.io.*;
import java.util.Scanner;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Maze 
{
/*
 * NOTE: If map is too big for the screen,
 * 
 * 		change CELLSIZE from 30 to 20
 * 		change WIDTH from 840 to 560
 * 		change HEIGHT from 930 to 620
 * 		change midx - 5 to midx - 3 in drawPellet method line 112
 * 		change midy - 5 to midy -3 in drawPellet method line 112
 * 		change size to 5 in drawPellet method line 112
 * 
 */
	final static int xtiles = 28; // 28 tiles wide
	final static int ytiles = 31; // 31 tiles high
	public final static int WIDTH = 560; // pixel width 
	public final static int HEIGHT = 620; // pixel height 
	final static int CELLSIZE = 20; // cell side length (square) 
	
	final static int EMPTY = 0;
	final static int WALL = 1;
	final static int PELLET = 2;
	
	String filename;
//	public int grid [][] = new int [HEIGHT][WIDTH]; //  pixel grid
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
	
	public int getMazeValue(int x, int y) // return cell value
	{
		int r = (int)(y / CELLSIZE);
		int c = (int)(x / CELLSIZE);
		return maze[r][c];
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
		gc.fillRect(midx - 3, midy - 3, 5, 5);
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
}
