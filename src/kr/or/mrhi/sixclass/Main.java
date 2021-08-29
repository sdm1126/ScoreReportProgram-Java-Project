package kr.or.mrhi.sixclass;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static final Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		final String CREATE = "1", READ = "2", LIST = "3", UPDATE = "4", DELETE = "5", RECORD = "6", QUIT = "7";
		
		boolean flag = false;
		String selectNumber = null;
		
		while(!flag) {
			selectNumber = displayMenu();
			switch(selectNumber) {
				case CREATE: createScore(); break;
				case READ:   readScore();   break;
				case LIST:   listScore();   break;
				case UPDATE: updateScore(); break;
				case DELETE: deleteScore(); break;
				case RECORD: recordScore(); break;
				case QUIT: 
					scanner.close();
					System.out.println("ÇÁ·Î±×·¥ÀÌ Á¾·áµÇ¾ú½À´Ï´Ù.");
					flag = true;
					break;
				default:
					System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
					break;
			}
			
		}

	}
	
	// 0. Menu
	private static String displayMenu() {
		
		boolean flag = false;
		String selectNumber = null;
		
		while(!flag) {			
			System.out.println("------------------------------------------------------------");
			System.out.println(" 1. ÀÔ·Â | 2. °Ë»ö | 3. Ãâ·Â | 4. ¼öÁ¤ | 5. »èÁ¦ | 6. ±â·Ï | 7. Á¾·á");
			System.out.println("------------------------------------------------------------");
			System.out.print("¹øÈ£ ÀÔ·Â> ");
			selectNumber = scanner.nextLine();			
			break;
		}
		
		return selectNumber;
	}

	// 1. Create
	private static void createScore() {
		
		String id = createId();		
		String name = createName();
		int reading = createReading();
		int listening = createListening();
		int speaking = createSpeaking();
		int writing = createWriting();
		
		int total = 0;
		double average = 0.0;
		String grade = null;
		
		Score score = new Score(id, name, reading, listening, speaking, writing, total, average, grade);
		int count = DBController.createScoreTBL(score);
		
		if(count != 0) {
			System.out.println("µ¥ÀÌÅÍ°¡ ÀÔ·ÂµÇ¾ú½À´Ï´Ù.");
		} else {
			System.out.println("µ¥ÀÌÅÍ ÀÔ·Â Áß ¿À·ù°¡ ¹ß»ýÇß½À´Ï´Ù.");
		}
		
	}
	
	private static String createId() {
		
		boolean flag = false;
		String id = null;
		
		while(!flag) {
			System.out.print("ID ÀÔ·Â(¼ýÀÚ, 8ÀÚ¸®)> ");
			id = scanner.nextLine();
			
			if(!id.matches("\\d{8}")) {
				System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
				continue;
			}
			// Check whether student_id is duplicated
			boolean isDuplicated = checkDuplication(id);
			if(isDuplicated == true) {
				System.out.println("ÀÌ¹Ì Á¸ÀçÇÏ´Â IDÀÔ´Ï´Ù.");
				continue;
			}
			
			flag = true;
		}
		
		return id;
	}
	
	private static boolean checkDuplication(String id) {
		
		List<Score> list = new ArrayList<>();
		String columnNumber = "1";
		
		list = DBController.readScoreTBL(columnNumber, id);
		
		if(list.size() >= 1) {
			return true;
		}
		
		return false;		
	}
	
	private static String createName() {
		
		boolean flag = false;
		String name = null;
		
		while(!flag) {
			System.out.print("ÀÌ¸§ ÀÔ·Â(ÇÑ±Û, 2~15ÀÚ)> ");
			name = scanner.nextLine();
			
			if(!name.matches("^[°¡-ÆR]{2,15}$")) {
				System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
				continue;
			}
			
			flag = true;
		}
		
		return name;
	}
	
	private static int createReading() {
		
		boolean flag = false;
		int reading = 0;
		
		while(!flag) {
			try {
				System.out.print("Reading Á¡¼ö ÀÔ·Â(¼ýÀÚ, 0~30)> ");
				reading = Integer.parseInt(scanner.nextLine());
				
				if(reading >= 0 && reading <= 30) {
					flag = true;
				} else {
					System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
					continue;
				}	
			
			} catch (NumberFormatException e) {
				System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
				continue;	
			}
		}
			
		return reading;
	}
	
	private static int createListening() {
		
		boolean flag = false;
		int listening = 0;
		
		while(!flag) {
			try {
				System.out.print("Listening Á¡¼ö ÀÔ·Â(¼ýÀÚ, 0~30)> ");
				listening = Integer.parseInt(scanner.nextLine());
				
				if(listening >= 0 && listening <= 30) {
					flag = true;
				} else {
					System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
					continue;
				}
			
			} catch (NumberFormatException e) {
				System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
				continue;
			}
			
		}
			
		return listening;
	}

	private static int createSpeaking() {
	
	boolean flag = false;
	int speaking = 0;
	
	while(!flag) {
		try {
			System.out.print("Speaking Á¡¼ö ÀÔ·Â(¼ýÀÚ, 0~30)> ");
			speaking = Integer.parseInt(scanner.nextLine());
			
			if(speaking >= 0 && speaking <= 30) {
				flag = true;
			} else {
				System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
				continue;
			}
		
		} catch (NumberFormatException e) {
			System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
			continue;
		}
		
	}
		
	return speaking;
}

	private static int createWriting() {
	
	boolean flag = false;
	int writing = 0;
	
	while(!flag) {
		try {
			System.out.print("Writing Á¡¼ö ÀÔ·Â(¼ýÀÚ, 0~30)> ");
			writing = Integer.parseInt(scanner.nextLine());
			
			if(writing >= 0 && writing <= 30) {
				flag = true;
			} else {
				System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
				continue;
			}
		
		} catch (NumberFormatException e) {
			System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
			continue;
		}
		
	}
		
	return writing;
}
	
	// 2. Read
	private static String displayReadMenu() {
		
		boolean flag = false;
		String selectNumber = null;
		
		while(!flag) {
			try {			
				System.out.println("----------------------------------");
				System.out.println("1. ID °Ë»ö | 2. ÀÌ¸§ °Ë»ö | 3. °Ë»ö Á¾·á");
				System.out.println("----------------------------------");
				System.out.print("¹øÈ£ ÀÔ·Â> ");
				selectNumber = scanner.nextLine();
			
			} catch(Exception e) {
				System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
				continue;
			}
			
			break;
		}
		
		return selectNumber;
	}
	
	private static void readScore() {
		
		final String ID = "1", NAME = "2", QUIT = "3";
		
		List<Score> list = new ArrayList<>();
		boolean flag = false;
		
		String columnNumber = null;
		String columnData = null;
		
		while(!flag) {
			String selectNumber = displayReadMenu();
			
			switch(selectNumber) {
				case ID:
					columnNumber = "1";
					
					System.out.print("ID ÀÔ·Â(¼ýÀÚ, 8ÀÚ¸®)> ");
					columnData = scanner.nextLine();
					
					if(!columnData.matches("\\d{8}")) {
						System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
						continue;
					}
					
					list = readData(columnNumber, columnData);
					break;
					
				case NAME:
					columnNumber = "2";
					
					System.out.print("ÀÌ¸§ ÀÔ·Â(ÇÑ±Û, 2~15ÀÚ)> ");
					columnData = scanner.nextLine();
					
					if(!columnData.matches("^[°¡-ÆR]{2,15}$")) {
						System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
						continue;
					}

					list = readData(columnNumber, columnData);
					break;
					
				case QUIT:
					return;
					
				default:
					System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
					break;
			}
		
		}
	
	}

	private static List<Score> readData(String columnNumber, String columnData) {
		
		List<Score> list = new ArrayList<>();
		list = DBController.readScoreTBL(columnNumber, columnData);
		
		if(list.size() <= 0) {
			System.out.println("µ¥ÀÌÅÍ°¡ Á¸ÀçÇÏÁö ¾Ê½À´Ï´Ù.");
			return list;
		}
		
		System.out.println("------------------------------------------------------------ ");
		System.out.println("         <          ¼º  Àû  °ü  ¸®  ½Ã  ½º  ÅÛ          >         ");
		System.out.println("------------------------------------------------------------ ");
		System.out.println("ID\t   ÀÌ¸§\t   ÀÐ±â\t µè±â   ¸»ÇÏ±â  ¾²±â\t   ÇÕ°è\tÆò±Õ\tµî±Þ  ");
		System.out.println("------------------------------------------------------------ ");
		for(Score score : list) {
			System.out.println(score.toString());
		}
		System.out.println("------------------------------------------------------------ ");

		return list;
	}
	
	// 3. List
	private static String displayListMenu() {
		
		boolean flag = false;
		String selectNumber = null;
		
		while(!flag) {
			try {			
				System.out.println("------------------------------------------------------------------------------------------------------------------------");
				System.out.println("1. ID Á¤·Ä | 2. ÀÌ¸§ Á¤·Ä | 3. ÀÐ±â Á¡¼ö Á¤·Ä | 4. µè±â Á¡¼ö Á¤·Ä | 5. ¸»ÇÏ±â Á¡¼ö Á¤·Ä | 6. ¾²±â Á¡¼ö Á¤·Ä | 7. ÇÕ°è/Æò±Õ/µî±Þ Á¤·Ä | 8. Á¤·Ä Á¾·á ");
				System.out.println("------------------------------------------------------------------------------------------------------------------------");
				System.out.print("¹øÈ£ ÀÔ·Â> ");
				selectNumber = scanner.nextLine();
				
				if(!selectNumber.matches("[1-8]")) {
					System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
					displayListMenu();
				}
			
			} catch(Exception e) {
				System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
				continue;	
			}
			
			break;
		}
		
		return selectNumber;
	}
	
	private static void listScore() {
		
		final String ID = "1", NAME = "2", READING = "3", LISTENING = "4", SPEAKING = "5", WRITING = "6", TOTAL_AVERAGE_GRADE = "7", QUIT = "8";
		
		List<Score> list = new ArrayList<>();
		boolean flag = false;

		String columnNumber = null;
		
		while(!flag) {
			String selectNumber = displayListMenu();
			
			switch(selectNumber) {
				case ID:                  columnNumber = "1"; break;
				case NAME:                columnNumber = "2"; break;					
				case READING:             columnNumber = "3"; break;					
				case LISTENING:           columnNumber = "4"; break;					
				case SPEAKING:            columnNumber = "5"; break;					
				case WRITING:             columnNumber = "6"; break;					
				case TOTAL_AVERAGE_GRADE: columnNumber = "7"; break;									
				case QUIT:                     				  return;
				default:									  break;
			}
			
			list = DBController.listScoreTBL(columnNumber);
			flag = true;
		}
		
		System.out.println("------------------------------------------------------------ ");
		System.out.println("         <          ¼º  Àû  °ü  ¸®  ½Ã  ½º  ÅÛ          >         ");
		System.out.println("------------------------------------------------------------ ");
		System.out.println("ID\t   ÀÌ¸§\t   ÀÐ±â\t µè±â   ¸»ÇÏ±â  ¾²±â\t   ÇÕ°è\tÆò±Õ\tµî±Þ  ");
		System.out.println("------------------------------------------------------------ ");
		for(Score score : list) {
			System.out.println(score.toString());
		}
		System.out.println("------------------------------------------------------------ ");
			
		if(list.size() <= 0) {
			System.out.println("µ¥ÀÌÅÍ°¡ Á¸ÀçÇÏÁö ¾Ê½À´Ï´Ù.");
			return;
		}
		
		}
	
	// 4. Update
	private static String displayUpdateMenu()  {
		
		boolean flag = false;
		String selectNumber = null;
		
		while(!flag) {
			try {			
				System.out.println("----------------------------------------------------------------------------------------");
				System.out.println("1. ÀÌ¸§ ¼öÁ¤ | 2. ÀÐ±â Á¡¼ö ¼öÁ¤ | 3. µè±â Á¡¼ö ¼öÁ¤ | 4. ¸»ÇÏ±â Á¡¼ö ¼öÁ¤ | 5. ¾²±â Á¡¼ö ¼öÁ¤ | 6. ¼öÁ¤ Á¾·á");
				System.out.println("----------------------------------------------------------------------------------------");
				System.out.print("¹øÈ£ ÀÔ·Â> ");
				selectNumber = scanner.nextLine();
			
			} catch(Exception e) {
				System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
				continue;	
			}
			
			break;
		}
		
		return selectNumber;
	}
	
	private static void updateScore() {
		
		final String NAME = "1", READING = "2", LISTENING = "3", SPEAKING = "4", WRITING = "5", QUIT = "6";
		
		String columnNumber = null;
		int count = 0;
		
		System.out.print("¼öÁ¤ÇÒ ID ÀÔ·Â(¼ýÀÚ, 8ÀÚ¸®)> ");
		String id = scanner.nextLine();
		
		if(!id.matches("\\d{8}")) {
			System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
			return;
		}
		
		List<Score> list = new ArrayList<>();
		list = DBController.readScoreTBL("1", id);
		
		if(list.size() <= 0) {
			System.out.println("Á¸ÀçÇÏÁö ¾Ê´Â IDÀÔ´Ï´Ù.");
			return;
		}
		
		boolean flag = false;
		while(!flag) {
			String selectNumber = displayUpdateMenu();
			
			switch(selectNumber) {
				case NAME:
					columnNumber = "1";
					
					String name = createName();
					count = DBController.updateScoreTBL(id, columnNumber, name);
					
					if(count != 0) {
						System.out.println("µ¥ÀÌÅÍ°¡ ¼öÁ¤µÇ¾ú½À´Ï´Ù.");
					} else {
						System.out.println("µ¥ÀÌÅÍ ¼öÁ¤ Áß ¿À·ù°¡ ¹ß»ýÇß½À´Ï´Ù.");
					}
					
					break;
				
				case READING:
					columnNumber = "2";
					
					int reading = createReading();
					count = DBController.updateScoreTBL(id, columnNumber, String.valueOf(reading));
					
					if(count != 0) {
						System.out.println("µ¥ÀÌÅÍ°¡ ¼öÁ¤µÇ¾ú½À´Ï´Ù.");
					} else {
						System.out.println("µ¥ÀÌÅÍ ¼öÁ¤ Áß ¿À·ù°¡ ¹ß»ýÇß½À´Ï´Ù.");
					}
					
					break;
				
				case LISTENING:
					columnNumber = "3";
					
					int listening = createListening();					
					count = DBController.updateScoreTBL(id, columnNumber, String.valueOf(listening));
					
					if(count != 0) {
						System.out.println("µ¥ÀÌÅÍ°¡ ¼öÁ¤µÇ¾ú½À´Ï´Ù.");
					} else {
						System.out.println("µ¥ÀÌÅÍ ¼öÁ¤ Áß ¿À·ù°¡ ¹ß»ýÇß½À´Ï´Ù.");
					}
					
					break;
				
				case SPEAKING:
					columnNumber = "4";
					
					int speaking = createSpeaking();
					count = DBController.updateScoreTBL(id, columnNumber, String.valueOf(speaking));
					
					if(count != 0) {
						System.out.println("µ¥ÀÌÅÍ°¡ ¼öÁ¤µÇ¾ú½À´Ï´Ù.");
					} else {
						System.out.println("µ¥ÀÌÅÍ ¼öÁ¤ Áß ¿À·ù°¡ ¹ß»ýÇß½À´Ï´Ù.");
					}
					
					break;
					
				case WRITING:
					columnNumber = "5";
					
					int write = createWriting();
					count = DBController.updateScoreTBL(id, columnNumber, String.valueOf(write));
					
					if(count != 0) {
						System.out.println("µ¥ÀÌÅÍ°¡ ¼öÁ¤µÇ¾ú½À´Ï´Ù.");
					} else {
						System.out.println("µ¥ÀÌÅÍ ¼öÁ¤ Áß ¿À·ù°¡ ¹ß»ýÇß½À´Ï´Ù.");
					}
					
					break;
				
				case QUIT: 
					return;
					
				default: 
					System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù."); 
					continue;
			}
			
		}
		
	}
	
	// 5. Delete
	private static void deleteScore() {
	
		System.out.print("»èÁ¦ÇÒ ID ÀÔ·Â> ");
		String id = scanner.nextLine();
		
		if(!id.matches("\\d{8}")) {
			System.out.println("Àß¸øµÈ ÀÔ·ÂÀÔ´Ï´Ù.");
			return;
		}
		
		boolean result = DBController.deleteScoreTBL(id);
		
		if(result == true) {
			System.out.println("µ¥ÀÌÅÍ°¡ »èÁ¦µÇ¾ú½À´Ï´Ù.");
		} else {
			System.out.println("Á¸ÀçÇÏÁö ¾Ê´Â IDÀÔ´Ï´Ù.");
		}
		
	}
	
	// 6. Record
	private static void recordScore() {
		
		List<Record> list = new ArrayList<>();
		list = DBController.recordScoreTBL();
		
		if(list.size() <= 0) {
			System.out.println("µ¥ÀÌÅÍ°¡ Á¸ÀçÇÏÁö ¾Ê½À´Ï´Ù.");
			return;
		}
		
		System.out.println("---------------------------------------------------------------------------------------------------------------- ");
		System.out.println("                                   <          ¼º  Àû  °ü  ¸®  ½Ã  ½º  ÅÛ          >                                   ");
		System.out.println("---------------------------------------------------------------------------------------------------------------- ");
		System.out.println("ID\t   ÀÌ¸§\t   ÀÐ±â\t µè±â   ¸»ÇÏ±â  ¾²±â\t   ÇÕ°è\tÆò±Õ\tµî±Þ   ³»¿ë\t ½Ã°£\t\t\t´ã´çÀÚ ");
		System.out.println("---------------------------------------------------------------------------------------------------------------- ");
		for(Record record : list) {
			System.out.println(record.toString());
		}
		System.out.println("---------------------------------------------------------------------------------------------------------------- ");
		
	}

}
