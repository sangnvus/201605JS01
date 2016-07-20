package vn.edu.fu.veazy.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.fu.veazy.core.dao.GenericDao;
import vn.edu.fu.veazy.core.model.TaskModel;

@Service
public class TaskServiceImpl implements TaskService{
	@Autowired
	private GenericDao<TaskModel, Integer> taskDao;

	@Override
	public void GetTask(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void GetSentTasks(Integer senderId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void GetReceivedTasks(Integer receiverId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void GetAllTasks(Integer userId) {
		// TODO Auto-generated method stub
		
	}
	
}
