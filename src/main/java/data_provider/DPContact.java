package data_provider;

import dto.UserDtoLombok;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static utils.RandomUtils.*;
import static utils.RandomUtils.generateString;

public class DPContact {
    @DataProvider
    public UserDtoLombok[] newAddContactDP() {
        UserDtoLombok contact1 = UserDtoLombok.builder()
                .name(generateString(10))
                .lastName(generateString(10))
                .email(generateEmail(10))
                .Phone(generatePhone(12))
                .Address("Address " + generateString(10))
                .description("good")
                .build();
        UserDtoLombok contact2 = UserDtoLombok.builder()
                .name(generateString(10))
                .lastName(generateString(10))
                .email(generateEmail(10))
                .Phone(generatePhone(12))
                .Address("Address "+ generateString(10))
                .description("good")
                .build();
        return new UserDtoLombok[]{contact1,contact2};
    }

    @DataProvider
    public UserDtoLombok[] newContactDP(){
        UserDtoLombok contact = UserDtoLombok.builder()
                .name(generateString(5))
                .lastName(generateString(10))
                .email(generateEmail(7))
                .Phone(generatePhone(12))
                .Address("Address "+ generateString(10))
                .build();
        UserDtoLombok contact1 = UserDtoLombok.builder()
                .name(generateString(5))
                .lastName(generateString(10))
                .email(generateEmail(7))
                .Phone(generatePhone(12))
                .Address("Address "+ generateString(10))
                .build();
        UserDtoLombok contact2 = UserDtoLombok.builder()
                .name(generateString(5))
                .lastName(generateString(10))
                .email(generateEmail(7))
                .Phone(generatePhone(12))
                .Address("Address "+ generateString(10))
                .build();
        return new UserDtoLombok[]{contact,contact1,contact2};
    }

    @DataProvider
    public Iterator<UserDtoLombok> newConatactDPFFile(){
        List<UserDtoLombok> contactList = new ArrayList<>();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader("src/test/resources/data_provader/table_contact.csv"));
            String line = bufferedReader.readLine();
                while (line != null){
            String[] splitArray = line.split(",");
            contactList.add(UserDtoLombok.builder()
                    .name(splitArray[0])
                    .lastName(splitArray[1])
                    .Phone(splitArray[2])
                    .email(splitArray[3])
                    .Address(splitArray[4])
                    .description(splitArray[5])
                    .build());
            line = bufferedReader.readLine();
        }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return contactList.listIterator();

    }
}
