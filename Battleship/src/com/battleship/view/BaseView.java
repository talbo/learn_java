package com.battleship.view;

import com.battleship.model.Battle;

public abstract class BaseView {

    protected Battle battle;

    public BaseView (Battle battle) {
        this.battle = battle;
    }

    public abstract void printFields();

}
