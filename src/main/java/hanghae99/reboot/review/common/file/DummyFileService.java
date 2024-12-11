package hanghae99.reboot.review.common.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DummyFileService implements FileService{

    @Override
    public String uploadFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        return "/image.png";
    }
}
