# Travel-Time Receiver Object Specification

## Description

The Travel-Time Receiver object is an object designed to define a geographic Receiver for travel time requests.  Travel-Time Receiver uses the
[JSON standard](http://www.json.org).

## Usage
Travel-Time Receiver is intended for use as part of a seismic travel time
information service

## Travel-Time Receiver

```json
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
```

## Glossary

**Required Values:**

These are the values **required** to define a Travel-Time Receiver.

* ID - A string containing an unique ID for this reciver
* Distance -  A number containing the source-receiver distance in
degrees.
* Elevation - A number containing the receiver elevation relative to
the WGS84 datum in kilometers.

**Optional Values:**
* Latitude - A number containing the geographic receiver latitude in
degrees.
* Longitude - A number containing the geographic receiver longitude in
degrees.

**Output Values:**

The following are values that are **required** be provided as part of a 
travel time receiver information returned.

* Branches - An array of [TravelTimeData](TravelTimeData.md) objects containing the requested
travel time information for this geographic receiver.
