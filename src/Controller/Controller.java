package Controller;

import Model.*;
import View.*;
import java.awt.Color;
import javax.swing.JButton;

public class Controller {

    private static Model model;
    private static View view;
    private static JButton jbutton;
    private static boolean win;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    public static void setSpotAsValue(JButton jbutton, int FieldPositionY, int FieldPositionX, int SpotPositionY, int SpotPositionX) {

        //Se ubica el simbolo en el boton y su modelo correspondiente
        Field selectedField = model.getFiel(FieldPositionY, FieldPositionX);
        int selectedSpot = selectedField.getSpot(SpotPositionY, SpotPositionX);

        //Si el turno es el primero, se pone donde sea
        if (Model.getTurnAsInt() == 0) {
            System.out.println("Primer turno, se ubica en donde sea.");
            if (selectedSpot == 0) {
                Controller.jbutton = jbutton;
                selectedField.setSpot(SpotPositionY, SpotPositionX, model.getTurnAsInt());
                Controller.jbutton.setText(model.getTurnAsString());
                Controller.jbutton.setBackground(Color.RED);

                model.setNextPosition(FieldPositionY, FieldPositionX, SpotPositionY, SpotPositionX);
                model.setNextTurn();
            } else {
                //si el spot ya esta ocupado no cambiara el turno ni el spot y muestra el mensaje
                System.out.println("Spot already occupied");
            }
        } else if (Model.getTurnAsInt() == 1 || Model.getTurnAsInt() == 2) {
            int[] previusPosition = model.getPreviusPosition();
            selectedField = model.getFiel(previusPosition[2], previusPosition[3]);

            //Si la posicion anterior no coincide con el nuevo Field, rechazar.
            if (previusPosition[2] == FieldPositionY && previusPosition[3] == FieldPositionX) {
                Controller.jbutton = jbutton;
                selectedField.setSpot(SpotPositionY, SpotPositionX, model.getTurnAsInt());
                Controller.jbutton.setText(model.getTurnAsString());
                Controller.jbutton.setBackground(Color.RED);
            } else {
                System.out.println("Invalid Spot, select the indicated space.");
            }

        } else if (Model.getTurnAsInt() == 3 || Model.getTurnAsInt() == 4) {
            int[] previusPosition = model.getPreviusPosition();
            selectedField = model.getFiel(previusPosition[2], previusPosition[3]);

            //Si la posicion anterior no coincide con el nuevo Field, rechazar.
            if (previusPosition[2] == FieldPositionY && previusPosition[3] == FieldPositionX) {
                Controller.jbutton = jbutton;
                selectedField.setSpot(SpotPositionY, SpotPositionX, model.getTurnAsInt());
                Controller.jbutton.setText(model.getTurnAsString());
                Controller.jbutton.setBackground(Color.BLUE);
            } else {
                System.out.println("Invalid Spot, select the indicated space.");
            }
        }

    }

    public void startGame() {
        System.out.println("Game started.");
        view.setVisible(true);
    }

    public void setWin() {
        Controller.win = true;
    }

    public void setSpot() {
        //view.set(o,o);
        model.getFiel(0, 0);
    }

    /*Comprobar tres en raya*/
    //realizar cada cambio de turno
    //Comprobar en el campo seleccionado
    //Comprobar en todo el campo
    //Indicar victoria
}
