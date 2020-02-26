# Location Result Format Specification

## Description

Location Result is a format designed to encode the specific information for an
earthquake event location.  Location uses the
[JSON standard](http://www.json.org).

## Usage

Location Result is intended for use as an input/output message for seismic
processing algorithms.

## Output

```json
    {
      "ID" : String,
      "Source" :
      {
          "AgencyID" : String,
          "Author"   : String,
          "Type"     : String
      },
      "Hypocenter"  :
      {
          "Latitude"        : Number,
          "Longitude"       : Number,
          "Depth"           : Number,
          "Time"            : ISO8601,
          "LatitudeError"   : Number,
          "LongitudeError"  : Number,
          "DepthError"      : Number,
          "TimeError"       : Number
      },
      "NumberOfAssociatedStations" : Number,
      "NumberOfAssociatedPhases"   : Number,
      "NumberOfUsedStations"       : Number,
      "NumberOfUsedPhases"         : Number,
      "Gap"             : Number,  
      "SecondaryGap"    : Number,  
      "MinimumDistance" : Number,
      "RMS"             : Number,  
      "Quality"         : String,
      "BayesianDepth"   : Number,
      "BayesianRange"   : Number,
      "DepthImportance" : Number,
      "LocatorExitCode" : String,
      "ErrorEllipse" :
      {
          "MaximumHorizontalProjection" : Number,
          "MaximumVerticalProjection"   : Number,
          "EquivalentHorizontalRadius"  : Number,
          "E0" :
          {
              "Error"   : Number,
              "Azimuth" : Number,
              "Dip"     : Number
          },
          "E1" :
          {
              "Error"   : Number,
              "Azimuth" : Number,
              "Dip"     : Number
          },
          "E2" :
          {
              "Error"   : Number,
              "Azimuth" : Number,
              "Dip"     : Number
          }
      },
      "SupportingData" :
      [
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
          "Time"         : ISO8601,
          "Used"         : Boolean,
          "LocatedPhase" : String,
          "Residual"     : Number,
          "Distance"     : Number,
          "Azimuth"      : Number,
          "Weight"       : Number,
          "Importance"   : Number
        },
        ...
      ]
    }
```

## Glossary

**Required Values:**

* Hypocenter - An object containing the hypocenter of the Location, see
[Hypocenter](Hypocenter.md).
* SupportingData - An array of [Pick](Pick.md) objects associated with this
Location.

**Optional Values:**

The following are supplementary values that **may or may not** be provided by
various algorithms.

* ID - A String containing the identifier for the location result.
* Source - An object containing the source of the location result, see
[Source](Source.md).
* NumberOfAssociatedStations - A number that indicates how many stations were
associated with the location.
* NumberOfAssociatedPhases - A number that indicates how many phases were
associated with the location.
* NumberOfUsedStations - A number that indicates how many stations were
used in the location.
* NumberOfUsedPhases - A number that indicates how many phases were
used in the location.
* Gap - A number containing the largest azimuthal gap in degrees.
* SecondaryGap - A number containing the second largest azimuthal gap in degrees.
* MinimumDistance - The minimum distance to the closest station in degrees
* RMS - A number that indicates the Standard Error of the residual in seconds.
* Quality - A string containing the quality flag.
* BayesianDepth - A number containing the suggested bayesian depth in
kilometers
* BayesianRange - A number containing the bayesian depth range (+\-) in
kilometers
* DepthImportance - A number containing the importance from the bayesian
depth constraint
* LocatorExitCode - A String containing the locator exit code, allowed values
are: "Success", "DidNotMove", "ErrorsNotComputed", "Failed", and "Unknown"
* ErrorEllipse - An object containing the error ellipse of the Location, see
[ErrorEllipse](ErrorEllipse.md)
