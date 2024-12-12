package hanghae99.reboot.review.common.file;

import hanghae99.reboot.review.common.exception.CommonErrorCode;
import hanghae99.reboot.review.common.exception.CustomCommonException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class DummyFileService implements FileService{

    @Value("${spring.web.resources.static-path}")
    private String fileDir;

    @Override
    public String uploadFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        try {
            String fileName = UUID.randomUUID() + "." + FileUtil.getExtension(file);
            String fullPath = fileDir + fileName;
            file.transferTo(new File(fullPath));
            return "/" + fileName;
        } catch (IOException e) {
            throw new CustomCommonException(CommonErrorCode.UNABLE_TO_UPLOAD_FILE);
        }
    }
}
