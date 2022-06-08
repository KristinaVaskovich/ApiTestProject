package playerControllerTests;

import models.PlayerCreateResponseDto;
import models.PlayerDeleteRequestDto;
import models.PlayerUpdateRequestDto;
import models.PlayerUpdateResponseDto;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UpdatePlayerTest extends BaseTest {
    private int id;

    @BeforeClass
    public void preconditions() {
        PlayerCreateResponseDto playerCreateResponseDto = apiClient.createPlayer("36", "male", "updatedtestuser", "updatedtestuser", "user", "updatedtestuser", "supervisor", 200);
        id = playerCreateResponseDto.getId();
    }

    @Test(dataProvider = "dataTest", description = "Checking user edit by id")
    public void updatePlayerTest(int id, int age, String gender, String login, String password, String role, String screenName, int expectedStatusCode) {
        logger.info("updatePlayer controller testing");
        PlayerUpdateRequestDto playerUpdateRequestDto = new PlayerUpdateRequestDto();
        playerUpdateRequestDto.setAge(age);
        playerUpdateRequestDto.setGender(gender);
        playerUpdateRequestDto.setLogin(login);
        playerUpdateRequestDto.setPassword(password);
        playerUpdateRequestDto.setRole(role);
        playerUpdateRequestDto.setScreenName(screenName);

        PlayerUpdateResponseDto playerUpdateResponseDto = apiClient.updatePlayer(playerUpdateRequestDto, id, "admin", expectedStatusCode);
        logger.info("updated player info: " + playerUpdateResponseDto.toString());
        SoftAssert asserts = new SoftAssert();
        asserts.assertEquals(playerUpdateResponseDto.getAge(), age);
        asserts.assertEquals(playerUpdateResponseDto.getGender(), gender);
        asserts.assertEquals(playerUpdateResponseDto.getLogin(), login);
        asserts.assertEquals(playerUpdateResponseDto.getRole(), role);
        asserts.assertEquals(playerUpdateResponseDto.getScreenName(), screenName);
        asserts.assertAll();
    }

    @AfterClass
    public void postConditions() {
        PlayerDeleteRequestDto playerDeleteRequestDto = new PlayerDeleteRequestDto();
        playerDeleteRequestDto.setPlayerId(id);
        apiClient.deletePlayer(playerDeleteRequestDto, "admin", 200);
    }

    @DataProvider(name = "dataTest", parallel = true)
    public Object[][] dataTest() {
        return new Object[][]{
                {
                        id, 34, "male", "testuser1", "testuser1", "user", "testuser1", 200
                },
                {
                        id, 26, "rtg", "testuser1", "пар", "user", "testuser1", 403
                }
        };
    }
}
