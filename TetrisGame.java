package io.github.sandeepsukumaran.javatetrisgame;

/**
 *
 * @author Sandeep Sukumaran
 */

/*
    *********************************************************************************************
    *                                           README                                          *          
    *********************************************************************************************

    Details of Implementation
    =========================
    +   M,N,S
        -   M is scoring factor: a multiplier that affects increase in score when a row is cleared.
        -   N is number of rows to be cleared to progress to next level of difficulty.
        -   S is speed factor: rate of increase of speed with increase in level of difficulty.
        -   Implemented with given ranges.
        -   Values set using sliders (Swing.JSlider) in settings dialog (Swing.JDialog) displayed
            at start of game.

    +   No holes in a row R
        -   Handled in methods checkRowsCompleted and clearRow
            --  checkRowsCompleted is called whenever a tetrominoe stops moving down.
            --  checkRowsCompleted checks rows from bottom up and if a row is fully filled,
                invokes clearRow on it. It then resumes checking from the same row upward.
            --  clearRow removes the current row by copying the row just above it downward.
                It also moves every row till the topmost row down (by similar copy operation).
        -   Score and level suitably updated by checkRowsCompleted method.

    +   Required number of rows cleared to progress to next level
        -   Level and falling speed of tetrominoe updated by checkRowsCompleted method.
            --  Updating falling speed is done by cancelling, purging current timer and starting
                new timer with updated falling speed.
            --  Updation done using event handling that modifies timer.

    +   When there is no more space for a tetrominoe, game ends
        -   Situation checked for when current tetrominoe has stopped movement.
            --  If there is no space for the next tetrominoe to be displayed, setGameOver method
                is called.
                --- setGameOver method cancels all further downward movement tasks.
                --- setGameOver also displays the settings dialog to permit restart of the game

    +   When cursor is within player piece, change current tetrominoe
        -   Mouse movement event handler used to check if mouse is inside main area.
            --  When mouse enter main area, in addition to pausing the game, a check is applied
                to see if the cursor is inside current tetrominoe. If so, changeCurrentPlayPiece
                method is invoked.
        -   changeCurrentPlayPiece method seeks a suitable replacement for current tetrominoe.
            --  First candidate is randomly generated, next candidates are checked cyclically.
            --  Alternate orientations of current candidate checked first before next candidate
                shape is considered.
            --  If replacement is found, replaceCurrentPlayPiece method is invoked.
        -   replaceCurrentPlayPiece method used to replace current with obtained candidate.
            --  Invokes replaceCPP method in supportFunctions class to perform replacement.
            --  Updates reference used by downward motion TimerTask
            --  Updates polygon vertex list for use with point-in-polygon algorithm
            --  Repaints the screen to reflect changes

    +   GUI for game settings
        -   Settings dialog (Swing.JDialog) displayed at start of game and after game over.
        -   M,N,S values set by slider.
        -   No. of colums in game set via text box (Swing.JFormattedTextField).
            --  No. of rows updated corresponding to user's choice for no. of columns.
            --  Ratio of rows:columns :: 2:1 maintained.
            --  Minimum number of columns = 8
            --  Maximum number of columns = 20
        -   User selection between normal and enlarged displays permitted using radio buttons
            --  JRadioButtons used to select between modes. ButtonGroup used to ensure mutual
                exclusion of modes.
            --Normal and Enlarged modes implemented by resizing the game window at game start
        -   Game started using Start button (Swing.JButton).
            --  checks to ensure bounds of values
            --  invokes initialiseGame method of event handlers (in this case, the main game)
    
    **  Downward motion of current tetrominoe is handled by a timer. Code for movement is held
        in DownwardMotion class that extends TimerTask. MoveCurrentPieceDownward is a
        Timer on which this task is scheduled.
        To change the falling speed, Timer class and TimerTask's subclass are re-initialised.

    *********************************************************************************************
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import java.util.Random;
import java.util.Timer;
import static java.lang.Float.max;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * Public class used to launch game.
 */
public class TetrisGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Create the main game object
        MainGameFrame mainWindow = new MainGameFrame();
    }
}

/**
 * Main class for game.Implements a custom listener to enable event handling - permits updation of various values from other classes.
 */
