package com.next.pong.pages.pause;

import com.next.pong.framework.activity.Activity;

import com.next.pong.framework.window.Window;
import com.next.pong.pages.home.HomeActivity;
import com.next.pong.pages.home.HomeLayout;
import com.next.pong.pages.game.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import com.next.pong.pages.game.GameActivity;
import com.next.pong.framework.activity.BeforeActivity;
import com.next.pong.framework.layout.Layout;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class PauseActivity extends Activity<PauseLayout> {

	public boolean pauseState = true;
	public PauseLayout pauseLayout;

	public PauseActivity() {
		super(new PauseLayout());
		if (Window.getPauseactivity() == null) {
			Window.setPauseactivity(this);
		}

		pauseLayout = this.getLayout();
		pauseLayout.reprendre.setOnMouseClicked(e -> {
			change();

			Stage stage = (Stage) this.getScene().getWindow();
			stage.close();
			Window.setPauseactivity(null);
		});
		pauseLayout.recommencer.setOnMouseClicked(e -> {
			change();
			Activity activity = Window.getActivity();
			if (activity != null) {
				Stage stage = (Stage) this.getScene().getWindow();
				stage.close();
				stage.close();
				stage.close();
				Stage stage1 = (Stage) activity.getScene().getWindow();
				stage1.close();
				Window.setActivity(activity);
				Window.initGame(stage);
			}

		});

		pauseLayout.acceuil.setOnMouseClicked(e -> {
			change();
			Stage stage = (Stage) this.getScene().getWindow();

			stage.close();
			Activity activity = Window.getActivity();
			Activity homeactivity = Window.getHomeactivity();
			if (activity != null && homeactivity != null) {
				Stage stage1 = (Stage) activity.getScene().getWindow();
				stage1.close();
				Window.goTo(new HomeActivity());

			}

		});

		pauseLayout.option.setOnMouseClicked(e -> {
			change();
			Stage stage = (Stage) this.getScene().getWindow();
			stage.close();
			Activity activity = Window.getActivity();
			Activity homeactivity = Window.getHomeactivity();
			if (activity != null && homeactivity != null) {
				Stage stage1 = (Stage) activity.getScene().getWindow();
				stage1.close();
				Window.goTo(new HomeActivity());
			}
		});

		pauseLayout.quitter.setOnMouseClicked(e -> {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle(" Quitter");
			alert.setHeaderText(" Vous allez quitter ...... ");
			alert.setContentText(" Nous espérons vous revoir bientot");

			if (alert.showAndWait().get() == ButtonType.OK) {
				change();
				Stage stage = (Stage) this.getScene().getWindow();

				stage.close();
				Activity activity = Window.getActivity();
				Activity homeactivity = Window.getHomeactivity();
				if (activity != null && homeactivity != null) {
					Stage stage1 = (Stage) activity.getScene().getWindow();
					stage1.close();

					Window.goTo(new HomeActivity());
				}
			} else {
				Stage stage = (Stage) this.getScene().getWindow();
				stage.close();
				Window.setPauseactivity(null);
			}
		});
	}

	public void change() {
		pauseState = false;
	}

	public boolean getP() {
		return pauseState;
	}

	@Override
	public boolean onStop() {
		return super.onStop();
	}
}
