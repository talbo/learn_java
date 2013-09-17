package com.battleship.controller;

import com.battleship.model.Battle;
import com.battleship.view.BaseView;

public class BaseController {

    private Battle battle;
    private BaseView view;

    public BaseController(Battle battle, BaseView view) {

        this.battle = battle;
        this.view = view;

    }


}
