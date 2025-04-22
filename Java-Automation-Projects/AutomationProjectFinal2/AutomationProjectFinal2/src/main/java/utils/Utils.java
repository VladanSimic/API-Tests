package utils;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.github.cdimascio.dotenv.Dotenv;
import model.LoginUserModel;
import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static Dotenv dotEnv() {
        return Dotenv.configure()
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();

    }

    @DataProvider(name = "dpTest")
    public static Object[][] dpTest(){
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"standard_user", "secret_sauce"}
        };
    }

    public static List<LoginUserModel> getDataFromJson() {
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get(System.getProperty("user.dir") + "/src/test/resources/user.json"));
            //src/test/resources/user.json
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<LoginUserModel> list = new Gson().fromJson(reader, new TypeToken<List<LoginUserModel>>() {
        }.getType());
        return list;
    }

    @DataProvider(name = "getData")
    public static Object[][] getData() {
        JsonElement jsonData = null;
        Reader reader;
        try {
            reader = new FileReader(System.getProperty("user.dir") + "/src/test/resources/user.json");
            jsonData = JsonParser.parseReader(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JsonArray jsonArray = jsonData.getAsJsonArray();
        //if we don't have an array, it would be the command for jsonObject
        ArrayList<LoginUserModel> list = new Gson().fromJson(jsonArray, new TypeToken<List<LoginUserModel>>() {
        }.getType());

        Object[][] returnedValue = new Object[list.size()][1];
        int index = 0;
        for (Object[] object : returnedValue) {
            object[0] = list.get(index++);
        }
        return returnedValue;
    }




}
