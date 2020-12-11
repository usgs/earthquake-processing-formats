# Travel-Time Plot Branch Sample Object Specification

## Description

The Travel-Time Plot Branch Sample object is an object designed to define the
information for a single sample returned as part of a travel time plot request.  
Travel-Time Plot Branch Sample uses the [JSON standard](http://www.json.org).

## Usage

Travel-Time Plot Branch Sample is intended for use as part of a seismic travel
time information service

## Output

```json
    {
      "Distance" : Number,
      "TravelTime" : Number,
      "StatisticalSpread" : Number,
      "Observability" : Number,
      "RayParameter" : Number
    }
```

## Glossary

**Output Values:**

The following are values that are **required** be provided as part of the
travel time plot data sample.

* Distance - A number containing the source-receiver distance in degrees.
* TravelTime - A number containing the travel time in seconds.

**Optional Values:**

The following are supplementary values that **may or may not** be provided as
part of a Travel-Time Plot Branch Sample Object.

* StatisticalSpread - A number containing the statistical spread in seconds.
* Observability - A number containing the relative observability.
* RayParameter - A number containing the ray parameter in seconds/degree.
