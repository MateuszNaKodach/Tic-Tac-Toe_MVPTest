package pl.nowakprojects.tic_tac_toe.ui.game;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import pl.nowakprojects.tic_tac_toe.R;
import pl.nowakprojects.tic_tac_toe.models.GameBoard;

public class GameBoardActivity extends AppCompatActivity implements GameBoardView {

    private GameBoardPresenter gameBoardPresenter;

    public void dropIn(View view){
        ImageView field = (ImageView) view;

        int fieldId = Integer.parseInt(field.getTag().toString());

        if(gameBoardPresenter.isFieldEmpty(fieldId)){
            showMarkDropInAnimation(field);
            gameBoardPresenter.placePlayerMarkOnField(fieldId);
                if(gameBoardPresenter.checkIfActivePlayerHasWon()){
                    Toast.makeText(this,"The player has won!", Toast.LENGTH_LONG).show();
                }
            gameBoardPresenter.changeActivePlayer();
        }

        //wideo na 37.00

    }

    private void showMarkDropInAnimation(ImageView mark){
        mark.setTranslationY(-1000f);
        mark.setImageResource(getActivePlayerMarkResourceID(gameBoardPresenter.getActivePlayerMark()));
        mark.animate().translationYBy(1000f).rotation(360).setDuration(1000);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gameBoardPresenter = new GameBoardPresenter(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getActivePlayerMarkResourceID(GameBoard.MARK activePlayerMark) {
        return (activePlayerMark == GameBoard.MARK.CIRCLE) ?
                R.drawable.circle
                :
                R.drawable.cross;
    }
}
