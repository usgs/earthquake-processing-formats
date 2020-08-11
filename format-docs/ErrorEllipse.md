# Error Ellipse Object Specification

## Description

The ErrorEllipse object is an object designed to define an Error Ellipse as
part of a [Location](Location.md).  ErrorEllipse uses the
[JSON standard](http://www.json.org).

## Usage

ErrorEllipse is intended for use as part of the [Location](Location.md) Format
in input/output messages for seismic processing algorithms.

## Output

```json
    {
      "E0" :
      {
          "Error"   : Number,
          "Azimuth" : Number,
          "Dip"     : Number
      },
      "E1" :
      {
          "Error"   : Number,
          "Azimuth" : Number,
          "Dip"     : Number
      },
      "E2" :
      {
          "Error"   : Number,
          "Azimuth" : Number,
          "Dip"     : Number
      },
      "MaximumHorizontalProjection" : Number,
      "MaximumVerticalProjection"   : Number,
      "EquivalentHorizontalRadius"  : Number
    }
```

## Glossary

**Required Values:**

* E0 - Largest (Major) error semi-axis.
* E1 - Second Largest (Semi-Major) error semi-axis.
* E2 - Smallest (Minor) error semi-axis.
* Error - A number containing the length of the axis of the error ellipsoid in
kilometers.
* Azimuth - A number containing the azimuth of the axis of the error ellipsoid  
in degrees.
* Dip - A number containing the dip of the axis of error ellipsoid in
degrees.

**Optional Values:**

The following are supplementary values that **may or may not** be provided by
various algorithms.

* MaximumHorizontalProjection -  A number containing the horizontal projection
of the error ellipsoid in kilometers.
* MaximumVerticalProjection -  A number containing the vertical projection of the
error ellipsoid in km in kilometers.
* EquivalentHorizontalRadius - A number containing the equivalent radius of the
horizontal error ellipsoid in kilometers.
