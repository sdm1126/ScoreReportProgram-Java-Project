package kr.or.mrhi.sixclass;

import java.util.Objects;

public class Score {

	private String id;
	private String name;
	private int reading;
	private int listening;
	private int speaking;
	private int writing;
	private int total;
	private double average;
	private String grade;
	
	public Score(String id, String name, int reading, int listening, int speaking, int writing, 
			     int total, double average, String grade) {	
		super();
		this.id = id;
		this.name = name;
		this.reading = reading;
		this.listening = listening;
		this.speaking = speaking;
		this.writing = writing;
		this.total = total;
		this.average = average;
		this.grade = grade;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getReading() {
		return reading;
	}

	public int getListening() {
		return listening;
	}

	public int getSpeaking() {
		return speaking;
	}

	public int getWriting() {
		return writing;
	}
	
	public int getTotal() {
		return total;
	}

	public double getAverage() {
		return average;
	}

	public String getGrade() {
		return grade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Score) {
			Score score = (Score) obj;
			return this.id.equals(score.getId());
		}
		
		return false;
	}

	@Override
	public String toString() {
		String strReading = String.format("%3d", reading);
		String strListening = String.format("%3d", listening);
		String strSpeaking = String.format("%3d", speaking);
		String strWriting = String.format("%3d", writing);
		String strTotal = String.format("%3d", total);
		String strAverage = String.format("%4.1f", average);
		String strGrade = String.format("%s", grade);
		
		return id + "   " + name + "   " + strReading + "   " + strListening + "   " + strSpeaking + "   " + strWriting + "   " + 
	           strTotal + "   " + strAverage + " \t" + strGrade;
	}
	
}
