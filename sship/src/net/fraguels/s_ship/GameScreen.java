/*
    Shitty ship it's a free software licensed under GPLv3 license
    Copyright (C) 2014  Guillermo Gutierrez Morote <ggmorote@gmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.fraguels.s_ship;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
public class GameScreen implements Screen 
{
	sship game;
	private Stage stage;
	
	private static Texture buttonTexture;
	private static Image upImage;
	private static Image fireImage;
	private static Music music; 
	private float time = 0;
	
	public GameScreen(final sship game)
	{
		this.game = game;
		
	}
	@Override
	public void render(float delta) 
	{
	 	Gdx.gl.glClearColor(.0f,.0f,.0f,1);
	   	Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); 	
	   	stage.setViewport(640, 480, true);
	   	CShip.collide(stage);
	   	if (time >= 3)
	   	{
	   		CUFO.getUFO(stage);		   		
	   		if (CCommon.getLives() < 0)
	   		{			
	   			game.setScreen(game.endScreen); 
	   			return;
	   		}
	   		if (Gdx.input.isKeyPressed(Keys.SPACE))
	   		{
	   			CShip.engineOn(stage);
	   		}
	   		if (Gdx.input.isKeyPressed(Keys.ENTER))
	   		{
	   			CShip.misilleLaunch(stage);
	   		}
	   		upImage.toFront();
	   		fireImage.toFront();
	   		stage.draw();
	   		stage.act();
	   		CCommon.writeCenterBitmap(stage, String.format(CLanguage._T("Lives: %d"), CCommon.getLives()), (int)(stage.getWidth()/2), 430);
	   		CCommon.writeCenterBitmap(stage, String.format(CLanguage._T("Points: %d"), CCommon.getPoints()), (int)(stage.getWidth()/2), 20);
	   	}
	   	else
	   	{
	   		stage.draw();
	   		stage.act();
	   		int secs = 3;
	   		time += Gdx.graphics.getDeltaTime();
	   		secs -= (int)time;
	   		CCommon.writeBitmap(stage, String.format("Start in %d secs", secs), (int)(stage.getWidth()/2),(int)(stage.getHeight()/2));	   			   		
	   	}
	}
	 @Override
     public void resize(int width, int height) {
		 stage.setViewport(640, 480, true);		 
		 stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);		 
     }


   @Override
   public void show() 
   {
        // called when this screen is set as the screen with game.setScreen();
	   if (stage == null)
	   {
			stage = new Stage();	
			float deltaTime = Gdx.graphics.getDeltaTime();
			System.out.print(String.format("%f", deltaTime));
			time = 0;
			Gdx.input.setInputProcessor(stage);
			stage.setViewport(640, 480, true);
			stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);	   	
			CCommon.resetLives();
			CCommon.resetPoints();	
			CUFO.resetUFO();
			buttonTexture = new Texture("images/game_button_100x100.png");
			upImage = new Image(buttonTexture);
			fireImage = new Image(buttonTexture);
			upImage.setPosition(0, 0);	
			float firePos = (stage.getWidth()- (float)buttonTexture.getWidth());
			fireImage.setPosition(firePos, 0);	
			upImage.addListener(new InputListener(){
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {	
					CShip.engineOn(stage);
					return true;
				};		
			} );
			fireImage.addListener(new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {				
					CShip.misilleLaunch(stage);
					
				};					
			} );		
		}
	   if (CConfig.getMusic())
	   {
		   music = Gdx.audio.newMusic(Gdx.files.internal("music/music.mp3"));
		   music.play();
	   }
	   stage.addActor(PlayBackground.getBackground());		
	   stage.addActor(CShip.getShip());
	   stage.addActor(upImage);
	   stage.addActor(fireImage);
    }


   @Override
     public void hide() {
	   stage.clear();
	   stage.dispose();
	   CCommon.disposeFonts();
	   buttonTexture.dispose();
	   buttonTexture = null;
	   upImage = null;
	   fireImage = null;
	   if (CConfig.getMusic())
	   {
		   music.dispose();	
		   music = null;
	   }
	   stage = null;
     }


   @Override
     public void pause() {
     }


   @Override
     public void resume() {
     }


   @Override
   public void dispose() 
   {
	   PlayBackground.Dispose();
	   
   }

}
