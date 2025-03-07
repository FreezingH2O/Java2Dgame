package logic;

import javafx.scene.media.AudioClip;

	public class Sound {
	private static AudioClip island_sound = new AudioClip(ClassLoader.getSystemResource("sound/island.wav").toString());
	public static AudioClip getIslandSound() {
		return island_sound;
	}
	
	private static AudioClip dungeon_sound = new AudioClip(ClassLoader.getSystemResource("sound/dungeon.wav").toString());
	public static AudioClip getDungeon_sound() {
		return dungeon_sound;
	}
	
	private static AudioClip bossroom_sound = new AudioClip(ClassLoader.getSystemResource("sound/finalBattle.wav").toString());
	public static AudioClip getBossroom_sound() {
		return bossroom_sound;
	}
	
	private static AudioClip gameover_sound = new AudioClip(ClassLoader.getSystemResource("sound/gameover.wav").toString());
	public static AudioClip getGameover_sound() {
		return gameover_sound;
	}
	
	private static AudioClip hitmonster_sound = new AudioClip(ClassLoader.getSystemResource("sound/hitmonster.wav").toString());
	public static AudioClip getHitmonster_sound() {
		return hitmonster_sound;
	}
	
	private static AudioClip powerup_sound = new AudioClip(ClassLoader.getSystemResource("sound/powerup.wav").toString());
	public static AudioClip getpowerup_sound() {
		return powerup_sound;
	}
	
	private static AudioClip levelup_sound = new AudioClip(ClassLoader.getSystemResource("sound/levelup.wav").toString());
	public static AudioClip getlevelup_sound() {
		return levelup_sound;
	}
	
	private static AudioClip dooropen_sound = new AudioClip(ClassLoader.getSystemResource("sound/opendoor.wav").toString());
	public static AudioClip getdooropen_sound() {
		return dooropen_sound;
	}
	
	private static AudioClip gamewin_sound = new AudioClip(ClassLoader.getSystemResource("sound/gamewin.wav").toString());
	public static AudioClip getGamewin_sound() {
		return gamewin_sound;
	}
}
