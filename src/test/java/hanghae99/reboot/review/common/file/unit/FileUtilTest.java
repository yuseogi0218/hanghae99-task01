package hanghae99.reboot.review.common.file.unit;

import hanghae99.reboot.review.common.file.FileUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.multipart.MultipartFile;

public class FileUtilTest {
    @Test
    public void getExtension_성공() {
        // given
        MultipartFile file = Mockito.mock(MultipartFile.class);

        // stub
        Mockito.when(file.isEmpty()).thenReturn(false);
        Mockito.when(file.getOriginalFilename()).thenReturn("image.png");

        // when
        String fileExtension = FileUtil.getExtension(file);

        // then
        Assertions.assertThat(fileExtension).isEqualTo("png");
    }

    @Test
    public void getExtension_성공_empty() {
        // given
        MultipartFile file = Mockito.mock(MultipartFile.class);

        // stub
        Mockito.when(file.isEmpty()).thenReturn(false);
        Mockito.when(file.getOriginalFilename()).thenReturn("image");

        // when
        String fileExtension = FileUtil.getExtension(file);

        // then
        Assertions.assertThat(fileExtension).isEmpty();
    }
}
