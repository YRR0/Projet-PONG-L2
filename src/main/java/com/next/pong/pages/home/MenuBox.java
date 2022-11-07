package com.next.pong.pages.home;

import com.next.pong.pages.home.SubMenu;
import com.sun.javafx.geom.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MenuBox extends Pane {
    static SubMenu subMenu;
    public MenuBox(SubMenu subMenu){
        MenuBox.subMenu = subMenu;

        setVisible(false);
        Rectangle bg = new Rectangle(900,600);
        //bg.setOpacity(0.4);
        getChildren().addAll(bg, subMenu);
    }
    public void setSubMenu(SubMenu subMenu){
        getChildren().remove(MenuBox.subMenu);
        MenuBox.subMenu = subMenu;
        getChildren().add(MenuBox.subMenu);
    }
}