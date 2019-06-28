import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ImageSaverTest {

    @Test
    void testSaveImage(){
        String testImage = "data:image/png;base64,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";
        String folder = "/Users/matiasmiodosky/sandbox/whatsApp/src/main/resources/QR";
        String name  = ImageSaver.saveQR(folder, testImage);
        File[] files = new File(folder).listFiles();
        assert files != null;
        assertTrue(Arrays.stream(files)
                .map(File::getName)
                .collect(Collectors.toList())
                .contains(name));
    }

}