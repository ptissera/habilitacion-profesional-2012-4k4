package com.coming.cellprojectmanager.ws;

public interface WsObserver {
	void notifiyPreExecute();
	void notifiyPosExecute(String result);
}
