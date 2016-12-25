package pl.nowakprojects.tic_tac_toe.models;

import java.util.ArrayList;
import java.util.Arrays;

import pl.nowakprojects.tic_tac_toe.util.Const;

/**
 * Created by Mateusz on 24.12.2016.
 */

public class GameBoard {

    public enum MARK {
        EMPTY("Nobody"),
        CIRCLE("Circle"),
        CROSS("Cross");

        private String name;

        MARK(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private final static int winningPositions[][] = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
    };

    private MARK activeMark;
    private MARK[] fieldsStats;
    private boolean activeGame;

    public GameBoard() {
        setActiveMark(MARK.CIRCLE);
        fieldsStats = new MARK[Const.GAME_BOARD_SIZE];
        startNewGame();
    }

    public void startNewGame(){
        initFieldStats();
        activeGame = true;
    }

    private void initFieldStats(){
        Arrays.fill(fieldsStats,MARK.EMPTY);
    }

    public boolean isGameFinished(){
        return isGameBoardFull() || checkIfActiveMarkHasWon();
    }

    private boolean checkIfActiveMarkHasWon(){
        return getActiveMark() == getWinnerMark();
    }

    public void placeMarkOnField(final int fieldId){
            fieldsStats[fieldId] = getActiveMark();
    }

    public MARK getActiveMark() {
        return activeMark;
    }

    public MARK getWinnerMark(){
        for(int[] winPos: winningPositions){
            if(checkIfActiveMarkHasWinningPosition(winPos,getActiveMarkFieldsIds()))
                return getActiveMark();
        }

        return MARK.EMPTY;
    }

    public boolean isFieldEmpty(final int fieldId){
        return fieldsStats[fieldId] == MARK.EMPTY;
    }

    private void setActiveMark(MARK activeMark) {
        this.activeMark = activeMark;
    }


    private boolean checkIfActiveMarkHasWinningPosition(int[] winningPositionFields, int[] activeMarksFields){
        int sameFields = 0;
        for(int i: winningPositionFields){
            for(int j: activeMarksFields)
                if(i==j)
                    sameFields++;
        }

        return sameFields==3;
    }

    private int[] getActiveMarkFieldsIds(){
        ArrayList<Integer> playerMarksIds = new ArrayList<>();
        for(int i=0;i<fieldsStats.length;i++)
            if(fieldsStats[i]== getActiveMark())
                playerMarksIds.add(i);

        int[] resultArray = new int[playerMarksIds.size()];

        for(int i=0;i<resultArray.length;i++)
            resultArray[i] = playerMarksIds.get(i);

        return resultArray;
    }

    public void changeActiveMark() {
        if(this.activeMark == MARK.CIRCLE)
            this.activeMark = MARK.CROSS;
        else
            this.activeMark = MARK.CIRCLE;
    }

    private boolean isGameBoardFull(){
        int freeFields = 0;
        for(MARK field: fieldsStats)
            if(field== MARK.EMPTY)
                freeFields++;

        return freeFields==0;
    }

    public void endGame(){
        activeGame=false;
    }

    public boolean isActiveGame() {
        return activeGame;
    }


}
