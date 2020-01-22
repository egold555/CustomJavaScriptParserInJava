package org.golde.jstest;

import java.util.Collections;
import java.util.List;

public class Channel {

	private final int id;
	private final String name;
	
	private final List<Event> events;
	
	public Channel(int id, String name, List<Event> events) {
		this.id = id;
		this.name = name;
		this.events = Collections.unmodifiableList(events);
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Event getEventAt(long time) {
		for(Event e : events) {
			if(e.getLocationInSong() == time) {
				return e;
			}
		}
		
		return null;
	}
	
	//Not implemented
	public List<Event> getEventsBetween(Event one, Event two){
		return null;
	}
	
	public List<Event> getAllEvents() {
		return events;
	}
	
}
