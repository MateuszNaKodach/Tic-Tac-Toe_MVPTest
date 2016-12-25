package pl.nowakprojects.tic_tac_toe.models;

import java.util.ArrayList;
import java.util.Arrays;

import pl.nowakprojects.tic_tac_toe.util.Const;

/**
 * Created by Mateusz on 24.12.2016.
 */

public class GameBoard {

    public enum MARK {
        EMPTY,
        CIRCLE,
        CROSS;
    }

    private final static int winningPositions[][] = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
    };

    private MARK activeMark;

    private MARK[] fieldsStats;

    public GameBoard() {
        setActiveMark(MARK.CIRCLE);
        fieldsStats = new MARK[Const.GAME_BOARD_SIZE];

        initFieldStats();
    }

    private void initFieldStats(){
        for(int i=0; i< fieldsStats.length; i++)
            fieldsStats[i] = MARK.EMPTY;
    }

    public void placeMarkOnField(final int fieldId){
            fieldsStats[fieldId] = getActiveMark();
    }

    public boolean isFieldEmpty(final int fieldId){
        return fieldsStats[fieldId] == MARK.EMPTY;
    }


    public void setActiveMark(MARK activeMark) {
        this.activeMark = activeMark;
    }

    public boolean checkIfActiveMarkHasWon(){
        return getActiveMark() == getWinnerMark();
    }

    public MARK getWinnerMark(){
        for(int[] winPos: winningPositions){
            if(Arrays.equals(winPos, getActiveMarkFieldsIds()))
                return getActiveMark();
        }

        return MARK.EMPTY;
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

    public MARK getActiveMark() {
        return activeMark;
    }

    public void changeActiveMark() {
        if(this.activeMark == MARK.CIRCLE)
            this.activeMark = MARK.CROSS;
        else
            this.activeMark = MARK.CIRCLE;
    }

    boolean isGameBoardFull(){
        int freeFields = 0;
        for(MARK field: fieldsStats)
            if(field== MARK.EMPTY)
                freeFields++;

        return freeFields==0;
    }
}
