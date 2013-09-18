package com.battleship.view;

import com.battleship.model.Battle;

public abstract class BaseView {

    protected Battle battle;

    public BaseView (Battle battle) {
        this.battle = battle;
    }

    public abstract void printFields();
    public abstract void gameHello();
    public abstract void printLine(String str);
    public abstract int askGameMode();
    public abstract int askNetworkGameMode();
    public abstract String askSreverAddress();

}
