# Travel-Time Plot Request Object Specification

## Description

The Travel-Time Plot Request object is an object designed to define a seismic travel
time request and the information returned, optimized for plotting travel time curves.  Travel-Time uses the
[JSON standard](http://www.json.org).

## Usage
Travel-Time Pot Request is intended for use as part of a seismic travel time
information service

## Lookup Request

```json
    {
      "Source" :
      {
        "Latitude" : Number,
        "Longitude": Number,
        "Depth" : Number
      },
      "EarthModel" : String,
      "PhaseTypes" :
      [
        String, ...
      ],
      "ReturnAllPhases" : Boolean,
      "ReturnBackBranches" : Boolean,
      "ConvertTectonic" : Boolean,
      "Response" :
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
          ]
        },
      ]
    }
```


## Glossary

**Required Input Values:**

These are the values **required** to define a Travel-Time request.

* Source -  A [TravelTimeSource](TravelTimeSource.md) object containing the geographic source information
* Receivers - An array of [TravelTimeReceiver](TravelTimeReceiver.md) objects containing the geographic distance (or receiver information) to request travel time information for.

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

* Data - An array of [TravelTimeReceiver](TravelTimeReceiver.md) objects containing the receiver and requested travel time information.