class MainGameFrame extends JFrame implements myListener {

    
    static {
        Level = 1;
        Lines = 0;
        Score = 0;
        RWIDTH = 80.0f;
        RHEIGHT = 100.0f;
        fl_sideOfSquare = 0.0f;
        SHAPES = new ArrayList<Shape>() {
            {
                addAll(Arrays.asList(Shape.values()));
            }
        };
        ORIENTATIONS = new ArrayList<Orientation>() {
            {
                addAll(Arrays.asList(Orientation.values()));
            }
        };
        currentPlayPiece = new Tetrominoe(Shape.SQUARE, Orientation.ORIG);
        nextBlock = new Tetrominoe(Shape.SQUARE, Orientation.ORIG);
        filledPlayArea = new GridInfo[20][10];
        displayPauseLabel = false;
        hnos = 20; //update later from GUI
        wnos = 10; //update later from GUI
        gamePlayPaused = false;
        gameOver = false;
        mouseEnabled = true;
        moveCurrentPieceDownward = new Timer("moveCPPDown", true); //true - isDaemon
        M = 1;
        N = 10;
        S = 0.1f;
        fallingSpeed = 1.0f;
    }

    MainGameFrame() {
        /*
            Constructor of main game object.
            Invokes basic_init() to perform initialisation of main game object.
            Brings the settings dialog to the front of other windows, if any.
         */
        super("Tetris");
        fl_sideOfSquare = RWIDTH / 20.0f;
        basic_init();
        settingsDialog.toFront();
    }

