package io.github.sandeepsukumaran.javatetrisgame;

import java.awt.Color;
import java.awt.Point;

/**
 * A class of functions to support the replacement of current tetrominoe on
 * mouse over. Contains two functions: one that checks if a new candidate fits
 * and another that replaces the current tetrominoe with the candidate
 */
class TetrominoeReplacement {

    /**
     * Replace the current play piece with the suitable replacement found in
     * method changeCurrentPlayPiece of class TetrisGame.
     *
     * @param nShape Shape of replacement tetrominoe.
     * @param nOrient Orientation of replacement tetrominoe.
     */
    static void replaceCPP(Shape nShape, Orientation nOrient) {
        /*
            To replace, color the previously used, newly unused squares white;
                        set location of new sqaures, and
                        color in the new tetrominoe fully
         */
        switch (MainGameFrame.currentPlayPiece.tShape) {
            case SQUARE: //replace square
                replaceSquareCPP(nShape, nOrient);
                break;
            case L: //replace L
                replaceLCPP(nShape, nOrient);
                break;
            case ALT_L: //replace ALT_L
                replaceALT_LCPP(nShape, nOrient);
                break;
            case T: //replace T 
                replaceTCPP(nShape, nOrient);
                break;
            case Z: //replace Z
                replaceZCPP(nShape, nOrient);
                break;
            case S: //replace S
                replaceSCPP(nShape, nOrient);
                break;
            case I: //replace I
                replaceICPP(nShape, nOrient);
                break;
        }
    }

    /**
     * Method to replace square tetrominoe with suitable replacement.
     *
     * @param nShape Shape of replacement tetrominoe.
     * @param nOrient Orientation of replacement tetrominoe.
     */
    private static void replaceSquareCPP(Shape nShape, Orientation nOrient) {
        newTetro = new Tetrominoe(nShape, nOrient);
        switch (nShape) {
            case L: //replace square with L
                replaceSquareCPPwithL(nShape, nOrient);
                break;
            case ALT_L: //replace square with ALT_L
                replaceSquareCPPwithALT_L(nShape, nOrient);
                break;
            case T: //replace square with T
                replaceSquareCPPwithT(nShape, nOrient);
                break;
            case Z: //replace square with Z
                replaceSquareCPPwithZ(nShape, nOrient);
                break;
            case S: //replace square with S
                replaceSquareCPPwithS(nShape, nOrient);
                break;
            case I: //replace square with I
                replaceSquareCPPwithI(nShape, nOrient);
                break;
        }
    }

    /**
     * Method to replace square tetrominoe with L shaped suitable replacement.
     *
     * @param nShape Shape of replacement tetrominoe.
     * @param nOrient Orientation of replacement tetrominoe.
     */
    private static void replaceSquareCPPwithL(Shape nShape, Orientation nOrient) {
        newTetro = new Tetrominoe(nShape, nOrient);
        switch (nOrient) {
            case ORIG: //replace square with ORIG L
                unusedSquare = MainGameFrame.currentPlayPiece.occupiedSquares[3];
                MainGameFrame.filledPlayArea[unusedSquare.x][unusedSquare.y].color = Color.WHITE;
                newTetro.occupiedSquares[0].x = MainGameFrame.currentPlayPiece.occupiedSquares[0].x;
                newTetro.occupiedSquares[0].y = MainGameFrame.currentPlayPiece.occupiedSquares[0].y;
                newTetro.occupiedSquares[1].x = MainGameFrame.currentPlayPiece.occupiedSquares[1].x;
                newTetro.occupiedSquares[1].y = MainGameFrame.currentPlayPiece.occupiedSquares[1].y;
                newTetro.occupiedSquares[2].x = MainGameFrame.currentPlayPiece.occupiedSquares[1].x;
                newTetro.occupiedSquares[2].y = MainGameFrame.currentPlayPiece.occupiedSquares[1].y + 1;
                newTetro.occupiedSquares[3].x = MainGameFrame.currentPlayPiece.occupiedSquares[3].x;
                newTetro.occupiedSquares[3].y = MainGameFrame.currentPlayPiece.occupiedSquares[3].y - 1;
                MainGameFrame.currentPlayPiece = newTetro;
                break;
            case CW90: //replace square with CW90 L
                //obfuscated
                break;
            case CW180: //replace square with CW180 L
                //obfuscated
                break;
            case CW270: //replace square with CW270 L
                //obfuscated
                break;
        }
    }

