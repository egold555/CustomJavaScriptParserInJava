package org.golde.jstest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class SingleInstance {

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
                this.engine.eval(new InputStreamReader(this.getResource("boot.js")));
                inv.invokeFunction("execute", this, engine);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } finally {
            currentThread.setContextClassLoader(previousClassLoader);
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
