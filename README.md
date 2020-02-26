# Processing Formats

The US Geological Survey (USGS) National Earthquake Information Center (NEIC)
defines a number of data exchange formats to communicate seismic event processing
information between seismic data services and/or algorithms. These formats
are defined using objects as defined in the [JSON standard](http://www.json.org).

[License](LICENSE.md)

The purpose of this project is to:

1. Define formats to hold data required for various types of seismic event
processing algorithms.
2. Store the format definitions in a source controlled manner.
3. Host libraries used to generate and parse the formats

## Defined formats:

* [Location Request](format-docs/LocationRequest.md) Format - A format for
for requesting an updated location from a location algorithm
* [Location Result](format-docs/LocationResult.md) Format - A format for
for a result from a location algorithm
* [Pick](format-docs/Pick.md) Format - A format for pick data from a
waveform arrival time picking algorithm or human analyst.
* [Travel Time Request](format-docs/TravelTimeRequest.md) Format - A format for
requesting travel time data
* [TravelTimeSession](format-docs/TravelTimeSession.md) Format - A format for
setting up a travel time session

## Supporting objects:

* [Error Elllipse](format-docs/TravelTimeData.md) Object - An object that
contains information about travel time data returned as part of a travel time
request.
* [Hypocenter](format-docs/Hypocenter.md) Object - An object that contains
information about a hypocenter.
* [Site](format-docs/Site.md) Object - An object that defines the station used
to create a pick.
* [Source](format-docs/Source.md) Object - An object that defines the
creator/source of a pick, request, or result.
* [Travel Time Data](format-docs/TravelTimeData.md) Object - An object that
contains information about travel time data returned as part of a travel time
request.
* [Travel Time Plot Data](format-docs/TravelTimePlotData.md) Object - An object that
contains information about travel time plot data returned as part of a travel time
request.
* [Travel Time Plot Data Branch](format-docs/TravelTimePlotDataBranch.md) Object - An object that
contains information about travel time plot data branch returned as part of a travel time
request.
* [Travel Time Plot Data Branch](format-docs/TravelTimePlotDataSample.md) Object - An object that
contains information about travel time plot data sample returned as part of a travel time
request.

## Supported Languages:

Currently a library written in C++11, a Java 1.7 jar file, and a Python 3.6
implementation exist to generate and parse these formats.

Getting Started
=====

## C++11 library

* See the [C++ README](cpp/README.md).

## Java 1.8 jar

* See the [Java README](java/README.md).

## Python 3.6

* See the [Python README](python/README.md).
