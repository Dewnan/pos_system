package me.dewnan.pos;

import me.dewnan.pos.db.DatabaseHandler;

public class Main {
    public static void main(String[] args) {
        DatabaseHandler.initialize();
        Navigation nav = new Navigation();
        System.out.println("POS system is started...");
        nav.showMainMenu();
    }
}