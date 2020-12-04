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
        "Source" :
        {
          "Latitude" : Number,
          "Longitude": Number,
          "Depth" : Number
        },
        "Recievers" : 
        [
          {
            "ID" : String,
            "Elevation" : Number,
            "Distance"  : Number,
            "Latitude"  : Number,
            "Longitude" : Number
          }
        ],
        "EarthModel" : String,
        "PhaseTypes" :
        [
          String, ...
        ],
        "ReturnAllPhases" : Boolean,
        "ReturnBackBranches" : Boolean,
        "ConvertTectonic" : Boolean
      },
      "Response" :
      [
        {
          "ID" : String,
          "Elevation" : Number,
          "Distance"  : Number,
          "Latitude"  : Number,
          "Longitude" : Number,
          "Branches" : 
          [
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
          ]
        }
    ]
    }
```


## Glossary

**Required Input Values:**

These are the values **required** to define a Travel-Time request.

* Source -  A [TravelTimeSource](TravelTimeSource.md) object containing the geographic source information
* Recievers - An array of [TravelTimeReciever](TravelTimeReciever.md) objects containing the geographic distance (or reciever information) to request travel time information for.

**Optional Input Values:**

* EarthModel - A string containing the earth model to use, defaults to the
AK135 earth model.
* PhaseTypes - An array of strings listing the phase types desired. Defaults to all phases.
* ReturnAllPhases - A boolean flag that indicates whether to return all phases,
defaults to false.
* ReturnBackBranches - A boolean flag that indicates whether to return all
arrivals of all phases, defaults to false.
* ConvertTectonic - A boolean flag that indicates whether to convert tectonic
phases (Pb to Pg and Sb to Sg), defaults to false.

**Output Values:**

The following are values that are **required** be provided as part of a the
travel time request information returned.

* Data - An array of [TravelTimeReciever](TravelTimeReciever.md) objects containing the reciever and requested travel time information.
