package pl.nowakprojects.tic_tac_toe.ui.game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import pl.nowakprojects.tic_tac_toe.R;
import pl.nowakprojects.tic_tac_toe.models.GameBoard;

public class GameBoardActivity extends AppCompatActivity implements GameBoardView {

    private GameBoardPresenter gameBoardPresenter;

    LinearLayout playAgainLayout;
    GridLayout gameBoardLayout;
    ImageView activeFieldImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initPresenter();
        initUserInterface();
    }

    @Override
    public void initPresenter() {
        gameBoardPresenter = new GameBoardPresenter(this);
    }

    public void initUserInterface(){
        playAgainLayout = (LinearLayout) findViewById(R.id.play_again_layout);
        gameBoardLayout = (GridLayout) findViewById(R.id.game_board_layout);
    }

    public void placeActiveMarkOnFieldView(View view) {
        activeFieldImageView = (ImageView) view;
        int fieldId = Integer.parseInt(activeFieldImageView.getTag().toString());

        placeActiveMarkIfAllowed(fieldId);

    }

    private void placeActiveMarkIfAllowed(int fieldId) {
        if (gameBoardPresenter.isAllowToPlaceMarkOnField(fieldId))
            placeActiveMarkOnFiledWithAnimation(fieldId);
    }

    private void placeActiveMarkOnFiledWithAnimation(int fieldId){
        showMarkDropInAnimation(activeFieldImageView);
        gameBoardPresenter.placeActiveMarkOnField(fieldId);
        gameBoardPresenter.finishGame();
    }

    private void showMarkDropInAnimation(ImageView mark) {
        mark.setTranslationY(-1000f);
        mark.setImageResource(getMarkResourceId(gameBoardPresenter.getActivePlayerMark()));
        mark.animate().translationYBy(1000f).rotation(360).setDuration(1000);
    }

    public void playAgain(View view) {
        gameBoardPresenter.startNewGame();
    }

    @Override
    public void showPlayAgainMessage() {
        TextView winnerMessage = (TextView) findViewById(R.id.winner_message_textView);
        winnerMessage.setText(getString(R.string.winner_message, gameBoardPresenter.getWinnerMark().toString()));
        playAgainLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePlayAgainMessage() {
        playAgainLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getMarkResourceId(GameBoard.MARK activeMark) {
        return (activeMark == GameBoard.MARK.CIRCLE) ?
                R.drawable.circle
                :
                R.drawable.cross;
    }

    @Override
    public void clearGameBoardViewLayout() {
        for (int i = 0; i < gameBoardLayout.getChildCount(); i++)
            ((ImageView) gameBoardLayout.getChildAt(i)).setImageResource(0);

        gameBoardLayout.setEnabled(true);
    }
}
