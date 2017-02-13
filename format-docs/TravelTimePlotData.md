# Travel-Time Plot Data Object Specification

## Description

The Travel-Time Plot Data object is an object designed to define the information
returned from a travel time plot request.  Travel-Time Plot Data uses the
[JSON standard](http://www.json.org).

## Usage
Travel-Time Plot Data is intended for use as part of a seismic travel time
information service

## Output
```json
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
```
Reciever
## Glossary

**Output Values:**

The following are values that are **required** be provided as part of the
travel time plot data.

* MaximumTravelTime - A number containing the maximum travel time for all
branches in degrees
* Branches - An array of [TravelTimePlotDataBranch](TravelTimePlotDataBranch.md)
objects containing all the valid travel time branches.
