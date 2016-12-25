package pl.nowakprojects.tic_tac_toe.ui.game;

import pl.nowakprojects.tic_tac_toe.models.GameBoard;
import pl.nowakprojects.tic_tac_toe.mvp.MvpPresenter;

/**
 * Created by Mateusz on 24.12.2016.
 */

final class GameBoardPresenter implements MvpPresenter<GameBoardView>{

    private GameBoardView gameBoardView;
    private GameBoard gameBoard;

    GameBoardPresenter(GameBoardView gameBoardView) {
        attachView(gameBoardView);
        initGameBoard(new GameBoard());
    }

    private void initGameBoard(GameBoard gameBoard){
        this.gameBoard = gameBoard;
    }

    GameBoard.MARK getActivePlayerMark(){
        return gameBoard.getActiveMark();
    }

    private boolean isFieldEmpty(int fieldId) {
        return gameBoard.isFieldEmpty(fieldId);
    }

    void placeActiveMarkOnField(int fieldId) {
        gameBoard.placeMarkOnField(fieldId);

    }

    private void changeActivePlayer() {
        gameBoard.changeActiveMark();
    }

    private boolean isGameFinished(){
        return gameBoard.isGameFinished();
    }

    void finishGame(){
        if (isGameFinished()) {

            gameBoardView.showPlayAgainMessage();
            endGame();
        }

        changeActivePlayer();
    }

    private void endGame(){
        gameBoard.endGame();
    }

    private boolean isActiveGame(){
        return gameBoard.isActiveGame();
    }

    void startNewGame(){
        gameBoardView.hidePlayAgainMessage();
        gameBoard.startNewGame();
        gameBoardView.clearGameBoardViewLayout();
    }

    GameBoard.MARK getWinnerMark(){
        return gameBoard.getWinnerMark();
    }

    boolean isAllowToPlaceMarkOnField(int fieldId){
        return isFieldEmpty(fieldId) && isActiveGame();
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
