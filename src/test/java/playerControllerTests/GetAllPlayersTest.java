package playerControllerTests;

import models.PlayerGetAllResponseDto;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class GetAllPlayersTest extends BaseTest {

    @Test(description = "Checking to get a list of all users")
    public void getAllPlayerTest() throws FileNotFoundException {
        PlayerGetAllResponseDto playerGetAllResponseDto = apiClient.getAllPlayers(200);
        PlayerGetAllResponseDto playerGetAllResponseDtoFromJson = gson.fromJson(new FileReader("src\\testResources\\players.json"), PlayerGetAllResponseDto.class);
        SoftAssert asserts = new SoftAssert();
        asserts.assertEquals(playerGetAllResponseDto.getPlayers(), playerGetAllResponseDtoFromJson.getPlayers());
        asserts.assertAll();
    }
}