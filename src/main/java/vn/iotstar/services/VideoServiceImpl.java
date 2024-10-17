package vn.iotstar.services;

import java.util.List;

import vn.iotstar.dao.VideoDao;
import vn.iotstar.dao.IVideoDao;
import vn.iotstar.entity.Video;

public class VideoServiceImpl implements IVideoService {
	
	public IVideoDao videoDao = new VideoDao();

	@Override
	public List<Video> findAll() {
		return videoDao.findAll();
	}

	@Override
	public Video findById(int id) {
		return videoDao.findById(id);
	}

	@Override
	public List<Video> searchById(int id) {
		return videoDao.searchById(id);
	}

	@Override
	public void insert(Video video) {
		Video vid = this.findByVideoname(video.getTitle());
		if (vid==null) {
			videoDao.insert(video);
		}
	}

	@Override
	public void update(Video video) {
		Video vid = this.findById(video.getVideoId());
		if (vid!=null) {
			videoDao.update(video);
		}
	}

	@Override
	public void delete(int id) {
		try {
			videoDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int count() {
		return videoDao.count();
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		return videoDao.findAll(page, pagesize);
	}

	@Override
	public Video findByVideoname(String name) {
		try {
			return videoDao.findByVideoname(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
