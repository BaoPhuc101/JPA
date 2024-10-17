package vn.iotstar.services;

import java.util.List;

import vn.iotstar.entity.Category;

public interface ICategoryService {
	Category findByCategoryname(String name);
	List<Category> findAll(int page, int pagesize);
	int count();
	void delete(int id);
	void update(Category category);
	void insert(Category category);
	List<Category> searchByName(String keyword);
	Category findById(int id);
	List<Category> findAll();
}
