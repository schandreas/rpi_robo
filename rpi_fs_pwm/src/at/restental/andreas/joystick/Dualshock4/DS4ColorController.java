package at.restental.andreas.joystick.Dualshock4;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import at.restental.andreas.joystick.JoystickLED;

public class DS4ColorController implements JoystickLED {
	protected Path red;
	protected Path green;
	protected Path blue;

	/**
	 * Opens the three color channel files
	 * 
	 * @throws Exception
	 *             if one or more of the channel files cannot be opened. Works as a
	 *             detect if the controller is a ds4 controller
	 */
	public DS4ColorController() throws Exception {
		File temp = new File("/sys/class/leds");
		String[] list = temp.list();
		for (int i = 0; i < list.length; i++) {
			try {
				if (list[i].endsWith("red"))
					red = new File("/sys/class/leds/" + list[i] + "/brightness").toPath();
				if (list[i].endsWith("green"))
					green = new File("/sys/class/leds/" + list[i] + "/brightness").toPath();
				if (list[i].endsWith("blue"))
					blue = new File("/sys/class/leds/" + list[i] + "/brightness").toPath();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (red == null || green == null || blue == null)
			throw new Exception();
	}

	/**
	 * Writes the red green and blue values to the corresponding channel files
	 */
	@Override
	public void setColor(int red, int green, int blue) {
		try {
			Files.write(this.red, Integer.toString(red).getBytes());
			Files.write(this.green, Integer.toString(green).getBytes());
			Files.write(this.blue, Integer.toString(blue).getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * NOT IMPLEMENTED. DOES NOTHING
	 */
	@Override
	public void setPattern(int pattern) {

	}

}
