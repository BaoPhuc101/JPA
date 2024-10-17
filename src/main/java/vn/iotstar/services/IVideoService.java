package vn.iotstar.services;

import java.util.List;

import vn.iotstar.entity.Video;

public interface IVideoService {

	Video findByVideoname(String name);
	List<Video> findAll(int page, int pagesize);
	int count();
	void delete(int id);
	void update(Video vid);
	void insert(Video vid);
	List<Video> searchById(int id);
	Video findById(int id);
	List<Video> findAll();

}
