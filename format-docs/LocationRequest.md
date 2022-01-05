# Location Request Format Specification

## Description

Locator Request is a format designed to encode the specific information
for configuring the locator algorithm.  Locator Request uses the
[JSON standard](http://www.json.org).

## Usage

Location Request is intended for use as part of an input/output message for the
location processing algorithm.

## Output

```json
    {
      "Request" :
      {
        "Type" : String,
        "ID" : String,
        "Source" :
        {
          "AgencyID" : String,
          "Author"   : String,
          "Type"     : String
        },
        "EarthModel" : String,
        "SlabResolution" : String,
        "SourceOriginTime" : Number,
        "SourceLatitude"  : Number,
        "SourceLongitude" : Number,
        "SourceDepth" : Number,
        "InputData" :
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
              "Type"     : String
            },
            "Time"     : ISO8601,
            "Affinity" : Number,
            "Quality"  : Number,
            "Use"      : Boolean,
            "PickedPhase"     : String,
            "AssociatedPhase" : String,
            "LocatedPhase"    : String
          },
          ...
        ],
        "IsLocationNew"  : Boolean,
        "IsLocationHeld" : Boolean,
        "IsDepthHeld"    : Boolean,  
        "IsBayesianDepth": Boolean,  
        "BayesianDepth"  : Number,
        "BayesianSpread" : Number,
        "UseSVD"         : boolean,
        "ReassessInitialPhaseIDs" : boolean 
      },
      "OutputData" : { }  
    }
```

## Glossary

**Required Input Values:**

* Type - A String containing the name of the algorithm this request is
valid for. Ex. "RayLoc".
* SourceOriginTime - A string containing the UTC origin time of this hypocenter, in
the ISO8601 format `YYYY-MM-DDTHH:MM:SS.SSSZ`.
* SourceLatitude - A number that identifies the geographic source latitude of  
this hypocenter in degrees.
* SourceLongitude - A number that identifies the geographic source longitude of  
this hypocenter in degrees.
* SourceDepth - A decimal number that identifies the depth of this hypocenter in
kilometers relative to the WGS84 datum.
* InputData - An array of [Pick](Pick.md) objects associated with this Location.

**Optional Input Values:**

The following are supplementary values that **may or may not** be provided.

* ID - A String containing the identifier for the location request.
* Source - An object containing the source of the location result, see 
[Source](Source.md).
* EarthModel - A String containing the Travel Time Earth Model to use, defaults to 'ak135'
* SlabResolution - A String containing the Slab Model to use, defaults to '2spd'
* IsLocationNew - A boolean flag that indicates if the location has been changed
outside the locator.
* IsLocationHeld - A boolean flag that indicates whether the location can be
changed by the locator.
* IsDepthHeld - A boolean flag that indicates whether the depth can be changed
by the locator.
* IsBayesianDepth - A boolean flag that indicates if the bayesian depth and
spread are to be used.
* BayesianDepth - A number that indicates the bayesian depth in kilometers
relative to the WGS84 datum.
* BayesianSpread - A number that indicates the standard deviation of the
bayesian depth in kilometers.
* UseSVD - A boolean flag that indicates whether the singular valued
decomposition logic is to be used.
* ReassessInitialPhaseIDs - A boolean flag that indicates whether to reasses
the given phase id's in the InputData.

**Output Values:**

The following are values that are **required** be provided as part of a the
location information returned.

* OutputData - A [LocationResult](LocationResult.md) object containing the
requested location information.
