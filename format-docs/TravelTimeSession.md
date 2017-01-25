# Travel-Time Session Specification

## Description

The Travel-Time Session is a format designed to initialize a seismic travel time
request session.  Travel-Time Session uses the
[JSON standard](http://www.json.org).

## Usage
Travel-Time Session is intended for use as part of a seismic travel time
information service

## Output
```json
    {
      "SourceDepth"   : Number,
      "EarthModel"    : String,      
      "PhaseTypes"    :
      [
        String, ...
      ],
      "SourceLatitude"   : Number,
      "SourceLongitude" : Number,
      "UseRSTT" : Boolean
    }
```
Reciever
## Glossary
**Required Input Values:**

These are the values **required** to define a Travel-Time Session.

* SourceDepth -  A number containing the depth of the source relative to the
WGS84 datum in kilometers.

**Optional Values:**

* EarthModel - A string containing the earth model to use, defaults to the
AK135 earth model.
* PhaseTypes - An array of strings listing the phase types desired.
* SourceLatitude - A number containing the geographic source latitude in
degrees.
* SourceLongitude - A number containing the geographic source longitude in
degrees.
* UseRSTT - A boolean flag that indicates whether the RSTT regional travel-time
model is to be used.
