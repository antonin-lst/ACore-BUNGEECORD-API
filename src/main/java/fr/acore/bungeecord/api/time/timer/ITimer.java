package fr.acore.bungeecord.api.time.timer;


import fr.acore.bungeecord.api.time.TimeUtils;

public interface ITimer extends TimeUtils {
	
	public long getCurrent();
	public void setCurrent(long current);
	public long getDelay();
	public void setDelay(long delay);
	public long getRemainingTime();
	public String getFromatedRemainingTime();
	public boolean isFinish();

}
