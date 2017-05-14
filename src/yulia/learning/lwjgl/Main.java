package yulia.learning.lwjgl;

import org.lwjgl.Version;
import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.opengl.GL11.*;

import yulia.learning.lwjgl.callback.CustomKeyCallback;

import static org.lwjgl.system.MemoryUtil.*;

public class Main implements Runnable {
	
	private boolean running = false;
	private GLFWErrorCallback errorCallback;
	private GLFWKeyCallback keyCallback;
	
	private long window;
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	
	public static void main(String[] args) {
		Main game = new Main();
		game.start();
    }

	@Override
	public void run() {
		init();
		
		while(!glfwWindowShouldClose(window)) {
			update();
			render();
		}
		
		glfwDestroyWindow(window);
		keyCallback.free();
		
		glfwTerminate();
        errorCallback.free();
	}
	
	private void update() {
		glClear(GL_COLOR_BUFFER_BIT);
	}

	private void render() {
		double time = glfwGetTime();
		
		glfwSwapBuffers(window);
		glfwPollEvents();		
	}

	private void init() {
		errorCallback = GLFWErrorCallback.createPrint(System.err);
		glfwSetErrorCallback(errorCallback);
		
		if (!glfwInit()) {
		    throw new IllegalStateException("Unable to initialize GLFW");
		}
		
		System.out.println("LWJGL Version " + Version.getVersion() + " is working.");
		
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);

		window = glfwCreateWindow(WIDTH, HEIGHT, "Learning LWJGL", NULL, NULL);
		if (window == NULL) {
		    glfwTerminate();
		    throw new RuntimeException("Failed to create the GLFW window");
		}
		
		
		/* Center the window on screen */
        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window,
                         (vidMode.width() - 640) / 2,
                         (vidMode.height() - 480) / 2
        );
		
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		 /* Enable vertical synchronization */
        glfwSwapInterval(1);

		keyCallback = new CustomKeyCallback();
		glfwSetKeyCallback(window, keyCallback);
	}

	public void start() {
		running = true;
		Thread game = new Thread(this);
		game.run();
	}
}
