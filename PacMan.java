import javafx.geometry.Point2D;
import java.util.Random;

public class PacMan extends Creature
{
	Random gen = new Random();
	public int visited [][] = new int [Maze.xtiles][Maze.ytiles]; //  keeps track of what cells PacMan has visited
	final static int UNKNOWN = 0;
	final static int VISITED = 1;
	final static int WALL = 2;
	
	
	public PacMan(double x1, double y1, double dx1, double dy1, int m1) 
	{
		super(x1, y1, dx1, dy1, m1);

		for(int r = 0; r < Maze.ytiles; r++) // initialize 2D array
			for(int c = 0; c < Maze.xtiles; c++)
			{
				if(Maze.maze[r][c] == 0 || Maze.maze[r][c] == 2)
				{
					visited[r][c] = UNKNOWN; // Pac Man did not visit these cells yet
				}
				else
				{
					visited[r][c] = WALL;
				}
			}
	}
	
	public void update() 
	{
		super.update();
		int col = Maze.toCol(x);
		int row = Maze.toRow(y);
		visited[row][col] = VISITED; // mark that Pacman has visited this cell
		this.motion = getDirection(row, col);
//		int jcol = ((int)x)/Maze.CELLSIZE; int jrow = ((int)y)/Maze.CELLSIZE;
//		Point2D dvec = ReversePacMan.dirs[motion];
//		int inFront = ReversePacMan.maze.getMazeValue(jrow+(int)dvec.getY(), jcol+(int)dvec.getX());
//		if(inFront==Maze.WALL) 
//		{
//			motion=0;
//		}
	}
	
	public int getDirection(int row, int col) // Pac man moves randomly within the maze
	{
		int motion = -1;
		boolean found = false;
		int d = gen.nextInt(4) + 1; //  random number 1-4 inclusive
		while(!found) 
		{
			if(d == 1) // left
			{
				if(visited[row][col - 1] != WALL)
				{
					if(Maze.maze[row][col - 1] == Maze.PELLET)
					{
						motion = 1;
						found = true;
						break;
					}
				}
			}
			
			if(d == 2) // right
			{
				if(visited[row][col + 1] != WALL)
				{
					if(Maze.maze[row][col + 1] == Maze.PELLET)
					{
						motion = 2;
						found = true;
						break;
					}
				}
			}
			
			if(d == 3) // up
			{
				if(visited[row - 1][col] != WALL)
				{
					if(Maze.maze[row - 1][col] == Maze.PELLET)
					{
						motion = 3;
						found = true;
						break;
					}
				}
			}
			
			if(d == 4) // down
			{
				if(visited[row + 1][col] != WALL)
				{
					if(Maze.maze[row + 1][col] == Maze.PELLET)
					{
						motion = 4;
						found = true;
						break;
					}
				}
			}
		}
		return motion;
	}
}
