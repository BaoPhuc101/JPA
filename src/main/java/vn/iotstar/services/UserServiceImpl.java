package vn.iotstar.services;

import java.util.List;

import vn.iotstar.entity.User;
import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.IUserDao;

public class UserServiceImpl implements IUserService {

	public IUserDao cateDao = new UserDao();

	@Override
	public List<User> findAll() {
		return cateDao.findAll();
	}

	@Override
	public User findById(int id) {
		return cateDao.findById(id);
	}

	@Override
	public List<User> searchByName(String keyword) {
		return cateDao.searchByName(keyword);
	}

	@Override
	public void insert(User category) {
		User cate = this.findByUsername(category.getUsername());
		if (cate==null) {
			cateDao.insert(category);
		}
	}

	@Override
	public void update(User category) {
		User cate = this.findById(category.getUserId());
		if (cate!=null) {
			cateDao.update(category);
		}
	}

	@Override
	public void delete(int id) {
		try {
			cateDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int count() {
		return cateDao.count();
	}

	@Override
	public List<User> findAll(int page, int pagesize) {
		return cateDao.findAll(page, pagesize);
	}

	@Override
	public User findByUsername(String name) {
		try {
			return cateDao.findByUsername(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
