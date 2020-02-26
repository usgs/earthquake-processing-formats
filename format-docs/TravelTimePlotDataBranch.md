# Travel-Time Plot Data Branch Object Specification

## Description

The Travel-Time Plot Data Branch object is an object designed to define the
information returned for a branch from a travel time plot request.  
Travel-Time Plot Data Branch uses the [JSON standard](http://www.json.org).

## Usage

Travel-Time Plot Data Branch is intended for use as part of a seismic travel
time information service

## Output

```json
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
    }
```

## Glossary

**Output Values:**

The following are values that are **required** be provided as part of the
travel time plot data.

* Phase - A string containing the phase code for the branch.
* Samples - An array of [TravelTimePlotDataSample](TravelTimePlotDataSample.md)
objects containing distance/time samples for the branch.
