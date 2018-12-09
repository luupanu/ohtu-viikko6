package laskin.komennot;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.Deque;
import java.util.LinkedList;
import laskin.Sovelluslogiikka;

public abstract class Laskuoperaatio implements Komento {

    private Deque<Integer> historia;
    private TextField syotekentta;
    private TextField tuloskentta;
    protected Sovelluslogiikka sovellus;

    public Laskuoperaatio(TextField tuloskentta, TextField syotekentta, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.sovellus = sovellus;
        this.historia = new LinkedList<>();
    }

    @Override
    public boolean suorita() {
        int arvo = 0;

        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
            return false;
        }

        muistaEdellinen();
        laske(arvo);

        return true;
    }

    @Override
    public void peru() {
        sovellus.nollaa();
        sovellus.plus(historia.pop());
    }

    @Override
    public void muistaEdellinen() {
        try {
            historia.push(Integer.parseInt(tuloskentta.getText()));
        } catch (Exception e) {
        }
    }

    protected abstract void laske(int luku);
}