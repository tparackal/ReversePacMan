import javafx.geometry.Point2D;

public class PacMan extends Creature{
	public PacMan(double x1, double y1, double dx1, double dy1, int m1) {
		super(x1, y1, dx1, dy1, m1);
	}
	public void update() {
		super.update();
		int jcol = ((int)x)/Maze.CELLSIZE; int jrow = ((int)y)/Maze.CELLSIZE;
		Point2D dvec = ReversePacMan.dirs[motion];
		int inFront = ReversePacMan.maze.getMazeValue(jrow+(int)dvec.getY(), jcol+(int)dvec.getX());
		if(inFront==Maze.WALL) {
			motion=0;
		}
	}
}
