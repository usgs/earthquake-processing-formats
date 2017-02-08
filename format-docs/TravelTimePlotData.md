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
* Branches - An array containing all the valid travel time branches.
* Phase - A string containing the phase code for an individual branch.
* Samples - An array containing distance/time samples for an individual branch.
* Distance - A number containing the source-receiver distance in degrees.
* TravelTime - A number containing the travel time in seconds.

**Optional Values:**

The following are supplementary values that **may or may not** be provided as
part of a Travel-Time Plot Data Object.

* StatisticalSpread - A number containing the statistical spread in seconds.
* Observability - A number containing the relative observability.
