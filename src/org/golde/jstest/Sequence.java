package org.golde.jstest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sequence {

	protected final List<Channel> channels = new ArrayList<Channel>();
	
	public List<Channel> getChannels() {
		return channels;
	}
	
	public Channel getChannelById(int id) {
		for(Channel c : channels) {
			if(c.getId() == id) {
				return c;
			}
		}
		return null;
	}
	
	public Channel getChannelByName(String name) {
		for(Channel c : channels) {
			if(c.getName().equalsIgnoreCase(name)) {
				return c;
			}
		}
		return null;
	}
	
	//Not implemented yet
	public SelectedArea getSelectedArea() {
		return null;
	}
	
	
}
