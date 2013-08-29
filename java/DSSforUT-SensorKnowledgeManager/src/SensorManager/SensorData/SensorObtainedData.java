package SensorManager.SensorData;

import java.util.Date;

/**
 * This class maps the needed data to be stored in the data base, including
 * sensor data
 * @author Sebastian
 */
public class SensorObtainedData {
    
    private String data;
    private String node;
    private Date time;

    public SensorObtainedData() {
    }

    public SensorObtainedData(String data, String node, Date time) {
        this.data = data;
        this.node = node;
        this.time = time;
    }
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    
    @Override
    public String toString(){
        return "{data: " + this.data + " node: " + this.node + "}";
    }
}