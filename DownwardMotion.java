package io.github.sandeepsukumaran.javatetrisgame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;

/**
 * Class that is used to handle all things related to moving the tetrominoe down with time.
 * Contains the logic to move tetrominoe down by one, and methods to handle completion of rows.
 */
class DownwardMotion extends TimerTask{

    /**
     * Logic used to move the current tetrominoe down at every invocation of this method by a timer.
     */
    @Override
    public void run(){

        if(!MainGameFrame.gamePlayPaused){//TRY TO MOVE TETROMINOE ONE BLOCK DOWN
            collisionFlag = false;
            //IF NO COLLISION UPDATE POSITION
            //IF COLLISION, SET CURRENT TETROMINOE SQUARES AS PERMANENTLY FILLED IN GRID
            //CURRENT TETROMINOE <- NEXT
            //NEXT TETROMINOE <- RAND
            switch(cpp.tShape){
                case SQUARE:
                    collisionFlag = ((cpp.occupiedSquares[2].x==MainGameFrame.hnos-1)||(fpA[cpp.occupiedSquares[2].x+1][cpp.occupiedSquares[2].y].color!=Color.WHITE)||(fpA[cpp.occupiedSquares[3].x+1][cpp.occupiedSquares[3].y].color!=Color.WHITE));
                    if(!collisionFlag){
                        fpA[cpp.occupiedSquares[0].x][cpp.occupiedSquares[0].y].color=Color.WHITE;
                        fpA[cpp.occupiedSquares[1].x][cpp.occupiedSquares[1].y].color=Color.WHITE;
                        ++cpp.occupiedSquares[0].x; ++cpp.occupiedSquares[1].x;
                        ++cpp.occupiedSquares[2].x; ++cpp.occupiedSquares[3].x;
                    }else;
                    break;
                case L:
                    //obfuscated
                    break;
                case ALT_L:
                    //obfuscated
                    break;
                case T:
                    switch(cpp.tOrientation){
                        case ORIG:
                            collisionFlag = ((cpp.occupiedSquares[3].x==MainGameFrame.hnos-1)||(fpA[cpp.occupiedSquares[0].x+1][cpp.occupiedSquares[0].y].color!=Color.WHITE)||(fpA[cpp.occupiedSquares[2].x+1][cpp.occupiedSquares[2].y].color!=Color.WHITE)||(fpA[cpp.occupiedSquares[3].x+1][cpp.occupiedSquares[3].y].color!=Color.WHITE));
                            if(!collisionFlag){
                                fpA[cpp.occupiedSquares[0].x][cpp.occupiedSquares[0].y].color=Color.WHITE;
                                fpA[cpp.occupiedSquares[1].x][cpp.occupiedSquares[1].y].color=Color.WHITE;
                                fpA[cpp.occupiedSquares[2].x][cpp.occupiedSquares[2].y].color=Color.WHITE;
                                ++cpp.occupiedSquares[0].x; ++cpp.occupiedSquares[1].x;
                                ++cpp.occupiedSquares[2].x; ++cpp.occupiedSquares[3].x;
                            }else;
                            break;
                        case CW90:
                            collisionFlag = ((cpp.occupiedSquares[2].x==MainGameFrame.hnos-1)||(fpA[cpp.occupiedSquares[2].x+1][cpp.occupiedSquares[2].y].color!=Color.WHITE)||(fpA[cpp.occupiedSquares[3].x+1][cpp.occupiedSquares[3].y].color!=Color.WHITE));
                            if(!collisionFlag){
                                fpA[cpp.occupiedSquares[0].x][cpp.occupiedSquares[0].y].color=Color.WHITE;
                                fpA[cpp.occupiedSquares[3].x][cpp.occupiedSquares[3].y].color=Color.WHITE;
                                ++cpp.occupiedSquares[0].x; ++cpp.occupiedSquares[1].x;
                                ++cpp.occupiedSquares[2].x; ++cpp.occupiedSquares[3].x;
                            }else;
                            break;
                        case CW180:
                            collisionFlag = ((cpp.occupiedSquares[0].x==MainGameFrame.hnos-1)||(fpA[cpp.occupiedSquares[0].x+1][cpp.occupiedSquares[0].y].color!=Color.WHITE)||(fpA[cpp.occupiedSquares[1].x+1][cpp.occupiedSquares[1].y].color!=Color.WHITE)||(fpA[cpp.occupiedSquares[2].x+1][cpp.occupiedSquares[2].y].color!=Color.WHITE));
                            if(!collisionFlag){
                                fpA[cpp.occupiedSquares[0].x][cpp.occupiedSquares[0].y].color=Color.WHITE;
                                fpA[cpp.occupiedSquares[2].x][cpp.occupiedSquares[2].y].color=Color.WHITE;
                                fpA[cpp.occupiedSquares[3].x][cpp.occupiedSquares[3].y].color=Color.WHITE;
                                ++cpp.occupiedSquares[0].x; ++cpp.occupiedSquares[1].x;
                                ++cpp.occupiedSquares[2].x; ++cpp.occupiedSquares[3].x;
                            }else;
                            break;
                        case CW270:
                            collisionFlag = ((cpp.occupiedSquares[0].x==MainGameFrame.hnos-1)||(fpA[cpp.occupiedSquares[0].x+1][cpp.occupiedSquares[0].y].color!=Color.WHITE)||(fpA[cpp.occupiedSquares[3].x+1][cpp.occupiedSquares[3].y].color!=Color.WHITE));
                            if(!collisionFlag){
                                fpA[cpp.occupiedSquares[2].x][cpp.occupiedSquares[2].y].color=Color.WHITE;
                                fpA[cpp.occupiedSquares[3].x][cpp.occupiedSquares[3].y].color=Color.WHITE;
                                ++cpp.occupiedSquares[0].x; ++cpp.occupiedSquares[1].x;
                                ++cpp.occupiedSquares[2].x; ++cpp.occupiedSquares[3].x;
                            }else;
                            break;
                    }
                    break;
                case Z:
                    switch(cpp.tOrientation){
                        case ORIG:
                        case CW180:
                            collisionFlag = ((cpp.occupiedSquares[3].x==MainGameFrame.hnos-1)||(fpA[cpp.occupiedSquares[0].x+1][cpp.occupiedSquares[0].y].color!=Color.WHITE)||(fpA[cpp.occupiedSquares[2].x+1][cpp.occupiedSquares[2].y].color!=Color.WHITE)||(fpA[cpp.occupiedSquares[3].x+1][cpp.occupiedSquares[3].y].color!=Color.WHITE));
                            if(!collisionFlag){
                                fpA[cpp.occupiedSquares[0].x][cpp.occupiedSquares[0].y].color=Color.WHITE;
                                fpA[cpp.occupiedSquares[1].x][cpp.occupiedSquares[1].y].color=Color.WHITE;
                                fpA[cpp.occupiedSquares[3].x][cpp.occupiedSquares[3].y].color=Color.WHITE;
                                ++cpp.occupiedSquares[0].x; ++cpp.occupiedSquares[1].x;
                                ++cpp.occupiedSquares[2].x; ++cpp.occupiedSquares[3].x;
                            }else;
                            break;
                        case CW90:
                        case CW270:
                            collisionFlag = ((cpp.occupiedSquares[3].x==MainGameFrame.hnos-1)||(fpA[cpp.occupiedSquares[1].x+1][cpp.occupiedSquares[1].y].color!=Color.WHITE)||(fpA[cpp.occupiedSquares[3].x+1][cpp.occupiedSquares[3].y].color!=Color.WHITE));
                            if(!collisionFlag){
                                fpA[cpp.occupiedSquares[0].x][cpp.occupiedSquares[0].y].color=Color.WHITE;
                                fpA[cpp.occupiedSquares[2].x][cpp.occupiedSquares[2].y].color=Color.WHITE;
                                ++cpp.occupiedSquares[0].x; ++cpp.occupiedSquares[1].x;
                                ++cpp.occupiedSquares[2].x; ++cpp.occupiedSquares[3].x;
                            }else;
                            break;
                    }
                    break;
                case S:
                    //obfuscated
                    break;
                case I:
                    //obfuscated
                    break;
            }
            
            //In case of collision, update
            if(collisionFlag){
                fillInColor = Tetrominoe.COLORMAP.get(cpp.tShape);
                fpA[cpp.occupiedSquares[0].x][cpp.occupiedSquares[0].y].color=fillInColor;
                fpA[cpp.occupiedSquares[1].x][cpp.occupiedSquares[1].y].color=fillInColor; 
                fpA[cpp.occupiedSquares[2].x][cpp.occupiedSquares[2].y].color=fillInColor; 
                fpA[cpp.occupiedSquares[3].x][cpp.occupiedSquares[3].y].color=fillInColor; 
                
                MainGameFrame.currentPlayPiece = MainGameFrame.nextBlock;
                MainGameFrame.nextBlock = new Tetrominoe(MainGameFrame.SHAPES.get(rand.nextInt(7)) , Orientation.ORIG);
                cpp = MainGameFrame.currentPlayPiece;
                nb = MainGameFrame.nextBlock;
                
                checkRowsCompleted();
                
                //check if new piece can be displayed, otherwise it's game over
                if((fpA[cpp.occupiedSquares[0].x][cpp.occupiedSquares[0].y].color!=Color.WHITE)||(fpA[cpp.occupiedSquares[1].x][cpp.occupiedSquares[1].y].color!=Color.WHITE)||(fpA[cpp.occupiedSquares[2].x][cpp.occupiedSquares[2].y].color!=Color.WHITE)||(fpA[cpp.occupiedSquares[3].x][cpp.occupiedSquares[3].y].color!=Color.WHITE)){
                    listeners.stream().forEach((hl) -> {
                        hl.setGameOver();
                    });
                }else;
            }else;
            listeners.stream().forEach((hl) -> {
                hl.repaintMustBeCalled();
            });
        }
    }
    
