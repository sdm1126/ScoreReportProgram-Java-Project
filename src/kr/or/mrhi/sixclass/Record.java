package kr.or.mrhi.sixclass;

public class Record {

	private String id;
	private String name;
	private int reading;
	private int listening;
	private int speaking;
	private int writing;
	private int total;
	private double average;
	private String grade;
	private String modifyType;
	private String modifyTime;
	private String modifyUser;
	
	public Record(String id, String name, int reading, int listening, int speaking, int writing, 
			      int total, double average, String grade, String modifyType, String modifyTime, String modifyUser) {
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
		this.modifyType = modifyType;
		this.modifyTime = modifyTime;
		this.modifyUser = modifyUser;
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
		String strModifyType = String.format("%-4s", modifyType);
		String strModifyTime = String.format("%20s", modifyTime);
		
		return id + "   " + name + "   " + strReading + "   " + strListening + "   " + strSpeaking + "   " + strWriting + "   " + 
		       strTotal + "   " + strAverage + " \t" + strGrade + "     " + strModifyType + "\t" + strModifyTime + "    " + modifyUser;
	}
	
}
