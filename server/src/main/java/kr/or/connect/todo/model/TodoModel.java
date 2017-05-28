package kr.or.connect.todo.model;

import java.sql.Timestamp;

public class TodoModel {
	private String id;
	private String todo;
	private String completed;
	private Timestamp date;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTodo() {
		return todo;
	}
	public void setTodo(String todo) {
		this.todo = todo;
	}
	public String getCompleted() {
		return completed;
	}
	public void setCompleted(String completed) {
		this.completed = completed;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "TodoModel [id=" + id + ", todo=" + todo + ", completed=" + completed + ", date=" + date + "]";
	}
}
