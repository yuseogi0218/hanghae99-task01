package hanghae99.reboot.review.common.file.unit;

import hanghae99.reboot.review.common.ServiceUnitTest;
import hanghae99.reboot.review.common.file.DummyFileService;
import hanghae99.reboot.review.common.file.FileService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

public class DummyFileServiceUnitTest extends ServiceUnitTest {

    @InjectMocks
    DummyFileService fileService;

    @Test
    public void uploadFile() {
        // given
        MockMultipartFile emptyFile = new MockMultipartFile(
                "file",
                "image.png",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                new byte[]{65}
        );

        // when
        String fileUrl = fileService.uploadFile(emptyFile);

        // then
        Assertions.assertThat(fileUrl).isEqualTo("/image.png");
    }

    @Test
    public void uploadFileWithOutFile() {
        // given

        // when
        String fileUrl = fileService.uploadFile(null);

        // then
        Assertions.assertThat(fileUrl).isNull();
    }

    @Test
    public void uploadFileWithEmptyFile() {
        // given
        MockMultipartFile emptyFile = new MockMultipartFile(
                "file",
                "image.png",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                new byte[0]
        );

        // when
        String fileUrl = fileService.uploadFile(emptyFile);

        // then
        Assertions.assertThat(fileUrl).isNull();
    }
}