    /**
     * Method to replace square tetrominoe with alternate L shaped suitable
     * replacement.
     *
     * @param nShape Shape of replacement tetrominoe.
     * @param nOrient Orientation of replacement tetrominoe.
     */
    private static void replaceSquareCPPwithALT_L(Shape nShape, Orientation nOrient) {
        //obfuscated
    }

    /**
     * Method to replace square tetrominoe with T shaped suitable replacement.
     *
     * @param nShape Shape of replacement tetrominoe.
     * @param nOrient Orientation of replacement tetrominoe.
     */
    private static void replaceSquareCPPwithT(Shape nShape, Orientation nOrient) {
        //obfuscated
    }

    /**
     * Method to replace square tetrominoe with Z shaped suitable replacement.
     *
     * @param nShape Shape of replacement tetrominoe.
     * @param nOrient Orientation of replacement tetrominoe.
     */
    private static void replaceSquareCPPwithZ(Shape nShape, Orientation nOrient) {
        //obfuscated
    }

    /**
     * Method to replace square tetrominoe with S shaped suitable replacement.
     *
     * @param nShape Shape of replacement tetrominoe.
     * @param nOrient Orientation of replacement tetrominoe.
     */
    private static void replaceSquareCPPwithS(Shape nShape, Orientation nOrient) {
        newTetro = new Tetrominoe(nShape, nOrient);
        switch (nOrient) {
            case ORIG:
            case CW180:
                unusedSquare = MainGameFrame.currentPlayPiece.occupiedSquares[0];
                MainGameFrame.filledPlayArea[unusedSquare.x][unusedSquare.y].color = Color.WHITE;
                newTetro.occupiedSquares[0].x = MainGameFrame.currentPlayPiece.occupiedSquares[2].x;
                newTetro.occupiedSquares[0].y = MainGameFrame.currentPlayPiece.occupiedSquares[2].y;
                newTetro.occupiedSquares[1].x = MainGameFrame.currentPlayPiece.occupiedSquares[3].x;
                newTetro.occupiedSquares[1].y = MainGameFrame.currentPlayPiece.occupiedSquares[3].y;
                newTetro.occupiedSquares[2].x = MainGameFrame.currentPlayPiece.occupiedSquares[1].x;
                newTetro.occupiedSquares[2].y = MainGameFrame.currentPlayPiece.occupiedSquares[1].y;
                newTetro.occupiedSquares[3].x = MainGameFrame.currentPlayPiece.occupiedSquares[1].x;
                newTetro.occupiedSquares[3].y = MainGameFrame.currentPlayPiece.occupiedSquares[1].y + 1;
                MainGameFrame.currentPlayPiece = newTetro;
                break;
            //obfuscated
        }
    }

    /**
     * Method to replace square tetrominoe with I shaped suitable replacement.
     *
     * @param nShape Shape of replacement tetrominoe.
     * @param nOrient Orientation of replacement tetrominoe.
     */
    private static void replaceSquareCPPwithI(Shape nShape, Orientation nOrient) {
        //obfuscated
    }

    /**
     * Method to replace L shaped tetrominoe with suitable replacement.
     *
     * @param nShape Shape of replacement tetrominoe.
     * @param nOrient Orientation of replacement tetrominoe.
     */
    private static void replaceLCPP(Shape nShape, Orientation nOrient) {
        switch (nShape) {
            case SQUARE: //replace L with square
                replaceLCPPwithSquare(nShape, nOrient);
                break;
            case ALT_L: //replace L with ALT_L
                replaceLCPPwithALT_L(nShape, nOrient);
                break;
            case T: //replace L with T
                replaceLCPPwithT(nShape, nOrient);
                break;
            case Z: //replace L with Z
                replaceLCPPwithZ(nShape, nOrient);
                break;
            case S: //replace L with S
                replaceLCPPwithS(nShape, nOrient);
                break;
            case I: //replace L with I
                replaceLCPPwithI(nShape, nOrient);
                break;
        }
    }

