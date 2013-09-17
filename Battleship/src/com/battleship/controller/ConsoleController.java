package com.battleship.controller;

import com.battleship.model.Battle;
import com.battleship.view.BaseView;

public class ConsoleController extends BaseController {

    public ConsoleController(Battle battle, BaseView view) {
        super(battle, view);

        view.printFields();

    }

}
