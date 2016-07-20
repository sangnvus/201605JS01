package vn.edu.fu.veazy.core.service;

import java.util.List;

import vn.edu.fu.veazy.core.model.TaskModel;

public interface TaskService {
	TaskModel getTask(Integer id) throws Exception;
	List<TaskModel> getSentTasks(Integer senderId) throws Exception;
	List<TaskModel> getReceivedTasks(Integer receiverId) throws Exception;
	List<TaskModel> getAllTasks(Integer userId) throws Exception;
}
