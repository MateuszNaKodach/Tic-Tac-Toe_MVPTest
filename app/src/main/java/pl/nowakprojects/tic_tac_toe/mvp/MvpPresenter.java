package pl.nowakprojects.tic_tac_toe.mvp;

/**
 * Created by Mateusz on 25.12.2016.
 */
public interface MvpPresenter<T extends MvpView> {
    void onCreate();

    void onStart();

    void onStop();

    void onPause();

    void attachView(T view);

    void detachView();
}
