# LEARNING LWJGL

## Test setup

On Mac OS X you need to add -XstartOnFirstThread as JVM argument to start the application.

You may also need to add -Djava.awt.headless=true as JVM argument to get access to the AWT classes.

	import org.lwjgl.Version;

	public class TestSetup {
	
	    public static void main(String[] args) {
	        System.out.println("LWJGL Version " + Version.getVersion() + " is working.");
	    }
	
	}
	
