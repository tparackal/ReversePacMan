import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Main class
 * 
 * @author Patrick Hara, Tharun Parackal
 */
public class ReversePacMan extends Application
{

//	final static int WIDTH = 840; // pixel width
//	final static int HEIGHT = 930; // pixel height
	final int FPS = 20; // originally 15
	public static Maze maze1;
	public static Creature[] creatures = new Creature[6];
	public static int dir;
	public static int dir2;
	public static int score = 0;
	public static int levelscore = 10000;
	public static int lives = 3;
	public static Point2D[] dirs = {new Point2D(0,0),
			new Point2D(-1,0), new Point2D(1,0),
			new Point2D(0,-1), new Point2D(0,1)
	};
	public static void main(String[] args) 
	{
		launch(args);
	}
	
	void setHandlers(Scene scene) {
		scene.setOnKeyPressed(
				e -> {
					switch (e.getCode())
					{
						case A:
							dir=1;
							break;
						case LEFT:
							dir2=1; // A:Left
							break;
						case D:
							dir=2;
							break;
						case RIGHT:
							dir2=2; // D:Right
							break;
						case W:
							dir=3;
							break;
						case UP:
							dir2=3; // W:Up
							break;
						case S:
							dir=4;
							break;
						case DOWN:
							dir2=4; // S:Down
							break;
						default:
							break;
					}
				}
		);
					
	}
	
