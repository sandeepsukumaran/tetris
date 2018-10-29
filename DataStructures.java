package io.github.sandeepsukumaran.javatetrisgame;

import java.awt.Color;
import java.awt.Point;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for each tetrominoe. Used to hold information about current and next tetrominoes.
 * Contains information like shape, orientation and occupied squares in game grid.
 * Colour of tetrominoe can be inferred from a static Map that sets a unique colour to each shape.
 */
class Tetrominoe{
    /**
     * Shape of tetrominoe, as a member of the Shape enumeration. {@link #Shape}
     */
    Shape tShape;
    /**
     * Orientation of tetrominoe, as a member of the Orientation enumeration. {@link #Orientation}
     */
    Orientation tOrientation;
    /**
     * List of (X,Y) co-ordinates of the four squares occupied by tetrominoe. X is row number. Y is column number.
     */
    Point[] occupiedSquares;
    /**
     * Map of colour of each tetrominoe based on its shape.
     */
    static final Map<Shape,Color> COLORMAP;
    static{
        Map<Shape,Color> _colorMap = new HashMap<>();
        _colorMap.put(Shape.SQUARE, Color.GREEN);
        _colorMap.put(Shape.L, Color.RED);
        _colorMap.put(Shape.ALT_L, Color.BLUE);
        _colorMap.put(Shape.Z, Color.MAGENTA);
        _colorMap.put(Shape.S, Color.YELLOW);
        _colorMap.put(Shape.I, Color.CYAN);
        _colorMap.put(Shape.T, Color.ORANGE);
        COLORMAP = Collections.unmodifiableMap(_colorMap);
    }
    
    /**
     * Constructor.
     * @param tetroShape Shape of tetrominoe to be constructed, as a member of the Shape enumeration.
     * @param tetroOrientation Orientation of tetrominoe to be constructed, as a member of the Orientation enumeration.
     */
    Tetrominoe(Shape tetroShape, Orientation tetroOrientation){
        tShape = tetroShape;
        tOrientation = tetroOrientation;
        occupiedSquares = new Point[4]; // x is ROW and y is COLUMN
        for(int i=0;i<4;++i)
            occupiedSquares[i] = new Point();
        switch(tShape){ //SQUARE L,ALT_L,T,Z,S,I
            case SQUARE:
                occupiedSquares[0].x = occupiedSquares[1].x = 0;
                occupiedSquares[2].x = occupiedSquares[3].x = 1;
                occupiedSquares[0].y = occupiedSquares[2].y = 4;
                occupiedSquares[1].y = occupiedSquares[3].y = 5;
                break;
            case L:
                occupiedSquares[0].x = occupiedSquares[1].x = occupiedSquares[2].x = 0;
                occupiedSquares[3].x = 1;
                occupiedSquares[0].y = occupiedSquares[3].y = 4;
                occupiedSquares[1].y = 5;
                occupiedSquares[2].y = 6;
                break;
            case ALT_L:
                occupiedSquares[0].x = occupiedSquares[1].x = occupiedSquares[2].x = 0;
                occupiedSquares[3].x = 1;
                occupiedSquares[0].y = 4;
                occupiedSquares[1].y = 5;
                occupiedSquares[2].y = occupiedSquares[3].y = 6;
                break;
            case T:
                occupiedSquares[0].x = occupiedSquares[1].x = occupiedSquares[2].x = 0;
                occupiedSquares[3].x = 1;
                occupiedSquares[0].y = 4;
                occupiedSquares[1].y = occupiedSquares[3].y = 5;
                occupiedSquares[2].y = 6;
                break;
            case Z:
                occupiedSquares[0].x = occupiedSquares[1].x = 0;
                occupiedSquares[2].x = occupiedSquares[3].x = 1;
                occupiedSquares[0].y = 4;
                occupiedSquares[1].y = occupiedSquares[2].y = 5;
                occupiedSquares[3].y = 6;
                break;
            case S:
                occupiedSquares[0].x = occupiedSquares[1].x = 1;
                occupiedSquares[2].x = occupiedSquares[3].x = 0;
                occupiedSquares[0].y = 4;
                occupiedSquares[1].y = occupiedSquares[2].y = 5;
                occupiedSquares[3].y = 6;
                break;
            case I:
                occupiedSquares[0].x = occupiedSquares[1].x = occupiedSquares[2].x = occupiedSquares[3].x = 0;
                occupiedSquares[0].y = 3;
                occupiedSquares[1].y = 4;
                occupiedSquares[2].y = 5;
                occupiedSquares[3].y = 6;
                break;
        }
    }
}

