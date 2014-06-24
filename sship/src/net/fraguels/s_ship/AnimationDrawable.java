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

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;

class AnimationDrawable extends BaseDrawable
{
	public final Animation anim;
	
	private float stateTime = 0;
	
	public AnimationDrawable(Animation anim)
	{
	    this.anim = anim;
	    setMinWidth(anim.getKeyFrame(0).getRegionWidth());
	    setMinHeight(anim.getKeyFrame(0).getRegionHeight());
	}
	
	public void act(float delta)
	{
	    stateTime += delta;
	}
	
	public void reset()
	{
	    stateTime = 0;
	}
	
	@Override
	public void draw(Batch batch, float x, float y, float width, float height)
	{
	    batch.draw(anim.getKeyFrame(stateTime), x, y, width, height);
	}
}