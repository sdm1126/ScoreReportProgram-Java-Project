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
					System.out.println("���α׷��� ����Ǿ����ϴ�.");
					flag = true;
					break;
				default:
					System.out.println("�߸��� �Է��Դϴ�.");
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
			System.out.println(" 1. �Է� | 2. �˻� | 3. ��� | 4. ���� | 5. ���� | 6. ��� | 7. ����");
			System.out.println("------------------------------------------------------------");
			System.out.print("��ȣ �Է�> ");
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
			System.out.println("�����Ͱ� �ԷµǾ����ϴ�.");
		} else {
			System.out.println("������ �Է� �� ������ �߻��߽��ϴ�.");
		}
		
	}
	
	private static String createId() {
		
		boolean flag = false;
		String id = null;
		
		while(!flag) {
			System.out.print("ID �Է�(����, 8�ڸ�)> ");
			id = scanner.nextLine();
			
			if(!id.matches("\\d{8}")) {
				System.out.println("�߸��� �Է��Դϴ�.");
				continue;
			}
			// Check whether student_id is duplicated
			boolean isDuplicated = checkDuplication(id);
			if(isDuplicated == true) {
				System.out.println("�̹� �����ϴ� ID�Դϴ�.");
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
			System.out.print("�̸� �Է�(�ѱ�, 15�� �̳�)> ");
			name = scanner.nextLine();
			
			if(!name.matches("^[��-�R]*$") || name.length() < 2 || name.length() > 15) {
				System.out.println("�߸��� �Է��Դϴ�.");
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
				System.out.print("Reading ���� �Է�(����, 0~30)> ");
				reading = Integer.parseInt(scanner.nextLine());
				
				if(reading >= 0 && reading <= 30) {
					flag = true;
				} else {
					System.out.println("�߸��� �Է��Դϴ�.");
					continue;
				}	
			
			} catch (NumberFormatException e) {
				System.out.println("�߸��� �Է��Դϴ�.");
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
				System.out.print("Listening ���� �Է�(����, 0~30)> ");
				listening = Integer.parseInt(scanner.nextLine());
				
				if(listening >= 0 && listening <= 30) {
					flag = true;
				} else {
					System.out.println("�߸��� �Է��Դϴ�.");
					continue;
				}
			
			} catch (NumberFormatException e) {
				System.out.println("�߸��� �Է��Դϴ�.");
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
			System.out.print("Speaking ���� �Է�(����, 0~30)> ");
			speaking = Integer.parseInt(scanner.nextLine());
			
			if(speaking >= 0 && speaking <= 30) {
				flag = true;
			} else {
				System.out.println("�߸��� �Է��Դϴ�.");
				continue;
			}
		
		} catch (NumberFormatException e) {
			System.out.println("�߸��� �Է��Դϴ�.");
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
			System.out.print("Writing ���� �Է�(����, 0~30)> ");
			writing = Integer.parseInt(scanner.nextLine());
			
			if(writing >= 0 && writing <= 30) {
				flag = true;
			} else {
				System.out.println("�߸��� �Է��Դϴ�.");
				continue;
			}
		
		} catch (NumberFormatException e) {
			System.out.println("�߸��� �Է��Դϴ�.");
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
				System.out.println("1. ID �˻� | 2. �̸� �˻� | 3. �˻� ����");
				System.out.println("----------------------------------");
				System.out.print("��ȣ �Է�> ");
				selectNumber = scanner.nextLine();
			
			} catch(Exception e) {
				System.out.println("�߸��� �Է��Դϴ�.");
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
					System.out.print("ID �Է�(����, 8�ڸ�)> ");
					columnData = scanner.nextLine();
					
					if(!columnData.matches("\\d{8}")) {
						System.out.println("�߸��� �Է��Դϴ�.");
						continue;
					}
					
					columnNumber = "1";
					
					list = readData(columnNumber, columnData);
					break;
					
				case NAME:
					System.out.print("�̸� �Է�(�ѱ�, 15�� �̳�)> ");
					columnData = scanner.nextLine();
					
					if(!columnData.matches("^[��-�R]*$") || columnData.length() < 2 || columnData.length() > 15) {
						System.out.println("�߸��� �Է��Դϴ�.");
						continue;
					}
					
					columnNumber = "2";

					list = readData(columnNumber, columnData);
					break;
					
				case QUIT:
					return;
					
				default:
					System.out.println("�߸��� �Է��Դϴ�.");
					break;
			}
		
		}
	
	}

	private static List<Score> readData(String columnNumber, String columnData) {
		
		List<Score> list = new ArrayList<>();
		list = DBController.readScoreTBL(columnNumber, columnData);
		
		System.out.println("------------------------------------------------------------ ");
		System.out.println("ID\t   �̸�\t   �б�\t ���   ���ϱ�  ����\t  �հ�\t���\t���  ");
		System.out.println("------------------------------------------------------------ ");
		for(Score score : list) {
			System.out.println(score.toString());
		}
		System.out.println("------------------------------------------------------------ ");
		
		if(list.size() <= 0) {
			System.out.println("�����Ͱ� �������� �ʽ��ϴ�.");
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
				System.out.println("1. ID ���� | 2. �̸� ���� | 3. �б� ���� ���� | 4. ��� ���� ���� | 5. ���ϱ� ���� ���� | 6. ���� ���� ���� | 7. �հ�/���/��� ���� | 8. ���� ���� ");
				System.out.println("------------------------------------------------------------------------------------------------------------------------");
				System.out.print("��ȣ �Է�> ");
				selectNumber = scanner.nextLine();
				
				if(!selectNumber.matches("[1-8]")) {
					System.out.println("�߸��� �Է��Դϴ�.");
					displayListMenu();
				}
			
			} catch(Exception e) {
				System.out.println("�߸��� �Է��Դϴ�.");
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
		System.out.println("ID\t   �̸�\t   �б�\t ���   ���ϱ�  ����\t  �հ�\t���\t���  ");
		System.out.println("------------------------------------------------------------ ");
		for(Score score : list) {
			System.out.println(score.toString());
		}
		System.out.println("------------------------------------------------------------ ");
			
		if(list.size() <= 0) {
			System.out.println("�����Ͱ� �������� �ʽ��ϴ�.");
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
				System.out.println("1. �̸� ���� | 2. �б� ���� ���� | 3. ��� ���� ���� | 4. ���ϱ� ���� ���� | 5. ���� ���� ���� | 6. ���� ����");
				System.out.println("----------------------------------------------------------------------------------------");
				System.out.print("��ȣ �Է�> ");
				selectNumber = scanner.nextLine();
			
			} catch(Exception e) {
				System.out.println("�߸��� �Է��Դϴ�.");
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
		
		System.out.print("������ ID �Է�(����, 8�ڸ�)> ");
		String id = scanner.nextLine();
		
		if(!id.matches("\\d{8}")) {
			System.out.println("�߸��� �Է��Դϴ�.");
			return;
		}
		
		List<Score> list = new ArrayList<>();
		list = DBController.readScoreTBL("1", id);
		
		if(list.size() <= 0) {
			System.out.println("�������� �ʴ� ID�Դϴ�.");
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
						System.out.println("�����Ͱ� �����Ǿ����ϴ�.");
					} else {
						System.out.println("������ ���� �� ������ �߻��߽��ϴ�.");
					}
					
					break;
				
				case READING:
					columnNumber = "2";
					int reading = createReading();
					count = DBController.updateScoreTBL(id, columnNumber, String.valueOf(reading));
					
					if(count != 0) {
						System.out.println("�����Ͱ� �����Ǿ����ϴ�.");
					} else {
						System.out.println("������ ���� �� ������ �߻��߽��ϴ�.");
					}
					
					break;
				
				case LISTENING:
					columnNumber = "3";
					int listening = createListening();					
					count = DBController.updateScoreTBL(id, columnNumber, String.valueOf(listening));
					
					if(count != 0) {
						System.out.println("�����Ͱ� �����Ǿ����ϴ�.");
					} else {
						System.out.println("������ ���� �� ������ �߻��߽��ϴ�.");
					}
					
					break;
				
				case SPEAKING:
					columnNumber = "4";
					int speaking = createSpeaking();
					count = DBController.updateScoreTBL(id, columnNumber, String.valueOf(speaking));
					
					if(count != 0) {
						System.out.println("�����Ͱ� �����Ǿ����ϴ�.");
					} else {
						System.out.println("������ ���� �� ������ �߻��߽��ϴ�.");
					}
					
					break;
					
				case WRITING:
					columnNumber = "5";
					int write = createWriting();
					count = DBController.updateScoreTBL(id, columnNumber, String.valueOf(write));
					
					if(count != 0) {
						System.out.println("�����Ͱ� �����Ǿ����ϴ�.");
					} else {
						System.out.println("������ ���� �� ������ �߻��߽��ϴ�.");
					}
					
					break;
				
				case QUIT: 
					return;
					
				default: 
					System.out.println("�߸��� �Է��Դϴ�."); 
					continue;
			}
			
		}
		
	}
	
	// 5. Delete
	private static void deleteScore() {
	
		System.out.print("������ ID �Է�> ");
		String id = scanner.nextLine();
		
		if(!id.matches("\\d{8}")) {
			System.out.println("�߸��� �Է��Դϴ�.");
		}
		
		boolean result = DBController.deleteScoreTBL(id);
		
		if(result == true) {
			System.out.println("�����Ͱ� �����Ǿ����ϴ�.");
		} else {
			System.out.println("�������� �ʴ� ID�Դϴ�.");
		}
		
	}
	
	// 6. Record
	private static void recordScore() {
		
		List<Record> list = new ArrayList<>();
		list = DBController.recordScoreTBL();
		
		System.out.println("---------------------------------------------------------------------------------------------------------------- ");
		System.out.println("ID\t   �̸�\t   �б�\t ���   ���ϱ�  ����\t  �հ�\t���\t���   ����\t �ð�\t\t\t����� ");
		System.out.println("---------------------------------------------------------------------------------------------------------------- ");
		for(Record record : list) {
			System.out.println(record.toString());
		}
		System.out.println("---------------------------------------------------------------------------------------------------------------- ");
		
		if(list.size() <= 0) {
			System.out.println("�����Ͱ� �������� �ʽ��ϴ�.");
			return;
		}
		
	}

}
