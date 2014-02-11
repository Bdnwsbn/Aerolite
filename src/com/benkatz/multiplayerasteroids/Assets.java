package com.benkatz.multiplayerasteroids;

import com.benkatz.framework.Image;
import com.benkatz.framework.Music;
import com.benkatz.framework.Sound;

public class Assets {

	public static Image menu, splash, background,
	characterUp, characterUp2, characterUp3, characterUp4,
	characterDown, characterDown2, characterDown3, characterDown4,
	characterLeft, characterLeft2, characterLeft3, characterLeft4,
	characterRight, characterRight2, characterRight3, characterRight4,
	//characterUpRight, characterUpRight2, characterUpRight3, 
	//characterUpLeft, characterUpLeft2, characterUpLeft3, 
	//characterDownRight, characterDownRight2, characterDownRight3,
	//characterDownLeft, characterDownLeft2, characterDownLeft3, 
	 asteroidBig, asteroidBig2, asteroidBig3, asteroidBig4, asteroidBig5,
	 asteroidBig6, asteroidBig7, asteroidBig8, asteroidBig9, asteroidBig10,
	 asteroidBig11, asteroidBig12, asteroidBig13, asteroidBig14, asteroidBig15,
	 asteroidBig16, asteroidBig17;
	
	public static Image button;
	public static Sound click;
	public static Music theme;
	
	public static void load(StartGame startGame) {
		theme = startGame.getAudio().createMusic("menutheme.mp3");
		theme.setLooping(true);
		theme.setVolume(0.85f);
		theme.play();
	}
}
