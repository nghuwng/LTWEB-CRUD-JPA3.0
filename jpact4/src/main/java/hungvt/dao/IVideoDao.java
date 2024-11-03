package hungvt.dao;

import hungvt.entity.Video;
import java.util.List;

public interface IVideoDao {
    void insert(Video video);
    void update(Video video);
    void delete(long videoId) throws Exception;
    Video findById(long videoId);
    List<Video> findAll();
    List<Video> findByTitle(String title);
    List<Video> findAll(int page, int pageSize);
    int count();
}
