# Hypocenter Object Specification

## Description

The Hypocenter object is an object designed to define a Hypocenter as part of a
[Location](Location.md).  Hypocenter uses the
[JSON standard](http://www.json.org).

## Usage
Hypocenter is intended for use as part of the [Location](Location.md) Format
in input/output messages for seismic processing algorithms.

## Output
```json
    {
      "Latitude"        : Number,
      "Longitude"       : Number,
      "Depth"           : Number,         
      "Time"            : ISO8601,
      "LatitudeError"   : Number,
      "LongitudeError"  : Number,
      "DepthError"      : Number,
      "TimeError"       : Number        
    }
```

## Glossary
**Required (Input) Values:**
* Latitude - A decimal number that identifies the latitude of this hypocenter in
degrees.
* Longitude - A decimal number that identifies the longitude of this hypocenter
in degrees.
* Depth - A decimal number that identifies the depth of this hypocenter in
kilometers.
* Time - A string containing the UTC origin time of this hypocenter, in the
ISO8601 format `YYYY-MM-DDTHH:MM:SS.SSSZ`.

**Optional (Output) Values:**

The following are supplementary values that **may or may not** be provided by
various algorithms.
* LatitudeError - A decimal number that identifies the error of the latitude of
this hypocenter in kilometers.
* LongitudeError - A decimal number that identifies the error of the longitude
of this hypocenter in kilometers.
* DepthError - A decimal number that identifies the depth error of this
hypocenter in kilometers.
* TimeError - A decimal number that identifies the error of the time in seconds.
