# Source Object Specification

## Description

The Source object is an object designed to define the originating seismic
organization that produced a [Pick](Pick.md).
Site uses the [JSON standard](http://www.json.org).

## Usage
Source is intended for use as part of the [Pick](Pick.md) Format in input/output
messages for seismic processing algorithms.

## Output
```json
    {
      "AgencyID" : String,
      "Author"   : String,
      "Type"     : String
    }
```

## Glossary
**Required Values:**

These are the values **required** to define a Source
* AgencyID - A string containing the originating agency FDSN ID.
* Author - A string containing the source author.
* Type - A string indicating the author type, valid values are: "LocalHuman",
"LocalAutomatic", "ContributedHuman" and "ContributedAutomatic".