	public void update() 
	{
		for (int i = 0; i < 3; i++) // update the loop to go further each time an element is added
		{
			creatures[i].update();
		}
	}
	void render(GraphicsContext gc) throws FileNotFoundException, IOException
	{
//	    	Canvas canvas = new Canvas( 600, 500 ); // 600 pixels wide, 500 pixels tall
//	 		maze = new Maze("level1.txt");
//	 		maze.initMaze();
	 		maze1.loadmaze(gc);
	 		gc.setFill(Color.AQUAMARINE); // ghost 1
//	 		int col = (int) (creatures[0].x / Maze.CELLSIZE);
//	 		int row = (int) (creatures[0].y / Maze.CELLSIZE);
	 		int col = Maze.toCol(creatures[0].x);
	 		int row = Maze.toRow(creatures[0].y);
	 		double topleftX = col * Maze.CELLSIZE;
	 		double topleftY = row * Maze.CELLSIZE;
	 		gc.fillRect(topleftX, topleftY, Maze.CELLSIZE, Maze.CELLSIZE);
//	 		gc.fillRect(creatures[0].x, creatures[0].y, Maze.CELLSIZE, Maze.CELLSIZE);
	 		
	 		
	 		gc.setFill(Color.RED); // ghost 2
//	 		col = (int) (creatures[1].x / Maze.CELLSIZE);
//	 		row = (int) (creatures[1].y / Maze.CELLSIZE);
	 		col = Maze.toCol(creatures[1].x);
	 		row = Maze.toRow(creatures[1].y);
	 		topleftX = col * Maze.CELLSIZE;
	 		topleftY = row * Maze.CELLSIZE;
	 		gc.fillRect(topleftX, topleftY, Maze.CELLSIZE, Maze.CELLSIZE);
//	 		gc.fillRect(creatures[1].x, creatures[1].y, Maze.CELLSIZE, Maze.CELLSIZE);
	 		
	 		gc.setFill(Color.YELLOW);
	 		col = Maze.toCol(creatures[2].x);
	 		row = Maze.toRow(creatures[2].y);
	 		topleftX = col * Maze.CELLSIZE;
	 		topleftY = row * Maze.CELLSIZE;
	 		gc.fillRect(topleftX, topleftY, Maze.CELLSIZE, Maze.CELLSIZE);
	}
	void render(GraphicsContext top_gc, GraphicsContext gc ) {
		maze1.loadmaze(gc);
		drawTop(top_gc);
		gc.setFill(Color.AQUAMARINE); // ghost 1
//		int col = (int) (creatures[0].x / Maze.CELLSIZE);
// 		int row = (int) (creatures[0].y / Maze.CELLSIZE);
 		int col = Maze.toCol(creatures[0].x);
 		int row = Maze.toRow(creatures[0].y);
 		double topleftX = col * Maze.CELLSIZE;
 		double topleftY = row * Maze.CELLSIZE;
 		gc.fillRect(topleftX, topleftY, Maze.CELLSIZE, Maze.CELLSIZE);
// 		gc.fillRect(creatures[0].x, creatures[0].y, Maze.CELLSIZE, Maze.CELLSIZE);
 		
 		
 		gc.setFill(Color.RED); // ghost 2
// 		col = (int) (creatures[1].x / Maze.CELLSIZE);
// 		row = (int) (creatures[1].y / Maze.CELLSIZE);
 		col = Maze.toCol(creatures[1].x);
 		row = Maze.toRow(creatures[1].y);
 		topleftX = col * Maze.CELLSIZE;
 		topleftY = row * Maze.CELLSIZE;
 		gc.fillRect(topleftX, topleftY, Maze.CELLSIZE, Maze.CELLSIZE);
// 		gc.fillRect(creatures[1].x, creatures[1].y, Maze.CELLSIZE, Maze.CELLSIZE);
 		
 		gc.setFill(Color.YELLOW);
 		col = Maze.toCol(creatures[2].x);
 		row = Maze.toRow(creatures[2].y);
 		topleftX = col * Maze.CELLSIZE;
 		topleftY = row * Maze.CELLSIZE;
 		gc.fillRect(topleftX, topleftY, Maze.CELLSIZE, Maze.CELLSIZE);
	}
	void drawTop(GraphicsContext top_gc){
		top_gc.setFill(Color.WHITE);
		top_gc.setStroke(Color.WHITE);
		int pellets = creatures[2].pelletsConsumed;
		int scoreLoss = pellets * 40;
		drawText(top_gc, "LEVEL SCORE: " + (levelscore-scoreLoss), 20, 15);
		drawText(top_gc, "LIVES: " + lives, 480, 15);
	}
	void drawText(GraphicsContext gc, String s, double x, double y)
	{
		gc.fillText(s, x, y);
		gc.strokeText(s, x, y);
	}
	public void collision() // checks if Pacman is captured by the ghosts
	{
		if(((creatures[2].x <= creatures[1].x + 10) && (creatures[2].x + 10 >= creatures[1].x)) && ((creatures[2].y <= creatures[1].y + 10) && (creatures[2].y + 10 >= creatures[1].y)))
		{
			lives--; // Pacman loses a life
			// reset Pacman to his starting position
			creatures[2].x = 280;
			creatures[2].y = 460;
			creatures[2].motion = 1;
		}
		else if(((creatures[2].x <= creatures[0].x + 10) && (creatures[2].x + 10 >= creatures[0].x)) && ((creatures[2].y <= creatures[0].y + 10) && (creatures[2].y + 10 >= creatures[0].y)))
		{
			lives--; // Pacman loses a life
			// reset Pacman to his starting position
			creatures[2].x = 280;
			creatures[2].y = 460;
			creatures[2].motion = 1;
		}
	}
	@Override
    public void start(Stage theStage) throws FileNotFoundException, IOException 
    {
        theStage.setTitle( "Reverse PacMan" );
        maze1 = new Maze("level1.txt");
        maze1.initMaze();
        creatures[0] = new Ghost(12*Maze.CELLSIZE, 14*Maze.CELLSIZE, 10, 10, 0); // ghost 1
        creatures[1] = new Ghost2(15*Maze.CELLSIZE, 14*Maze.CELLSIZE, 10, 10, 0); // ghost 2
        creatures[2] = new PacMan(280, 460, 10, 10, 1, maze1.maze); // Pacman
        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );
        
        Canvas canvas = new Canvas( Maze.WIDTH, Maze.HEIGHT ); // 840 pixels wide, 930 pixels tall
        root.getChildren().add( canvas );
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        GraphicsContext top_gc = canvas.getGraphicsContext2D();
        render(gc);
        setHandlers(theScene);
        KeyFrame kf = new KeyFrame(Duration.millis(1000 / FPS),
				e -> {
					// update position
					update();
					// draw frame
					render(top_gc, gc);
				}
			);
        Timeline mainLoop = new Timeline(kf);
		mainLoop.setCycleCount(Animation.INDEFINITE);
		mainLoop.play();
        theStage.show();
    }
    
   

}
