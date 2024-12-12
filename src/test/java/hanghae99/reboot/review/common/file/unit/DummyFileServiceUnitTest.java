package hanghae99.reboot.review.common.file.unit;

import hanghae99.reboot.review.common.ServiceUnitTest;
import hanghae99.reboot.review.common.file.DummyFileService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;

public class DummyFileServiceUnitTest extends ServiceUnitTest {

    @InjectMocks
    DummyFileService fileService;

    @Test
    public void uploadFile() {
        // given
        String testDir = "src/test/resources/static/";
        ReflectionTestUtils.setField(fileService, "fileDir", testDir);
        MockMultipartFile emptyFile = new MockMultipartFile(
                "file",
                "image.png",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                new byte[]{65}
        );

        // when
        String fileUrl = fileService.uploadFile(emptyFile);

        // then
        Assertions.assertThat(fileUrl).isNotNull();

        // 파일 경로 확인
        File file = new File(testDir + fileUrl);
        Assertions.assertThat(file.exists()).isTrue();

        // 테스트 후 파일 삭제
        if (file.exists()) {
            Assertions.assertThat(file.delete()).isTrue();
        }
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
