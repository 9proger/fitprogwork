package ru.omstu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.omstu.service.DataProcessingService;
import ru.omstu.service.CacheService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
class DataProcessingIntegrationTest {

    @Autowired
    private DataProcessingService processingService;

    @Autowired
    private CacheService cacheService;

    @Test
    void testCachingLogic() throws Exception {
        String type = "json";
        String data = "{\"val\": \"test\"}";
        String path = "val";

        cacheService.clear();

        String res1 = processingService.process(type, data, path);

        String res2 = processingService.process(type, data, path);

        assertThat(res1).isEqualTo("test");
        assertThat(res2).isEqualTo(res1);
    }
}
