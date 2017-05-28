package kr.or.connect.todo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.todo.model.TodoModel;
import kr.or.connect.todo.service.TodoService;

@RestController
public class TodoController {
	private static final String COMPLETED = "1";
	@Autowired
	TodoService todoService;
	
	@GetMapping("/todo")
	public String selectAllTodoList() {
		return todoService.selectAllTodoList();
	}
	
	@GetMapping("/todo/active")
	public String selectActiveTodoList() {
		return todoService.selectActiveTodoList();
	}
	
	@GetMapping("/todo/completed")
	public String selectCompletedTodoList() {
		return todoService.selectCompletedTodoList();
	}
	
	@PostMapping("/todo")
	public String insertTodoItem(@RequestBody String todoText) {
		todoService.insertTodoItem(todoText);
		return todoService.selectRecentTodo();
	}
	
	@DeleteMapping("/todo/{id}")
	public void deleteTodoItem(@PathVariable("id") String id) {
		todoService.deleteTodoItem(id);
	}
	
	@PutMapping("/todo")
	public void updateCompletedStatus(@RequestBody TodoModel todoModel) {
		todoService.updateCompletedStatus(todoModel.getId(), todoModel.getCompleted());
	}
	
	@DeleteMapping("/todo/items")
	public void deleteAllCompletedItems(){
		todoService.deleteAllCompletedItems(COMPLETED);
	}
	
}
