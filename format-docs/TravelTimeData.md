# Travel-Time Data Object Specification

## Description

The Travel-Time Data object is an object designed to define the information
returned from a travel time request.  Travel-Time Data uses the
[JSON standard](http://www.json.org).

## Usage

Travel-Time Data is intended for use as part of a seismic travel time
information service

## Output

```json
    {
      "Type"       : String,
      "Phase"      : String,
      "TravelTime" : Number,
      "DistanceDerivative" : Number,
      "DepthDerivative"    : Number,
      "RayDerivative"      : Number,
      "StatisticalSpread"  : Number,
      "Observability"      : Number,
      "TeleseismicPhaseGroup" : String,
      "AuxiliaryPhaseGroup"   : String,
      "LocationUseFlag"       : Boolean,
      "AssociationWeightFlag" : Boolean
    }
```

## Glossary

**Output Values:**

The following are values that are **required** be provided as part of the
travel time data.

* Type - A string containing the type of data, always "TTData"
* Phase - A string containing the seismic phase code.
* TravelTime - A number containing the travel time in seconds.
* DistanceDerivative - A number containing the derivative with respect to
distance of the travel time in seconds/degree.
* DepthDerivative - A number containing the derivative with respect to
depth of the travel time in seconds/kilometer.
* RayDerivative - A number containing the derivative with respect to
ray parameter of the distance in degrees/second.
* StatisticalSpread - A number containing the observed travel time scatter in
seconds.
* Observability - A number containing the statistical observability of the
seismic phase.
* TeleseismicPhaseGroup - A String containing the teleseismic phase group
identifier.
* AuxiliaryPhaseGroup - A String containing the auxiliary phase group
identifier.
* LocationUseFlag - A boolean flag indicating whether the phase may be used in
a location.
* AssociationWeightFlag - A boolean flag indicating whether a phase should be
down weighted in association.
