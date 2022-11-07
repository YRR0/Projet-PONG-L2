package com.next.pong.pages.home;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.util.Collection;

public class SubMenu extends VBox {
    public SubMenu(javafx.scene.control.MenuItem...items){
        setSpacing(15);
        setTranslateY(100);
        setTranslateX(50);
        for(javafx.scene.control.MenuItem item : items){
            getChildren().addAll((Collection<? extends Node>) item);
        }
    }
}