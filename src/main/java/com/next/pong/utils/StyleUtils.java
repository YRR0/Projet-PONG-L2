package com.next.pong.utils;

import com.next.pong.framework.layout.Layout;
import javafx.scene.Node;

public class StyleUtils {
    public static void center(Node n) {
        var nWidth = n.getLayoutBounds().getWidth();
        n.setLayoutX(0.5 * Layout.DEFAULT_WIDTH - 0.5 * nWidth);
    }
}
