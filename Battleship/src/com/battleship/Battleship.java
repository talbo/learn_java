package com.battleship;

import com.battleship.model.Battle;
import com.battleship.view.ConsoleView;
import com.battleship.controller.ConsoleController;

public class Battleship {

    public static void main(String[] args) {

        Battle battle = new Battle();
        ConsoleView view = new ConsoleView(battle);
        ConsoleController controller = new ConsoleController(battle, view);

    }

}
