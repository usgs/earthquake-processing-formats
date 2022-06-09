#!/usr/bin/env python3
# -*- coding: utf-8 -*-


# package imports
import processingformats.pick

# stdlib imports
import json
import datetime


class LocationRequest:
    """
    A conversion class used to create, parse, and validate location request data
    """

    # JSON Keys
    TYPE_KEY = "Type"  # Required
    ID_KEY = "ID"  # Optional
    SOURCE_KEY = "Source"  # Optional
    EARTHMODEL_KEY = "EarthModel"  # Optional
    SLABRESOLUTION_KEY = "SlabResolution"  # Optional
    SOURCEORIGINTIME_KEY = "SourceOriginTime"  # Required
    SOURCELATITUDE_KEY = "SourceLatitude"  # Required
    SOURCELONGITUDE_KEY = "SourceLongitude"  # Required
    SOURCEDEPTH_KEY = "SourceDepth"  # Required
    INPUTDATA_KEY = "InputData"  # Required
    ISLOCATIONNEW_KEY = "IsLocationNew"  # Optional
    ISLOCATIONHELD_KEY = "IsLocationHeld"  # Optional
    ISDEPTHHELD_KEY = "IsDepthHeld"  # Optional
    ISBAYESIANDEPTH_KEY = "IsBayesianDepth"  # Optional
    BAYESIANDEPTH_KEY = "BayesianDepth"  # Optional
    BAYESIANSPREAD_KEY = "BayesianSpread"  # Optional
    USESVD_KEY = "UseSVD"  # Optional
    REASSESSINITIALPHASEIDS_KEY = "ReassessInitialPhaseIDs"  # Optional
    OUTPUTDATA_KEY = "OutputData"  # Contain output from locator

    # Intialize members
    def __init__(
        self,
        newID=None,
        newSource=None,
        newType=None,
        newEarthModel=None,
        newSlabResolution=None,
        newSourceLatitude=None,
        newSourceLongitude=None,
        newSourceOriginTime=None,
        newSourceDepth=None,
        newInputData=None,
        newIsLocationNew=None,
        newIsLocationHeld=None,
        newIsDepthHeld=None,
        newIsBayesianDepth=None,
        newBayesianDepth=None,
        newBayesianSpread=None,
        newReassessInitialPhaseIDs=None,
        newUseSVD=None,
    ):
        """Initializes the pick object. Constructs empty object if all are none

        newID: a string containing the ID
        newSource: a processingformats.source.Source containing desired source (and supporting info)
        newType: a type identifier for this Location Request
        newEarthModel: an earth model for this Location Request, default ak135
        newSlabResolution: an earth model for this Location Request, default 2spd
        newSourceLatitude: a double containing the source latitude in degrees
        newSourceLongitude: a double containing the source longitude in degrees
        newSourceOriginTime: a Datetime containing the source time
        newSourceDepth: a double containing the source depth in kilometers
        newInputData: a vector of input Pick objects for this Location Result
        newIsLocationNew: a boolean indicating whether the location is new
        newIsLocationHeld: a boolean indicating whether the location is held
        newIsDepthHeld: a boolean indicating whether the depth is held
        newIsBayesianDepth: a boolean indicating whether the depth is bayesian
        newBayesianDepth: a double containing the bayesian depth
        newBayesianSpread: a double containing the bayesian spread
        newUseSVD: a boolean indicating whether use SVD
        newReassessInitialPhaseIDs: a boolean indicating whether reassess inital phase identifications
        """

        self.outputData = None

        # Required Keys
        if newType is not None:
            self.type = newType

        if newSourceLatitude is not None:
            self.sourceLatitude = newSourceLatitude

        if newSourceLongitude is not None:
            self.sourceLongitude = newSourceLongitude

        if newSourceDepth is not None:
            self.sourceDepth = newSourceDepth

        if newSourceOriginTime is not None:
            self.sourceOriginTime = newSourceOriginTime

        if newInputData is not None:
            self.inputData = newInputData

        # Optional Keys
        if newID is not None:
            self.id = newID

        if newSource is not None:
            self.source = newSource
        else:
            self.source = processingformats.source.Source()

        if newEarthModel is not None:
            self.earthModel = newEarthModel
        else:
            self.earthModel = "ak135"

        if newSlabResolution is not None:
            self.slabResolution = newSlabResolution
        else:
            self.slabResolution = "2spd"

        if newIsLocationNew is not None:
            self.isLocationNew = newIsLocationNew

        if newIsLocationHeld is not None:
            self.isLocationHeld = newIsLocationHeld

        if newIsDepthHeld is not None:
            self.isDepthHeld = newIsDepthHeld

        if newIsBayesianDepth is not None:
            self.isBayesianDepth = newIsBayesianDepth

        if newBayesianDepth is not None:
            self.bayesianDepth = newBayesianDepth

        if newBayesianSpread is not None:
            self.bayesianSpread = newBayesianSpread

        if newUseSVD is not None:
            self.useSVD = newUseSVD

        if newReassessInitialPhaseIDs is not None:
            self.reassessInitialPhaseIDs = newReassessInitialPhaseIDs

    def fromJSONString(self, JSONString):
        """Populates object from a JSON formatted string

        JSONString: a required string containing the JSON formatted text
        """
        JSONObject = json.loads(JSONString)
        self.fromDict(JSONObject)

    def fromDict(self, aDict):
        """Populates object from a dictionary

        aDict: a required dictionary
        """

        # Required Keys
        try:
            self.type = aDict[self.TYPE_KEY]
            self.sourceLatitude = aDict[self.SOURCELATITUDE_KEY]
            self.sourceLongitude = aDict[self.SOURCELONGITUDE_KEY]
            self.sourceDepth = aDict[self.SOURCEDEPTH_KEY]
            timeString = aDict[self.SOURCEORIGINTIME_KEY][:-1] + "000Z"
            self.sourceOriginTime = datetime.datetime.strptime(
                timeString, "%Y-%m-%dT%H:%M:%S.%fZ"
            )

            if self.INPUTDATA_KEY in aDict:
                aDataList = aDict[self.INPUTDATA_KEY]
                if aDataList:
                    self.inputData = []

                    for aData in aDataList:
                        newPick = processingformats.pick.Pick()
                        newPick.fromDict(aData)
                        self.inputData.append(newPick)

        except (ValueError, KeyError, TypeError) as e:
            print("Dictionary format error, missing required keys: %s" % e)

        # Optional Keys
        if self.ID_KEY in aDict:
            self.id = aDict[self.ID_KEY]

        if self.SOURCE_KEY in aDict:
            self.source.fromDict(aDict[self.SOURCE_KEY])

        if self.EARTHMODEL_KEY in aDict:
            self.earthModel = aDict[self.EARTHMODEL_KEY]
        else:
            self.earthModel = "ak135"

        if self.SLABRESOLUTION_KEY in aDict:
            self.slabResolution = aDict[self.SLABRESOLUTION_KEY]
        else:
            self.slabResolution = "2spd"

        if self.ISLOCATIONNEW_KEY in aDict:
            self.isLocationNew = aDict[self.ISLOCATIONNEW_KEY]

        if self.ISLOCATIONHELD_KEY in aDict:
            self.isLocationHeld = aDict[self.ISLOCATIONHELD_KEY]

        if self.ISDEPTHHELD_KEY in aDict:
            self.isDepthHeld = aDict[self.ISDEPTHHELD_KEY]

        if self.ISBAYESIANDEPTH_KEY in aDict:
            self.isBayesianDepth = aDict[self.ISBAYESIANDEPTH_KEY]

        if self.BAYESIANDEPTH_KEY in aDict:
            self.bayesianDepth = aDict[self.BAYESIANDEPTH_KEY]

        if self.BAYESIANSPREAD_KEY in aDict:
            self.bayesianSpread = aDict[self.BAYESIANSPREAD_KEY]

        if self.USESVD_KEY in aDict:
            self.useSVD = aDict[self.USESVD_KEY]

        if self.REASSESSINITIALPHASEIDS_KEY in aDict:
            self.reassessInitialPhaseIDs = aDict[self.REASSESSINITIALPHASEIDS_KEY]

    def toJSONString(self):
        """Converts object to a JSON formatted string

        Returns: JSON formatted message as a string
        """
        JSONObject = self.toDict()

        return json.dumps(JSONObject, ensure_ascii=False)

    def toDict(self):
        """Converts object to a dictionary

        Returns: the dictionary
        """

        aDict = {}

        # Required Keys
        try:
            aDict[self.TYPE_KEY] = self.type
            aDict[self.SOURCELATITUDE_KEY] = self.sourceLatitude
            aDict[self.SOURCELONGITUDE_KEY] = self.sourceLongitude
            aDict[self.SOURCEDEPTH_KEY] = self.sourceDepth
            timestring = self.sourceOriginTime.isoformat(timespec="milliseconds") + "Z"
            aDict[self.SOURCEORIGINTIME_KEY] = timestring

            aDataList = []
            if self.inputData and len(self.inputData) > 0:
                for aData in self.inputData:
                    aDataList.append(aData.toDict())

            aDict[self.INPUTDATA_KEY] = aDataList
        except (NameError, AttributeError) as e:
            print("Missing required data error: %s" % e)

        # Optional Keys
        if hasattr(self, "id"):
            aDict[self.ID_KEY] = self.id

        if hasattr(self, "source"):
            if not self.source.isEmpty():
                aDict[self.SOURCE_KEY] = self.source.toDict()

        if hasattr(self, "earthModel"):
            aDict[self.EARTHMODEL_KEY] = self.earthModel
        else:
            aDict[self.EARTHMODEL_KEY] = "ak135"

        if hasattr(self, "slabResolution"):
            aDict[self.SLABRESOLUTION_KEY] = self.slabResolution
        else:
            aDict[self.SLABRESOLUTION_KEY] = "2spd"

        if hasattr(self, "isLocationNew"):
            aDict[self.ISLOCATIONNEW_KEY] = self.isLocationNew

        if hasattr(self, "isLocationHeld"):
            aDict[self.ISLOCATIONHELD_KEY] = self.isLocationHeld

        if hasattr(self, "isDepthHeld"):
            aDict[self.ISDEPTHHELD_KEY] = self.isDepthHeld

        if hasattr(self, "isBayesianDepth"):
            aDict[self.ISBAYESIANDEPTH_KEY] = self.isBayesianDepth

        if hasattr(self, "bayesianDepth"):
            aDict[self.BAYESIANDEPTH_KEY] = self.bayesianDepth

        if hasattr(self, "bayesianSpread"):
            aDict[self.BAYESIANSPREAD_KEY] = self.bayesianSpread

        if hasattr(self, "useSVD"):
            aDict[self.USESVD_KEY] = self.useSVD

        if hasattr(self, "reassessInitialPhaseIDs"):
            aDict[self.REASSESSINITIALPHASEIDS_KEY] = self.reassessInitialPhaseIDs

        return aDict

    def isValid(self):
        """Checks to see if object is valid

        Returns: true if object is valid, false otherwise
        """
        errorList = self.getErrors()

        return not errorList

    def getErrors(self):
        """Gets a list of object validation errors

        Returns: a list of string containing the validation error messages
        """
        errorList = []

        # required keys

        # sourceLatitude
        try:
            if self.sourceLatitude < -90 or self.sourceLatitude > 90:
                errorList.append(
                    "Source Latitude in LocationRequest Class not in the range of -90 to 90."
                )
        except (NameError, AttributeError):
            errorList.append("No Source Latitude in LocationRequest Class.")

        # sourceLongitude
        try:
            if self.sourceLongitude < -180 or self.sourceLongitude > 180:
                errorList.append(
                    "Source Longitude in LocationRequest Class not in range of -180 to 180."
                )
        except (NameError, AttributeError):
            errorList.append("No Source Longitude in LocationRequest Class")

        # sourceDepth
        try:
            if self.sourceDepth < -100 or self.sourceDepth > 1500:
                errorList.append(
                    "Source Depth in LocationRequest Class not in the range of -100 to 1500"
                )
        except (NameError, AttributeError):
            errorList.append("No Source Depth in LocationRequest Class.")

        # sourceOriginTime
        try:
            self.sourceOriginTime
        except (NameError, AttributeError):
            errorList.append("No Source Origin Time in LocationRequest Class.")

        # inputData
        if hasattr(self, "inputData"):
            if self.inputData and len(self.inputData) > 0:
                for anInput in self.inputData:
                    if not anInput.isValid():
                        errorList.append("Invalid Input in LocationRequest Class.")

        if hasattr(self, "source"):
            if not self.source.isEmpty():
                if not self.source.isValid():
                    errorList.append("Invalid Source in LocationRequest Class.")

        return errorList
