package pl.nowakprojects.tic_tac_toe.ui.game;

import pl.nowakprojects.tic_tac_toe.models.GameBoard;
import pl.nowakprojects.tic_tac_toe.mvp.MvpPresenter;

/**
 * Created by Mateusz on 24.12.2016.
 */

public class GameBoardPresenter implements MvpPresenter<GameBoardView>{

    GameBoardView gameBoardView;
    GameBoard gameBoard;

    public GameBoardPresenter(GameBoardView gameBoardView) {
        attachView(gameBoardView);
        initGameBoard(new GameBoard());
    }

    private void initGameBoard(GameBoard gameBoard){
        this.gameBoard = gameBoard;
    }

    public GameBoard.MARK getActivePlayerMark(){
        return gameBoard.getActiveMark();
    }

    public boolean isFieldEmpty(int fieldId) {
        return gameBoard.isFieldEmpty(fieldId);
    }

    public void placePlayerMarkOnField(int fieldId) {
        gameBoard.placeMarkOnField(fieldId);
    }

    public void changeActivePlayer() {
        gameBoard.changeActiveMark();
    }

    public boolean checkIfActivePlayerHasWon() {
        return gameBoard.checkIfActiveMarkHasWon();
    }

    public GameBoard.MARK getWinnerMark(){
        return gameBoard.getWinnerMark();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(GameBoardView view) {
        gameBoardView = view;
    }


    @Override
    public void detachView() {
        gameBoardView = null;
    }
}
