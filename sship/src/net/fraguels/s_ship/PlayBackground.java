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

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public final class PlayBackground {
	private static Texture backgroundTexture;
	private static Image backgroundImage;	

	private PlayBackground() {
				
	}	
	public static Image getBackground()
	{
		if (backgroundTexture == null)
		{
			backgroundTexture = new Texture("images/background_2560x628.png");
			backgroundImage = new Image(backgroundTexture);		
			backgroundImage.setPosition(0, 0);
			backgroundImage.addAction(Actions.forever(Actions.sequence(Actions.moveBy(-640,0, 5), Actions.moveBy(640, 0))));		
		}
		return backgroundImage;
	}
	public static void Dispose()
	{		
		backgroundImage.clear();
		backgroundImage = null;
		backgroundTexture.dispose();
	}
}
