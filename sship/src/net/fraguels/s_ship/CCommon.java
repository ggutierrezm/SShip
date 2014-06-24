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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class CCommon 
{
	private static int points = 0;	
	private static int lives = 0;
	
	private static BitmapFont alphaFont = null;
	private static SpriteBatch alphaBatch = null;
	private static TextBounds bounds = null;
	private static Texture backgroundTexture = null;
	private static Vector2 coordinates = new Vector2();
	
	private static Texture buttonTexture = null;
	private static BitmapFont buttonFont;
	private static TextButtonStyle skin;
	private static TextureRegion downRegion;
	private static TextureRegion upRegion;
	private static Texture checkBoxTexture = null;
	private static BitmapFont checkBoxFont;
	private static CheckBoxStyle checkBoxSkin;
	private static TextureRegion onRegion;
	private static TextureRegion offRegion;
	
	
	private CCommon()
	{
		
	}
	public static void dispose()
	{
		coordinates = null;
		alphaBatch.dispose();
		alphaFont.dispose();
		backgroundTexture.dispose();		
				
	}
	public static int getPoints()
	{
		return points;
	}
	public static void resetPoints()
	{
		points = 0;
	}
	public static void increasePoints()
	{
		points += 1000;
	}
	public static int getLives()
	{
		return lives;
	}
	public static void resetLives()
	{
		lives = 3;
	}
	public static void lostLive()
	{
		lives--;
	}
	public static void writeBitmap(Stage stage, String text, int x, int y)
	{
		coordinates.x = x;
		coordinates.y = y;
		stage.stageToScreenCoordinates(coordinates);
		if (alphaFont == null) 
		{
			alphaBatch = new SpriteBatch();  
			alphaFont = new BitmapFont(Gdx.files.internal("fonts/Alpha.fnt"), Gdx.files.internal("fonts/Alpha.png"), false);
			bounds = new TextBounds();
		}		
		alphaBatch.begin();
		alphaFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		alphaFont.draw(alphaBatch, text, coordinates.x, coordinates.y);	
		 
		
		
		alphaBatch.end();
	}	
	public static void disposeFonts()
	{
		if (alphaFont != null) 
		{
			alphaFont.dispose();
			alphaFont = null;
			alphaBatch.dispose();
			alphaBatch = null;
			bounds = null;
		}
		
	}
	public static void writeCenterBitmap(Stage stage, String text, int x, int y)
	{
		coordinates.x = x;
		coordinates.y = y;
		stage.stageToScreenCoordinates(coordinates);
		if (alphaFont == null) 
		{
			alphaBatch = new SpriteBatch();  
			alphaFont = new BitmapFont(Gdx.files.internal("fonts/Alpha.fnt"), Gdx.files.internal("fonts/Alpha.png"), false);
			bounds = new TextBounds();		
		}
		alphaBatch.begin();
		alphaFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		alphaFont.getBounds(text, bounds);
		alphaFont.draw(alphaBatch, text, coordinates.x-(bounds.width/2) , coordinates.y-(bounds.height/2));					 				
		alphaBatch.end();				
	}	
	public static Image getBackgroundImage()
	{
		if (backgroundTexture != null)
		{
				backgroundTexture.dispose();
		}
		backgroundTexture = new Texture("images/start_640x480.png");
		return new Image(backgroundTexture);
	}
	public static TextButton getButton(String caption)
	{		
		buttonTexture = new Texture("images/button_80x76.png");						
		buttonFont = new BitmapFont();		
		skin = new TextButtonStyle();
		downRegion = new TextureRegion(buttonTexture, 80, 38);
		upRegion = new TextureRegion(buttonTexture, 0, 38, 80, 38);				
		skin.up = new TextureRegionDrawable(upRegion);
		skin.down = new TextureRegionDrawable(downRegion);
		skin.over = new TextureRegionDrawable(downRegion);
		skin.font = buttonFont;
		
		return new TextButton(caption, skin);
		
	}
	public static CheckBox getCheckBox(String caption)
	{		
		checkBoxTexture = new Texture("images/checkButton_27x44.png");						
		checkBoxFont = new BitmapFont();		
		checkBoxSkin = new CheckBoxStyle();
		onRegion = new TextureRegion(checkBoxTexture, 27, 20);
		offRegion = new TextureRegion(checkBoxTexture, 0, 20, 27, 21);				
		checkBoxSkin.checkboxOff  = new TextureRegionDrawable(offRegion);
		checkBoxSkin.checkboxOn = new TextureRegionDrawable(onRegion);
		checkBoxSkin.font = checkBoxFont;		
		return new CheckBox(caption,checkBoxSkin);
		
	}
}
