package playerControllerTests;

import models.PlayerCreateResponseDto;
import models.PlayerDeleteRequestDto;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreatePlayerTest extends BaseTest {
    private int id;

    @Test(dataProvider = "dataTest",description = "Checking user creation")
    public void createPlayerTest(String age, String gender, String login, String password, String role, String screenName, String creator, int expectedStatusCode) {
        logger.info("createPlayer controller testing");
        PlayerCreateResponseDto playerCreateResponseDto = apiClient.createPlayer(age, gender, login, password, role, screenName, creator, expectedStatusCode);
        SoftAssert asserts = new SoftAssert();
        asserts.assertEquals(playerCreateResponseDto.getAge(), age);
        asserts.assertEquals(playerCreateResponseDto.getGender(), gender);
        asserts.assertEquals(playerCreateResponseDto.getLogin(), login);
        asserts.assertEquals(playerCreateResponseDto.getPassword(), password);
        asserts.assertEquals(playerCreateResponseDto.getRole(), role);
        asserts.assertEquals(playerCreateResponseDto.getScreenName(), screenName);
        id = playerCreateResponseDto.getId();
        logger.info("id of created user is " + id);
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
                        "34", "male", "testuser1", "testuser1", "user", "testuser1", "supervisor", 200
                },
                {
                        "90", "female", "testuser1", "testuser2", "user", "testuser2", "supervisor", 403
                }
        };
    }
}
