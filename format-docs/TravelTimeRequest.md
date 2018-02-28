# Travel-Time Request Object Specification

## Description

The Travel-Time Request object is an object designed to define a seismic travel
time request and the information returned.  Travel-Time uses the
[JSON standard](http://www.json.org).

## Usage
Travel-Time Request is intended for use as part of a seismic travel time
information service

## Lookup Request
```json
    {
      "Request" :
      {
        "Type"      : String,
        "Distance"  : Number,
        "Elevation" : Number,
        "Latitude"  : Number,
        "Longitude" : Number
      },
      "Data" :
      [
        {
          "Type"       : String,
          "Phase"      : String,
          "TravelTime" : Number,
          "DistanceDerivative" : Number,
          "DepthDerivative"    : Number,
          "RayDerivative"      : Number,
          "StatisticalSpread"  : Number,
          "Observability"      : Number,
          "TeleseismicPhaseGroup" : Number,
          "AuxiliaryPhaseGroup"   : Number,
          "LocationUseFlag"       : Boolean,
          "AssociationWeightFlag" : Boolean
        }
    ]
    }
```

## Plot Request
```json
    {
      "Request" :
      {
        "Type"       : String
      },
      "Data" :
      {
        "Type"              : String,
        "MaximumTravelTime" : Number,
        "Branches" :
        [
          {
            "Phase" : String,
            "Samples" :
            [
              {
                "Distance" : Number,
                "TravelTime" : Number,
                "StatisticalSpread" : Number,
                "Observability" : Number              
              },
              ...
            ]
          },
          ...
        ]
      }
    }
```

## Glossary
**Required Input Values:**

These are the values **required** to define a Travel-Time request.
* Type - A string indicating the type of request, "Standard", "Plot", or
"PlotStatistics". The default is "Standard"
* Distance -  A number containing the source-receiver distance in
degrees.
* Elevation - A number containing the receiver elevation relative to
the WGS84 datum in kilometers.

**Optional Input Values:**

* Latitude - A number containing the geographic receiver latitude in
degrees.
* Longitude - A number containing the geographic receiver longitude in
degrees.

**Output Values:**

The following are values that are **required** be provided as part of a the
travel time request information returned.

* Data - An array of [TravelTimeData](TravelTimeData.md) or
[TravelTimePlotData](TravelTimePlotData.md) objects containing the requested
travel time information.
