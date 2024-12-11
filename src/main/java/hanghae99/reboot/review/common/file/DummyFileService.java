package hanghae99.reboot.review.common.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DummyFileService implements FileService{

    @Override
    public String uploadFile(MultipartFile file) {
        return "/image.png";
    }
}
