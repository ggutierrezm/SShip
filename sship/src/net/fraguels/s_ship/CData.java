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
import com.badlogic.gdx.Preferences;

public class CData {
	private static Preferences prefs = null;
	public static boolean newRecord = false;
	private CData() 
	{	
	}
	public static int getPoints()
	{
		if (prefs == null)
		{
			prefs = Gdx.app.getPreferences("SShip");
		}
		return prefs.getInteger("points", 0);
	}
	public static void setPoints(int val)
	{
		if (prefs == null)
		{
			prefs = Gdx.app.getPreferences("SShip");
		}
		prefs.putInteger("points", val);
	}
	public static void flush()
	{
		if (prefs == null)
		{
			prefs = Gdx.app.getPreferences("SShip");
		}
		prefs.flush();
	}
}