    /**
     * Method to replace L shaped tetrominoe with suitable square shaped
     * replacement.
     *
     * @param nShape Shape of replacement tetrominoe.
     * @param nOrient Orientation of replacement tetrominoe.
     */
    private static void replaceLCPPwithSquare(Shape nShape, Orientation nOrient) {
        newTetro = new Tetrominoe(nShape, nOrient);
        switch (MainGameFrame.currentPlayPiece.tOrientation) {
            case ORIG: //replace orig L with square
                unusedSquare = MainGameFrame.currentPlayPiece.occupiedSquares[2];
                MainGameFrame.filledPlayArea[unusedSquare.x][unusedSquare.y].color = Color.WHITE;
                newTetro.occupiedSquares[0].x = MainGameFrame.currentPlayPiece.occupiedSquares[0].x;
                newTetro.occupiedSquares[0].y = MainGameFrame.currentPlayPiece.occupiedSquares[0].y;
                newTetro.occupiedSquares[1].x = MainGameFrame.currentPlayPiece.occupiedSquares[1].x;
                newTetro.occupiedSquares[1].y = MainGameFrame.currentPlayPiece.occupiedSquares[1].y;
                newTetro.occupiedSquares[2].x = MainGameFrame.currentPlayPiece.occupiedSquares[3].x;
                newTetro.occupiedSquares[2].y = MainGameFrame.currentPlayPiece.occupiedSquares[3].y;
                newTetro.occupiedSquares[3].x = MainGameFrame.currentPlayPiece.occupiedSquares[3].x;
                newTetro.occupiedSquares[3].y = MainGameFrame.currentPlayPiece.occupiedSquares[3].y + 1;
                MainGameFrame.currentPlayPiece = newTetro;
                break;
            case CW90: //replace CW90 L with square
                //obfuscated
                break;
            case CW180: //replace CW180 L with square
                //obfuscated
                break;
            case CW270: //replace CW270 L with square
                //obfuscated
                break;
        }
    }

    /**
     * Method to replace L shaped tetrominoe with suitable alternate L shaped
     * replacement.
     *
     * @param nShape Shape of replacement tetrominoe.
     * @param nOrient Orientation of replacement tetrominoe.
     */
    private static void replaceLCPPwithALT_L(Shape nShape, Orientation nOrient) {
        newTetro = new Tetrominoe(nShape, nOrient);
        switch (nOrient) {
            case ORIG: //replace L with ORIG ALT_L
                switch (MainGameFrame.currentPlayPiece.tOrientation) {
                    case ORIG: // replace ORIG L with ORIG ALT_L
                        //obfuscated
                        break;
                    case CW90: // replace CW90 L with ORIG ALT_L
                        unusedSquare = MainGameFrame.currentPlayPiece.occupiedSquares[2];
                        MainGameFrame.filledPlayArea[unusedSquare.x][unusedSquare.y].color = Color.WHITE;
                        newTetro.occupiedSquares[0].x = MainGameFrame.currentPlayPiece.occupiedSquares[3].x;
                        newTetro.occupiedSquares[0].y = MainGameFrame.currentPlayPiece.occupiedSquares[3].y - 1;
                        newTetro.occupiedSquares[1].x = MainGameFrame.currentPlayPiece.occupiedSquares[3].x;
                        newTetro.occupiedSquares[1].y = MainGameFrame.currentPlayPiece.occupiedSquares[3].y;
                        newTetro.occupiedSquares[2].x = MainGameFrame.currentPlayPiece.occupiedSquares[0].x;
                        newTetro.occupiedSquares[2].y = MainGameFrame.currentPlayPiece.occupiedSquares[0].y;
                        newTetro.occupiedSquares[3].x = MainGameFrame.currentPlayPiece.occupiedSquares[1].x;
                        newTetro.occupiedSquares[3].y = MainGameFrame.currentPlayPiece.occupiedSquares[1].y;
                        MainGameFrame.currentPlayPiece = newTetro;
                        break;
                    case CW180: // replace CW180 L with ORIG ALT_L
                        //obfuscated
                        break;
                    case CW270: // replace CW270 L with ORIG ALT_L
                        //obfuscated
                        break;
                }
                break;
            case CW90://replace L with CW90 ALT_L
                //obfuscated
                break;
            case CW180: //replace L with CW180 ALT_L
                //obfuscated
                break;
            case CW270: //replace L with CW270 ALT_L
                //obfuscated
                break;
        }
    }

    /**
     * Method to replace L shaped tetrominoe with suitable T shaped replacement.
     *
     * @param nShape Shape of replacement tetrominoe.
     * @param nOrient Orientation of replacement tetrominoe.
     */
    private static void replaceLCPPwithT(Shape nShape, Orientation nOrient) {
        //obfuscated
    }

    /**
     * Method to replace L shaped tetrominoe with suitable Z shaped replacement.
     *
     * @param nShape Shape of replacement tetrominoe.
     * @param nOrient Orientation of replacement tetrominoe.
     */
    private static void replaceLCPPwithZ(Shape nShape, Orientation nOrient) {
        //obfuscated
    }

    // Around 6k LOC obfuscated

    private static Point unusedSquare;
    private static Tetrominoe newTetro;
}