    /**
     * Performs basic initialization of game object.Creates and displays the
     * settings dialog.Registers event handlers for window resizing and relevant
     * mouse actions.
     */
    final void basic_init() {
        settingsDialog = new GameSettings();
        settingsDialog.addListener(this);
        settingsDialog.setVisible(true);

        mouseIsInPlayPiece = false;

        this.addComponentListener(new ComponentAdapter() {
            /**
             * Event handler for window resizing events.Updates a few relevant variables and invokes updateVariables to update the rest.
             * @param e Event corresponding to resizing of window.
             */
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension d = getContentPane().getSize();
                frameWidth = d.width;
                frameHeight = d.height;
                if (d.height <= (int) (d.width / 0.8)) {
                    frameWidth = (int) (0.8 * frameHeight);
                } else {
                    frameHeight = (int) (frameWidth * 1.25); //or divide by 0.8f
                }
                updateVariables();
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            /**
             * Event handler for mouse movements.New position of the cursor is examined to decide if cursor is within the main game area, and within the current tetrominoe.Various flags are updated so that relevant history of cursor movements is available for decision making.
             * @param e Event corresponding to movement of mouse.
             */
            @Override
            public void mouseMoved(MouseEvent e) {
                /*
                    
                 */
                if (mouseInPlayingArea(e.getPoint())) {
                    boolean mipp;
                    if (displayPauseLabel) {
                        //Do Nothing
                        mipp = mouseInPlayPiece(e.getPoint());
                        if (mipp) {
                            if (!mouseIsInPlayPiece) {//moving mouse into play piece for first time
                                mouseIsInPlayPiece = true;
                                changeCurrentPlayPiece();
                                repaint();
                            } else {
                            }                 //moving mouse within play piece
                        } else {
                            mouseIsInPlayPiece = false; //moving mouse out of play piece but within play area
                        }
                    } else { //entering play area for the first time
                        displayPauseLabel = true;
                        gamePlayPaused = true;
                        calcCPPPolyPoints();
                        mipp = mouseInPlayPiece(e.getPoint());
                        if (mipp) {
                            if (!mouseIsInPlayPiece) { //first time mouse enters play piece
                                mouseIsInPlayPiece = true;
                                changeCurrentPlayPiece();
                            } else {
                            }                  //mouse already in play piece - IMPOSSIBLE IF ENTERING PLAY AREA FOR FIRST TIME
                        } else {
                            mouseIsInPlayPiece = false; //redundant : it will already be set to false
                        }
                        repaint();
                    }
                } else if (displayPauseLabel) {
                    displayPauseLabel = false;
                    gamePlayPaused = false;
                    mouseIsInPlayPiece = false;
                    repaint();
                } else {
                    //Do nothing
                }
            }
        });
        this.addMouseListener(new MouseAdapter() {
            /**
             * Event handler for mouse clicks.Mouse clicks can correspond to requests to translate the current tetrominoe or command to exit game.
             * @param e Event corresponding to mouse click.
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                if (mouseInQuitButton(e.getPoint())) {
                    /*
                        When quit button is pressed, all further downward movement tasks are cancelled and the game ends.
                        Settings dialog is not redisplayed since it is assumed the player wants to stop playing for good.
                     */
                    moveCurrentPieceDownward.cancel();
                    moveCurrentPieceDownward.purge();
                    System.exit(0);
                } else if ((!mouseInPlayingArea(e.getPoint())) && !gamePlayPaused) {
                    //Invoke method to translate the current tetrominoe
                    Translation.translateCurrentPlayPieceTetrominoe(e);
                    repaint();
                }
            }
        });
        this.addMouseWheelListener(new MouseAdapter() {
            /**
             * Event handler for mouse wheel movements.Events are passed to rotateCurrentPlayPieceTetrominoe that rotates the current tetrominoe based on player input.Events are ignored if the game is paused, mouse is on the quit button, or mouse is in playing area.Events are also ignored if the current tetrominoe is a square since rotating it has no effect; this reduces computation.
             * @param e Event corresponding to movement of mouse wheel.
             */
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (!gamePlayPaused && (!(mouseInQuitButton(e.getPoint()) || mouseInPlayingArea(e.getPoint()) || (currentPlayPiece.tShape == Shape.SQUARE)))) {
                    try {
                        Rotation.rotateCurrentPlayPieceTetrominoe((e.getWheelRotation() > 0));
                        repaint();
                    } catch (InterruptedException ie) {}
                }
            }
        });
    }

    /**
     * Initializes various values used by the main game. Invoked by {@link #initialiseGame(float, int, int, int, float) }.
     * @param fl_sOS logical length of side of a square in a tetrominoe.
     */
    final void initialize(float fl_sOS) {
        
        fl_sideOfSquare = fl_sOS;
        RWIDTH = fl_sideOfSquare * 2.0f * wnos;     //0.5w = wnos*sOS
        RHEIGHT = (fl_sideOfSquare * hnos) / 0.8f;    //0.8l = hnos*sOS

        if (fl_sOS == 4.0f) {//Normal mode
            this.setSize(400, 500);//300,375);
            frameWidth = 400;
            frameHeight = 500;
        } else {//Enlarged Mode
            this.setSize(600, 750);
            frameWidth = 600;
            frameHeight = 700;
        }
        this.setMinimumSize(new Dimension(300, 375)); //TODO: check if this behaviour works outside netbeans
        maxX = frameWidth - 1;
        maxY = frameHeight - 1;
        pixelSize = max(RHEIGHT / maxY, RWIDTH / maxX);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        displayStrings = new Point[3];
        for (int i = 0; i < 3; ++i) {
            displayStrings[i] = new Point();
        }
        displayStrings[0].x = displayStrings[1].x = displayStrings[2].x = xtoX(0.6f * RWIDTH);
        displayStrings[0].y = ytoY(0.5f * RHEIGHT);
        displayStrings[1].y = ytoY(0.4f * RHEIGHT);
        displayStrings[2].y = ytoY(0.3f * RHEIGHT);

        fl_playAreaTLx = 0.05f * RWIDTH;
        fl_playAreaTLy = 0.9f * RHEIGHT;
        fl_playAreaBRx = 0.55f * RWIDTH;
        fl_playAreaBRy = 0.1f * RHEIGHT;
        playAreaTL = new Point(xtoX(fl_playAreaTLx), ytoY(fl_playAreaTLy));
        playArea = new Dimension(dimltoD(fl_playAreaBRx, fl_playAreaTLx), dimltoD(fl_playAreaTLy, fl_playAreaBRy));

        fl_nextBlockDisplayTLx = 0.6f * RWIDTH;
        fl_nextBlockDisplayTLy = 0.9f * RHEIGHT;
        fl_nextBlockDisplayBRx = 0.9f * RWIDTH;
        fl_nextBlockDisplayBRy = 0.6f * RHEIGHT;
        fl_nextBlockDisplayCentrex = (fl_nextBlockDisplayTLx + fl_nextBlockDisplayBRx) / 2;
        fl_nextBlockDisplayCentrey = (fl_nextBlockDisplayTLy + fl_nextBlockDisplayBRy) / 2;

        nextBlockDisplayTL = new Point(xtoX(fl_nextBlockDisplayTLx), ytoY(fl_nextBlockDisplayTLy));
        nextBlockDisplay = new Dimension(dimltoD(fl_nextBlockDisplayBRx, fl_nextBlockDisplayTLx), dimltoD(fl_nextBlockDisplayTLy, fl_nextBlockDisplayBRy));
        nextBlockDisplayCentreX = xtoX(fl_nextBlockDisplayCentrex);
        nextBlockDisplayCentreY = ytoY(fl_nextBlockDisplayCentrey);

        fl_quitButtonTLx = 0.6f * RWIDTH;
        fl_quitButtonTLy = 0.2f * RHEIGHT;
        fl_quitButtonBRx = 0.75f * RWIDTH;
        fl_quitButtonBRy = 0.12f * RHEIGHT;
        quitButtonTL = new Point(xtoX(fl_quitButtonTLx), ytoY(fl_quitButtonTLy));
        quitButtonSize = new Dimension(dimltoD(fl_quitButtonBRx, fl_quitButtonTLx), dimltoD(fl_quitButtonTLy, fl_quitButtonBRy));

        pauseLabelTL = new Point(xtoX(0.175f * RWIDTH), ytoY(0.5f * RHEIGHT));
        pauseLabelSize = new Dimension(xtoX(0.25f * RWIDTH), xtoX(0.1f * RWIDTH));

        sideOfSquare = Math.round(fl_sideOfSquare / pixelSize);
        hsOS = Math.round((0.5f * fl_sideOfSquare) / pixelSize);
        thsOS = Math.round((1.5f * fl_sideOfSquare) / pixelSize);
        dsOS = Math.round((2.0f * fl_sideOfSquare) / pixelSize);
        tsOS = Math.round((3.0f * fl_sideOfSquare) / pixelSize);
        filledPlayArea = new GridInfo[hnos][wnos];
        for (int i = 0; i < hnos; ++i) {
            for (int j = 0; j < wnos; ++j) {
                filledPlayArea[i][j] = new GridInfo(i, j, Color.WHITE);
            }
        }

        //UPDATE STATIC VALUES INITIALISED EARLIER TO BE ACTUALLY RANDOM
        rand = new Random();
        currentPlayPiece = new Tetrominoe(SHAPES.get(rand.nextInt(7)), Orientation.ORIG);
        nextBlock = new Tetrominoe(SHAPES.get(rand.nextInt(7)), Orientation.ORIG);

        polyPoints = new ArrayList<>();
        mouseIsInPlayPiece = false;
        tdm = new DownwardMotion();
        tdm.addListener(this);
        moveCurrentPieceDownward = new Timer("moveCPPDown", true);
    }

    /**
     * Checks whether given point is within the main game area.
     * @param p Point to be checked.
     * @return True or False based on whether point is within main game area.
     */
    boolean mouseInPlayingArea(Point p) {
        return ((p.x > playAreaTL.x) && (p.x < playAreaTL.x + playArea.width) && (p.y > playAreaTL.y) && (p.y < playAreaTL.y + playArea.height));
    }

    /**
     * Checks whether given point is inside the quit button.
     * @param p Point to be checked.
     * @return True or False based on whether point is inside the quit button.
     */
    boolean mouseInQuitButton(Point p) {
        return ((p.x > (int) (0.6 * frameWidth)) && (p.x < (int) (0.75 * frameWidth)) && (p.y > (int) (0.8 * frameHeight)) && (p.y < (int) (0.98 * frameHeight)));
    }

    /**
     * Checks whether given point is within the current tetrominoe using point-in-polygon algorithm.
     * @param p Position of current point.
     * @return True or false based on whether the point is in the current tetrominoe.
     */
    boolean mouseInPlayPiece(Point p) {

        if (polyPoints.isEmpty()) {
            calcCPPPolyPoints();
        } else {
        }
        int n = polyPoints.size(), j = n - 1;
        boolean b = false;
        int x = p.x, y = p.y;
        for (int i = 0; i < n; ++i) {
            if (((polyPoints.get(j).y <= y) && (y < polyPoints.get(i).y)
                    && (area2(polyPoints.get(j), polyPoints.get(i), p) > 0))
                    || ((polyPoints.get(i).y <= y) && (y < polyPoints.get(j).y)
                    && (area2(polyPoints.get(i), polyPoints.get(j), p) > 0))) {
                b = !b;
            }
            j = i;
        }

        return b;
    }

    /*
        Method used with point-in-polygon test to check orientation of 3 points.
        Returns +ve if orientation is ACW, -ve if CW, and 0 if points are colinear.
     */
    float area2(Point a, Point b, Point c) {
        return (a.x - c.x) * (b.y - c.y) - (a.y - c.y) * (b.x - c.x);
    }

    /**
     * Changes the current tetrominoe to a new tetrominoe, distinct from the present current and next tetrominoes. If it is not possible to change the current tetrominoe, nothing happens.
     */
    void changeCurrentPlayPiece() {
        /*
            Randomly generates new shape and orientation.
            If an orientation is not suitable, all other orientations are tried first before moving onto the next shape.
            Shapes are tried cyclically till a suitable one is found (or all exhausted). [This restricts the use of random number generator to atmost two times]
         */

        //new shape and orientation
        int nShapeIndex = rand.nextInt(7);
        Shape nShape = SHAPES.get(nShapeIndex);
        int nOrientIndex;
        Orientation nOrient;
        if (nShape == Shape.SQUARE) {
            nOrientIndex = 0;
        } else {
            nOrientIndex = rand.nextInt(4);
        }
        nOrient = ORIENTATIONS.get(nOrientIndex);

        //current tetrominoe and next tetrominoe
        Shape currShape = currentPlayPiece.tShape;
        Orientation currOrient = currentPlayPiece.tOrientation;
        Shape nextShape = nextBlock.tShape;

        boolean newTfound = false;  //flag for requisite tetrominoe has been found
        int iterCount = 0;          //how many possibilities have been tried
        while (!newTfound && iterCount < 20) {  //19 possibilities exist for the configuration of a tetrominoe
            ++iterCount;
            if ((nShape == currShape) || (nShape == nextShape)) {
                nShapeIndex = (nShapeIndex + 1) % 7;
                nShape = SHAPES.get(nShapeIndex);
                continue;
            } else {
            }
            switch (nShape) {
                case SQUARE:
                    if (TetrominoeReplacement.candidateFits(nShape, nOrient)) {
                        newTfound = true;
                    } else {
                        nShapeIndex = (nShapeIndex + 1) % 7;
                        nShape = SHAPES.get(nShapeIndex);
                        //continue loop_start;
                    }
                    break;
                case I:
                case Z:
                case S:
                    for (int i = 0; !newTfound && (i <= 1); ++i) { //try upto both orientations of current block
                        if (TetrominoeReplacement.candidateFits(nShape, nOrient)) {
                            newTfound = true;
                        } else {
                            nOrientIndex = (nOrientIndex + 1) % 4;
                            nOrient = ORIENTATIONS.get(nOrientIndex);
                        }
                    }
                    if (!newTfound) { //if neither of the orientations fits, try the next shape
                        nShapeIndex = (nShapeIndex + 1) % 7;
                        nShape = SHAPES.get(nShapeIndex);
                    } else {
                    }
                    break;
                case L:
                case ALT_L:
                case T:
                    for (int i = 0; !newTfound && (i <= 3); ++i) { //try upto all four orientations of current block
                        if (TetrominoeReplacement.candidateFits(nShape, nOrient)) {
                            newTfound = true;
                        } else {
                            nOrientIndex = (nOrientIndex + 1) % 4;
                            nOrient = ORIENTATIONS.get(nOrientIndex);
                        }
                    }
                    if (!newTfound) { //if none of the orientations fit, try the next shape
                        nShapeIndex = (nShapeIndex + 1) % 7;
                        nShape = SHAPES.get(nShapeIndex);
                    } else {
                    }
                    break;
            }
        }

        //update current piece to the found candidate
        if (newTfound) {
            replaceCurrentPlayPiece(nShape, nOrient);
            Score = (int) max(0.0f, Score - (Level * M));
        } else {
        }//not possible to replace, so do nothing
    }

    /**
     * Replaces current tetrominoe with suitable replacement found in {@link #changeCurrentPlayPiece() }.
     * @param nShape Shape of replacement candidate tetrominoe, from the enumeration {@link Shape}.
     * @param nOrient Orientation of replacement candidate tetrominoe, from the enumeration {@link Orientation}.
     */
    void replaceCurrentPlayPiece(Shape nShape, Orientation nOrient) {
        /*
            Replace the current tetrominoe with suitable replacement found in changeCurrentPlayPiece method.
            Invokes the replaceCPP function in TetrominoeReplacement class, passing all received arguments as parameters.
            Also updates the downward motion object (extending TimerTask) to point to new tetrominoe, 
                and re-calculates the vertices of tetrominoe for use with point-in-polygon test.
            Finally, repaints the entire screen so that new tetrominoe is displayed.
         */
        TetrominoeReplacement.replaceCPP(nShape, nOrient);
        tdm.updateCPP();
        calcCPPPolyPoints();
        repaint();
    }

    /**
     * Converts current tetrominoe into a set of Point objects for use with {@link #mouseInPlayPiece(java.awt.Point) }method.
     */
    void calcCPPPolyPoints() {
        /*
        CONVERTS CURRENT PLAY PIECE INTO A SET OF POINT2D OBJECTS FOR USE WITH THE POINT-IN-POLYGON ALGORITHM
        AS MANDATED IN SPECIFICATION
         */
        //empty the List
        polyPoints = new ArrayList<>();
        Point p;

        //populate list based on type of tetrominoe, add points in ACW order. Repeat the first vertex as last vertex for compatibility.
        switch (currentPlayPiece.tShape) {
            case SQUARE:
                p = filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].TL;
                polyPoints.add(new Point(p.x, p.y));
                p = filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y].TL;
                polyPoints.add(new Point(p.x, p.y + sideOfSquare));
                p = filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y].TL;
                polyPoints.add(new Point(p.x + sideOfSquare, p.y + sideOfSquare));
                p = filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y].TL;
                polyPoints.add(new Point(p.x + sideOfSquare, p.y));
                p = filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].TL;
                polyPoints.add(new Point(p.x, p.y));
                break;
            case L:
                switch (currentPlayPiece.tOrientation) {
                    case ORIG:
                        p = filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].TL;
                        polyPoints.add(new Point(p.x, p.y));

                        p = filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y].TL;
                        polyPoints.add(new Point(p.x, p.y + sideOfSquare));
                        polyPoints.add(new Point(p.x + sideOfSquare, p.y + sideOfSquare));
                        polyPoints.add(new Point(p.x + sideOfSquare, p.y));

                        p = filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y].TL;
                        polyPoints.add(new Point(p.x + sideOfSquare, p.y + sideOfSquare));
                        polyPoints.add(new Point(p.x + sideOfSquare, p.y));

                        p = filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].TL;
                        polyPoints.add(new Point(p.x, p.y));
                        break;
                    case CW90:
                        //obfuscated
                        break;
                    case CW180:
                        //obfuscated
                        break;
                    case CW270:
                        //obfuscated
                        break;
                }
                break;
            case ALT_L:
                //obfuscated
                break;
            case T:
                //obfuscted
                break;
            case Z:
                //obfuscated
                break;
            case S:
                //obfuscated
                break;
            case I:
                //obfuscated
                break;
        }
    }

    @Override
    public void paint(Graphics g) {
        /*
            Method to draw everything on the game window.
         */
        super.paint(g);
        g.setFont(new Font("TimesRoman", Font.BOLD, 12));

        //draw main play area rectangle
        g.drawRect(playAreaTL.x, playAreaTL.y, playArea.width, playArea.height);
        fillMainPlayArea(g);

        //draw PAUSE banner
        if (displayPauseLabel) {
            g.setColor(Color.blue);
            g.drawRect(pauseLabelTL.x, pauseLabelTL.y, pauseLabelSize.width, pauseLabelSize.height);
            g.drawString("PAUSE", (int) (0.25 * frameWidth), (int) (0.55 * frameHeight));
            g.setColor(Color.black);
        }

        //draw nextblock rectangle
        g.drawRect(nextBlockDisplayTL.x, nextBlockDisplayTL.y, nextBlockDisplay.width, nextBlockDisplay.height);
        drawNextBlock(g);

        //draw Quit button
        g.drawRect(quitButtonTL.x, quitButtonTL.y, quitButtonSize.width, quitButtonSize.height);
        g.drawString("QUIT", (int) (0.625 * frameWidth), (int) (0.85 * frameHeight));

        //Print strings level,lines and score
        g.drawString("Level:" + Level, displayStrings[0].x, displayStrings[0].y);
        g.drawString("Lines:" + Lines, displayStrings[1].x, displayStrings[1].y);
        g.drawString("Score:" + Score, displayStrings[2].x, displayStrings[2].y);

    }

    /**
     * Updates various variables when window has been resized.
     */
    void updateVariables() {
        maxX = frameWidth - 1;
        maxY = frameHeight - 1;
        pixelSize = RHEIGHT / maxY;
        displayStrings[0].x = displayStrings[1].x = displayStrings[2].x = (int) (0.6 * frameWidth);
        displayStrings[0].y = (int) (0.5 * frameHeight);
        displayStrings[1].y = (int) (0.6 * frameHeight);
        displayStrings[2].y = (int) (0.7 * frameHeight);

        playAreaTL.x = xtoX(fl_playAreaTLx);
        playAreaTL.y = ytoY(fl_playAreaTLy);
        playArea.width = dimltoD(fl_playAreaBRx, fl_playAreaTLx);
        playArea.height = dimltoD(fl_playAreaTLy, fl_playAreaBRy);

        nextBlockDisplayTL.x = xtoX(fl_nextBlockDisplayTLx);
        //obfuscated
        //obfuscated
        //obfuscated
        //obfuscated
        //obfuscated

        quitButtonTL.x = xtoX(fl_quitButtonTLx);
        //obfuscated
        //obfuscated
        //obfuscated
        //obfuscated
        //obfuscated
        pauseLabelSize.width = (int) (0.25 * frameWidth);
        pauseLabelSize.height = (int) (0.1 * frameWidth);

        //obfuscated
        //obfuscated
        //obfuscated
        //obfuscated
        //obfuscated

        //update filled in Area
        for (int i = 0; i < hnos; ++i) {
            for (int j = 0; j < wnos; ++j) {
                filledPlayArea[i][j].TL.x = xtoX(MainGameFrame.fl_playAreaTLx + j * MainGameFrame.fl_sideOfSquare);
                filledPlayArea[i][j].TL.y = ytoY(MainGameFrame.fl_playAreaTLy - i * MainGameFrame.fl_sideOfSquare);
            }
        }
    }

    /**
     * Fills in the main game grid.
     * @param g Graphic context.
     */
    void fillMainPlayArea(Graphics g) {
        /*
            First fill in the squares corresponding to current tetrominoe.
            Then fill in the rest of the grid.
         */
        Color cppc = (currentPlayPiece != null) ? Tetrominoe.COLORMAP.get(currentPlayPiece.tShape) : Color.WHITE;
        for (int i, j, k = 0; k < 4; ++k) {
            i = currentPlayPiece.occupiedSquares[k].x;
            j = currentPlayPiece.occupiedSquares[k].y;
            filledPlayArea[i][j].color = cppc;
        }
        for (int i = 0; i < hnos; ++i) {
            for (int j = 0; j < wnos; ++j) {
                if (filledPlayArea[i][j].color != Color.WHITE) {
                    //obfuscated
                    //obfuscated
                    //obfuscated
                    //obfuscated
                }
            }
        }

    }

    /**
     * Draws next tetrominoe in next block display area.
     * @param g Graphics context.
     */
    void drawNextBlock(Graphics g) {
        if (nextBlock == null) {
            return;
        } else {
        }

        g.setColor(Tetrominoe.COLORMAP.get(nextBlock.tShape));
        switch (nextBlock.tShape) {
            case SQUARE:
                g.fillRect(nextBlockDisplayCentreX - sideOfSquare, nextBlockDisplayCentreY - sideOfSquare, dsOS, dsOS);
                g.setColor(Color.BLACK);
                g.drawRect(nextBlockDisplayCentreX - sideOfSquare, nextBlockDisplayCentreY - sideOfSquare, dsOS, dsOS);
                g.drawLine(nextBlockDisplayCentreX, nextBlockDisplayCentreY - sideOfSquare, nextBlockDisplayCentreX, nextBlockDisplayCentreY + sideOfSquare);
                g.drawLine(nextBlockDisplayCentreX - sideOfSquare, nextBlockDisplayCentreY, nextBlockDisplayCentreX + sideOfSquare, nextBlockDisplayCentreY);
                break;
            case L:
                g.fillRect(nextBlockDisplayCentreX - thsOS, nextBlockDisplayCentreY - hsOS, tsOS, sideOfSquare);
                g.fillRect(nextBlockDisplayCentreX - thsOS, nextBlockDisplayCentreY + hsOS, sideOfSquare, sideOfSquare);
                g.setColor(Color.BLACK);
                g.drawRect(nextBlockDisplayCentreX - thsOS, nextBlockDisplayCentreY - hsOS, tsOS, sideOfSquare);
                g.drawRect(nextBlockDisplayCentreX - thsOS, nextBlockDisplayCentreY + hsOS, sideOfSquare, sideOfSquare);
                g.drawLine(nextBlockDisplayCentreX - hsOS, nextBlockDisplayCentreY - hsOS, nextBlockDisplayCentreX - hsOS, nextBlockDisplayCentreY + hsOS);
                g.drawLine(nextBlockDisplayCentreX + hsOS, nextBlockDisplayCentreY - hsOS, nextBlockDisplayCentreX + hsOS, nextBlockDisplayCentreY + hsOS);
                break;
            case ALT_L:
                //obfuscated
                break;
            case T:
                //obfuscated
                break;
            case Z:
                //obfuscated
                break;
            case S:
                //obfuscated
                break;
            case I:
                //obfuscated
                break;
        }
    }

    
    /**
     * Helper method to convert logical x co-ordinate to device X co-ordinate.
     * @param x Horizontal distance from origin in logical co-ordinates [Logical abscissa].
     * @return Horizontal distance from origin in device co-ordinates [Device abscissa].
     */
    static int xtoX(float x) {
        return Math.round(x / pixelSize);
    }

    /**
     * Helper method to convert logical y co-ordinate to device Y co-ordinate.
     * @param y Vertical distance from origin in logical co-ordinates [Logical ordinate].
     * @return Vertical distance from origin in device co-ordinates [Device ordinate].
     */
    static int ytoY(float y) {
        return maxY - Math.round(y / pixelSize);
    }

    /**
     * Helper method to convert a distance from logical to device co-ordinates.
     * @param a logical abscissa/ordinate of one end point.
     * @param b logical abscissa/ordinate of second end point.
     * @return length along device X or Y axis corresponding to logical end-points.
     */
    static int dimltoD(float a, float b) {
        return Math.round((a - b) / pixelSize);
    }

    @Override
    public void setGameOver() {
        /*
            Called via event handling when the game is over.
            Inhibits further downward movement of the tetrominoe by cancelling and purging queue of associated timer.
            Redisplays the settings dialog so that gameplay may be restarted.
         */
        gameOver = true;
        moveCurrentPieceDownward.cancel();
        //obfuscated
        //obfuscated
        settingsDialog.toFront();
    }

    @Override
    public void repaintMustBeCalled() {
        /*
            Called via event handling when the screen must be refreshed.
         */
        repaint();
    }

    @Override
    public void updateTimerInterval() {
        /*
            Change the timer interval to reflect new falling speed
            Map falling speed to Timer value
            Mapping Timer interval = 1 second/falling_speed
         */
        moveCurrentPieceDownward.cancel();
        //obfuscated
        //obfuscated
        tdm = new DownwardMotion();
        //obfuscated
        //obfuscated
    }

    @Override
    public void initialiseGame(float fl_sOS, int horizontalNoOfSquares, int currMSliderVal, int currNSliderVal, float currSSliderVal) {
        /*
            Called by press of start button in settings dialog, by event handling
         */
        wnos = horizontalNoOfSquares;
        hnos = 2 * wnos;
        M = currMSliderVal;
        N = currNSliderVal;
        S = currSSliderVal;
        initialize(fl_sOS);
        startGame();
    }

    /**
     * Schedules downward motion of tetrominoes on a timer. Invoked by {@link #initialiseGame(float, int, int, int, float) }.
     */
    public void startGame() {
        moveCurrentPieceDownward.scheduleAtFixedRate(tdm, 2000, 1000);
    }

    static int Level;
    static int Lines;
    static int Score;
    static boolean displayPauseLabel;
    static int hnos;
    static int wnos;
    int frameWidth;    //effective dimensions of canvas
    int frameHeight;   //effective dimensions of canvas
    Point playAreaTL;  //top left (on-screen) vertex of the play area
    Dimension playArea; //width and height of play area
    Point nextBlockDisplayTL; ///top left(on-screen) vertex of the next block display
    Dimension nextBlockDisplay; //width and height of next block display

    Point quitButtonTL;
    Dimension quitButtonSize;
    Point pauseLabelTL;
    Dimension pauseLabelSize;
    Point[] displayStrings; //positions of "Level", "Lines", and "Score" strings
    int sideOfSquare;
    int hsOS; //half of sideOfSquare
    int thsOS; //1.5 times sideOfSquare (named thrice half of sideOfSquare
    int dsOS; //double sideOfSquare
    int tsOS;
    static Tetrominoe currentPlayPiece;
    static Tetrominoe nextBlock;
    static GridInfo[][] filledPlayArea;

    static int maxX;
    static int maxY;
    int nextBlockDisplayCentreX;
    int nextBlockDisplayCentreY;

    //FLOATING POINT CO-ORDINATES
    static float RWIDTH;
    static float RHEIGHT;
    static float pixelSize;
    static float fl_playAreaTLx;
    static float fl_playAreaTLy;
    float fl_playAreaBRx;
    float fl_playAreaBRy;
    float fl_nextBlockDisplayTLx;
    float fl_nextBlockDisplayTLy;
    float fl_nextBlockDisplayBRx;
    float fl_nextBlockDisplayBRy;
    float fl_nextBlockDisplayCentrex;
    float fl_nextBlockDisplayCentrey;
    float fl_quitButtonTLx;
    float fl_quitButtonTLy;
    float fl_quitButtonBRx;
    float fl_quitButtonBRy;
    static float fl_sideOfSquare;

    static final ArrayList<Shape> SHAPES;
    static final ArrayList<Orientation> ORIENTATIONS;
    Random rand;
    static DownwardMotion tdm;
    static boolean gamePlayPaused;
    volatile static boolean gameOver;
    static boolean mouseEnabled;
    static Timer moveCurrentPieceDownward;
    List<Point> polyPoints;

    //following mandated by design guidelines
    /**
     * Scoring Factor. Range [1,10].
     */
    static int M;       //SCORING FACTOR: 1-10
    /**
     * Number of rows to be cleared to progress to next level of difficulty. Range [20-50].
     */
    static int N;       //NO. OF ROWS REQUIRED FOR EACH LEVEL OF DIFFICULTY: 20-50
    /**
     * Speed factor. Affects rate of increase of speed with difficulty level. Does not change initial speed. Range [0.1-1.0].
     */
    static float S;     //SPEED FACTOR: 0.1-1.0

    GameSettings settingsDialog;
    static float fallingSpeed;

    static boolean mouseIsInPlayPiece;
}
