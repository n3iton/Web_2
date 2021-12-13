package helpers;

import java.util.List;
import models.ResultData;

public class JsonParser {
  public String toJson(ResultData result) {
    return '{' +
        "\"x\":" + "\"" + result.getX() + "\"" + "," +
        "\"y\":" + "\"" + result.getY() + "\"" + "," +
        "\"r\":" + "\"" + result.getR() + "\"" + "," +
        "\"result\":" + "\"" + result.getResult() + "\"" + "," +
        "\"currentTime\":" + "\"" + result.getCurrentTime() + "\"" + "," +
        "\"processingTime\":" + "\"" + result.getProcessingTime() + "\"" +
        "}";
  }

  public String toJson(List<ResultData> arrayOfResult) {
    String jsonArray = "[";
    for (ResultData result:arrayOfResult) {
      jsonArray += toJson(result);
      jsonArray += ",";
    }
    jsonArray = jsonArray.substring(0, jsonArray.length() - 1);
    jsonArray += "]";
    return jsonArray;
  }
}
