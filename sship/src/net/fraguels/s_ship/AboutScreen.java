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
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
public class AboutScreen implements Screen  {
	sship game;
	private static Stage stage;
	private static Label lbAbout;
	private static TextButton btnMain;
	AboutScreen(sship game)
	{
		this.game = game;
	}
	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(.0f,.0f,.0f,1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();		
	}
	 @Override
     public void resize(int width, int height) {
		 stage.setViewport(640, 480, true);		 
		stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);
     }


   @Override
     public void show() {
	   	Gdx.gl.glClearColor(.0f,.0f,.0f,1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Texture.setEnforcePotImages(false);
	
		if (stage == null)
		{
			stage = new Stage();		
			btnMain = CCommon.getButton(CLanguage._T("Return"));
			btnMain.setPosition(50, 370);
			btnMain.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {					
					return true;
				};
				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button)  {				
					game.setScreen(game.mainScreen);
					super.touchUp(event, x, y, pointer, button);
				};
				}
			);
			FileHandle fileHandle = Gdx.files.internal("data/about.txt");
			String aboutText = fileHandle.readString();			
			LabelStyle style = new LabelStyle(new BitmapFont(), Color.WHITE);
			lbAbout = new Label(aboutText, style);
			lbAbout.setPosition(60+ btnMain.getWidth(),  (0 - lbAbout.getHeight()));			
			lbAbout.addAction(Actions.forever(Actions.sequence(Actions.moveBy(0, (stage.getHeight()+lbAbout.getHeight()), 20), Actions.moveTo(60+ btnMain.getWidth(),   (0 - lbAbout.getHeight())))));
			
		}
		Gdx.input.setInputProcessor(stage);
		stage.setViewport(640, 480, true);
		stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);
		Texture.setEnforcePotImages(false);	
		
		
		stage.addActor(CCommon.getBackgroundImage());
		stage.addActor(btnMain);
		stage.addActor(lbAbout);		
     }


   @Override
     public void hide() {
          // called when current screen changes from this to a different screen
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
