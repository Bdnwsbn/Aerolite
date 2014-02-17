Aerolite
========

Touch-controlled multiplayer asteroids game for the Android platform that pushes real-time updates to all players using UDP delivery protocol.

Uses 'Java-Server' to test multiplayer functionality. 

## Installation

* Using Eclipse
  * Import 'Aerolite' into Eclipse. 'Run' project.
* To use multiplayer functionality, run 'Java-Server', then run 'Aerolite' on two or more Android devices running Android 2.3 or higher. 
* **IMPORTANT** - To test the multiplayer, you must change line 91 in GameScreen.java to your IP address. To find your IP address on Mac (my development machine of choice) open Terminal -> type 'ifconfig' -> Find the 'inet' address in the 'en1:' block. Or your 'BROADCAST' 'inet' address. Replace the IP in line 91 with your IP and it should work. 
 * This will be changed to dynamically find your IP address in the future.


## Implemented
* Multiplayer functionality
* Sprite animation
* Touch controls
* 4-Directional movement with background scrolling
* Collision detection (currently bugged)
* Score
* Custom graphics 
* Sound

### To do
Major updates 
 * Switch to OpenGL for graphics  
 * Implement two joysticks to control movement and shooting
 * Deploy server to a platform
 * More enemies
 * Powerups
 * Dynamically find correct IP address
 
Minor updates
 * Fix collision detection bug (Broke when ported code to Android)
 * Fix minor UI glitch
