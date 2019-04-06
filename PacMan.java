import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import java.util.Random;

/**
 * Class for Pacman
 * 
 * @author Patrick Hara, Tharun Parackal
 */
public class PacMan extends Creature
{
	Random gen = new Random();
	public int visited [][]; //= new int [Maze.xtiles][Maze.ytiles]; //  keeps track of what cells PacMan has visited
	public int [] crossX = {6, 9, 12, 15, 18, 21, 21, 26, 21, 15, 21, 18, 18, 18, 21, 18, 21, 24, 15, 12, 15, 3, 9, 6, 9, 6, 9, 9, 6, 12, 6, 6, 1}; // col coordinates of intersections
	public int [] crossY = {1, 5, 5, 5, 5, 1, 5, 5, 8, 11, 14, 14, 17, 20, 20, 23, 23, 26, 29, 29, 23, 26, 23, 23, 20, 20, 17, 14, 14, 11, 8, 5, 5}; // row coordinates of intersections
	final static int UNKNOWN = 0;
	final static int VISITED = 1;
	final static int WALL = 2;

//	boolean lastwall = false;
	
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
		if(ReversePacMan.maze1.getMazeValue(row, col) == Maze.PELLET) {
			ReversePacMan.maze1.consumePellet(row, col);
			pelletsConsumed++;
		}
		visited[row][col] = VISITED; // mark that Pacman has visited this cell
		if((row == 14) && (col == 0) && (motion == 1)) //  set Pacman to the right side if it goes to the left side
		{
			x = 27 * Maze.CELLSIZE;
			y = 14 * Maze.CELLSIZE;
			return;
		}
		if((row == 14) && (col == 27) && (motion == 2)) // set Pacman to the left side if it goes to the right side
		{
			x = 0 * Maze.CELLSIZE;
			y = 14 * Maze.CELLSIZE;
			return;
		}
		
		for(int i = 0; i < crossX.length; i++)
		{
			if((col == crossX[i]) && (row == crossY[i]))
			{
				boolean back = false;
				int d = gen.nextInt(4) + 1;
				while(!back)
				{
					if((motion == 1) && (d == 2))
					{
						d = gen.nextInt(4) + 1;
					}
					else if((motion == 2) && (d == 1))
					{
						d = gen.nextInt(4) + 1;
					}
					else if((motion == 3) && (d == 4))
					{
						d = gen.nextInt(4) + 1;
					}
					else if ((motion == 4) && (d == 3))
					{
						d = gen.nextInt(4) + 1;
					}
					else
					{
						break;
					}
				}
				motion = d;
				break;
			}
		}
		
//		motion = getDirection(row, col);
//		int jcol = ((int)x)/Maze.CELLSIZE; int jrow = ((int)y)/Maze.CELLSIZE;
		Point2D dvec = ReversePacMan.dirs[motion];
		int inFront = ReversePacMan.maze1.getMazeValue(row+(int)dvec.getY(), col+(int)dvec.getX());
		if(inFront==Maze.WALL) // change direction when Pacman reaches a wall 
		{
			motion = getDirection(row, col);
		}
//		int right = ReversePacMan.maze1.getMazeValue(row, col + 1);
//		int left = ReversePacMan.maze1.getMazeValue(row, col - 1);
//		int up = ReversePacMan.maze1.getMazeValue(row - 1, col);
//		int down = ReversePacMan.maze1.getMazeValue(row + 1, col);
//		if((right != Maze.WALL) || (left != Maze.WALL) || (up != Maze.WALL) || (down != Maze.WALL))
//		{
//			motion = getDirection(row, col); 
//		}
	}
	
	public void render(GraphicsContext gc)
	{
 		gc.setFill(Color.YELLOW);
 		int col = Maze.toCol(x);
 		int row = Maze.toRow(y);
 		int topleftX = col * Maze.CELLSIZE;
 		int topleftY = row * Maze.CELLSIZE;
 		if(ReversePacMan.PacState == 1){
 			if(motion == 1){
 				gc.drawImage(ReversePacMan.pacMan1, topleftX, topleftY);
 				ReversePacMan.PacState = 2;
 			}
 			if((motion == 2)||(motion == 0)){
 				gc.drawImage(ReversePacMan.pacMan2, topleftX, topleftY);
 				ReversePacMan.PacState = 2;
 			}
 			if(motion == 3){
 				gc.drawImage(ReversePacMan.pacMan3, topleftX, topleftY);
 				ReversePacMan.PacState = 2;
 			}
 			if(motion == 4){
 				gc.drawImage(ReversePacMan.pacMan4, topleftX, topleftY);
 				ReversePacMan.PacState = 2;
 			}
 			
 		}
 		else if(ReversePacMan.PacState == 2){
 			gc.drawImage(ReversePacMan.pacMan5, topleftX, topleftY);
 			ReversePacMan.PacState = 1;
 		}
	}
	
	public int getDirection(int row, int col) // Pac man moves randomly within the maze
	{
		int result = 0;
		boolean found = false;
		int d = motion; //  random number 1-4 inclusive
		while(!found)
		{
			if(d == 0){
				result = 1;
				break;
			}
			if(d == 1) //left
			{
				if(Maze.maze[row][col - 1] != Maze.WALL)
				{
					result = 1;
					found = true;
					break;
				}
				else
				{
					d = gen.nextInt(2) + 3; // else go up or down
					if((Maze.maze[row - 1][col] == Maze.WALL)&&(d == 3)) 
					{
						result = 4;
						break;
					}
					if((Maze.maze[row + 1][col] == Maze.WALL)&&(d == 4)) 
					{
						result = 3;
						break;
					}
				}
			}
		
			if(d == 2) //right
			{
				if(Maze.maze[row][col + 1] != Maze.WALL)
				{
					result = 2;
					found = true;
					break;
				}
				else
				{
					d = gen.nextInt(2) + 3; // else go up or down
					if((Maze.maze[row - 1][col] == Maze.WALL)&&(d == 3)) 
					{
						result = 4;
						break;
					}
					if((Maze.maze[row + 1][col] == Maze.WALL)&&(d == 4)) 
					{
						result = 3;
						break;
					}
				}
			}
		
			if(d == 3) //up
			{
				if(Maze.maze[row - 1][col] != Maze.WALL)
				{
					result = 3;
					found = true;
					break;
				}
				else
				{
					d = gen.nextInt(2) + 1; // else go left or right
					if((Maze.maze[row][col - 1] == Maze.WALL)&&(d == 1)) 
					{
						result = 2;
						break;
					}
					if((Maze.maze[row][col + 1] == Maze.WALL)&&(d == 2)) 
					{
						result = 1;
						break;
					}
				}
			}
		
			if(d == 4) //down
			{
				if(Maze.maze[row + 1][col] != Maze.WALL)
				{
					result = 4;
					found = true;
					break;
				}
				else
				{
					d = gen.nextInt(2) + 1; // else go left or right
					if((Maze.maze[row][col - 1] == Maze.WALL)&&(d == 1)) 
					{
						result = 2;
						break;
					}
					if((Maze.maze[row][col + 1] == Maze.WALL)&&(d == 2)) 
					{
						result = 1;
						break;
					}
				}
			}
		}
		return result;
	}
}
