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
        "RequestType"       : String,
        "ReceiverDistance"  : Number,
        "ReceiverElevation" : Number,
        "ReceiverLatitude"  : Number,
        "ReceiverLongitude" : Number
      },
      "Data" :
      {
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
    }
```

## Plot Request
```json
    {
      "Request" :
      {
        "RequestType"       : String
      },
      "Data" :
      {
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
* RequestType - A string indicating the type of request, "Standard", "Plot", or
"PlotStatistics". The default is "Standard"
* ReceiverDistance -  A number containing the source-receiver distance in
degrees.
* ReceiverElevation - A number containing the receiver elevation relative to
the WGS84 datum in kilometers.

**Optional Input Values:**

* ReceiverLatitude - A number containing the geographic receiver latitude in
degrees.
* ReceiverLongitude - A number containing the geographic receiver longitude in
degrees.

**Output Values:**

The following are values that are **required** be provided as part of a the
travel time request information returned.

* Data - A [TravelTimeData](TravelTimeData.md) or
[TravelTimePlotData](TravelTimePlotData.md)object containing the requested
travel time information.
