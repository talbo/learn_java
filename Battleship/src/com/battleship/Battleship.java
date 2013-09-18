package com.battleship;

import com.battleship.controller.Controller;
import com.battleship.model.Battle;
import com.battleship.view.ConsoleView;

public class Battleship {

    public static void main(String[] args) {

        Battle battle = new Battle();
        ConsoleView view = new ConsoleView(battle);
        Controller controller = new Controller(battle, view);

    }

}
