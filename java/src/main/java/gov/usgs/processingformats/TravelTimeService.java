package gov.usgs.processingformats;

/** TravelTimeService provides an interface for processing a TravelTimeRequest. */
public interface TravelTimeService {

  /**
   * Process a TravelTimeRequest
   *
   * @param request traveltime inputs.
   * @return calculated traveltime.
   * @throws TravelTimeException if errors occur.
   */
  public TravelTimeRequest getTravelTimes(final TravelTimeRequest request)
      throws TravelTimeException;

  /**
   * Process a TravelTimePlotRequest
   *
   * @param request traveltime inputs.
   * @return calculated traveltime.
   * @throws TravelTimeException if errors occur.
   */
  public TravelTimePlotRequest getTravelTimePlot(final TravelTimePlotRequest request)
      throws TravelTimeException;
}
