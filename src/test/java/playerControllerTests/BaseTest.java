package playerControllerTests;

import com.google.gson.Gson;
import core.ApiClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {
    protected ApiClient apiClient = new ApiClient();
    protected Gson gson = new Gson();
    protected static final Logger logger = LoggerFactory.getLogger(ApiClient.class);
}