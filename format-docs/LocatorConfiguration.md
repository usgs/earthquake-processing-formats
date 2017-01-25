# Locator Configuration Format Specification

## Description

Locator Configuration is a format designed to encode the specific information
for configuring the locator algorithm.  Locator Configuration uses the
[JSON standard](http://www.json.org).

## Usage
Location is intended for use as part of an input/output message for the location
processing algorithm.

## Output
```json
    {
      "Type" : String,
      "EarthModel" : String,
      "IsLocationNew"  : Boolean,
      "IsLocationHeld" : Boolean,   
      "IsDepthHeld"    : Boolean,  
      "IsBayesianDepth": Boolean,  
      "BayesianDepth"  : Number,
      "BayesianSpread" : Number,
      "UseRSTT" : Boolean,
      "UseSVD"  : boolean,  
      "Data" :
      [
        {
          "ID"  : String,
          "Use" : Boolean
        },
        ...
      ]   
    }
```

## Glossary
**Required Values:**
* Type - A String containing the name of the algorithm this configuration is
valid for. Ex. "RayLoc".
* EarthModel - A String containing the Travel Time Earth Model to use.

**Optional  Values:**

The following are supplementary values that **may or may not** be provided.
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
* UseRSTT - A boolean flag that indicates whether the RSTT regional travel-time
model is to be used.
* UseSVD - A boolean flag that indicates whether the singular valued
decomposition logic is to be used.
*  Data - An array of objects containing pick identifiers and use flags.
