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
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public final class CShip {
	private static Texture shipTexture;
	private static Texture missileTexture;
	private static Image shipImage;
	private static Sound shootSound;
	private static Sound explosionSound;
	private CShip()
	{
		
	}
	public static Image getShip()
	{
		if (shipImage == null)
		{
			shipTexture = new Texture("images/ship_100x53.png");
			shipImage = new Image(shipTexture);
			missileTexture = new Texture("images/missile_14x14.png");
		}
		shipImage.clearActions();
		shipImage.setPosition(20, 480);
		shipImage.setName("ship");
		shipImage.addAction(Actions.forever(Actions.moveBy(0,-480, 1.5f)));
		shipImage.addAction(Actions.rotateTo(-20, 0.5f));
		shootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/shoot.wav"));
		explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));
			
		return shipImage;
	}
	public static void removeShip(Stage stage)
	{
		if (shipImage == null)
		{
			return;
		}		
		shipImage.clearActions();
		shipImage.addAction(Actions.sequence(Actions.rotateTo(0,1) ));			
		return;
	}
	public static void Dispose()
	{
		if (shipImage == null)
		{
			shipImage.remove();
			shipTexture.dispose();
			missileTexture.dispose();
		}
	}
	public static void misilleLaunch(Stage stage)
	{		
		Image missile = new Image(missileTexture);
		missile.setName("missile");
		float rot = shipImage.getRotation();
		missile.setPosition(120, shipImage.getY() + 53* MathUtils.sinDeg(rot));				
		float _y = stage.getWidth() * MathUtils.sinDeg(rot);
		missile.addAction(Actions.sequence(Actions.moveBy(stage.getWidth(), _y, 1), Actions.removeActor()));
		missile.addAction(Actions.forever(Actions.rotateBy(-180, 1)));
		stage.addActor(missile);
		if (CConfig.getSound())
		{
			shootSound.play();
		}		
		return;
	}
	
	public static boolean collide(Stage stage) { 
		if (shipImage.getY() < 0 )
		{
			shipImage.setPosition(20,480);
		}
		Array<Actor> list = stage.getActors(); 
		for (int i=0; i < list.size; i++)
		{
			Actor _actor0 = list.items[i];
			if (_actor0.getName() == "missile")
			{
				for (Actor _actor1 : list) 
				{
					if (_actor1.getName() == "ufo")
					{
						//Missile hits an ufo
						Circle _missileCircle = new Circle();
						_missileCircle.x = _actor0.getX() + 7;
						_missileCircle.y = _actor0.getY() + 7;
						_missileCircle.radius = 14;
						Rectangle _ufoRectangle = new Rectangle(); 
						_ufoRectangle.x = _actor1.getX();
						_ufoRectangle.y = _actor1.getY();
						_ufoRectangle.height = 50;
						_ufoRectangle.width = 75;
						if (Intersector.overlaps(_missileCircle , _ufoRectangle))
						{
							CCommon.increasePoints();
							_actor1.remove();
							_actor0.remove();
							if (CConfig.getSound())
							{
								explosionSound.play();
							}
							CUFO.explodeUFO();
						}
						_missileCircle = null;
						_ufoRectangle = null;
						
					}
				}
				//Loop the shoots if goes out 
				if (_actor0.getY() < 0)
				{
					_actor0.setPosition(_actor0.getX(), stage.getHeight());
				}				
				else if (_actor0.getY() > stage.getHeight())
				{
					_actor0.setPosition(_actor0.getX(), 0);
				}
			}
			else if(_actor0.getName() == "ufo")
			{
				
				Rectangle _ufoRectangle = new Rectangle(); 
				_ufoRectangle.x = _actor0.getX();
				_ufoRectangle.y = _actor0.getY();
				_ufoRectangle.height = 50;
				_ufoRectangle.width = 75;
				Rectangle _shipRectangle = new Rectangle(); 
				_shipRectangle.x = shipImage.getX();
				_shipRectangle.y = shipImage.getY();
				_shipRectangle.height = 53;
				_shipRectangle.width = 100;
				if (Intersector.overlaps(_shipRectangle , _ufoRectangle))
				{				
					//Ship hits an ufo
					CCommon.lostLive();
					_actor0.remove();
					CUFO.explodeUFO();
					
				}
				_ufoRectangle = null;
				_shipRectangle = null;
			}
			
		}
	
		return true;
	}
	
	public static void engineOn(Stage stage)
	{
		shipImage.addAction(Actions.rotateTo(20));
		float _up = (shipImage.getY() + 100);
			
		if (_up > stage.getHeight())
		{	
			
			_up = _up - stage.getHeight();
			shipImage.addAction(Actions.moveTo(shipImage.getX(), 0));
		}						
		shipImage.addAction(Actions.moveTo(shipImage.getX(), _up, 0.5f));				
		shipImage.addAction(Actions.rotateTo(-20, 1));							
	}
}
