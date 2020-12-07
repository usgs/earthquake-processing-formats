package gov.usgs.processingformats;

/** TravelTimeService provides an interface for processing a TravelTimeRequest. */
public interface TravelTimeService {

  /**
   * Process a TravelTimeRequest
   *
   * @param request traveltime inputs.
   * @return calculated traveltime.
   * @throws LocationException if errors occur.
   */
  public TravelTimeRequest getTravelTimes(final TravelTimeRequest request)
      throws TravelTimeException;
}
