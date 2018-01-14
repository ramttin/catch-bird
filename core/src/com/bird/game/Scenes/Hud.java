package com.bird.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


/**
 * Created by Ahoura on 1/15/2018.
 */

public class Hud {
    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private Integer score;

    Label countdownLable;
    Label scoreLabel;
    Label Bird;
    Label World;

    public Hud(SpriteBatch sb){
        worldTimer=100;
        score=0;

        viewport = new FitViewport(com.bird.game.Bird.W, com.bird.game.Bird.H,new OrthographicCamera());
        stage = new Stage(viewport,sb);
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countdownLable = new Label(String.format("%03d",worldTimer),new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        scoreLabel = new Label(String.format("%06d",score),new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        table.add(countdownLable).expand().padTop(10);
        table.add(scoreLabel).expand().padTop(10);
//        table.row();

        stage.addActor(table);
    }
}
