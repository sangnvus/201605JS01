package vn.edu.fu.veazy.core.service;

public interface TaskService {
	void GetTask(Integer id);
	void GetSentTasks(Integer senderId);
	void GetReceivedTasks(Integer receiverId);
	void GetAllTasks(Integer userId);
}
