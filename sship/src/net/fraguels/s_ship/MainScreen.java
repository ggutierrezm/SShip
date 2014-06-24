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
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MainScreen implements Screen{
	sship game;
	private static Texture shipTexture;
	private static Image shipImage;
	private Stage stage;
	
	public MainScreen(final sship game)
	{
		
		this.game = game;
			
	}
	@Override
	public void render(float delta) 
	{
		 Gdx.gl.glClearColor(.0f,.0f,.0f,1);
		 Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);		
		 stage.setViewport(640, 480, true);		 
		 stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);		 
		 stage.act();
		 stage.draw();		
		 CCommon.writeCenterBitmap(stage, "Shitty Ship", (int)(stage.getWidth()/2f), 350);		 
		 
		 			
	}
	 @Override
     public void resize(int width, int height) {
		
		 stage.setViewport(640, 480, true);		 
		 stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);
     }


   @Override
     public void show() {
	   
	   if (stage == null)
	   {
		   stage = new Stage();
		   Texture.setEnforcePotImages(false);
		   stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);	
		   Gdx.input.setInputProcessor(stage);
		   stage.setViewport(640, 480, true);
		   shipTexture = new Texture("images/ship_331x177.png");
		   shipImage = new Image(shipTexture);
		   shipImage.setPosition(530, 100);
		   shipImage.rotate(60);
		   TextButton bntTutorial = CCommon.getButton(CLanguage._T("Tutorial"));
		   TextButton bntPlay = CCommon.getButton(CLanguage._T("Play"));
		   TextButton bntConfig = CCommon.getButton(CLanguage._T("Configuration"));
		   TextButton bntAbout = CCommon.getButton(CLanguage._T("About"));
		   //Positioning the actors
			
		   bntPlay.setPosition(50, 370);
		   bntTutorial.setPosition(50, 320);
		   bntConfig.setPosition(50, 270);
		   bntAbout.setPosition(50, 220);
		   //Connecting the event listeners

			
			
		   bntTutorial.addListener(new InputListener(){
			    @Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;//super.touchDown(event, x, y, pointer, button);
					
				};
				
				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button)  {				
					game.setScreen(game.tutorialScreen);
					game.tutorialScreen.show();
					super.touchUp(event, x, y, pointer, button);
				};
			} );
			
			bntPlay.addListener(new InputListener(){
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;//super.touchDown(event, x, y, pointer, button);
					
				};
				
				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button)  {
					game.setScreen(game.gameScreen);
					super.touchUp(event, x, y, pointer, button);
				};
			} );
			
			bntAbout.addListener(new InputListener(){
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;//super.touchDown(event, x, y, pointer, button);
					
				};
				
				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button)  {				
					game.setScreen(game.aboutScreen);
					game.aboutScreen.show();
					super.touchUp(event, x, y, pointer, button);
				};
			} );
			
			bntConfig.addListener(new InputListener(){
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;//super.touchDown(event, x, y, pointer, button);
					
				};
				
				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button)  {				
					game.setScreen(game.configurationScreen);
					game.configurationScreen.show();
					super.touchUp(event, x, y, pointer, button);
				};
			} );
			
			stage.addActor(CCommon.getBackgroundImage());
			stage.addActor(shipImage);
			
			stage.addActor(bntTutorial);		
			stage.addActor(bntPlay);
			stage.addActor(bntConfig);
			stage.addActor(bntAbout);

	   }
		
	       	
				
     }


   @Override
     public void hide() {
	   stage.clear();
	   stage.dispose();
	   CCommon.disposeFonts();
	   stage = null;
     }


   @Override
     public void pause() {
     }


   @Override
     public void resume() {
     }


   @Override
     public void dispose() {
             // never called automatically
	   CCommon.dispose();
	   stage.dispose();
     }

}
