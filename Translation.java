package io.github.sandeepsukumaran.javatetrisgame;

import java.awt.Color;
import java.awt.event.MouseEvent;
import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

/**
 * Handles translation of current tetrominoe based on user input.
 */
class Translation{
    /**
     * Translates current tetrominoe based on mouse click.
     * @param e Mouse click.
     */
    static void translateCurrentPlayPieceTetrominoe(MouseEvent e){
        
        if(isLeftMouseButton(e)){
            //MOVE LEFT COMMAND RECEIVED
            switch(MainGameFrame.currentPlayPiece.tShape){
                case SQUARE:
                    //check if already at leftmost
                    if(MainGameFrame.currentPlayPiece.occupiedSquares[0].y == 0)
                        return;
                    //check if left squares are free
                    else if((MainGameFrame.filledPlayArea[MainGameFrame.currentPlayPiece.occupiedSquares[0].x][MainGameFrame.currentPlayPiece.occupiedSquares[0].y -1].color !=Color.WHITE)||(MainGameFrame.filledPlayArea[MainGameFrame.currentPlayPiece.occupiedSquares[2].x][MainGameFrame.currentPlayPiece.occupiedSquares[2].y -1].color !=Color.WHITE))
                        return;
                    else;
                    //set rightmost to white
                    MainGameFrame.filledPlayArea[MainGameFrame.currentPlayPiece.occupiedSquares[1].x][MainGameFrame.currentPlayPiece.occupiedSquares[1].y].color=Color.WHITE;
                    MainGameFrame.filledPlayArea[MainGameFrame.currentPlayPiece.occupiedSquares[3].x][MainGameFrame.currentPlayPiece.occupiedSquares[3].y].color=Color.WHITE;
                    //move left
                    --MainGameFrame.currentPlayPiece.occupiedSquares[1].y;
                    --MainGameFrame.currentPlayPiece.occupiedSquares[3].y;
                    --MainGameFrame.currentPlayPiece.occupiedSquares[0].y;
                    --MainGameFrame.currentPlayPiece.occupiedSquares[2].y;
                    break;
                case L:
                    //obfuscated
                case ALT_L:
                    //obfuscated
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
        }else if(isRightMouseButton(e)){
            //MOVE RIGHT COMMAND RECEIVED
            switch(MainGameFrame.currentPlayPiece.tShape){
                case SQUARE:
                    //check if already at rightmost
                    if(MainGameFrame.currentPlayPiece.occupiedSquares[1].y == MainGameFrame.wnos-1)
                        return;
                    //check if right squares are free
                    else if((MainGameFrame.filledPlayArea[MainGameFrame.currentPlayPiece.occupiedSquares[1].x][MainGameFrame.currentPlayPiece.occupiedSquares[1].y +1].color !=Color.WHITE)||(MainGameFrame.filledPlayArea[MainGameFrame.currentPlayPiece.occupiedSquares[3].x][MainGameFrame.currentPlayPiece.occupiedSquares[3].y +1].color !=Color.WHITE))
                        return;
                    else;
                    //set leftmost to white
                    MainGameFrame.filledPlayArea[MainGameFrame.currentPlayPiece.occupiedSquares[0].x][MainGameFrame.currentPlayPiece.occupiedSquares[0].y].color=Color.WHITE;
                    MainGameFrame.filledPlayArea[MainGameFrame.currentPlayPiece.occupiedSquares[2].x][MainGameFrame.currentPlayPiece.occupiedSquares[2].y].color=Color.WHITE;
                    //move right
                    ++MainGameFrame.currentPlayPiece.occupiedSquares[1].y;
                    ++MainGameFrame.currentPlayPiece.occupiedSquares[3].y;
                    ++MainGameFrame.currentPlayPiece.occupiedSquares[0].y;
                    ++MainGameFrame.currentPlayPiece.occupiedSquares[2].y;
                    break;
                case L:
                    switch(MainGameFrame.currentPlayPiece.tOrientation){
                        case ORIG:
                            //check if already at rightmost
                            if(MainGameFrame.currentPlayPiece.occupiedSquares[2].y == MainGameFrame.wnos-1)
                                return;
                            //check if right squares are free
                            else if((MainGameFrame.filledPlayArea[MainGameFrame.currentPlayPiece.occupiedSquares[2].x][MainGameFrame.currentPlayPiece.occupiedSquares[2].y + 1].color!=Color.WHITE)||(MainGameFrame.filledPlayArea[MainGameFrame.currentPlayPiece.occupiedSquares[3].x][MainGameFrame.currentPlayPiece.occupiedSquares[3].y + 1].color!=Color.WHITE))
                                return;
                            else;
                            //set leftmost to white
                            MainGameFrame.filledPlayArea[MainGameFrame.currentPlayPiece.occupiedSquares[0].x][MainGameFrame.currentPlayPiece.occupiedSquares[0].y].color=Color.WHITE;
                            MainGameFrame.filledPlayArea[MainGameFrame.currentPlayPiece.occupiedSquares[3].x][MainGameFrame.currentPlayPiece.occupiedSquares[3].y].color=Color.WHITE;
                            //move right
                            ++MainGameFrame.currentPlayPiece.occupiedSquares[0].y; ++MainGameFrame.currentPlayPiece.occupiedSquares[1].y;
                            ++MainGameFrame.currentPlayPiece.occupiedSquares[2].y; ++MainGameFrame.currentPlayPiece.occupiedSquares[3].y;
                            break;
                        case CW90:
                            //obfuscated
                            break;
                        case CW180:
                            //obfuscated
                            break;
                        case CW270:
                            ///obfuscated
                            break;
                    }
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
    }
}