/**
 * Possible shapes of tetrominoes.
 * <li>{@link #SQUARE}</li>
 * <li>{@link #L}</li>
 * <li>{@link #ALT_L}</li>
 * <li>{@link #T}</li>
 * <li>{@link #Z}</li>
 * <li>{@link #S}</li>
 * <li>{@link #I}</li>
 */
enum Shape{
    /**
     * Square shaped.
     */
    SQUARE,
    /**
     * L shaped.
     */
    L,
    /**
     * Alternate L shaped.
     */
    ALT_L,
    /**
     * T shaped.
     */
    T,
    /**
     * Z shaped.
     */
    Z,
    /**
     * S shaped.
     */
    S,
    /**
     * I shaped.
     */
    I
}

/**
 * Possible orientations of tetrominoes.
 * <li>{@link #ORIG}</li>
 * <li>{@link #CW90}</li>
 * <li>{@link #CW180}</li>
 * <li>{@link #CW270}</li>
 */
enum Orientation{
    /**
     * Original orientation.
     */
    ORIG,
    /**
     * Rotated clockwise through 90 degrees.
     */
    CW90,
    /**
     * Rotated clockwise through 180 degrees.
     */
    CW180,
    /**
     * Rotated clockwise through 270 degrees.
     */
    CW270
}



/**
 * Interface used to implement updating of values in main game using event handling
 */
interface myListener{
    /**
     * Method to initialize various parameters of game.
     * @param fl_sOS Length of side of square in logical co-ordinates.
     * @param horizontalNoOfSquares No. of squares in a row in the game.
     * @param currMSliderVal Scoring factor.
     * @param currNSliderVal No. of rows to be cleared to increase difficulty level.
     * @param currSSliderVal Speed Increment Factor.
     */
    void initialiseGame(float fl_sOS, int horizontalNoOfSquares, int currMSliderVal, int currNSliderVal, float currSSliderVal);
    /**
     * Method to invoke repaint method.
     */
    void repaintMustBeCalled();
    /**
     * Method to change the timer interval to reflect new falling speed.
     */
    void updateTimerInterval();
    /**
     * Method to perform cleanup operations at end of game.
     */
    void setGameOver();
}


/**
 * Class used to store information about each square in the game grid.
 * Each square is represented by the top left vertex and a colour (default WHITE).
 */
final class GridInfo{
    /**
     * Colour of square in grid. White only if empty.
     */
    Color color;
    /**
     * (X,Y) device co-ordinates of top left vertex of square in grid.
     */
    Point TL;
    /**
     * Constructor.
     * @param i Row number.
     * @param j Column number.
     * @param c Color of square.
     */
    GridInfo(int i,int j, Color c){
        initTL(i,j);
        color = c;
    }
    
    /**
     * Method to compute (X,Y) device co-ordinates of top left vertex of square.
     * @param i Row number.
     * @param j Column number.
     */
    void initTL(int i,int j){
        TL = new Point(MainGameFrame.xtoX(MainGameFrame.fl_playAreaTLx + j*MainGameFrame.fl_sideOfSquare), MainGameFrame.ytoY(MainGameFrame.fl_playAreaTLy - i*MainGameFrame.fl_sideOfSquare));
    }
}
