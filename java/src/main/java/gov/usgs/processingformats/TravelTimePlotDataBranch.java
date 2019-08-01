package gov.usgs.processingformats;

import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * a conversion class used to create, parse, and validate travel time plot branch data
 *
 * @author U.S. Geological Survey &lt;jpatton at usgs.gov&gt;
 */
public class TravelTimePlotDataBranch implements ProcessingInt {

  /** JSON Keys */
  public static final String PHASE_KEY = "Phase";

  public static final String SAMPLES_KEY = "Samples";

  /** Required seismic Phase code for this branch */
  public String Phase;

  /** A required ArrayList of TravelTimePlotDataSample objects */
  public ArrayList<TravelTimePlotDataSample> Samples;

  /** The constructor for the TravelTimePlotDataBranch class. Initializes members to null values. */
  public TravelTimePlotDataBranch() {

    reload(null, null);
  }

  /**
   * Advanced constructor
   *
   * <p>The advanced constructor for the TravelTimeData class. Initializes members to provided
   * values.
   *
   * @param newPhase - A String containing the seismic Phase code
   * @param newSamples - A ArrayList&lt;TravelTimePlotDataSample&gt; containing the sample data
   */
  public TravelTimePlotDataBranch(String newPhase, ArrayList<TravelTimePlotDataSample> newSamples) {

    reload(newPhase, newSamples);
  }

  /**
   * Constructs the class from a JSONObject, populating members
   *
   * @param newJSONObject - A JSONObject.
   */
  public TravelTimePlotDataBranch(JSONObject newJSONObject) {

    // Required values
    // Phase
    if (newJSONObject.containsKey(PHASE_KEY)) {
      Phase = newJSONObject.get(PHASE_KEY).toString();
    } else {
      Phase = null;
    }

    // Samples
    if (newJSONObject.containsKey(SAMPLES_KEY)) {

      Samples = new ArrayList<TravelTimePlotDataSample>();

      // get the array
      JSONArray samplesArray = (JSONArray) newJSONObject.get(SAMPLES_KEY);

      if ((samplesArray != null) && (!samplesArray.isEmpty())) {

        // go through the whole array
        for (int i = 0; i < samplesArray.size(); i++) {

          // get the object
          JSONObject samplesObject = (JSONObject) samplesArray.get(i);

          // add to ArrayList
          Samples.add(new TravelTimePlotDataSample(samplesObject));
        }
      }
    } else {
      Samples = null;
    }
  }

  /**
   * Constructs the class from a TravelTimePlotDataBranch object, populating members (copy
   * constructor)
   *
   * @param sourceObject - A TravelTimePlotData object.
   */
  public TravelTimePlotDataBranch(TravelTimePlotDataBranch sourceObject) {

    reload(sourceObject.Phase, sourceObject.Samples);
  }

  /**
   * Reload Function
   *
   * <p>The reload function for the TravelTimeData class. Initializes members to provided values.
   *
   * @param newPhase - A String containing the seismic Phase code
   * @param newSamples - A ArrayList&lt;TravelTimePlotDataSample&gt; containing the sample data
   */
  public void reload(String newPhase, ArrayList<TravelTimePlotDataSample> newSamples) {

    Phase = newPhase;
    Samples = newSamples;
  }

  /**
   * Converts the contents of the class to a json object
   *
   * @return Returns a JSONObject containing the class contents
   */
  @SuppressWarnings("unchecked")
  public JSONObject toJSON() {

    JSONObject newJSONObject = new JSONObject();

    // Phase
    if (Phase != null) {
      newJSONObject.put(PHASE_KEY, Phase);
    }

    // Samples
    if ((Samples != null) && (!Samples.isEmpty())) {

      JSONArray samplesArray = new JSONArray();

      // enumerate through the whole arraylist
      for (Iterator<TravelTimePlotDataSample> samplesIterator = Samples.iterator();
          samplesIterator.hasNext(); ) {

        // convert sample to JSON object
        JSONObject pickObject = ((TravelTimePlotDataSample) samplesIterator.next()).toJSON();

        samplesArray.add(pickObject);
      }

      if (!samplesArray.isEmpty()) {
        newJSONObject.put(SAMPLES_KEY, samplesArray);
      }
    }

    return (newJSONObject);
  }

  /**
   * Validates the class.
   *
   * @return Returns true if successful
   */
  public boolean isValid() {
    if (getErrors() == null) {
      return (true);
    } else if (getErrors().size() == 0) {
      return (true);
    } else {
      return (false);
    }
  }

  /**
   * Gets any validation errors in the class.
   *
   * @return Returns a List&lt;String&gt; of any errors found
   */
  public ArrayList<String> getErrors() {
    ArrayList<String> errorList = new ArrayList<String>();

    // Phase
    if (Phase == null) {
      // Phase not found
      errorList.add("No Phase in TravelTimePlotDataBranch Class.");
    } else if (Phase.isEmpty()) {
      // Phase empty
      errorList.add("Empty Phase in TravelTimePlotDataBranch Class.");
    }

    // Samples
    if ((Samples != null) && (!Samples.isEmpty())) {

      // enumerate through the whole arraylist
      for (Iterator<TravelTimePlotDataSample> samplesIterator = Samples.iterator();
          samplesIterator.hasNext(); ) {

        // get next sample object
        TravelTimePlotDataSample sampleObject = ((TravelTimePlotDataSample) samplesIterator.next());

        if (!sampleObject.isValid()) {
          errorList.add(
              "Invalid TravelTimePlotDataSample in samples in TravelTimePlotDataBranch Class");
          break;
        }
      }
    }

    return (errorList);
  }
}
