package kr.or.connect.todo.persistence;

public class TodoSqls {
	static final String DELETE_BY_ID =
			"DELETE FROM todo WHERE id=:id";
	
	static final String UPDATE_COMPLETED_STATUS =
			"UPDATE todo SET completed = :setStatus WHERE id = :id";
	
	static final String DELETE_ALL_COMPLETE_TODO_LIST =
			"DELETE FROM todo WHERE completed = :completed";
			
	static final String SELECT_ALL_TODO_LIST =
			"SELECT * FROM TODO ORDER BY id DESC";
	
	static final String SELECT_COMPLETED_TODO_LIST = 
			"SELECT * FROM TODO WHERE completed=1 ORDER BY id DESC";
	
	static final String SELECT_ACTIVE_TODO_LIST = 
			"SELECT * FROM TODO WHERE completed=0 ORDER BY id DESC";
	
	static final String SELECT_RECENT_TODO =
			"SELECT * FROM TODO ORDER BY id DESC LIMIT 1";
	
	static final String SELECT_BY_ID = 
			"SELECT * FROM TODO WHERE ID = :id";
}
