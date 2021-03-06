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
					System.out.println("프로그램이 종료되었습니다.");
					flag = true;
					break;
				default:
					System.out.println("잘못된 입력입니다.");
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
			System.out.println(" 1. 입력 | 2. 검색 | 3. 출력 | 4. 수정 | 5. 삭제 | 6. 기록 | 7. 종료");
			System.out.println("------------------------------------------------------------");
			System.out.print("번호 입력> ");
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
			System.out.println("데이터가 입력되었습니다.");
		} else {
			System.out.println("데이터 입력 중 오류가 발생했습니다.");
		}
		
	}
	
	private static String createId() {
		
		boolean flag = false;
		String id = null;
		
		while(!flag) {
			System.out.print("ID 입력(숫자, 8자리)> ");
			id = scanner.nextLine();
			
			if(!id.matches("\\d{8}")) {
				System.out.println("잘못된 입력입니다.");
				continue;
			}
			// Check whether student_id is duplicated
			boolean isDuplicated = checkDuplication(id);
			if(isDuplicated == true) {
				System.out.println("이미 존재하는 ID입니다.");
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
			System.out.print("이름 입력(한글, 2~15자)> ");
			name = scanner.nextLine();
			
			if(!name.matches("^[가-힣]{2,15}$")) {
				System.out.println("잘못된 입력입니다.");
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
				System.out.print("Reading 점수 입력(숫자, 0~30)> ");
				reading = Integer.parseInt(scanner.nextLine());
				
				if(reading >= 0 && reading <= 30) {
					flag = true;
				} else {
					System.out.println("잘못된 입력입니다.");
					continue;
				}	
			
			} catch (NumberFormatException e) {
				System.out.println("잘못된 입력입니다.");
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
				System.out.print("Listening 점수 입력(숫자, 0~30)> ");
				listening = Integer.parseInt(scanner.nextLine());
				
				if(listening >= 0 && listening <= 30) {
					flag = true;
				} else {
					System.out.println("잘못된 입력입니다.");
					continue;
				}
			
			} catch (NumberFormatException e) {
				System.out.println("잘못된 입력입니다.");
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
			System.out.print("Speaking 점수 입력(숫자, 0~30)> ");
			speaking = Integer.parseInt(scanner.nextLine());
			
			if(speaking >= 0 && speaking <= 30) {
				flag = true;
			} else {
				System.out.println("잘못된 입력입니다.");
				continue;
			}
		
		} catch (NumberFormatException e) {
			System.out.println("잘못된 입력입니다.");
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
			System.out.print("Writing 점수 입력(숫자, 0~30)> ");
			writing = Integer.parseInt(scanner.nextLine());
			
			if(writing >= 0 && writing <= 30) {
				flag = true;
			} else {
				System.out.println("잘못된 입력입니다.");
				continue;
			}
		
		} catch (NumberFormatException e) {
			System.out.println("잘못된 입력입니다.");
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
				System.out.println("1. ID 검색 | 2. 이름 검색 | 3. 검색 종료");
				System.out.println("----------------------------------");
				System.out.print("번호 입력> ");
				selectNumber = scanner.nextLine();
			
			} catch(Exception e) {
				System.out.println("잘못된 입력입니다.");
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
					
					System.out.print("ID 입력(숫자, 8자리)> ");
					columnData = scanner.nextLine();
					
					if(!columnData.matches("\\d{8}")) {
						System.out.println("잘못된 입력입니다.");
						continue;
					}
					
					list = readData(columnNumber, columnData);
					break;
					
				case NAME:
					columnNumber = "2";
					
					System.out.print("이름 입력(한글, 2~15자)> ");
					columnData = scanner.nextLine();
					
					if(!columnData.matches("^[가-힣]{2,15}$")) {
						System.out.println("잘못된 입력입니다.");
						continue;
					}

					list = readData(columnNumber, columnData);
					break;
					
				case QUIT:
					return;
					
				default:
					System.out.println("잘못된 입력입니다.");
					break;
			}
		
		}
	
	}

	private static List<Score> readData(String columnNumber, String columnData) {
		
		List<Score> list = new ArrayList<>();
		list = DBController.readScoreTBL(columnNumber, columnData);
		
		if(list.size() <= 0) {
			System.out.println("데이터가 존재하지 않습니다.");
			return list;
		}
		
		System.out.println("------------------------------------------------------------ ");
		System.out.println("         <          성  적  관  리  시  스  템          >         ");
		System.out.println("------------------------------------------------------------ ");
		System.out.println("ID\t   이름\t   읽기\t 듣기   말하기  쓰기\t   합계\t평균\t등급  ");
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
				System.out.println("1. ID 정렬 | 2. 이름 정렬 | 3. 읽기 점수 정렬 | 4. 듣기 점수 정렬 | 5. 말하기 점수 정렬 | 6. 쓰기 점수 정렬 | 7. 합계/평균/등급 정렬 | 8. 정렬 종료 ");
				System.out.println("------------------------------------------------------------------------------------------------------------------------");
				System.out.print("번호 입력> ");
				selectNumber = scanner.nextLine();
				
				if(!selectNumber.matches("[1-8]")) {
					System.out.println("잘못된 입력입니다.");
					displayListMenu();
				}
			
			} catch(Exception e) {
				System.out.println("잘못된 입력입니다.");
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
		System.out.println("         <          성  적  관  리  시  스  템          >         ");
		System.out.println("------------------------------------------------------------ ");
		System.out.println("ID\t   이름\t   읽기\t 듣기   말하기  쓰기\t   합계\t평균\t등급  ");
		System.out.println("------------------------------------------------------------ ");
		for(Score score : list) {
			System.out.println(score.toString());
		}
		System.out.println("------------------------------------------------------------ ");
			
		if(list.size() <= 0) {
			System.out.println("데이터가 존재하지 않습니다.");
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
				System.out.println("1. 이름 수정 | 2. 읽기 점수 수정 | 3. 듣기 점수 수정 | 4. 말하기 점수 수정 | 5. 쓰기 점수 수정 | 6. 수정 종료");
				System.out.println("----------------------------------------------------------------------------------------");
				System.out.print("번호 입력> ");
				selectNumber = scanner.nextLine();
			
			} catch(Exception e) {
				System.out.println("잘못된 입력입니다.");
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
		
		System.out.print("수정할 ID 입력(숫자, 8자리)> ");
		String id = scanner.nextLine();
		
		if(!id.matches("\\d{8}")) {
			System.out.println("잘못된 입력입니다.");
			return;
		}
		
		List<Score> list = new ArrayList<>();
		list = DBController.readScoreTBL("1", id);
		
		if(list.size() <= 0) {
			System.out.println("존재하지 않는 ID입니다.");
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
						System.out.println("데이터가 수정되었습니다.");
					} else {
						System.out.println("데이터 수정 중 오류가 발생했습니다.");
					}
					
					break;
				
				case READING:
					columnNumber = "2";
					
					int reading = createReading();
					count = DBController.updateScoreTBL(id, columnNumber, String.valueOf(reading));
					
					if(count != 0) {
						System.out.println("데이터가 수정되었습니다.");
					} else {
						System.out.println("데이터 수정 중 오류가 발생했습니다.");
					}
					
					break;
				
				case LISTENING:
					columnNumber = "3";
					
					int listening = createListening();					
					count = DBController.updateScoreTBL(id, columnNumber, String.valueOf(listening));
					
					if(count != 0) {
						System.out.println("데이터가 수정되었습니다.");
					} else {
						System.out.println("데이터 수정 중 오류가 발생했습니다.");
					}
					
					break;
				
				case SPEAKING:
					columnNumber = "4";
					
					int speaking = createSpeaking();
					count = DBController.updateScoreTBL(id, columnNumber, String.valueOf(speaking));
					
					if(count != 0) {
						System.out.println("데이터가 수정되었습니다.");
					} else {
						System.out.println("데이터 수정 중 오류가 발생했습니다.");
					}
					
					break;
					
				case WRITING:
					columnNumber = "5";
					
					int write = createWriting();
					count = DBController.updateScoreTBL(id, columnNumber, String.valueOf(write));
					
					if(count != 0) {
						System.out.println("데이터가 수정되었습니다.");
					} else {
						System.out.println("데이터 수정 중 오류가 발생했습니다.");
					}
					
					break;
				
				case QUIT: 
					return;
					
				default: 
					System.out.println("잘못된 입력입니다."); 
					continue;
			}
			
		}
		
	}
	
	// 5. Delete
	private static void deleteScore() {
	
		System.out.print("삭제할 ID 입력> ");
		String id = scanner.nextLine();
		
		if(!id.matches("\\d{8}")) {
			System.out.println("잘못된 입력입니다.");
			return;
		}
		
		boolean result = DBController.deleteScoreTBL(id);
		
		if(result == true) {
			System.out.println("데이터가 삭제되었습니다.");
		} else {
			System.out.println("존재하지 않는 ID입니다.");
		}
		
	}
	
	// 6. Record
	private static void recordScore() {
		
		List<Record> list = new ArrayList<>();
		list = DBController.recordScoreTBL();
		
		if(list.size() <= 0) {
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		
		System.out.println("---------------------------------------------------------------------------------------------------------------- ");
		System.out.println("                                   <          성  적  관  리  시  스  템          >                                   ");
		System.out.println("---------------------------------------------------------------------------------------------------------------- ");
		System.out.println("ID\t   이름\t   읽기\t 듣기   말하기  쓰기\t   합계\t평균\t등급   내용\t 시간\t\t\t담당자 ");
		System.out.println("---------------------------------------------------------------------------------------------------------------- ");
		for(Record record : list) {
			System.out.println(record.toString());
		}
		System.out.println("---------------------------------------------------------------------------------------------------------------- ");
		
	}

}
