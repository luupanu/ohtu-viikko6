package laskin;

import laskin.komennot.Erotus;
import laskin.komennot.Komento;
import laskin.komennot.Nollaa;
import laskin.komennot.Summa;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Tapahtumankuuntelija implements EventHandler {

    private Button nollaa;
    private Button undo;
    private TextField tuloskentta;
    private TextField syotekentta;

    private Deque<Komento> historia;
    private Map<Button, Komento> komennot;
    private Sovelluslogiikka sovellus;

    public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        
        this.sovellus = new Sovelluslogiikka();
        this.historia = new LinkedList<>();
        this.komennot = new HashMap<>();

        komennot.put(plus, new Summa(tuloskentta, syotekentta, sovellus));
        komennot.put(miinus, new Erotus(tuloskentta, syotekentta, sovellus));
        komennot.put(nollaa, new Nollaa(tuloskentta, syotekentta, sovellus));
    }
    
    @Override
    public void handle(Event event) {
        if (event.getTarget() != undo) {
            Komento komento = komennot.get((Button) event.getTarget());
            if (komento.suorita()) {
                historia.push(komento);
            }
        } else {
            Komento edellinen = historia.pop();
            if (edellinen != null) {
                edellinen.peru();
            }
        }
        paivitaGrafiikka();
    }

    private void paivitaGrafiikka() {
        paivitaSyoteKentta();
        paivitaTulosKentta();
        paivitaNollausNappi();
        paivitaUndoNappi();
    }

    private void paivitaSyoteKentta() {
        syotekentta.setText("");
    }

    private void paivitaTulosKentta() {
        tuloskentta.setText("" + sovellus.tulos());
    }

    private void paivitaNollausNappi() {
        if (sovellus.tulos() == 0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
    }

    private void paivitaUndoNappi() {
        if (historia.peek() == null) {
            undo.disableProperty().set(true);
        } else {
            undo.disableProperty().set(false);
        }
    }
}
