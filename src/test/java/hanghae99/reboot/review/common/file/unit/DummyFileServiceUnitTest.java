package hanghae99.reboot.review.common.file.unit;

import hanghae99.reboot.review.common.ServiceUnitTest;
import hanghae99.reboot.review.common.exception.CommonErrorCode;
import hanghae99.reboot.review.common.exception.CustomCommonException;
import hanghae99.reboot.review.common.file.DummyFileService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class DummyFileServiceUnitTest extends ServiceUnitTest {

    @InjectMocks
    DummyFileService fileService;

    @Test
    public void uploadFile() throws Exception {
        // given
        String testDir = "src/test/resources/static/";
        ReflectionTestUtils.setField(fileService, "fileDir", testDir);
        MultipartFile file = mock(MultipartFile.class);

        // stub
        when(file.isEmpty()).thenReturn(false);
        when(file.getOriginalFilename()).thenReturn("image.png");
        doNothing().when(file).transferTo(any(File.class));

        // when
        String fileUrl = fileService.uploadFile(file);

        // then
        Assertions.assertThat(fileUrl).isNotNull();
        verify(file, times(1)).transferTo(any(File.class));
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
        MultipartFile emptyFile = mock(MultipartFile.class);

        // stub
        when(emptyFile.isEmpty()).thenReturn(true);

        // when
        String fileUrl = fileService.uploadFile(emptyFile);

        // then
        Assertions.assertThat(fileUrl).isNull();
    }

    @Test
    public void uploadFile_실패_UNABLE_TO_UPLOAD_FILE() throws Exception {
        // given
        MultipartFile file = mock(MultipartFile.class);

        // stub
        when(file.isEmpty()).thenReturn(false);
        when(file.getOriginalFilename()).thenReturn("image.png");
        doThrow(new IOException("Mock IOException")).when(file).transferTo(any(File.class));

        // when & then
        Assertions.assertThatThrownBy(() -> fileService.uploadFile(file))
                .isInstanceOf(CustomCommonException.class)
                .hasMessage(CommonErrorCode.UNABLE_TO_UPLOAD_FILE.getMessage());
    }
}
