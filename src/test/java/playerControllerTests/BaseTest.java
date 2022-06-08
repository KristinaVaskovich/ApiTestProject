package playerControllerTests;

import com.google.gson.Gson;
import core.ApiClient;

public class BaseTest {
    protected ApiClient apiClient = new ApiClient();
    protected Gson gson = new Gson();
}