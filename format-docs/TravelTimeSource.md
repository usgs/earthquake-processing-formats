# Travel-Time Source Object Specification

## Description

The Travel-Time Source object is an object designed to define a geographic source for travel time Sources.  Travel-Time uses the
[JSON standard](http://www.json.org).

## Usage
Travel-Time Source is intended for use as part of a seismic travel time
information service

## Travel-Time Source

```json
    {
        "Latitude" : Number,
        "Longitude": Number,
        "Depth" : Number
    }
```

## Glossary

**Required Values:**

These are the values **required** to define a Travel-Time Source.

* Latitude - A number containing the geographic source latitude in
degrees.
* Longitude - A number containing the geographic source longitude in
degrees.
* Depth - A number containing the geographic source depth in
kilometers.