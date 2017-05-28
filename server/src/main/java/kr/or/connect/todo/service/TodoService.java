package kr.or.connect.todo.service;

public interface TodoService {
	void insertTodoItem(String todoText);
	void deleteTodoItem(String id);
	void updateCompletedStatus(String id, String setStatus);
	void deleteAllCompletedItems(String isCompleted);
	String selectAllTodoList();
	String selectActiveTodoList();
	String selectCompletedTodoList();
	String selectRecentTodo();
	String selectCompletedStatus(String id);
}
