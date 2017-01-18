# Location Format Specification

## Description

Location is a format designed to encode the specific information for an
earthquake event location.  Location uses the
[JSON standard](http://www.json.org).

## Usage
Location is intended for use as an input/output message for seismic processing
algorithms.

## Output
```json
    {
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
      "SuggestedInputDepth" : Number,
      "SuggestedDepthRange" : Number,
      "DataImportance" : Number,
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
      "Data" :
      [
        {
          "ID"        : String,
          "Site"      :
          {
             "SiteID"    : String,
             "Station"   : String,
             "Channel"   : String,
             "Network"   : String,
             "Location"  : String
          },
          "Time"            : ISO8601,
          "PickedPhase"     : String,
          "AssociatedPhase" : String,
          "LocatedPhase"    : String,
          "Residual" : Number,
          "Distance" : Number,
          "Azimuth"  : Number,
          "Weight"   : Number,
          "Importance" : Number
        },
        ...
      ]
    }
```

## Glossary
Required (Input) Values:
* Hypocenter - An object containing the hypocenter of the Location, see
[Hypocenter](Hypocenter.md).
Data - An array of [Pick](Pick.md) objects associated with this Location.

Optional (Output) Values:

The following are supplementary values that **may or may not** be provided by
various algorithms.
* NumberOfAssociatedStations - A number that indicates how many stations were
associated with the location.
* NumberOfAssociatedPhases - A number that indicates how many phases were
associated with the location.
* NumberOfUsedStations - A number that indicates how many stations were
used in the location.
* NumberOfUsedPhases - A number that indicates how many phases were
used in the location.
* Gap - A number containing the largest azmuthal gap in degrees.
* SecondaryGap - A number containing the second largest azmuthal gap in degrees.
* MinimumDistance - The minimum distance to the closest station in degrees
* RMS - A number that indicates the Standard Error of the residual in seconds.
* Quality - A string containing the quality flag.
* SuggestedInputDepth - A number containing the suggested input depth in
kilometers
* SuggestedDepthRange - A number containing the suggested constrained depth
range (+\-) in kilometers
* DataImportance - A number containing the data importance from the baysian
depth constraint
* ErrorEllipse - An object containing the error ellipse of the Location, see
[ErrorEllipse](ErrorEllipse.md)
