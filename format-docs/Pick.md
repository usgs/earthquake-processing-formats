# Pick Object Specification

## Description

The Pick object is an object designed to define a pick as part of a
[Location](Location.md).

## Usage
Pick is intended for use as part of the [Location](Location.md) Format
in input/output messages for seismic processing algorithms.

## Output
```json
    {
      "ID"        : String,
      "Site"      :
      {
         "Station"   : String,
         "Channel"   : String,
         "Network"   : String,
         "Location"  : String,
         "Latitude"  : Number,
         "Longitude" : Number,
         "Elevation" : Number
      },
      "Source" :
      {
        "AgencyID" : String,
        "Author"   : String,
        "Type"     : Number
      },
      "Time"     : ISO8601,
      "Affinity" : Number,
      "Quality"  : Number,
      "Use"      : Boolean,
      "PickedPhase"     : String,
      "AssociatedPhase" : String,
      "LocatedPhase"    : String,
      "Residual" : Number,
      "Distance" : Number,
      "Azimuth"  : Number,
      "Weight"   : Number,
      "Importance" : Number
    }
```

## Glossary
**Input Values:**

* ID - A string containing an unique identifier for this pick.
* Site - An object containing the station the pick was made at, see
[Site](Site.md).
* Source - An object containing the source of the pick, see [Source](Source.md).
* Time - A string containing the UTC arrival time of the phase that was picked,
in the ISO8601 format `YYYY-MM-DDTHH:MM:SS.SSSZ`.
* Affinity - A number indicating how strongly the pick phase identification
should be used.
* Quality - A number indicating the pick quality.
* Use - A boolean flag indicating whether the pick should be used in a location.

**Output Values:**

The following are supplementary values that **may or may not** be provided by
various algorithms.

* PickedPhase - A string that identifies the seismic phase that the picking
algorithm identified.
* AssociatedPhase - A string that identifies the seismic phase that the
association algorithm identified.
* LocatedPhase - A string that identifies the seismic phase the locator selected.
* Residual - A number containing the phase residual in seconds.
* Distance - A number containing the station distance in degrees.
* Azimuth - A number containing the station azimuth in degrees.
* Weight - A number containing the pick weight.
* Importance - A number containing the data importance for this pick.
