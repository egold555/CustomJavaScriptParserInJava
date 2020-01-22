package org.golde.jstest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class SingleInstance2 {

	private String NO_JAVASCRIPT_MESSAGE = "No JavaScript Engine available.";
	protected ScriptEngine engine = null;

	Sequence seq = new SequenceTest(1000); //20 seconds 50ms / 1000

	public void run() {
		loadJSEngine();
	}

	private void loadJSEngine() {
		Thread currentThread = Thread.currentThread();
		ClassLoader previousClassLoader = currentThread.getContextClassLoader();
		currentThread.setContextClassLoader(getClass().getClassLoader());
		try {
			ScriptEngineManager factory = new ScriptEngineManager();
			this.engine = factory.getEngineByName("JavaScript");
			if (this.engine == null) {
				System.err.println(NO_JAVASCRIPT_MESSAGE);
			} 
			else {
				Invocable inv = (Invocable) this.engine;

				List<File> allScripts = new ArrayList<File>();
				getAllFilesInDir("res", allScripts);
				for(File f : allScripts) {

					if(f.getName().contains(".js")) {
						try {
							this.engine.eval(new InputStreamReader(new FileInputStream(f)));
							inv.invokeFunction("execute", this, engine);
						}
						catch(Exception e) {
							System.err.println("Could not compile " + f.getName() + " @ " + f.getAbsolutePath());
							e.printStackTrace();
						}
					}
					else {
						System.err.println(f.getAbsolutePath() + " is not a script!");
					}

					System.out.println("File found: " + f.getAbsolutePath());
				}


			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		} finally {
			currentThread.setContextClassLoader(previousClassLoader);
		}
	}

	private void getAllFilesInDir(String directoryName, List<File> files) {
		File directory = new File(directoryName);

		// Get all files from a directory.
		File[] fList = directory.listFiles();
		if(fList != null) {
			for (File file : fList) {      
				if (file.isFile()) {
					files.add(file);
				} else if (file.isDirectory()) {
					getAllFilesInDir(file.getAbsolutePath(), files);
				}
			}
		}
	}


	private FileInputStream getResource(String string) {
		try {
			return new FileInputStream("res/" + string);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void log(String msg) {
		System.out.println("[FROM JS] " + msg);
	}

	public Sequence getSequence() {
		return seq;
	}

}
