package playerControllerTests;

import models.PlayerGetByPlayerIdRequestDto;
import models.PlayerGetByPlayerIdResponseDto;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetPlayerByPlayerIdTest extends BaseTest {

    @Test(dataProvider = "dataTest", description = "Checking getting a user by id")
    public void getPlayerByPlayerIdTest(int id, int age, String gender, String login, String password, String role, String screenName, int expectedStatusCode) {
        PlayerGetByPlayerIdRequestDto playerGetByPlayerIdRequestDto = new PlayerGetByPlayerIdRequestDto();
        playerGetByPlayerIdRequestDto.setPlayerId(id);
        PlayerGetByPlayerIdResponseDto playerGetByPlayerIdResponseDto = apiClient.getPlayerByPlayerId(playerGetByPlayerIdRequestDto, expectedStatusCode);

        SoftAssert asserts = new SoftAssert();
        asserts.assertEquals(playerGetByPlayerIdResponseDto.getAge(), age);
        asserts.assertEquals(playerGetByPlayerIdResponseDto.getGender(), gender);
        asserts.assertEquals(playerGetByPlayerIdResponseDto.getLogin(), login);
        asserts.assertEquals(playerGetByPlayerIdResponseDto.getPassword(), password);
        asserts.assertEquals(playerGetByPlayerIdResponseDto.getRole(), role);
        asserts.assertEquals(playerGetByPlayerIdResponseDto.getScreenName(), screenName);
        asserts.assertAll();
    }

    @DataProvider(name = "dataTest", parallel = true)
    public Object[][] dataTest() {
        return new Object[][]{
                {
                        1, 28, "male", "supervisor", "testSupervisor", "supervisor", "testSupervisor", 200
                },
                {
                        -5, -5, "male", "supervisor", "testSupervisor", "supervisor", "testSupervisor", 403
                }
        };
    }
}
