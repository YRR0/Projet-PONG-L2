package com.next.pong.framework.activity;

import com.next.pong.framework.layout.Layout;
import javafx.scene.Scene;
import com.next.pong.framework.activity.Activity;

public class BeforeActivity {

private static Layout layout=null;
//  public static miseLayout(Layout<?> layout){
    
//         // Window.goTo(pause);
//         Stage a = new Stage();
//         Scene sc = layout.getScene();
//         a.setScene(sc);
//         a.close();
//  }
 
  public static void misesLayout(Layout layouts){
    
      layout=layouts;
 }
   public static Layout layoutGet(){
    
     return layout;
 }
}
