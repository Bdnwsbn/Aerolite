package com.benkatz.multiplayerasteroids;

import com.benkatz.framework.Game;
import com.benkatz.framework.Graphics;
import com.benkatz.framework.Graphics.ImageFormat;
import com.benkatz.framework.Screen;

public class LoadingScreen extends Screen{
	
	public LoadingScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		
		Assets.menu = g.newImage("menu.png", ImageFormat.RGB565);
		Assets.background = g.newImage("background.png", ImageFormat.RGB565);
		Assets.button = g.newImage("button.jpg", ImageFormat.RGB565);
		
		Assets.characterUp = g.newImage("characterUp.png", ImageFormat.ARGB4444);
		Assets.characterUp2 = g.newImage("characterUp2.png", ImageFormat.ARGB4444);
		Assets.characterUp3 = g.newImage("characterUp3.png", ImageFormat.ARGB4444);
		Assets.characterUp4 = g.newImage("characterUp4.png", ImageFormat.ARGB4444);
		
		Assets.characterDown = g.newImage("characterDown.png", ImageFormat.ARGB4444);
		Assets.characterDown2 = g.newImage("characterDown2.png", ImageFormat.ARGB4444);
		Assets.characterDown3 = g.newImage("characterDown3.png", ImageFormat.ARGB4444);
		Assets.characterDown4 = g.newImage("characterDown4.png", ImageFormat.ARGB4444);
		
		Assets.characterLeft = g.newImage("characterLeft.png", ImageFormat.ARGB4444);
		Assets.characterLeft2 = g.newImage("characterLeft2.png", ImageFormat.ARGB4444);
		Assets.characterLeft3 = g.newImage("characterLeft3.png", ImageFormat.ARGB4444);
		Assets.characterLeft4 = g.newImage("characterLeft4.png", ImageFormat.ARGB4444);
		
		Assets.characterRight = g.newImage("characterRight.png", ImageFormat.ARGB4444);
		Assets.characterRight2 = g.newImage("characterRight2.png", ImageFormat.ARGB4444);
		Assets.characterRight3 = g.newImage("characterRight3.png", ImageFormat.ARGB4444);
		Assets.characterRight4 = g.newImage("characterRight4.png", ImageFormat.ARGB4444);
		
		Assets.asteroidBig = g.newImage("asteroidBig.png", ImageFormat.ARGB4444);
		Assets.asteroidBig2 = g.newImage("asteroidBig2.png", ImageFormat.ARGB4444);
		Assets.asteroidBig3 = g.newImage("asteroidBig3.png", ImageFormat.ARGB4444);
		Assets.asteroidBig4 = g.newImage("asteroidBig4.png", ImageFormat.ARGB4444);
		Assets.asteroidBig5 = g.newImage("asteroidBig5.png", ImageFormat.ARGB4444);
		Assets.asteroidBig6 = g.newImage("asteroidBig6.png", ImageFormat.ARGB4444);
		Assets.asteroidBig7 = g.newImage("asteroidBig7.png", ImageFormat.ARGB4444);
		Assets.asteroidBig8 = g.newImage("asteroidBig8.png", ImageFormat.ARGB4444);
		Assets.asteroidBig9 = g.newImage("asteroidBig9.png", ImageFormat.ARGB4444);
		Assets.asteroidBig10 = g.newImage("asteroidBig10.png", ImageFormat.ARGB4444);
		Assets.asteroidBig11 = g.newImage("asteroidBig11.png", ImageFormat.ARGB4444);
		Assets.asteroidBig12 = g.newImage("asteroidBig12.png", ImageFormat.ARGB4444);
		Assets.asteroidBig13 = g.newImage("asteroidBig13.png", ImageFormat.ARGB4444);
		Assets.asteroidBig14 = g.newImage("asteroidBig14.png", ImageFormat.ARGB4444);
		Assets.asteroidBig15 = g.newImage("asteroidBig15.png", ImageFormat.ARGB4444);
		Assets.asteroidBig16 = g.newImage("asteroidBig16.png", ImageFormat.ARGB4444);
		Assets.asteroidBig17 = g.newImage("asteroidBig17.png", ImageFormat.ARGB4444);
		
		game.setScreen(new MainMenuScreen(game));
		
	}
	
	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.splash, 0, 0);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

	// Intentionally blank
	@Override
	public void backButton() {
	}
	
	
}
