package com.next.pong.pages.game;

import com.next.pong.framework.activity.Activity;
import com.next.pong.game.Game;
import com.next.pong.game.player.ComputerPlayer;
import com.next.pong.game.player.HumanPlayer;
import com.next.pong.utils.Vector2;

public class GameActivity extends Activity<GameLayout> {

    private final Game game;

    public GameActivity() {
        super(new GameLayout());

        int width = GameLayout.DEFAULT_WIDTH;
        int height = GameLayout.DEFAULT_HEIGHT;

        var playerA = new HumanPlayer(
                new Vector2(0.1 * width, 0.5 * height),
                new Vector2(0.0, 0.0),
                new Vector2(0.01 * width, 0.3 * height)
        );

        var playerB = new ComputerPlayer(
                new Vector2(0.9 * width, 0.5 * height),
                new Vector2(0.0, 0.0),
                new Vector2(0.01 * width, 0.3 * height)
        );

        game = new Game(width, height, playerA, playerB);
    }

    @Override
    public void onUpdate(double deltaMs) {
        game.update(deltaMs * 0.001);

        var ball = game.getBall();
        var ballPosition = ball.getPosition();
        layout.setBallProperties(ballPosition.x(), ballPosition.y(), ball.getRadius());

        var playerA = game.getPlayerA();
        var positionA = playerA.getPosition();
        var sizeA = playerA.getSize();
        layout.setPlayerElementA(positionA.x(), positionA.y(), sizeA.x(), sizeA.y());

        var playerB = game.getPlayerB();
        var positionB = playerB.getPosition();
        var sizeB = playerB.getSize();
        layout.setPlayerElementB(positionB.x(), positionB.y(), sizeB.x(), sizeB.y());
    }

}
