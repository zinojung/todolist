package kr.or.connect.todo.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import kr.or.connect.todo.persistence.TodoDao;

@Service("todoService")
public class TodoServiceImpl implements TodoService{

	@Autowired
	TodoDao todoDao;
	
	private static Gson gson = new Gson();
	
	@Override
	public void insertTodoItem(String todoText) {
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		todoDao.insertTodoItem(todoText, currentTime);
	}

	@Override
	public void deleteTodoItem(String id) {
		todoDao.deleteTodoItem(id);
	}

	@Override
	public void updateCompletedStatus(String id, String setStatus) {
		todoDao.updateCompletedStatus(id, setStatus);
	}

	@Override
	public void deleteAllCompletedItems(String isCompleted) {
		todoDao.deleteCompletedTodoList(isCompleted);
	}

	@Override
	public String selectAllTodoList() {
		return gson.toJson(todoDao.selectAllTodoList());
	}

	@Override
	public String selectActiveTodoList() {
		return gson.toJson(todoDao.selectActiveTodoList());
	}

	@Override
	public String selectCompletedTodoList() {
		return gson.toJson(todoDao.selectCompletedTodoList());
	}
	
	@Override
	public String selectRecentTodo() {
		return gson.toJson(todoDao.selectRecentTodo());
	}
	
	@Override
	public String selectCompletedStatus(String id) {
		return gson.toJson(todoDao.selectCompletedStatusById(id));
	}

}
