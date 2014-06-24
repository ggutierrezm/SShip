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
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
public class TutorialScreen implements Screen  {
	sship game;
	private Stage stage;
	
	private static Texture buttonTexture;
	private static Image upImage;
	private static Image fireImage;
	private static Texture arrowTexture;
	private static TextureRegion arrowFlipRegion;
	
	 
	private static Image arrowImage;
	private static Image arrowFlipImage;
	private static Dialog endDialog;

	private static int step = 0;
	TutorialScreen(sship game)
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
		if (Gdx.input.isKeyPressed(Keys.SPACE))
		{
			CShip.engineOn(stage);
		}
		if (Gdx.input.isKeyPressed(Keys.ENTER))
		{
			CShip.misilleLaunch(stage);
		}
		if (step == 10)
		{
			arrowFlipImage.addAction(Actions.removeActor());
			CShip.removeShip(stage);
			step++;
			upImage.clearListeners();
			fireImage.clearListeners();
			CCommon.resetPoints();		
		}
		
		stage.draw();
		stage.act();
		
		if (step == 0)
		{
			CCommon.writeCenterBitmap(stage, String.format(CLanguage._T("Touch the button to go up")), (int)(stage.getWidth()/2), 230);
		}
		else if (step < 5)
		{
			CCommon.writeCenterBitmap(stage, String.format(CLanguage._T("Keep touching")), (int)(stage.getWidth()/2), 230);
		} 
		else if (step == 5)
		{
			CCommon.writeCenterBitmap(stage, String.format(CLanguage._T("Touch the button to shoot")), (int)(stage.getWidth()/2), 230);
		}
		else if (step < 10)
		{
			CCommon.writeCenterBitmap(stage, String.format(CLanguage._T("Destroy 4 enemies")), (int)(stage.getWidth()/2), 230);
			CUFO.getUFO(stage);
		}
		if (CCommon.getPoints() >= 4000)
		{
			step = 10;										
		}		
		else if (step == 11)
		{
			step++;
			endDialog.show(stage);
		}

	}
	 @Override
     public void resize(int width, int height) {
		 stage.setViewport(640, 480, true);		 
		 stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);		 
		
     }


   @Override
     public void show() {
          // called when this screen is set as the screen with game.setScreen();
	   step = 0;
	   if (stage == null)
	   {
		    CUFO.resetUFO();
			stage = new Stage();	
			Gdx.input.setInputProcessor(stage);
			stage.setViewport(640, 480, true);
			stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);	   	
			CCommon.resetLives();
			CCommon.resetPoints();				
			buttonTexture = new Texture("images/game_button_100x100.png");
			arrowTexture = new Texture("images/arrow_125x125.png");
			
			
			upImage = new Image(buttonTexture);
			fireImage = new Image(buttonTexture);
			arrowFlipRegion = new TextureRegion(arrowTexture);
			arrowFlipRegion.flip(true, false);
			arrowImage = new Image(arrowTexture);
			arrowFlipImage = new Image(arrowFlipRegion);

			
			upImage.setPosition(0, 0);				
			arrowImage.setPosition(75, 130);
			float firePos = (stage.getWidth()- (float)buttonTexture.getWidth());
			fireImage.setPosition(firePos, 0);	
			upImage.addListener(new InputListener(){
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {						
					CShip.engineOn(stage);
					if (step < 5)
					{
						step++;
					}
					
					if (step == 5)
					{
						float firePos = (stage.getWidth()- (float)buttonTexture.getWidth());	
						arrowImage.addAction(Actions.removeActor());						
						arrowFlipImage.setPosition(firePos -75, 130);
						stage.addActor(arrowFlipImage);			
						
					}
					return true;
				};		
			} );
			fireImage.addListener(new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {				
					CShip.misilleLaunch(stage);
					if (step == 5)
					{
						step++;
					}
					
				};					
			} );
			Skin dialogSkin  = new Skin(Gdx.files.internal("skin/uiskin.json"));
			endDialog = new Dialog(CLanguage._T("Tutorial"), dialogSkin) {
			{
					text(CLanguage._T("Great job\nNow let's go to play!"));
					button(CLanguage._T("OK"));				
				}
			    @Override
			    protected void result(Object object)
			    {
			    	game.setScreen(game.mainScreen);
			    }
			};			
			
		}
	    stage.addActor(PlayBackground.getBackground());		
		stage.addActor(CShip.getShip());
		stage.addActor(upImage);
		stage.addActor(fireImage);
		stage.addActor(arrowImage);
     }


   @Override
     public void hide() {
	   stage.clear();
	   stage.dispose();
	   CCommon.disposeFonts();
	   buttonTexture.dispose();	   
	   buttonTexture = null;
	   arrowTexture.dispose();
	   arrowTexture = null;
	   upImage = null;
	   fireImage = null;
	   arrowImage = null;	
	   
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
     }

}
