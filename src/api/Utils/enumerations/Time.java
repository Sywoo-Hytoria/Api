package fr.hytoria.api.Utils.enumerations;

public enum Time {

	DAY(Integer.valueOf(0)),
	MIDDAY(Integer.valueOf(6000)),
	MIDNIGHT(Integer.valueOf(12000)),
	NIGHT(Integer.valueOf(18000));
	
	private long time;
	
	private Time(Integer value) {
		 this.time = value.intValue();
	}
	
	public long getHour(){
		return this.time;
	}
		
	
	
	
}
