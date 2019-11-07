package gov.usgs.processingformats;

import java.util.ArrayList;
import org.json.simple.JSONObject;

public interface ProcessingInt {
  public JSONObject toJSON();

  public boolean isValid();

  public ArrayList<String> getErrors();
}
