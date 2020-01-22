package org.golde.jstest;

public class Event {

	//should be a unsigned byte technically
	private int value; //0-100
	private final long locationInSong;
	
	private static final int VALUE_MAX = 255;
	private static final int VALUE_MIN = 0;
	
	public Event(long locationInSong) {
		this(locationInSong, VALUE_MAX);
	}
	
	public Event(long locationInSong, int value) {
		this.locationInSong = locationInSong;
		this.setValue(value);
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		if(value >= VALUE_MIN && value <= VALUE_MAX) {
			this.value = value;
		}
		else {
			System.err.println("Value '" + value + "' is not between " + VALUE_MIN + " - " + VALUE_MAX);
		}
	}
	
	public long getLocationInSong() {
		return locationInSong;
	}
	
	public boolean isOn() {
		return value > 0;
	}
	
}
