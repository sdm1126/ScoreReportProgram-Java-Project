package kr.or.mrhi.sixclass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBController {

	// 1. Create(using Stored procedure from MySQL)
	public static int createScoreTBL(Score score) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = 0;
		
		String createQuery = "CALL procedure_create_scoreTBL(?, ?, ?, ?, ?, ?)";
		
		try {
			connection = DBUtility.getConnection();
			preparedStatement = connection.prepareStatement(createQuery);
			preparedStatement.setString(1, score.getId());
			preparedStatement.setString(2, score.getName());
			preparedStatement.setInt(3, score.getReading());
			preparedStatement.setInt(4, score.getListening());
			preparedStatement.setInt(5, score.getSpeaking());
			preparedStatement.setInt(6, score.getWriting());
			count = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}

				if(connection != null && !connection.isClosed()) {
					connection.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				
			}

		}
		
		return count;
	}

	// 2. Read
	public static List<Score> readScoreTBL(String columnNumber, String columnData) {
		
		final String ID = "1", NAME = "2"; 
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		List<Score> list = new ArrayList<>();
		String readQuery = "SELECT * FROM scoreTBL WHERE";
		
		switch(columnNumber) {
			case ID:   readQuery = readQuery + " id LIKE ?";   break;
			case NAME: readQuery = readQuery + " name LIKE ?"; break;
		}
		
		try {
			connection = DBUtility.getConnection();
			preparedStatement = connection.prepareStatement(readQuery);
			columnData = "%" + columnData + "%";
			preparedStatement.setString(1, columnData);
			resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.isBeforeFirst()) {
				return list;
			}
			
			while(resultSet.next()) {
				String id = resultSet.getString(1);
				String name = resultSet.getString(2);
				int reading = resultSet.getInt(3);
				int listening = resultSet.getInt(4);
				int speaking = resultSet.getInt(5);
				int writing = resultSet.getInt(6);
				int total = resultSet.getInt(7);
				double average = resultSet.getDouble(8);
				String grade = resultSet.getString(9);
				list.add(new Score(id, name, reading, listening, speaking, writing, total, average, grade));
			}
			
		} catch (Exception e) {
	         e.printStackTrace();
			
		} finally {
			try {
	            if(preparedStatement != null && !preparedStatement.isClosed()) {
	               preparedStatement.close();
	            }
	            
	            if(connection != null && !connection.isClosed()) {
	               connection.close();
	            }
	         
	         } catch (SQLException e) {
	        	 e.printStackTrace();
	        	 
	         }
		}

		return list;
	}

	// 3. List
	public static List<Score> listScoreTBL(String columnNumber) {
		
		final String ID = "1", NAME = "2", READING = "3", LISTENING = "4", SPEAKING = "5", WRITING = "6", TOTAL_AVERAGE_GRADE = "7";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		List<Score> list = new ArrayList<>();
		String listQuery = "SELECT * FROM scoreTBL ORDER BY";
		
		switch(columnNumber) {
			case ID:                  listQuery = listQuery + " id";             break;
			case NAME:                listQuery = listQuery + " name";           break;
			case READING:             listQuery = listQuery + " reading DESC";   break;
			case LISTENING:           listQuery = listQuery + " listening DESC"; break;
			case SPEAKING:            listQuery = listQuery + " speaking DESC";  break;
			case WRITING:             listQuery = listQuery + " writing DESC";   break;
			case TOTAL_AVERAGE_GRADE: listQuery = listQuery + " total DESC";     break;
		}
		
		try {
			connection = DBUtility.getConnection();			
			preparedStatement = connection.prepareStatement(listQuery);
			resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.isBeforeFirst()) {
				return list;
			}
			
			while(resultSet.next()) {
				String id = resultSet.getString(1);
				String name = resultSet.getString(2);
				int reading = resultSet.getInt(3);
				int listening = resultSet.getInt(4);
				int speaking = resultSet.getInt(5);
				int writing = resultSet.getInt(6);
				int total = resultSet.getInt(7);
				double average = resultSet.getDouble(8);
				String grade = resultSet.getString(9);
				list.add(new Score(id, name, reading, listening, speaking, writing, total, average, grade));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		
		} finally {
			try {
				if(preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				
				if(connection != null && !connection.isClosed()) {
					connection.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
			
		}
		
		return list;
	}
	
	// 4. Update(using Stored procedure from MySQL)
	public static int updateScoreTBL(String id, String columnNumber, String columnData) {
		
		final String NAME = "1", READING = "2", LISTENING = "3", SPEAKING = "4", WRITING = "5";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = 0;
		
		String updateQuery = "CALL procedure_update_scoreTBL(?, ?, ?)";
			
		try {
			connection = DBUtility.getConnection();			
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, columnNumber);
			
			switch(columnNumber) {
				case NAME:
					preparedStatement.setString(3, columnData);
					break;
				case READING:
				case LISTENING:
				case SPEAKING:
				case WRITING:
					preparedStatement.setInt(3, Integer.parseInt(columnData));
					break;
			}
			
			count = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				
				if(connection != null && !connection.isClosed()) {
					connection.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			}	
			
		}
		
		return count;
	}
	
	// 5. Delete(using Function from MySQL)
	public static boolean deleteScoreTBL(String id) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		List<Score> list = new ArrayList<>();
		String deleteQuery = "SELECT function_delete_scoreTBL(?)";
		
		try {
			connection = DBUtility.getConnection();
			preparedStatement = connection.prepareStatement(deleteQuery);
			preparedStatement.setString(1, id);
			
			String columnNumber = "1";
			list = readScoreTBL(columnNumber, id);
			
			if(list.size() == 0) {
				return false;
			} else {
				resultSet = preparedStatement.executeQuery();
				return true;
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				
				if(connection != null && !connection.isClosed()) {
					connection.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
			
		}
		
		return false;
	}

	// 6. Record(using Trigger from MySQL)
	public static List<Record> recordScoreTBL(){
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		List<Record> list = new ArrayList<>();
		String recordQuery = "SELECT * FROM recordTBL";
		
		try {
			connection = DBUtility.getConnection();
			preparedStatement = connection.prepareStatement(recordQuery);
			resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.isBeforeFirst()) {
				return list;
			}
			
			while(resultSet.next()) {
				String id = resultSet.getString(1);
				String name = resultSet.getString(2);
				int reading = resultSet.getInt(3);
				int listening = resultSet.getInt(4);
				int speaking = resultSet.getInt(5);
				int writing = resultSet.getInt(6);
				int total = resultSet.getInt(7);
				double average = resultSet.getDouble(8);
				String grade = resultSet.getString(9);
				String modifyType = resultSet.getString(10);
				String modifyDate = resultSet.getString(11);
				String modifyUser = resultSet.getString(12);
				list.add(new Record(id, name, reading, listening, speaking, writing, total, average, grade, modifyType, modifyDate, modifyUser));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		
		} finally {
			try {
				if(preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				
				if(connection != null && !connection.isClosed()) {
					connection.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
			
		}
		
		return list;
	}

}
