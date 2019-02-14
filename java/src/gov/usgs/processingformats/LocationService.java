package gov.usgs.processingformats;

/**
 * LocationService provides an interface for processing a LocationRequest.
 */
public interface LocationService {

    /**
     * Process a LocationRequest
     *
     * @param request location inputs.
     * @return calcualated location.
     * @throws LocationException if errors occur.
     */
    public LocationData getLocation(final LocationRequest request) throws LocationException;

}
