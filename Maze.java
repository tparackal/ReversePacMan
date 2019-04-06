import java.io.*;
import java.util.Scanner;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Class for the maze
 * 
 * @author Patrick Hara, Tharun Parackal
 */
public class Maze 
{
	public final static int xtiles = 28; // 28 tiles wide
	public final static int ytiles = 31; // 31 tiles high
	public final static int WIDTH = 560; // pixel width 
	public final static int HEIGHT = 620; // pixel height 
	final static int CELLSIZE = 20; // cell side length (square) 
	
	final static int EMPTY = 0;
	final static int WALL = 1;
	final static int PELLET = 2;
	
	String filename;
	public static int[][] maze; // cell grid
	
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
	}
	
	public void render(GraphicsContext gc) // maze render method
	{
		for(int r = 0; r < ytiles; r++) // draw map based on 2d array
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
		try
		{
			return maze[x][y];
		} catch(ArrayIndexOutOfBoundsException e)
		{
			return 1;
		}
	}
	void consumePellet(int r, int c) // change pellet space to empty space when Pacman consumes pellet
	{
		if(maze[r][c] == PELLET)
		{
			maze[r][c] = EMPTY;
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
		gc.fillRect(midx - 3, midy - 3, 5, 5);
	}
	
	private void drawEmpty(int r, int c, GraphicsContext gc) // r and c are the row and column of the cell
	{
		int x = c * CELLSIZE; // top left x coordinate of cell
		int y = r * CELLSIZE; // top left y coordinate of cell
		gc.setFill(Color.BLACK);
		gc.fillRect(x, y, CELLSIZE, CELLSIZE);
	}
	
	public static int toRow(double y) // convert pixel y coordinate to cell row coordinate
	{
		int row = (int)(y / Maze.CELLSIZE);
		return row;
	}
	
	public static int toCol(double x) // convert pixel x coordinate to cell column coordinate
	{
		int col = (int)(x / Maze.CELLSIZE);
		return col;
	}
}
