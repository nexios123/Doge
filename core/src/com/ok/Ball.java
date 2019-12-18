package com.ok;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class Ball extends DynamicGameObject implements Pool.Poolable {
    public static float BALL_WIDTH = 50;
    public static float BALL_HEIGHT = 50;

    public static final Pool<Ball> poolBalls = Pools.get(Ball.class, 100);

    public Ball(){
        super(MathUtils.random(0, Gdx.graphics.getWidth()), Gdx.graphics.getHeight(), BALL_WIDTH, BALL_HEIGHT);
        velocity = Math.random() * ( 1000 - 100 );
        img = new Texture(Gdx.files.internal("Ball64.png"));

    }
    public void init(float posX, float posY, double speed) {
        position.set(posX,  posY);
        velocity = speed;
    }

    @Override public void reset() {
        init(MathUtils.random(0, Gdx.graphics.getWidth()), Gdx.graphics.getHeight(),Math.random() * ( 1000 - 100 )); //Called when it is returned to pool (free method is called)
    }
    public void finish() { poolBalls.free(this); reset(); }

    @Override
    public void overlap(Doge player) {
        wof.play();
        player.ballsRescuedScore++;
        finish();
    }

}
