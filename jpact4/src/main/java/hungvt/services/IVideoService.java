package hungvt.services;

import hungvt.entity.Video;
import java.util.List;

public interface IVideoService {
    void createVideo(Video video);
    Video getVideoById(long videoId);
    List<Video> getAllVideos();
    List<Video> searchVideos(String title);
    void updateVideo(Video video);
    void deleteVideo(long videoId) throws Exception;
    List<Video> getVideosWithPagination(int page, int pageSize);
    int getTotalVideos();
}
