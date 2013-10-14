package com.dssforut.services;

public class HistoryData {

	private String[] data;
	private String[] hours;
	int size;

	public HistoryData(String[] data, String[] hours, int size) {

		this.data = data;
		this.hours = hours;
		this.size = size;

	}

	public String[] getData() {
		return data;
	}

	public String[] getHours() {
		return hours;
	}

	public void setData(String[] data) {
		this.data = data;
	}

	public void setHours(String[] hours) {
		this.hours = hours;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
