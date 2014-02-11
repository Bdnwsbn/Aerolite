package com.benkatz.multiplayerasteroids;

import com.benkatz.framework.Screen;
import com.benkatz.framework.implementation.AndroidGame;

public class StartGame extends AndroidGame {

	boolean firstTimeCreate = true;
	
	@Override
	public Screen getInitScreen() {
		if (firstTimeCreate) {
			Assets.load(this);
			firstTimeCreate = false;
		}
		
		return new SplashLoadingScreen(this);
	}
	
	@Override
	public void onBackPressed() {
		getCurrentScreen().backButton();
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
		
		Assets.theme.play();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		
		Assets.theme.pause();
	}
}
