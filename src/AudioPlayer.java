import javax.sound.sampled.*;

public class AudioPlayer {
	private static Clip loopclip;
	private static Clip shortSoundclip;
	private static long clipTime;
	private static Boolean muted=false;
	
	public static void playSound(String name) {
		if (!muted) {
			try {
				shortSoundclip = AudioSystem.getClip();
				
				AudioInputStream ais = AudioSystem.getAudioInputStream(AudioPlayer.class.getResource(name));
				
				shortSoundclip.open(ais);
				shortSoundclip.start();
			} catch (Exception ex) {
				System.out.println("Error with playing sound.");
				ex.printStackTrace();
			}
			
		}
	}

	public static void lowVolumeloopSound(String name) {
		try {

			loopclip = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(AudioPlayer.class.getResource(name));

			loopclip.open(ais);
			FloatControl gainControl = (FloatControl) loopclip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-17.0f); // Reduce volume by 17 decibels.

			loopclip.loop(Clip.LOOP_CONTINUOUSLY);

		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

	public static void loopSound(String name) {

		try {

			loopclip = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(AudioPlayer.class.getResource(name));

			loopclip.open(ais);
			loopclip.loop(Clip.LOOP_CONTINUOUSLY);

		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

	public static void stoploopSound() {
		if (loopclip != null && loopclip.isRunning()) {
		    loopclip.stop();
		}
	}
	public static void pauseloopSound() {
		clipTime=loopclip.getMicrosecondPosition();
		loopclip.stop();
	}
	public static void unpauseloopSound() {
		loopclip.setMicrosecondPosition(clipTime);
		loopclip.start();
	}
	
	public static void muteloopSound(){
			BooleanControl muteControlloop = (BooleanControl) loopclip.getControl(BooleanControl.Type.MUTE);
		    muteControlloop.setValue(true);
		    muted = true;
	}
	
	public static void unmuteloopSound(){
			BooleanControl muteControlloop = (BooleanControl) loopclip.getControl(BooleanControl.Type.MUTE);
		    muteControlloop.setValue(false);
		    muted = false;
	}
}
