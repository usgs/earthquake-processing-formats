# Travel-Time Request Object Specification

## Description

The Travel-Time Request object is an object designed to define a seismic travel
time request and the information returned.  Travel-Time uses the
[JSON standard](http://www.json.org).

## Usage
Travel-Time Request is intended for use as part of a seismic travel time
information service

## Output
```json
    {
      "ReceiverDistance"    : Number,
      "ReceiverElevation"   : Number,
      "ReceiverLatitude"    : Number,
      "ReceiverLongitude"   : Number,
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
```
Reciever
## Glossary
**Required Input Values:**

These are the values **required** to define a Travel-Time request.

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
* TeleseismicPhaseGroup - A number containing the teleseismic phase group
identifier.
* AuxiliaryPhaseGroup - A number containing the auxiliary phase group
identifier.
* LocationUseFlag - A boolean flag indicating whether the phase may be used in
a location.
* AssociationWeightFlag - A boolean flag indicating whether a phase should be
down weighted in assocation.
