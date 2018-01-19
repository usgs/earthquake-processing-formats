The purpose of these formats is to hold data representing the input or output
of various types of seismic processing algorithms.

## Defined formats:
* [LocationRequest](LocationRequest.md) Format - A format to contain a
request for the location of a seismic event from a location algorithm
* [TravelTimeSession](TravelTimeSession.md) Format - A format to open a
session for requesting travel time information.
* [TravelTimeRequest](TravelTimeRequest.md) Format - A format to contain
a request for travel time information.

## Supporting objects:
* [Location](LocationData.md) Object - An object to contain the processed location
of a seismic event from a location algorithm
* [TravelTimeData](TravelTimeData.md) Object - An object to contain requested
travel-time information.
* [TravelTimePlotData](TravelTimePlotData.md) Object - An object to contain
requested travel-time plot information.
* [TravelTimePlotDataBranch](TravelTimePlotDataBranch.md) Object - An object to
contain requested travel-time plot branch information.
* [TravelTimePlotDataSample](TravelTimePlotDataSample.md) Object - An object to
contain individual travel-time plot data samples.
* [Hypocenter](Hypocenter.md) Object - An object that contains information about
a hypocenter as part of a location.
* [ErrorEllipse](ErrorEllipse.md) Object - An object that contains information
about an error ellipse as part of a location.
* [Pick](Pick.md) Object - An object that contains information about picks
associated with a location.
* [Site](Site.md) Object - An object that defines the source station for a pick.
* [Source](Source.md) Object - An object that defines the creator/source of a
pick.
