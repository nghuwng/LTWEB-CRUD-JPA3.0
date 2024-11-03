package hungvt.services.impl;

import hungvt.dao.IVideoDao;
import hungvt.dao.impl.VideoDao;
import hungvt.entity.Video;
import hungvt.services.IVideoService;

import java.util.List;

public class VideoService implements IVideoService {
    private final IVideoDao videoDao;

    public VideoService() {
        this.videoDao = new VideoDao();
    }

    @Override
    public void createVideo(Video video) {
        videoDao.insert(video);
    }

    @Override
    public Video getVideoById(long videoId) {
        return videoDao.findById(videoId);
    }

    @Override
    public List<Video> getAllVideos() {
        return videoDao.findAll();
    }

    @Override
    public List<Video> searchVideos(String title) {
        return videoDao.findByTitle(title);
    }

    @Override
    public void updateVideo(Video video) {
        videoDao.update(video);
    }

    @Override
    public void deleteVideo(long videoId) throws Exception {
        videoDao.delete(videoId);
    }

    @Override
    public List<Video> getVideosWithPagination(int page, int pageSize) {
        return videoDao.findAll(page, pageSize);
    }

    @Override
    public int getTotalVideos() {
        return videoDao.count();
    }
}
