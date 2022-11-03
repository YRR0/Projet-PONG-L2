package com.next.pong.pause;


import com.next.pong.framework.activity.Activity;
import com.next.pong.framework.window.Window;
import com.next.pong.pages.home.HomeActivity;
import com.next.pong.pages.home.HomeLayout;

public class PauseActivity extends Activity<PauseLayout>  {
	
	public PauseActivity() {
		super(new PauseLayout());
		PauseLayout l = this.getLayout();
		
		l.recommencer.setOnMouseClicked(e -> Window.goTo(new HomeActivity()));
		l.reprendre.setOnMouseClicked(e -> Window.goTo(new HomeActivity()));
		l.acceuil.setOnMouseClicked(e -> Window.goTo(new HomeActivity()));
		l.option.setOnMouseClicked(e -> Window.goTo(new HomeActivity()));
		l.quitter.setOnMouseClicked(e -> Window.goTo(new HomeActivity()));
		
	}
}
