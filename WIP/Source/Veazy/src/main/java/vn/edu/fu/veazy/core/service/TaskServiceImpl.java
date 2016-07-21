package vn.edu.fu.veazy.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fu.veazy.core.dao.GenericDao;
import vn.edu.fu.veazy.core.model.TaskModel;

@Service
public class TaskServiceImpl implements TaskService{
	@Autowired
	private GenericDao<TaskModel, Integer> taskDao;

	@Override
	@Transactional
	public TaskModel getTask(Integer id) throws Exception {
		return taskDao.findById(id);	
	}

	@Override
	@Transactional
	public List<TaskModel> getSentTasks(Integer senderId) throws Exception {
		TaskModel task = new TaskModel();
		task.setSenderId(senderId);
		return taskDao.findByExample(task);
	}

	@Override
	@Transactional
	public List<TaskModel> getReceivedTasks(Integer receiverId) throws Exception {
		TaskModel task = new TaskModel();
		task.setReceiverId(receiverId);
		return taskDao.findByExample(task);
		
	}

	@Override
	@Transactional
	public List<TaskModel> getAllTasks(Integer userId) throws Exception {
		List<TaskModel> tasks = getSentTasks(userId);
		tasks.addAll(getReceivedTasks(userId));
		return tasks;
	}
	
}
