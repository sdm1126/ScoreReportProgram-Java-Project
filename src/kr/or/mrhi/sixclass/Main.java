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
					System.out.println("«¡∑Œ±◊∑•¿Ã ¡æ∑·µ«æ˙Ω¿¥œ¥Ÿ.");
					flag = true;
					break;
				default:
					System.out.println("¿ﬂ∏¯µ» ¿‘∑¬¿‘¥œ¥Ÿ.");
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
			System.out.println(" 1. ¿‘∑¬ | 2. ∞Àªˆ | 3. √‚∑¬ | 4. ºˆ¡§ | 5. ªË¡¶ | 6. ±‚∑œ | 7. ¡æ∑·");
			System.out.println("------------------------------------------------------------");
			System.out.print("π¯»£ ¿‘∑¬> ");
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
			System.out.println("µ•¿Ã≈Õ∞° ¿‘∑¬µ«æ˙Ω¿¥œ¥Ÿ.");
		} else {
			System.out.println("µ•¿Ã≈Õ ¿‘∑¬ ¡ﬂ ø¿∑˘∞° πﬂª˝«ﬂΩ¿¥œ¥Ÿ.");
		}
		
	}
	
	private static String createId() {
		
		boolean flag = false;
		String id = null;
		
		while(!flag) {
			System.out.print("ID ¿‘∑¬(º˝¿⁄, 8¿⁄∏Æ)> ");
			id = scanner.nextLine();
			
			if(!id.matches("\\d{8}")) {
				System.out.println("¿ﬂ∏¯µ» ¿‘∑¬¿‘¥œ¥Ÿ.");
				continue;
			}
			// Check whether student_id is duplicated
			boolean isDuplicated = checkDuplication(id);
			if(isDuplicated == true) {
				System.out.println("¿ÃπÃ ¡∏¿Á«œ¥¬ ID¿‘¥œ¥Ÿ.");
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
			System.out.print("¿Ã∏ß ¿‘∑¬(«—±€, 15¿⁄ ¿Ã≥ª)> ");
			name = scanner.nextLine();
			
			if(!name.matches("^[∞°-∆R]*$") || name.length() < 2 || name.length() > 15) {
				System.out.println("¿ﬂ∏¯µ» ¿‘∑¬¿‘¥œ¥Ÿ.");
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
				System.out.print("Reading ¡°ºˆ ¿‘∑¬(º˝¿⁄, 0~30)> ");
				reading = Integer.parseInt(scanner.nextLine());
				
				if(reading >= 0 && reading <= 30) {
					flag = true;
				} else {
					System.out.println("¿ﬂ∏¯µ» ¿‘∑¬¿‘¥œ¥Ÿ.");
					continue;
				}	
			
			} catch (NumberFormatException e) {
				System.out.println("¿ﬂ∏¯µ» ¿‘∑¬¿‘¥œ¥Ÿ.");
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
				System.out.print("Listening ¡°ºˆ ¿‘∑¬(º˝¿⁄, 0~30)> ");
				listening = Integer.parseInt(scanner.nextLine());
				
				if(listening >= 0 && listening <= 30) {
					flag = true;
				} else {
					System.out.println("¿ﬂ∏¯µ» ¿‘∑¬¿‘¥œ¥Ÿ.");
					continue;
				}
			
			} catch (NumberFormatException e) {
				System.out.println("¿ﬂ∏¯µ» ¿‘∑¬¿‘¥œ¥Ÿ.");
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
			System.out.print("Speaking ¡°ºˆ ¿‘∑¬(º˝¿⁄, 0~30)> ");
			speaking = Integer.parseInt(scanner.nextLine());
			
			if(speaking >= 0 && speaking <= 30) {
				flag = true;
			} else {
				System.out.println("¿ﬂ∏¯µ» ¿‘∑¬¿‘¥œ¥Ÿ.");
				continue;
			}
		
		} catch (NumberFormatException e) {
			System.out.println("¿ﬂ∏¯µ» ¿‘∑¬¿‘¥œ¥Ÿ.");
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
			System.out.print("Writing ¡°ºˆ ¿‘∑¬(º˝¿⁄, 0~30)> ");
			writing = Integer.parseInt(scanner.nextLine());
			
			if(writing >= 0 && writing <= 30) {
				flag = true;
			} else {
				System.out.println("¿ﬂ∏¯µ» ¿‘∑¬¿‘¥œ¥Ÿ.");
				continue;
			}
		
		} catch (NumberFormatException e) {
			System.out.println("¿ﬂ∏¯µ» ¿‘∑¬¿‘¥œ¥Ÿ.");
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
				System.out.println("1. ID ∞Àªˆ | 2. ¿Ã∏ß ∞Àªˆ | 3. ∞Àªˆ ¡æ∑·");
				System.out.println("----------------------------------");
				System.out.print("π¯»£ ¿‘∑¬> ");
				selectNumber = scanner.nextLine();
			
			} catch(Exception e) {
				System.out.println("¿ﬂ∏¯µ» ¿‘∑¬¿‘¥œ¥Ÿ.");
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
					System.out.print("ID ¿‘∑¬(º˝¿⁄, 8¿⁄∏Æ)> ");
					columnData = scanner.nextLine();
					
					if(!columnData.matches("\\d{8}")) {
						System.out.println("¿ﬂ∏¯µ» ¿‘∑¬¿‘¥œ¥Ÿ.");
						continue;
					}
					
					columnNumber = "1";
					
					list = readData(columnNumber, columnData);
					break;
					
				case NAME:
					System.out.print("¿Ã∏ß ¿‘∑¬(«—±€, 15¿⁄ ¿Ã≥ª)> ");
					columnData = scanner.nextLine();
					
					if(!columnData.matches("^[∞°-∆R]*$") || columnData.length() < 2 || columnData.length() > 15) {
						System.out.println("¿ﬂ∏¯µ» ¿‘∑¬¿‘¥œ¥Ÿ.");
						continue;
					}
					
					columnNumber = "2";

					list = readData(columnNumber, columnData);
					break;
					
				case QUIT:
					return;
					
				default:
					System.out.println("¿ﬂ∏¯µ» ¿‘∑¬¿‘¥œ¥Ÿ.");
					break;
			}
		
		}
	
	}

	private static List<Score> readData(String columnNumber, String columnData) {
		
		List<Score> list = new ArrayList<>();
		list = DBController.readScoreTBL(columnNumber, columnData);
		
		System.out.println("------------------------------------------------------------ ");
		System.out.println("ID\t   ¿Ã∏ß\t   ¿–±‚\t µË±‚   ∏ª«œ±‚  æ≤±‚\t  «’∞Ë\t∆Ú±’\tµÓ±ﬁ  ");
		System.out.println("------------------------------------------------------------ ");
		for(Score score : list) {
			System.out.println(score.toString());
		}
		System.out.println("------------------------------------------------------------ ");
		
		if(list.size() <= 0) {
			System.out.println("µ•¿Ã≈Õ∞° ¡∏¿Á«œ¡ˆ æ Ω¿¥œ¥Ÿ.");
		}

		return list;
	}
	
	// 3. List
	private static String displayListMenu() {
		
		boolean flag = false;
		String selectNumber = null;
		
		while(!flag) {
			try {			
				System.out.println("------------------------------------------------------------------------------------------------------------------------");
				System.out.println("1. ID ¡§∑ƒ | 2. ¿Ã∏ß ¡§∑ƒ | 3. ¿–±‚ ¡°ºˆ ¡§∑ƒ | 4. µË±‚ ¡°ºˆ ¡§∑ƒ | 5. ∏ª«œ±‚ ¡°ºˆ ¡§∑ƒ | 6. æ≤±‚ ¡°ºˆ ¡§∑ƒ | 7. «’∞Ë/∆Ú±’/µÓ±ﬁ ¡§∑ƒ | 8. ¡§∑ƒ ¡æ∑· ");
				System.out.println("------------------------------------------------------------------------------------------------------------------------");
				System.out.print("π¯»£ ¿‘∑¬> ");
				selectNumber = scanner.nextLine();
				
				if(!selectNumber.matches("[1-8]")) {
					System.out.println("¿ﬂ∏¯µ» ¿‘∑¬¿‘¥œ¥Ÿ.");
					displayListMenu();
				}
			
			} catch(Exception e) {
				System.out.println("¿ﬂ∏¯µ» ¿‘∑¬¿‘¥œ¥Ÿ.");
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
		System.out.println("ID\t   ¿Ã∏ß\t   ¿–±‚\t µË±‚   ∏ª«œ±‚  æ≤±‚\t  «’∞Ë\t∆Ú±’\tµÓ±ﬁ  ");
		System.out.println("------------------------------------------------------------ ");
		for(Score score : list) {
			System.out.println(score.toString());
		}
		System.out.println("------------------------------------------------------------ ");
			
		if(list.size() <= 0) {
			System.out.println("µ•¿Ã≈Õ∞° ¡∏¿Á«œ¡ˆ æ Ω¿¥œ¥Ÿ.");
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
				System.out.println("1. ¿Ã∏ß ºˆ¡§ | 2. ¿–±‚ ¡°ºˆ ºˆ¡§ | 3. µË±‚ ¡°ºˆ ºˆ¡§ | 4. ∏ª«œ±‚ ¡°ºˆ ºˆ¡§ | 5. æ≤±‚ ¡°ºˆ ºˆ¡§ | 6. ºˆ¡§ ¡æ∑·");
				System.out.println("----------------------------------------------------------------------------------------");
				System.out.print("π¯»£ ¿‘∑¬> ");
				selectNumber = scanner.nextLine();
			
			} catch(Exception e) {
				System.out.println("¿ﬂ∏¯µ» ¿‘∑¬¿‘¥œ¥Ÿ.");
				continue;	
			}
			
			break;
		}
		
		return selectNumber;
	}
	
	private static void updateScore() {
		
		final String NAME = "1", READING = "2", LISTENING = "3", SPEAKING = "4", WRITING = "5", QUIT = "6";
		
		String columnNumber = null;
		String columnData = null;
		int count = 0;
		
		System.out.print("ºˆ¡§«“ ID ¿‘∑¬(º˝¿⁄, 8¿⁄∏Æ)> ");
		String id = scanner.nextLine();
		
		if(!id.matches("\\d{8}")) {
			System.out.println("¿ﬂ∏¯µ» ¿‘∑¬¿‘¥œ¥Ÿ.");
			return;
		}
		
		List<Score> list = new ArrayList<>();
		list = DBController.readScoreTBL("1", id);
		
		if(list.size() <= 0) {
			System.out.println("¡∏¿Á«œ¡ˆ æ ¥¬ ID¿‘¥œ¥Ÿ.");
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
						System.out.println("µ•¿Ã≈Õ∞° ºˆ¡§µ«æ˙Ω¿¥œ¥Ÿ.");
					} else {
						System.out.println("µ•¿Ã≈Õ ºˆ¡§ ¡ﬂ ø¿∑˘∞° πﬂª˝«ﬂΩ¿¥œ¥Ÿ.");
					}
					
					break;
				
				case READING:
					columnNumber = "2";
					int reading = createReading();
					count = DBController.updateScoreTBL(id, columnNumber, String.valueOf(reading));
					
					if(count != 0) {
						System.out.println("µ•¿Ã≈Õ∞° ºˆ¡§µ«æ˙Ω¿¥œ¥Ÿ.");
					} else {
						System.out.println("µ•¿Ã≈Õ ºˆ¡§ ¡ﬂ ø¿∑˘∞° πﬂª˝«ﬂΩ¿¥œ¥Ÿ.");
					}
					
					break;
				
				case LISTENING:
					columnNumber = "3";
					int listening = createListening();					
					count = DBController.updateScoreTBL(id, columnNumber, String.valueOf(listening));
					
					if(count != 0) {
						System.out.println("µ•¿Ã≈Õ∞° ºˆ¡§µ«æ˙Ω¿¥œ¥Ÿ.");
					} else {
						System.out.println("µ•¿Ã≈Õ ºˆ¡§ ¡ﬂ ø¿∑˘∞° πﬂª˝«ﬂΩ¿¥œ¥Ÿ.");
					}
					
					break;
				
				case SPEAKING:
					columnNumber = "4";
					int speaking = createSpeaking();
					count = DBController.updateScoreTBL(id, columnNumber, String.valueOf(speaking));
					
					if(count != 0) {
						System.out.println("µ•¿Ã≈Õ∞° ºˆ¡§µ«æ˙Ω¿¥œ¥Ÿ.");
					} else {
						System.out.println("µ•¿Ã≈Õ ºˆ¡§ ¡ﬂ ø¿∑˘∞° πﬂª˝«ﬂΩ¿¥œ¥Ÿ.");
					}
					
					break;
					
				case WRITING:
					columnNumber = "5";
					int write = createWriting();
					count = DBController.updateScoreTBL(id, columnNumber, String.valueOf(write));
					
					if(count != 0) {
						System.out.println("µ•¿Ã≈Õ∞° ºˆ¡§µ«æ˙Ω¿¥œ¥Ÿ.");
					} else {
						System.out.println("µ•¿Ã≈Õ ºˆ¡§ ¡ﬂ ø¿∑˘∞° πﬂª˝«ﬂΩ¿¥œ¥Ÿ.");
					}
					
					break;
				
				case QUIT: 
					return;
					
				default: 
					System.out.println("¿ﬂ∏¯µ» ¿‘∑¬¿‘¥œ¥Ÿ."); 
					continue;
			}
			
		}
		
	}
	
	// 5. Delete
	private static void deleteScore() {
	
		System.out.print("ªË¡¶«“ ID ¿‘∑¬> ");
		String id = scanner.nextLine();
		
		if(!id.matches("\\d{8}")) {
			System.out.println("¿ﬂ∏¯µ» ¿‘∑¬¿‘¥œ¥Ÿ.");
		}
		
		boolean result = DBController.deleteScoreTBL(id);
		
		if(result == true) {
			System.out.println("µ•¿Ã≈Õ∞° ªË¡¶µ«æ˙Ω¿¥œ¥Ÿ.");
		} else {
			System.out.println("¡∏¿Á«œ¡ˆ æ ¥¬ ID¿‘¥œ¥Ÿ.");
		}
		
	}
	
	// 6. Record
	private static void recordScore() {
		
		List<Record> list = new ArrayList<>();
		list = DBController.recordScoreTBL();
		
		System.out.println("---------------------------------------------------------------------------------------------------------------- ");
		System.out.println("ID\t   ¿Ã∏ß\t   ¿–±‚\t µË±‚   ∏ª«œ±‚  æ≤±‚\t  «’∞Ë\t∆Ú±’\tµÓ±ﬁ   ≥ªøÎ\t Ω√∞£\t\t\t¥„¥Á¿⁄ ");
		System.out.println("---------------------------------------------------------------------------------------------------------------- ");
		for(Record record : list) {
			System.out.println(record.toString());
		}
		System.out.println("---------------------------------------------------------------------------------------------------------------- ");
		
		if(list.size() <= 0) {
			System.out.println("µ•¿Ã≈Õ∞° ¡∏¿Á«œ¡ˆ æ Ω¿¥œ¥Ÿ.");
			return;
		}
		
	}

}
