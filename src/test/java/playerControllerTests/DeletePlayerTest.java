package playerControllerTests;

import models.PlayerCreateResponseDto;
import models.PlayerDeleteRequestDto;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DeletePlayerTest extends BaseTest {
    private int id;

    @BeforeClass
    public void preconditions() {
        PlayerCreateResponseDto playerCreateResponseDto = apiClient.createPlayer("34", "male", "deletedtestuser", "deletedtestuser", "user", "deletedtestuser", "supervisor", 200);
        id = playerCreateResponseDto.getId();
    }

    @Test(dataProvider = "dataTest", description = "Checking user deletion by id")
    public void deletePlayerTest(int idToDelete, int expectedStatusCode) {
        logger.info("deletePlayer controller testing");
        PlayerDeleteRequestDto playerDeleteRequestDto = new PlayerDeleteRequestDto();
        playerDeleteRequestDto.setPlayerId(idToDelete);
        apiClient.deletePlayer(playerDeleteRequestDto, "admin", expectedStatusCode);
        logger.info("id of deleted user is " + id);
    }

    @DataProvider(name = "dataTest", parallel = true)
    public Object[][] dataTest() {
        return new Object[][]{
                {
                        id, 200
                },
                {
                        -7, 403
                }
        };
    }
}
