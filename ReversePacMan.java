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
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

/**
 * Main class
 * 
 * @author Patrick Hara, Tharun Parackal
 */
public class ReversePacMan extends Application
{
	final int FPS = 20;
	public static Maze maze1;
	public static Creature[] creatures = new Creature[6]; // creature array contains all the creatures in the game (2 ghosts and Pacman)
	public static int endCount = 0;
	public static int dir;
	public static int dir2;
	public static int levelscore = 10000;
	public static int lives = 3;
	public static int PacState = 1;
	public static Point2D[] dirs = {new Point2D(0,0),
			new Point2D(-1,0), new Point2D(1,0),
			new Point2D(0,-1), new Point2D(0,1)
	};
	int gamestate = 1;
	public static Image pacMan1;
	public static Image pacMan2;
	public static Image pacMan3;
	public static Image pacMan4;
	public static Image pacMan5;
	public static Image ghost1Left, ghost1Right, ghost1Up, ghost1Down, 
			ghost2Left, ghost2Right, ghost2Up, ghost2Down;
	public static Image title;
	public static AudioClip begin; // beginning tune (played at the start screen)
	public static AudioClip death; // death tune (played when the player wins (when Pacman loses))
	
	public static void main(String[] args) 
	{
		launch(args);
	}
	
	void setHandlers(Scene scene) {
		scene.setOnKeyPressed
		(
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
						case SPACE:
							gamestate=2;
							break;
							
						case U: // shortcut to game over screen
							gamestate=4;
							break;
						case I: // shortcut to win screen
							gamestate=5;
							break;
						default:
							break;
					}
				}
		);
					
	}
	
	public void update() 
	{
		switch(gamestate) {
			case 1:
				break;
			case 2:
				creatures[0].x = 12*Maze.CELLSIZE;
				creatures[0].y = 14*Maze.CELLSIZE;
				creatures[0].motion = 0;
				creatures[1].x = 15*Maze.CELLSIZE;
				creatures[1].y = 14*Maze.CELLSIZE;
				creatures[1].motion = 0;
				creatures[2].x = 280;
				creatures[2].y = 460;
				creatures[2].motion = 2;
				gamestate = 3;
				break;
			case 3:	
				for (int i = 0; i < 3; i++) // update the loop to go further each time an element is added
				{
					creatures[i].update();
				}
				collision(); // detect collision between Pacman and ghosts
				break;
			case 4:
				for (int i = 0; i < 3; i++) // update the loop to go further each time an element is added
				{
					creatures[i].motion = 0;
				}
				break;
			case 5:
				for (int i = 0; i < 3; i++) // update the loop to go further each time an element is added
				{
					creatures[i].motion = 0;
				}
				if(endCount == 0) // this keeps the audio clip from playing each frame
				{
					death.play(); // play audio clip at end screen
				}
				endCount++;
				break;
		}
	}

	void render(GraphicsContext top_gc, GraphicsContext gc ) 
	{
		switch(gamestate)
		{
			case 1:
				gc.setFill(Color.BLUE);
				gc.fillRect(0, 0, 560, 620);
				gc.drawImage(title, 80, 250);
				break;
			case 2:
				maze1.render(gc);
				drawTop(top_gc);
				for(int i = 0; i < 3; i++)
				{
					creatures[i].render(gc);
				}
				break;
			case 3:
				maze1.render(gc);
				drawTop(top_gc);
				for(int i = 0; i < 3; i++)
				{
					creatures[i].render(gc);
				}
				break;
			case 4:
				maze1.render(gc);
				drawTop(top_gc);
				for(int i = 0; i < 3; i++)
				{
					creatures[i].render(gc);
				}
				drawText(gc, "GAME OVER", 250, 355);
				break;
			case 5:
				maze1.render(gc);
				drawTop(top_gc);
				for(int i = 0; i < 3; i++)
				{
					creatures[i].render(gc);
				}
				drawText(gc, "You Win", 260, 355);
				break;
				
		}
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
			
			// reset ghosts to their starting positions
			creatures[0].x = 12*Maze.CELLSIZE;
			creatures[0].y = 14*Maze.CELLSIZE;
			creatures[0].motion = 0;
			
			creatures[1].x = 15*Maze.CELLSIZE;
			creatures[1].y = 14*Maze.CELLSIZE;
			creatures[1].motion = 0;
		}
		else if(((creatures[2].x <= creatures[0].x + 10) && (creatures[2].x + 10 >= creatures[0].x)) && ((creatures[2].y <= creatures[0].y + 10) && (creatures[2].y + 10 >= creatures[0].y)))
		{
			lives--; // Pacman loses a life
			
			// reset Pacman to his starting position
			creatures[2].x = 280;
			creatures[2].y = 460;
			creatures[2].motion = 1;
			
			// reset ghosts to their starting positions
			creatures[0].x = 12*Maze.CELLSIZE;
			creatures[0].y = 14*Maze.CELLSIZE;
			creatures[0].motion = 0;
						
			creatures[1].x = 15*Maze.CELLSIZE;
			creatures[1].y = 14*Maze.CELLSIZE;
			creatures[1].motion = 0;
		}
	}
	
	void drawTop(GraphicsContext top_gc) // draw score and lives 
	{
		top_gc.setFill(Color.WHITE);
		top_gc.setStroke(Color.WHITE);
		int pellets = creatures[2].pelletsConsumed;
		int scoreLoss = pellets * 40;
		drawText(top_gc, "LEVEL SCORE: " + (levelscore-scoreLoss), 20, 15);
		drawText(top_gc, "LIVES: " + lives, 480, 15);
		if(pellets == 246)
		{
			gamestate = 4;
		}
		if(lives == 0)
		{
			gamestate = 5;
		}
	}
	
	void drawText(GraphicsContext gc, String s, double x, double y)
	{
		gc.fillText(s, x, y);
		gc.strokeText(s, x, y);
	}
	
	@Override
    public void start(Stage theStage) throws FileNotFoundException, IOException 
    {
        theStage.setTitle( "Reverse PacMan" );
        
        maze1 = new Maze("level1.txt"); // set maze
        maze1.initMaze(); // initialize maze
        
        creatures[0] = new Ghost(12*Maze.CELLSIZE, 14*Maze.CELLSIZE, 10, 10, 0); // ghost 1
        creatures[1] = new Ghost2(15*Maze.CELLSIZE, 14*Maze.CELLSIZE, 10, 10, 0); // ghost 2
        creatures[2] = new PacMan(280, 460, 10, 10, 1, maze1.maze); // Pacman
        
        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );
        
        title = new Image("Title.png"); // title image
        
        // Pacman images
        pacMan1 = new Image("PacmanLeft.png");
        pacMan2 = new Image("PacmanRight.png");
        pacMan3 = new Image("PacmanUp.png");
        pacMan4 = new Image("PacmanDown.png");
        pacMan5 = new Image("Pacman.png");
        
        // Ghost images
        ghost1Left = new Image("Ghost1Left.png");
        ghost1Right = new Image("Ghost1Right.png");
        ghost1Up = new Image("Ghost1Up.png");
        ghost1Down = new Image("Ghost1Down.png");
        ghost2Left = new Image("Ghost2Left.png");
        ghost2Right = new Image("Ghost2Right.png");
        ghost2Up = new Image("Ghost2Up.png");
        ghost2Down = new Image("Ghost2Down.png");
        
        // audio clips
        begin = new AudioClip(ClassLoader.getSystemResource("pacman_beginning.wav").toString()); // played at beginning
        death = new AudioClip(ClassLoader.getSystemResource("pacman_death.wav").toString()); // played when player wins
        begin.play(); // play audio clip at the start screen
        
        Canvas canvas = new Canvas( Maze.WIDTH, Maze.HEIGHT ); // 560 pixels wide, 620 pixels tall
        root.getChildren().add( canvas );
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        GraphicsContext top_gc = canvas.getGraphicsContext2D();
        //render(top_gc, gc);
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
