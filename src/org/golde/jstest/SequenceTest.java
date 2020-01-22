package org.golde.jstest;

import java.util.ArrayList;
import java.util.List;

public class SequenceTest extends Sequence {

	public SequenceTest(long length) {

		List<Event> events = new ArrayList<Event>();
		
		//fake beats
		for(int j = 0; j < length; j++) {
			int val = 0;
			if(j % 50 == 0) {
				val = 255;
			}
			events.add(new Event(j, val));
		}
		
		channels.add(new Channel(0, "channel #0", events));
		
		for(int i = 1; i < 6; i++) {
			
			events.clear();
			
			channels.add(new Channel(i, "channel #" + i, events));
			
		}

	}
	
}
