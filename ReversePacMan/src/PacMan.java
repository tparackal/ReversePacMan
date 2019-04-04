import javafx.geometry.Point2D;
import java.util.Random;

public class PacMan extends Creature
{
	Random gen = new Random();
	public int visited [][]; //= new int [Maze.xtiles][Maze.ytiles]; //  keeps track of what cells PacMan has visited
	final static int UNKNOWN = 0;
	final static int VISITED = 1;
	final static int WALL = 2;
	boolean lastwall = false;
	
	public PacMan(double x1, double y1, double dx1, double dy1, int m1, int[][] maze) 
	{
		super(x1, y1, dx1, dy1, m1);
		visited = new int [Maze.ytiles][Maze.xtiles];
		for(int r = 0; r < Maze.ytiles; r++) // initialize 2D array
			for(int c = 0; c < Maze.xtiles; c++)
			{
				if(maze[r][c] == 0 || maze[r][c] == 2) // if there is an empty space or a pellet
				{
					visited[r][c] = UNKNOWN; // Pac Man did not visit these cells yet
				}
				else
				{
					visited[r][c] = WALL; // Pac Man cannot be in these cells
				}
			}
	}
	
	public void update() 
	{
		super.update();
		int col = Maze.toCol(x);
		int row = Maze.toRow(y);
		visited[row][col] = VISITED; // mark that Pacman has visited this cell
//		motion = getDirection(row, col);
//		int jcol = ((int)x)/Maze.CELLSIZE; int jrow = ((int)y)/Maze.CELLSIZE;
		Point2D dvec = ReversePacMan.dirs[motion];
		int inFront = ReversePacMan.maze1.getMazeValue(row+(int)dvec.getY(), col+(int)dvec.getX());
		if(inFront==Maze.WALL) 
		{
			motion = getDirection(row, col);
		}
	}
	
	public int getDirection(int row, int col) // Pac man moves randomly within the maze
	{
		int result = 0;
		boolean found = false;
		int d = motion; //  random number 1-4 inclusive
		int sidePath;
		while(!found)
		{
			if(d == 1) //left
			{
				
				if(Maze.maze[row][col - 1] != Maze.WALL)
				{
//					if(Maze.maze[row - 1][col] != Maze.WALL) {
//						sidePath = gen.nextInt(100) + 1;
//						if(sidePath > 10) {
//							result = 3;
//							break;
//						}
//					}
//					if(Maze.maze[row + 1][col] != Maze.WALL) {
//						sidePath = gen.nextInt(100) + 1;
//						if(sidePath > 10) {
//							result = 4;
//							break;
//						}
//					}
					result = 1;
					found = true;
					break;
				}
				else
				{
					d = gen.nextInt(2) + 3;
					if((Maze.maze[row - 1][col] == Maze.WALL)&&(d == 3)) {
						result = 4;
						break;
					}
					if((Maze.maze[row + 1][col] == Maze.WALL)&&(d == 4)) {
						result = 3;
						break;
					}
				}
			}
		
			if(d == 2) //right
			{
				
				if(Maze.maze[row][col + 1] != Maze.WALL)
				{
//					if(Maze.maze[row - 1][col] != Maze.WALL) {
//						sidePath = gen.nextInt(100) + 1;
//						if(sidePath > 10) {
//							result = 3;
//							break;
//						}
//					}
//					if(Maze.maze[row + 1][col] != Maze.WALL) {
//						sidePath = gen.nextInt(100) + 1;
//						if(sidePath > 10) {
//							result = 4;
//							break;
//						}
//					}
					result = 2;
					found = true;
					break;
				}
				else
				{
					d = gen.nextInt(2) + 3;
					if((Maze.maze[row - 1][col] == Maze.WALL)&&(d == 3)) {
						result = 4;
						break;
					}
					if((Maze.maze[row + 1][col] == Maze.WALL)&&(d == 4)) {
						result = 3;
						break;
					}
				}
			}
		
			if(d == 3) //up
			{
				
				if(Maze.maze[row - 1][col] != Maze.WALL)
				{
//					if(Maze.maze[row][col - 1] != Maze.WALL) {
//						sidePath = gen.nextInt(100) + 1;
//						if(sidePath > 10) {
//							result = 1;
//							break;
//						}
//					}
//					if(Maze.maze[row][col + 1] != Maze.WALL) {
//						sidePath = gen.nextInt(100) + 1;
//						if(sidePath > 10) {
//							result = 2;
//							break;
//						}
//					}
					result = 3;
					found = true;
					break;
				}
				else
				{
					d = gen.nextInt(2) + 1;
					if((Maze.maze[row][col - 1] == Maze.WALL)&&(d == 1)) {
						result = 2;
						break;
					}
					if((Maze.maze[row][col + 1] == Maze.WALL)&&(d == 2)) {
						result = 1;
						break;
					}
				}
			}
		
			if(d == 4) //down
			{
				
				if(Maze.maze[row + 1][col] != Maze.WALL)
				{
//					if(Maze.maze[row][col - 1] != Maze.WALL) {
//						sidePath = gen.nextInt(100) + 1;
//						if(sidePath > 10) {
//							result = 1;
//							break;
//						}
//					}
//					if(Maze.maze[row][col + 1] != Maze.WALL) {
//						sidePath = gen.nextInt(100) + 1;
//						if(sidePath > 10) {
//							result = 2;
//							break;
//						}
//					}
					result = 4;
					found = true;
					break;
				}
				else
				{
					d = gen.nextInt(2) + 1;
					if((Maze.maze[row][col - 1] == Maze.WALL)&&(d == 1)) {
						result = 2;
						break;
					}
					if((Maze.maze[row][col + 1] == Maze.WALL)&&(d == 2)) {
						result = 1;
						break;
					}
				}
			}
		}
//		while(!found) 
//		{
//			if(d == 1) // left
//			{
//				if(visited[row][col - 1] != WALL)
//				{
//					if(Maze.maze[row][col - 1] == Maze.PELLET)
//					{
//						motion = 1;
//						found = true;
//						break;
//					}
//				}
//			}
//			
//			if(d == 2) // right
//			{
//				if(visited[row][col + 1] != WALL)
//				{
//					if(Maze.maze[row][col + 1] == Maze.PELLET)
//					{
//						motion = 2;
//						found = true;
//						break;
//					}
//				}
//			}
//			
//			if(d == 3) // up
//			{
//				if(visited[row - 1][col] != WALL)
//				{
//					if(Maze.maze[row - 1][col] == Maze.PELLET)
//					{
//						motion = 3;
//						found = true;
//						break;
//					}
//				}
//			}
//			
//			if(d == 4) // down
//			{
//				if(visited[row + 1][col] != WALL)
//				{
//					if(Maze.maze[row + 1][col] == Maze.PELLET)
//					{
//						motion = 4;
//						found = true;
//						break;
//					}
//				}
//			}
//		}
		return result;
	}
}
