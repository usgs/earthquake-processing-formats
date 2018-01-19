# Processing Formats

The US Geological Survey (USGS) National Earthquake Information Center (NEIC)
defines a number of data exchange formats to communicate seismic event processing
information between seismic data services and/or algorithms. These formats
are defined using objects as defined in the [JSON standard](http://www.json.org).

[License](LICENSE.md)

The purpose of this project is to:

1. Define formats to hold data representing the estimates of various types of
seismic event detections.
2. Store the format definitions in a source controlled manner.
3. Host libraries used to generate and parse the formats

## Defined formats:

## Supporting objects:
* [TravelTimeData](format-docs/TravelTimeData.md) Object - An object that
contains information about travel time data returned as part of a travel time
request.

## Supported Languages:
Currently a library written in C++11 is planned, and a Java 1.7 jar file exist
to generate and parse these formats.

Getting Started
=====

## Java 1.7 jar
* See the [Java README](java/README.md).
