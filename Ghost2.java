import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Class for the second ghost
 * 
 * (The two ghosts are separate classes since they use different controls)
 * 
 * @author Patrick Hara, Tharun Parackal
 */
public class Ghost2 extends Creature
{
	public Ghost2(double x1, double y1, double dx1, double dy1, int m1) 
	{
		super(x1, y1, dx1, dy1, m1);
	}
	public void update() 
	{
		super.update();
		int col = Maze.toCol(x);
		int row = Maze.toRow(y);
		if((row == 14) && (col == 0) && (motion == 1)) //  set Ghost to the right side if it goes to the left side
		{
			x = 27 * Maze.CELLSIZE;
			y = 14 * Maze.CELLSIZE;
			return;
		}
		if((row == 14) && (col == 27) && (motion == 2)) // set Ghost to the left side if it goes to the right side
		{
			x = 0 * Maze.CELLSIZE;
			y = 14 * Maze.CELLSIZE;
			return;
		}
		motion=ReversePacMan.dir2;
//		int jcol = ((int)x)/Maze.CELLSIZE; int jrow = ((int)y)/Maze.CELLSIZE;
		Point2D dvec = ReversePacMan.dirs[ReversePacMan.dir2];
		int inFront = ReversePacMan.maze1.getMazeValue(row+(int)dvec.getY(), col+(int)dvec.getX());
		if(inFront==Maze.WALL) // ghost does not go through wall
		{
			motion=0;
		}
	}
	
	public void render(GraphicsContext gc)
	{
 		gc.setFill(Color.RED); // ghost 2
// 		col = (int) (creatures[1].x / Maze.CELLSIZE);
// 		row = (int) (creatures[1].y / Maze.CELLSIZE);
 		int col = Maze.toCol(x);
 		int row = Maze.toRow(y);
 		int topleftX = col * Maze.CELLSIZE;
 		int topleftY = row * Maze.CELLSIZE;
 		gc.fillRect(topleftX, topleftY, Maze.CELLSIZE, Maze.CELLSIZE);
// 		gc.fillRect(creatures[1].x, creatures[1].y, Maze.CELLSIZE, Maze.CELLSIZE);
	}
}
