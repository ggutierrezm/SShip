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

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class CUFO {
	private static Texture ufoTexture;
	private static TextureRegion[] ufoTextureRegion = new TextureRegion[10];
	private static Animation ufoAnimation;
	private static AnimatedActor ufoAnimatedActor;
	private static Image ufoImage;
	private static int ufoCount = 0;
	private CUFO()
	{
		
	}
	
	public static void getUFO(Stage stage)	
	{			
		if (ufoCount > ((CCommon.getPoints()/3000.f)+3))
		{
			return;
		}		
		if (ufoImage == null)
		{			
			ufoTexture = new Texture("images/ufo_sprites(10)_250x100.png");
			TextureRegion[][] tmp = TextureRegion.split(ufoTexture, 50, 50);
			int index=0;
			for (int i=0; i<5; i++)
				for (int j=0; j<2; j++)
					ufoTextureRegion[index++] = tmp[j][i];			
								
		}
		ufoAnimation = new Animation(0.2f, ufoTextureRegion);
		ufoAnimatedActor = new AnimatedActor(new AnimationDrawable(ufoAnimation));
		
		ufoAnimatedActor.setName("ufo");
		float _y = MathUtils.random(25.f, stage.getHeight()-25);
		float _x = MathUtils.random(25.f, 200.f);
		ufoAnimatedActor.setPosition(stage.getWidth() + _x, _y);
		
		ufoAnimatedActor.addAction(Actions.sequence(Actions.moveBy(-(stage.getWidth() +150), 0, 2f), Actions.run(new Runnable() {
			public void run () {				
				ufoCount--;
			}
		}
		),Actions.removeActor()));
		ufoCount++;
		stage.addActor(ufoAnimatedActor);		
	}
	public static void explodeUFO()
	{
		ufoCount--;
	}
	public static void resetUFO()
	{
		ufoCount = 0;
	}
}
