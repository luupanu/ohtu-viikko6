package laskin.komennot;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.Deque;
import java.util.LinkedList;
import laskin.Sovelluslogiikka;

public class Nollaa implements Komento {

    private Deque<Integer> historia;
    private TextField syotekentta;
    private TextField tuloskentta;
    protected Sovelluslogiikka sovellus;

    public Nollaa(TextField tuloskentta, TextField syotekentta, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.sovellus = sovellus;
        this.historia = new LinkedList<>();
    }

    @Override
    public boolean suorita() {
        muistaEdellinen();

        sovellus.nollaa();

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
}