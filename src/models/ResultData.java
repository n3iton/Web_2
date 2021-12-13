package models;

public class ResultData {
  double x;
  double y;
  double r;
  String result;
  String currentTime;
  long processingTime;

  public ResultData(double x, double y, double r, String result, String currentTime, long processingTime) {
    this.x = x;
    this.y = y;
    this.r = r;
    this.result = result;
    this.currentTime = currentTime;
    this.processingTime = processingTime;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public double getR() {
    return r;
  }

  public String getResult() {
    return result;
  }
  public String getCurrentTime() {
    return currentTime;
  }

  public long getProcessingTime() {
    return processingTime;
  }

}
