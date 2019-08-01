package gov.usgs.processingformats;

/**
 * LocationService provides an interface for processing a LocationRequest.
 */
public interface LocationService {

    /**
     * Process a LocationRequest
     *
     * @param request location inputs.
     * @return calculated location.
     * @throws LocationException if errors occur.
     */
    public LocationResult getLocation(final LocationRequest request) throws LocationException;

}
