package okhttp;

import dto.UserAPIDtoLombok;
import dto.UserDtoLombok;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseApi;

import java.io.IOException;

import static utils.RandomUtils.*;

public class RegistrationTests implements BaseApi {

    @Test
    public void registrationPositiveTest(){
        UserAPIDtoLombok userAPIDtoLombok = UserAPIDtoLombok.builder()
                .username(generateEmail(10))
                .password("123TYUads$")
                .build();
        RequestBody requestBody = RequestBody
                .create(GSON.toJson(userAPIDtoLombok),JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+REGISTRATION_PATH)
                .post(requestBody).build();
        try {
            Response response = OK_HTTP_CLIENT.newCall(request).execute();
            Assert.assertTrue(response.isSuccessful());
        } catch (IOException e) {
            Assert.fail("Created exception");
            throw new RuntimeException(e);
        }
    }
    @Test
    public void registrationNegativeTest(){
        UserAPIDtoLombok userAPIDtoLombok = UserAPIDtoLombok.builder()
                .username(generateString(10))
                .password("123TYUads$")
                .build();
        RequestBody requestBody = RequestBody
                .create(GSON.toJson(userAPIDtoLombok),JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+REGISTRATION_PATH)
                .post(requestBody).build();
        try {
            Response response = OK_HTTP_CLIENT.newCall(request).execute();

            System.out.println(response.code());
            System.out.println(response.message());

            Assert.assertEquals(response.code(), 400);

        } catch (IOException e) {
            Assert.fail("Created exception");
            throw new RuntimeException(e);
        }
    }
    @Test
    public void registrationNegativeTestDuplicateUser(){
        UserAPIDtoLombok userAPIDtoLombok = UserAPIDtoLombok.builder()
                .username(generateEmail(10))
                .password("123TYUads$")
                .build();
        RequestBody requestBody = RequestBody
                .create(GSON.toJson(userAPIDtoLombok),JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+REGISTRATION_PATH)
                .post(requestBody).build();
        try {
            Response response = OK_HTTP_CLIENT.newCall(request).execute();
            if (response.isSuccessful()){
                Response response1 = OK_HTTP_CLIENT.newCall(request).execute();
                Assert.assertEquals(response1.code(), 409);
            }

        } catch (IOException e) {
            Assert.fail("Created exception");
            throw new RuntimeException(e);
        }
    }
}