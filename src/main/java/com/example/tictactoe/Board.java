package com.example.tictactoe;

import java.util.*;

public class Board {
    //store elements of grid
    //boolean value if game ended or not

    private static final Random Rand = new Random();
    private char currPlayer;
    private char[] dim;
    private boolean ended;

    public Board(){
        dim = new char[9];
        newGame();
    }

    public void newGame(){
        //initialize dim of board as blank
        for(int i = 0; i < dim.length; ++i){
            dim[i] = ' ';
        }

        //first player is always X
        currPlayer = 'X';
        ended = false;

    }

    public boolean isEnded(){
        return ended;

    }

    public char play(int x, int y){
        //set currentPlayer position x,y

        if((ended == false)&&(dim[3 * y + x] == ' ')){
            dim[3 * y + x] = currPlayer;
            changePlayer();
        }

        return checkEnd();
    }

    public void changePlayer(){
        //change player for next round
        if(currPlayer == 'X'){
            currPlayer = 'O';
        }
        else{
            currPlayer = 'X';
        }
    }

    public char getPos(int x, int y){
        //char of pos (X or O)
        return dim[3 * y + x];

    }
    public char computer(){
        //let computer randomly choose pos of move
        int pos;
        if(ended == false){
            pos = -1;

            do{
                //randomize 0 to 8
                //continue to choose move until board pos is available
                pos = Rand.nextInt(9);
            }while(dim[pos] != ' ');

            dim[pos] = currPlayer; //mark pos with X or O
            changePlayer();
        }

        return checkEnd();
    }

    public char checkEnd(){
        //check if game ended
        //game end if winner or draw(all grid pos filled)

        for(int i = 0; i < 3; ++i){
            //win vertically col 1
            if((getPos(i,0) != ' ')&&(getPos(i,0) == getPos(i,1))&&(getPos(i,1) == getPos(i,2))){
                //end game (winner)
                ended = true;
                //return winning char (X or O)
                return getPos(i,0);

            }
            // win horizontally row 1
            if((getPos(0,i) != ' ')&&(getPos(0,i) == getPos(1,i))&&(getPos(1,i) == getPos(2,i))){
                ended = true;
                return getPos(0,i);
            }

        }
        //win diagonally \
        if((getPos(0,0) != ' ')&&(getPos(0,0) == getPos(1,1))&&(getPos(1,1) == getPos(2,2))){
            ended = true;
            return getPos(0,0);

        }
        //win diagonally /
        if((getPos(2,0) != ' ')&&(getPos(2,0) == getPos(1,1))&&(getPos(1,1) == getPos(0,2))){
            ended = true;
            return getPos(2,0);
        }

        //empty board or unfinished game
        for(int i = 0; i < 9 ; ++i){
            if(dim[i] == ' '){
                return ' ';
            }
        }
        //its a draw
        return 'T';
    }

}