    /**
     * Check if any row has been completely filled by squares and clear any such rows.
     */
    private void checkRowsCompleted(){

        MainGameFrame.gamePlayPaused =  true;
        boolean fallingSpeedUpdated = false;
        for(int i= MainGameFrame.hnos-1; i>=0; --i){
            int j=0;
            while(j<MainGameFrame.wnos){
                //obfuscated
            }
           
            if(j==MainGameFrame.wnos){ //no WHITE in this row
                clearRow(i);     //Clear this row
                ++i;             //In next iteration, check this same row again
                ++MainGameFrame.Lines;
                
                //update the level and speed if enough rows have been cleared
                if(MainGameFrame.Lines >= MainGameFrame.N){
                    MainGameFrame.Lines -= MainGameFrame.N;
                    ++MainGameFrame.Level;
                    //obfuscated
                    fallingSpeedUpdated = true;
                }else;
                
                MainGameFrame.Score += MainGameFrame.Level * MainGameFrame.M; //update score                
            }else{}
        }
        
        if(fallingSpeedUpdated){
           listeners.stream().forEach((hl) -> {
                hl.updateTimerInterval();
            }); 
        }else;
        
        MainGameFrame.gamePlayPaused = false;
    }
    
    /**
     * Removes every square in a row and moves all rows above it down a row.
     * @param i Index of row in filledPlayArea object in mainGame class to be cleared.
     */
    private void clearRow(int i){
        
        if(i==0){ //clear out the topmost row
            for(int j=0;j<MainGameFrame.wnos;++j)
                fpA[0][j].color = Color.WHITE;
        }else{}
        boolean notAllWhite = true;
        int m = i-1;
        for(;notAllWhite && (m>=0); --m){                       //STOP IF AN ALL WHITE ROW HAS BEEN COPIED TO LOWER ROW
            notAllWhite = false;
            for(int n=0; n<MainGameFrame.wnos; ++n){
                //obfuscated
                if(!notAllWhite)                                        //IF ONLY WHITES HAVE BEEN SEEN SO FAR IN THIS ROW
                    notAllWhite |= (fpA[m][n].color != Color.WHITE);    //CHECK IF CURRENT SQUARE IS COLOURED
                else;
            }
        }
        if(m==-1 && notAllWhite) //Top row is non-empty and has been copied to lower row. Now clear it
            for(int n=0;n<MainGameFrame.wnos; ++n)
                fpA[0][n].color = Color.WHITE;
        else;
    }
    
    DownwardMotion(){
        init();
    }
    private void init(){
        cpp = MainGameFrame.currentPlayPiece;
        nb = MainGameFrame.nextBlock;
        //obfuscated
        rand = new Random();
        collisionFlag = false;
    }
    
    void updateCPP(){
        cpp = MainGameFrame.currentPlayPiece;
    }
    
    /*
    *
    * obfuscated
    */

    Tetrominoe cpp;
    Tetrominoe nb;
    GridInfo[][] fpA;
    Random rand;
    Boolean collisionFlag;
    Color fillInColor;
}
