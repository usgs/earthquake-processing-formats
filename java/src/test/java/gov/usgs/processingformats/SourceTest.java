package gov.usgs.processingformats;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SourceTest {

  public static final String SOURCE_STRING =
      "{\"Author\":" + "\"TestAuthor\",\"AgencyID\":\"US\",\"Type\":\"Unknown\"}";
  public static final String AGENCYID = "US";
  public static final String AUTHOR = "TestAuthor";
  public static final String TYPE = "Unknown";

  /** Able to write a JSON string */
  @Test
  public void writesJSON() {

    Source sourceObject = new Source(AGENCYID, AUTHOR, TYPE);

    // write out to a string
    String jsonString = Utility.toJSONString(sourceObject.toJSON());

    // check the data
    try {
      checkData(new Source(Utility.fromJSONString(jsonString)), "WritesJSON");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /** Able to read a JSON string */
  @Test
  public void readsJSON() {

    try {

      checkData(new Source(Utility.fromJSONString(SOURCE_STRING)), "ReadsJSON");
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /** Able to run validation function */
  @Test
  public void validate() {

    // build source object
    Source sourceObject = new Source(AGENCYID, AUTHOR, TYPE);

    // Successful validation
    boolean rc = sourceObject.isValid();

    // check return code
    Assertions.assertEquals(true, rc, "Successful Validation");

    // build bad source object
    Source badSourceObject = new Source(AGENCYID, null, null);

    rc = badSourceObject.isValid();

    // check return code
    Assertions.assertEquals(false, rc, "Unsuccessful Validation");
  }

  /** Checks the data in the class */
  public void checkData(Source SourceObject, String TestName) {

    // check SourceObject.AgencyID
    Assertions.assertEquals(AGENCYID, SourceObject.AgencyID, TestName + " AgencyID Equals");

    // check SourceObject.Author
    Assertions.assertEquals(AUTHOR, SourceObject.Author, TestName + " Author Equals");

    // check SourceObject.Author
    Assertions.assertEquals(TYPE, SourceObject.Type, TestName + " Type Equals");
  }
}
