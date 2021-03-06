package utils.tasks;

import utils.pathfinding.Location;

public class KelpTask extends Task{
	private Location location;
	
	KelpTask(Location location) {
		for(Location testLocation : Location.getBlockLocations()){
			if(location == testLocation){
				this.location = testLocation;
				break;
			}
		}
	}
	/**
	 * 
	 * @return The location that the robot was in when this task was queued.
	 */
	public Location getLocation() {
		return location;
	}
	
}
