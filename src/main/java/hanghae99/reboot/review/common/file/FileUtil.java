package hanghae99.reboot.review.common.file;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

    private FileUtil() {
    }

    public static String getExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (fileName != null && fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return ""; // 확장자가 없는 경우
        }
    }

}
