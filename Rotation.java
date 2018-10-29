package io.github.sandeepsukumaran.javatetrisgame;

import java.awt.Color;
import static tetrisgame.MainGameFrame.currentPlayPiece;
import static tetrisgame.MainGameFrame.filledPlayArea;
import static tetrisgame.MainGameFrame.wnos;
import static tetrisgame.MainGameFrame.hnos;


/**
 * Handles rotation of current tetrominoe based on user input.
 */
class Rotation{
    
    /**
     * Method to rotate the current tetrominoe, called when the user moves scroll wheel.
     * @param ACWRotation True if anti-clockwise rotation has been requested, False otherwise.
     * @throws InterruptedException 
     */
    static void rotateCurrentPlayPieceTetrominoe(boolean ACWRotation) throws InterruptedException {

        if (ACWRotation) {
            switch (currentPlayPiece.tShape) {
                case SQUARE:
                    break;
                case L:
                    switch (currentPlayPiece.tOrientation) {
                        case ORIG:
                            //check if at prevented by any walls
                            if (currentPlayPiece.occupiedSquares[0].x == 0) //TOP WALL
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[2].x + 1][currentPlayPiece.occupiedSquares[2].y].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[1].x - 1][currentPlayPiece.occupiedSquares[1].y].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[1].x + 1][currentPlayPiece.occupiedSquares[1].y].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y].color = Color.WHITE;
                            //update square of current play piece
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            currentPlayPiece.tOrientation = Orientation.CW270;
                            break;
                        case CW90:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[0].y == wnos - 1) //RIGHT WALL
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y - 1].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y + 1].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y - 1].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y].color = Color.WHITE;
                            //update square of current play piece
                            ++currentPlayPiece.occupiedSquares[0].x;
                            --currentPlayPiece.occupiedSquares[0].y; //0
                            --currentPlayPiece.occupiedSquares[2].x;
                            ++currentPlayPiece.occupiedSquares[2].y;//2
                            currentPlayPiece.occupiedSquares[3].x += 2;                                             //3
                            currentPlayPiece.tOrientation = Orientation.ORIG;
                            break;
                        case CW180:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[3].x == 0) //TOP WALL - impossible if initial orientation is fixed
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[1].x - 1][currentPlayPiece.occupiedSquares[1].y].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[1].x - 2][currentPlayPiece.occupiedSquares[1].y].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[2].x - 2][currentPlayPiece.occupiedSquares[2].y].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y].color = Color.WHITE;
                            //update square of current play piece
                            currentPlayPiece.occupiedSquares[0].x -= 2;
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            currentPlayPiece.tOrientation = Orientation.CW90;
                            break;
                        case CW270:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[0].y == 0) //LEFT WALL
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y - 1].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y + 1].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y].color = Color.WHITE;
                            //update square of current play piece
                            ++currentPlayPiece.occupiedSquares[0].y;                                              //0
                            ++currentPlayPiece.occupiedSquares[1].x;                                              //1
                            currentPlayPiece.occupiedSquares[2].x += 2;
                            --currentPlayPiece.occupiedSquares[2].y;//2
                            --currentPlayPiece.occupiedSquares[3].x;                                              //3
                            currentPlayPiece.tOrientation = Orientation.CW180;
                            break;
                    }
                    break;
                case ALT_L:
                    switch (currentPlayPiece.tOrientation) {
                        case ORIG:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[0].x == 0) //TOP WALL
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[2].x - 1][currentPlayPiece.occupiedSquares[2].y].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[1].x - 1][currentPlayPiece.occupiedSquares[1].y].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[1].x + 1][currentPlayPiece.occupiedSquares[1].y].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y].color = Color.WHITE;
                            //update square of current play piece
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            currentPlayPiece.tOrientation = Orientation.CW270;
                            break;
                        case CW90:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[0].y == wnos - 1) //RIGHT WALL
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y - 1].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y + 1].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y + 1].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y].color = Color.WHITE;
                            //update square of current play piece
                            ++currentPlayPiece.occupiedSquares[0].x;
                            --currentPlayPiece.occupiedSquares[0].y;//0
                            --currentPlayPiece.occupiedSquares[2].x;
                            ++currentPlayPiece.occupiedSquares[2].y;//2
                            currentPlayPiece.occupiedSquares[3].y += 2;                                            //3
                            currentPlayPiece.tOrientation = Orientation.ORIG;
                            break;
                        case CW180:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[3].x == 0) //TOP WALL - Impossible if initial orientation fixed
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[1].x - 1][currentPlayPiece.occupiedSquares[1].y].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[1].x - 2][currentPlayPiece.occupiedSquares[1].y].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y].color = Color.WHITE;
                            //update square of current play piece
                            currentPlayPiece.occupiedSquares[0].x -= 2;
                            --currentPlayPiece.occupiedSquares[0].y;//0
                            --currentPlayPiece.occupiedSquares[1].x;                                              //1
                            ++currentPlayPiece.occupiedSquares[2].y;                                              //2
                            ++currentPlayPiece.occupiedSquares[3].x;                                              //3
                            currentPlayPiece.tOrientation = Orientation.CW90;
                            break;
                        case CW270:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[0].x == 0) //LEFT WALL
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y + 1].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y - 1].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y - 1].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y].color = Color.WHITE;
                            //update square of current play piece
                            ++currentPlayPiece.occupiedSquares[0].y;                                              //0
                            ++currentPlayPiece.occupiedSquares[1].x;                                              //1
                            currentPlayPiece.occupiedSquares[2].x += 2;
                            --currentPlayPiece.occupiedSquares[2].y;//2
                            ++currentPlayPiece.occupiedSquares[3].x;
                            currentPlayPiece.occupiedSquares[3].y -= 2;//3
                            currentPlayPiece.tOrientation = Orientation.CW180;
                            break;
                    }
                    break;
                case T:
                    switch (currentPlayPiece.tOrientation) {
                        case ORIG:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[0].x == 0) //TOP WALL
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[1].x - 1][currentPlayPiece.occupiedSquares[1].y].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            //update square of current play piece
                            ++currentPlayPiece.occupiedSquares[0].x;
                            ++currentPlayPiece.occupiedSquares[0].y; //0
                            --currentPlayPiece.occupiedSquares[2].x;
                            --currentPlayPiece.occupiedSquares[2].y; //2
                            --currentPlayPiece.occupiedSquares[3].x;
                            ++currentPlayPiece.occupiedSquares[3].y; //3
                            currentPlayPiece.tOrientation = Orientation.CW270;
                            break;
                        case CW90:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[0].y == wnos - 1) //RIGHT WALL
                            {
                                return;
                            } else;
                            //check if squares are free
                            if (filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y + 1].color != Color.WHITE) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            //update square of current play piece
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            currentPlayPiece.tOrientation = Orientation.ORIG;
                            break;
                        case CW180:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[3].x == 0) //TOP WALL - Impossible if initial orientation fixed
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[3].x - 1][currentPlayPiece.occupiedSquares[3].y].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y - 1].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y].color = Color.WHITE;
                            //update square of current play piece
                            currentPlayPiece.occupiedSquares[0].x -= 2;
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            currentPlayPiece.tOrientation = Orientation.CW90;
                            break;
                        case CW270:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[0].x == 0) //LEFT WALL
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y - 1].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y + 1].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y].color = Color.WHITE;
                            //update square of current play piece
                            ++currentPlayPiece.occupiedSquares[0].y;                                              //0
                            ++currentPlayPiece.occupiedSquares[1].x;                                              //1
                            currentPlayPiece.occupiedSquares[2].x += 2;
                            --currentPlayPiece.occupiedSquares[2].y;//2
                            --currentPlayPiece.occupiedSquares[3].y;                                              //3
                            currentPlayPiece.tOrientation = Orientation.CW180;
                            break;
                    }
                    break;
                case Z:
                    switch (currentPlayPiece.tOrientation) {
                        case ORIG:
                        case CW180:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[0].x == 0) //TOP WALL
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[1].x - 1][currentPlayPiece.occupiedSquares[1].y + 1].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y + 1].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y].color = Color.WHITE;
                            //update square of current play piece
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            currentPlayPiece.tOrientation = Orientation.CW90;
                            break;
                        case CW90:
                        case CW270:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[2].y == 0) //LEFT WALL
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y - 1].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y + 1].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y].color = Color.WHITE;
                            //update square of current play piece
                            ++currentPlayPiece.occupiedSquares[0].x;
                            currentPlayPiece.occupiedSquares[0].y -= 2; //0
                            --currentPlayPiece.occupiedSquares[1].y;                                              //1
                            ++currentPlayPiece.occupiedSquares[2].x;                                              //2
                            ++currentPlayPiece.occupiedSquares[3].y;                                              //3
                            currentPlayPiece.tOrientation = Orientation.ORIG;
                            break;
                    }
                    break;
                case S:
                    switch (currentPlayPiece.tOrientation) {
                        case ORIG:
                        case CW180:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[2].x == 0) //TOP WALL
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[0].x - 1][currentPlayPiece.occupiedSquares[0].y].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[0].x - 2][currentPlayPiece.occupiedSquares[0].y].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y].color = Color.WHITE;
                            //update square of current play piece
                            currentPlayPiece.occupiedSquares[0].x -= 2;                                            //0
                            --currentPlayPiece.occupiedSquares[1].x;
                            --currentPlayPiece.occupiedSquares[1].y;//1
                            ++currentPlayPiece.occupiedSquares[3].x;
                            --currentPlayPiece.occupiedSquares[3].y;//3
                            currentPlayPiece.tOrientation = Orientation.CW90;
                            break;
                        case CW90:
                        case CW270:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[2].y == wnos - 1) //RIGHT WALL
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y + 1].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y - 1].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y].color = Color.WHITE;
                            //update square of current play piece
                            currentPlayPiece.occupiedSquares[0].x += 2;                                             //0
                            ++currentPlayPiece.occupiedSquares[1].x;
                            ++currentPlayPiece.occupiedSquares[1].y;//1
                            --currentPlayPiece.occupiedSquares[3].x;
                            ++currentPlayPiece.occupiedSquares[3].y;//3
                            currentPlayPiece.tOrientation = Orientation.ORIG;
                            break;
                    }
                    break;
                case I:
                    switch (currentPlayPiece.tOrientation) {
                        case ORIG:
                        case CW180:
                            //check if prevented by any walls
                            if ((currentPlayPiece.occupiedSquares[0].x == 0) || (currentPlayPiece.occupiedSquares[0].x >= (hnos - 2))) //TOP AND BOTTOM WALLS
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[2].x - 1][currentPlayPiece.occupiedSquares[2].y].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[2].x + 1][currentPlayPiece.occupiedSquares[2].y].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[2].x + 2][currentPlayPiece.occupiedSquares[2].y].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y].color = Color.WHITE;
                            //update square of current play piece
                            --currentPlayPiece.occupiedSquares[0].x;
                            currentPlayPiece.occupiedSquares[0].y += 2;//0
                            ++currentPlayPiece.occupiedSquares[1].y;                                              //1
                            ++currentPlayPiece.occupiedSquares[2].x;                                              //2
                            currentPlayPiece.occupiedSquares[3].x += 2;
                            --currentPlayPiece.occupiedSquares[3].y;//3
                            currentPlayPiece.tOrientation = Orientation.CW90;
                            break;
                        case CW90:
                        case CW270:
                            //check if prevented by any walls
                            if ((currentPlayPiece.occupiedSquares[0].y == (wnos - 1)) || (currentPlayPiece.occupiedSquares[0].y <= 1)) //RIGHT AND LEFT WALLS
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y + 1].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y - 1].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y - 2].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y].color = Color.WHITE;
                            //update square of current play piece
                            ++currentPlayPiece.occupiedSquares[0].x;
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            currentPlayPiece.tOrientation = Orientation.ORIG;
                            break;
                    }
                    break;
            }
        } else {
            switch (currentPlayPiece.tShape) {
                case SQUARE:
                    break;
                case L:
                    switch (currentPlayPiece.tOrientation) {
                        case ORIG:
                            //check if at prevented by any walls
                            if (currentPlayPiece.occupiedSquares[0].x == 0) //TOP WALL
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[0].x - 1][currentPlayPiece.occupiedSquares[0].y].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[1].x - 1][currentPlayPiece.occupiedSquares[1].y].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[1].x + 1][currentPlayPiece.occupiedSquares[1].y].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y].color = Color.WHITE;
                            //update square of current play piece
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            currentPlayPiece.tOrientation = Orientation.CW90;
                            break;
                        case CW90:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[0].y == wnos - 1) //RIGHT WALL
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y + 1].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y + 1].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y - 1].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y].color = Color.WHITE;
                            //update square of current play piece
                            currentPlayPiece.occupiedSquares[0].x += 2;
                            ++currentPlayPiece.occupiedSquares[0].y; //0
                            ++currentPlayPiece.occupiedSquares[1].x;                                              //1
                            --currentPlayPiece.occupiedSquares[2].y;                                              //2
                            ++currentPlayPiece.occupiedSquares[3].x;
                            currentPlayPiece.occupiedSquares[3].y += 2; //3
                            currentPlayPiece.tOrientation = Orientation.CW180;
                            break;
                        case CW180:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[3].x == 0) //TOP WALL - impossible if initial orientation is fixed
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[1].x - 1][currentPlayPiece.occupiedSquares[1].y].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[1].x - 2][currentPlayPiece.occupiedSquares[1].y].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y].color = Color.WHITE;
                            //update square of current play piece
                            --currentPlayPiece.occupiedSquares[0].y;                                              //0
                            --currentPlayPiece.occupiedSquares[1].x;                                              //1
                            currentPlayPiece.occupiedSquares[2].x -= 2;
                            ++currentPlayPiece.occupiedSquares[2].y;  //2
                            ++currentPlayPiece.occupiedSquares[3].x;                                              //3
                            currentPlayPiece.tOrientation = Orientation.CW270;
                            break;
                        case CW270:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[0].y == 0) //LEFT WALL
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y - 1].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y - 1].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y + 1].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y].color = Color.WHITE;
                            //update square of current play piece
                            --currentPlayPiece.occupiedSquares[0].x;
                            --currentPlayPiece.occupiedSquares[0].y;   //0
                            ++currentPlayPiece.occupiedSquares[2].x;
                            ++currentPlayPiece.occupiedSquares[2].y;   //2
                            currentPlayPiece.occupiedSquares[3].y -= 2;                                             //3
                            currentPlayPiece.tOrientation = Orientation.ORIG;
                            break;
                    }
                    break;
                case ALT_L:
                    switch (currentPlayPiece.tOrientation) {
                        case ORIG:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[0].x == 0) //TOP WALL
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[0].x + 1][currentPlayPiece.occupiedSquares[0].y].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[1].x - 1][currentPlayPiece.occupiedSquares[1].y].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[1].x + 1][currentPlayPiece.occupiedSquares[1].y].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y].color = Color.WHITE;
                            //update square of current play piece
                            --currentPlayPiece.occupiedSquares[0].x;
                            ++currentPlayPiece.occupiedSquares[0].y; //0
                            ++currentPlayPiece.occupiedSquares[2].x;
                            --currentPlayPiece.occupiedSquares[2].y; //2
                            currentPlayPiece.occupiedSquares[3].y -= 2;                                            //3
                            currentPlayPiece.tOrientation = Orientation.CW90;
                            break;
                        case CW90:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[0].y == wnos - 1) //RIGHT WALL
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y - 1].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y + 1].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y].color = Color.WHITE;
                            //update square of current play piece
                            currentPlayPiece.occupiedSquares[0].x += 2;
                            ++currentPlayPiece.occupiedSquares[0].y; //0
                            ++currentPlayPiece.occupiedSquares[1].x;                                              //1
                            --currentPlayPiece.occupiedSquares[2].y;                                              //2
                            --currentPlayPiece.occupiedSquares[3].x;                                              //3
                            currentPlayPiece.tOrientation = Orientation.CW180;
                            break;
                        case CW180:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[3].x == 0) //TOP WALL - Impossible if initial orientation fixed
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[1].x - 1][currentPlayPiece.occupiedSquares[1].y].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[1].x - 2][currentPlayPiece.occupiedSquares[1].y].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[0].x - 2][currentPlayPiece.occupiedSquares[0].y].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y].color = Color.WHITE;
                            //update square of current play piece
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            currentPlayPiece.tOrientation = Orientation.CW270;
                            break;
                        case CW270:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[0].x == 0) //LEFT WALL
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y + 1].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y - 1].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y + 1].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y].color = Color.WHITE;
                            //update square of current play piece
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            currentPlayPiece.tOrientation = Orientation.ORIG;
                            break;
                    }
                    break;
                case T:
                    switch (currentPlayPiece.tOrientation) {
                        case ORIG:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[0].x == 0) //TOP WALL
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[1].x - 1][currentPlayPiece.occupiedSquares[1].y].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y].color = Color.WHITE;
                            //update square of current play piece
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            currentPlayPiece.tOrientation = Orientation.CW90;
                            break;
                        case CW90:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[0].y == wnos - 1) //RIGHT WALL
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y - 1].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y + 1].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y].color = Color.WHITE;
                            //update square of current play piece
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            currentPlayPiece.tOrientation = Orientation.CW180;
                            break;
                        case CW180:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[3].x == 0) //TOP WALL - Impossible if initial orientation fixed
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[3].x - 1][currentPlayPiece.occupiedSquares[3].y].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y + 1].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y].color = Color.WHITE;
                            //update square of current play piece
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            currentPlayPiece.tOrientation = Orientation.CW270;
                            break;
                        case CW270:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[0].x == 0) //LEFT WALL
                            {
                                return;
                            } else;
                            //check if squares are free
                            if (filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y - 1].color != Color.WHITE) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y].color = Color.WHITE;
                            //update square of current play piece
                            --currentPlayPiece.occupiedSquares[0].x;
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            currentPlayPiece.tOrientation = Orientation.ORIG;
                            break;
                    }
                    break;
                case Z:
                    switch (currentPlayPiece.tOrientation) {
                        case ORIG:
                        case CW180:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[0].x == 0) //TOP WALL
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[1].x - 1][currentPlayPiece.occupiedSquares[1].y + 1].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y + 1].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y].color = Color.WHITE;
                            //update square of current play piece
                            --currentPlayPiece.occupiedSquares[0].x;
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            currentPlayPiece.tOrientation = Orientation.CW90;
                            break;
                        case CW90:
                        case CW270:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[2].y == 0) //LEFT WALL
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y - 1].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y + 1].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y].color = Color.WHITE;
                            //update square of current play piece
                            ++currentPlayPiece.occupiedSquares[0].x;
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            currentPlayPiece.tOrientation = Orientation.ORIG;
                            break;
                    }
                    break;
                case S:
                    switch (currentPlayPiece.tOrientation) {
                        case ORIG:
                        case CW180:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[2].x == 0) //TOP WALL
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[0].x - 1][currentPlayPiece.occupiedSquares[0].y].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[0].x - 2][currentPlayPiece.occupiedSquares[0].y].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y].color = Color.WHITE;
                            //update square of current play piece
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            currentPlayPiece.tOrientation = Orientation.CW90;
                            break;
                        case CW90:
                        case CW270:
                            //check if prevented by any walls
                            if (currentPlayPiece.occupiedSquares[2].y == wnos - 1) //RIGHT WALL
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y + 1].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y - 1].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y].color = Color.WHITE;
                            //update square of current play piece
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            currentPlayPiece.tOrientation = Orientation.ORIG;
                            break;
                    }
                    break;
                case I:
                    switch (currentPlayPiece.tOrientation) {
                        case ORIG:
                        case CW180:
                            //check if prevented by any walls
                            if ((currentPlayPiece.occupiedSquares[0].x == 0) || (currentPlayPiece.occupiedSquares[0].x >= (hnos - 2))) //TOP AND BOTTOM WALLS
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[2].x - 1][currentPlayPiece.occupiedSquares[2].y].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[2].x + 1][currentPlayPiece.occupiedSquares[2].y].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[2].x + 2][currentPlayPiece.occupiedSquares[2].y].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y].color = Color.WHITE;
                            //update square of current play piece
                            --currentPlayPiece.occupiedSquares[0].x;
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            currentPlayPiece.tOrientation = Orientation.CW90;
                            break;
                        case CW90:
                        case CW270:
                            //check if prevented by any walls
                            if ((currentPlayPiece.occupiedSquares[0].y == (wnos - 1)) || (currentPlayPiece.occupiedSquares[0].y <= 1)) //RIGHT AND LEFT WALLS
                            {
                                return;
                            } else;
                            //check if squares are free
                            if ((filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y + 1].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y - 1].color != Color.WHITE) || (filledPlayArea[currentPlayPiece.occupiedSquares[1].x][currentPlayPiece.occupiedSquares[1].y - 2].color != Color.WHITE)) {
                                return;
                            } else;
                            //set original unused squares to white
                            filledPlayArea[currentPlayPiece.occupiedSquares[0].x][currentPlayPiece.occupiedSquares[0].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[2].x][currentPlayPiece.occupiedSquares[2].y].color = Color.WHITE;
                            filledPlayArea[currentPlayPiece.occupiedSquares[3].x][currentPlayPiece.occupiedSquares[3].y].color = Color.WHITE;
                            //update square of current play piece
                            ++currentPlayPiece.occupiedSquares[0].x;
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            //obfuscated
                            currentPlayPiece.tOrientation = Orientation.ORIG;
                            break;
                    }
                    break;
            }
        }
    }
}
