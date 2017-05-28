package kr.or.connect.todo.persistence;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.todo.mapper.TodoMapper;
import kr.or.connect.todo.model.TodoModel;
import static kr.or.connect.todo.persistence.TodoSqls.*;

@Repository
public class TodoDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;

	@Autowired
	public TodoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("todo")
				.usingGeneratedKeyColumns("id");
	}
	
	public List<TodoModel> selectAllTodoList(){
		List<TodoModel> todoModels = (List<TodoModel>)jdbc.query(SELECT_ALL_TODO_LIST, new TodoMapper());
		return todoModels;
	}
	
	public List<TodoModel> selectActiveTodoList() {
		List<TodoModel> todoModels = (List<TodoModel>)jdbc.query(SELECT_ACTIVE_TODO_LIST, new TodoMapper());
		return todoModels;
	}
	
	public List<TodoModel> selectCompletedTodoList() {
		List<TodoModel> todoModels = (List<TodoModel>)jdbc.query(SELECT_COMPLETED_TODO_LIST, new TodoMapper());
		return todoModels;
	}
	
	public String selectRecentTodo() {
		List<TodoModel> todoModel = (List<TodoModel>) jdbc.query(SELECT_RECENT_TODO, new TodoMapper());
		return todoModel.get(0).getId();
	}
	
	public String selectCompletedStatusById(String id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
		List<TodoModel> todoModel = (List<TodoModel>) jdbc.query(SELECT_BY_ID, namedParameters, new TodoMapper());
		return todoModel.get(0).getCompleted();
	}
	
	public void insertTodoItem(String todoText, Timestamp currentTime){
		Map<String,Object> param = new HashMap<>();
		param.put("todo", todoText);
		param.put("completed",0);
		param.put("date", currentTime);
		insertAction.execute(param);
	}
	
	public void deleteTodoItem(String id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
		jdbc.update(DELETE_BY_ID, namedParameters);
	}
	
	public void updateCompletedStatus(String id, String setStatus) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("setStatus", setStatus);
		SqlParameterSource namedParameters = new MapSqlParameterSource(map);
		jdbc.update(UPDATE_COMPLETED_STATUS, namedParameters);
	}
	
	public void deleteCompletedTodoList(String completed) {
		SqlParameterSource namedParameters = new MapSqlParameterSource("completed", completed);
		jdbc.update(DELETE_ALL_COMPLETE_TODO_LIST, namedParameters);
	}
}
				