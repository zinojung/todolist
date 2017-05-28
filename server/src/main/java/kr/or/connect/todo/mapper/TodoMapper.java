package kr.or.connect.todo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kr.or.connect.todo.model.TodoModel;

public class TodoMapper implements RowMapper {

	@Override
	public TodoModel mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		TodoModel todoModel = new TodoModel();
		todoModel.setId(resultSet.getString("id"));
		todoModel.setTodo(resultSet.getString("todo"));
		todoModel.setCompleted(resultSet.getString("Completed"));
		todoModel.setDate(resultSet.getTimestamp("date"));
		return todoModel;
	}
}
