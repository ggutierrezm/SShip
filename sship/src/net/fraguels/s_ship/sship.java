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

import com.badlogic.gdx.Game;

public class sship extends Game {
	
	MainScreen 			mainScreen;
	GameScreen 			gameScreen;
	AboutScreen 		aboutScreen;
	TutorialScreen 		tutorialScreen;
	ConfigurationScreen configurationScreen;
	EndScreen 			endScreen;
	@Override
	public void create() {		
		mainScreen 			= 	new MainScreen(this);
		gameScreen 			= 	new GameScreen(this);
		aboutScreen 		= 	new AboutScreen(this);		
		tutorialScreen 		= 	new TutorialScreen(this);
		configurationScreen = 	new ConfigurationScreen(this);
		endScreen 			= 	new EndScreen(this);
		setScreen(mainScreen);
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void render() {		
		getScreen().render(0);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
