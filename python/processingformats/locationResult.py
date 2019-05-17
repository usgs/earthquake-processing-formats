#!/usr/bin/env python3
# -*- coding: utf-8 -*-


#package imports
import processingformats.hypocenter
import processingformats.errorEllipse

#stdlib import
import json

class LocationResult:
    """
    A conversion class used to create, parse, and validate location processing data
    """
    
    #JSON Keys
    ID_KEY = "ID" #Optional
    HYPOCENTER_KEY = "Hypocenter" #Required
    SUPPORTINGDATA_KEY = "SupportingData" #Required
    ASSOCIATEDSTATIONS_KEY = "NumberOfAssociatedStations" #Optional
    ASSOCIATEDPHASES_KEY = "NumberOfAssociatedPhases" #Optional
    USEDSTATIONS_KEY = "NumberOfUsedStations" #Optional
    USEDPHASES_KEY = "NumberOfUsedPhases" #Optional
    GAP_KEY = "Gap" #Optional
    SECONDARYGAP_KEY = "SecondaryGap" #Optional
    MINIMUMDISTANCE_KEY = "MinimumDistance" #Required
    RMS_KEY = "RMS" #Optional
    QUALITY_KEY = "Quality" #Optional
    BAYESIANDEPTH_KEY = "BayesianDepth" #Optional
    BAYESIANRANGE_KEY = "BayesianRange" #Optional
    DEPTHIMPORTANCE_KEY = "DepthImportance" #Optional
    LOCATOREXITCODE_KEY = "LocatorExitCode" #Optional
    ERRORELLIPSE_KEY = "ErrorEllipse" #Optional
    
    #Constructor. Initializes members to null or provided values
    def __init__ (self, newID = None, newHypocenter = None, newSupportingData = None, 
                  newAssociatedStations = None, newAssociatedPhases = None, 
                  newUsedStations = None, newUsedPhases = None, newGap = None, 
                  newSecondaryGap = None, newMinimumDistance = None, 
                  newRMS = None, newQuality = None, newBayesianDepth = None, 
                  newBayesianRange = None, newDepthImportance = None, 
                  newLocatorExitCode = None, newErrorEllipse = None):
        
        #Required Keys
        if newHypocenter is not None:
            self.hypocenter = newHypocenter
        else:
            self.hypocenter = processingformats.hypocenter.Hypocenter()
            
        #FIXME
        if newSupportingData is not None:
            self.supportingData = newSupportingData
            
        if newMinimumDistance is not None:
            self.minimumDistance = newMinimumDistance
        
        #Optional Keys
        if newID is not None:
            self.id = newID
            
        if newAssociatedStations is not None:
            self.associatedStations = newAssociatedStations
            
        if newAssociatedPhases is not None:
            self.associatedPhases = newAssociatedPhases
            
        if newUsedStations is not None:
            self.usedStations = newUsedStations
            
        if newUsedPhases is not None:
            self.usedPhases = newUsedPhases
            
        if newGap is not None:
            self.gap = newGap
            
        if newSecondaryGap is not None:
            self.secondaryGap = newSecondaryGap
            
        if newRMS is not None:
            self.RMS = newRMS
            
        if newQuality is not None:
            self.quality = newQuality
            
        if newBayesianDepth is not None:
            self.bayesianDepth = newBayesianDepth
            
        if newBayesianRange is not None:
            self.bayesianRange = newBayesianRange
            
        if newDepthImportance is not None:
            self.depthImportance = newDepthImportance
            
        if newLocatorExitCode is not None:
            self.locatorExitCode = newLocatorExitCode
        
        if newErrorEllipse is not None:
            self.errorEllipse = newErrorEllipse
        else:
            self.errorEllipse = processingformats.errorEllipse.ErrorEllipse()
            
    #Populates object from a JSON formatted string
    def fromJSONString (self, JSONString):
        JSONObject = json.loads(JSONString)
        self.fromDict(JSONObject)
        
    #Populates object from a dictionary
    def fromDict(self, aDict):
        
        #Required keys
        try:
            self.hypocenter.fromDict(aDict[self.HYPOCENTER_KEY])
            self.supportingData = aDict[self.SUPPORTINGDATA_KEY]
            self.minimumDistance = aDict[self.MINIMUMDISTANCE_KEY]
        except(ValueError, KeyError, TypeError) as e:
            print("Dictionary format error, missing required keys: %s" % e)
        
        #Optional Keys
        if self.ID_KEY in aDict:
            self.id = aDict[self.ID_KEY]
            
        if self.ASSOCIATEDSTATIONS_KEY in aDict:
            self.associatedStations = aDict[self.ASSOCIATEDSTATIONS_KEY]
            
        if self.ASSOCIATEDPHASES_KEY in aDict:
            self.associatedPhases = aDict[self.ASSOCIATEDPHASES_KEY]
            
        if self.USEDSTATIONS_KEY in aDict:
            self.usedStations = aDict[self.USEDSTATIONS_KEY]
            
        if self.USEDPHASES_KEY in aDict:
            self.usedPhases = aDict[self.USEDPHASES_KEY]
            
        if self.GAP_KEY in aDict:
            self.gap = aDict[self.GAP_KEY]
            
        if self.SECONDARYGAP_KEY in aDict:
            self.secondaryGap = aDict[self.SECONDARYGAP_KEY]
            
        if self.RMS_KEY in aDict:
            self.RMS = aDict[self.RMS_KEY]
            
        if self.QUALITY_KEY in aDict:
            self.quality = aDict[self.QUALITY_KEY]
            
        if self.BAYESIANDEPTH_KEY in aDict:
            self.bayesianDepth = aDict[self.BAYESIANDEPTH_KEY]
            
        if self.BAYESIANRANGE_KEY in aDict:
            self.bayesianRange = aDict[self.BAYESIANRANGE_KEY]
            
        if self.DEPTHIMPORTANCE_KEY in aDict:
            self.depthImportance = aDict[self.DEPTHIMPORTANCE_KEY]
            
        if self.LOCATOREXITCODE_KEY in aDict:
            self.locatorExitCode = aDict[self.LOCATOREXITCODE_KEY]
            
        if self.ERRORELLIPSE_KEY in aDict:
            self.errorEllipse = processingformats.errorEllipse.ErrorEllipse()
            self.errorEllipse.fromDict(aDict[self.ERRORELLIPSE_KEY])
            
    #Converts object to a JSON formatted string
    def toJSONString(self):
        JSONObject = self.toDict()
        
        return json.dumps(JSONObject, ensure_ascii=False)
    
    #Converts object to a dictionary
    def toDict(self):
        
        aDict = {}
        
        #Required Keys
        try:
            aDict[self.HYPOCENTER_KEY] = self.hypocenter.toDict()
            aDict[self.SUPPORTINGDATA_KEY] = self.supportingData
            aDict[self.MINIMUMDISTANCE_KEY] = self.minimumDistance
        except(NameError, AttributeError) as e:
            print("Missing required data error: %s" % e)
            
        #Optional Keys
        if hasattr(self, 'id'):
            aDict[self.ID_KEY] = self.id
        
        if hasattr(self, 'associatedStations'):
            aDict[self.ASSOCIATEDSTATIONS_KEY] = self.associatedStations
            
        if hasattr(self, 'associatedPhases'):
            aDict[self.ASSOCIATEDPHASES_KEY] = self.associatedPhases
            
        if hasattr(self, 'usedStations'):
            aDict[self.USEDSTATIONS_KEY] = self.usedStations
            
        if hasattr(self, 'usedPhases'):
            aDict[self.USEDPHASES_KEY] = self.usedPhases
            
        if hasattr(self, 'gap'):
            aDict[self.GAP_KEY] = self.gap
            
        if hasattr(self, 'secondaryGap'):
            aDict[self.SECONDARYGAP_KEY] = self.secondaryGap
            
        if hasattr(self, 'RMS'):
            aDict[self.RMS_KEY] = self.RMS
            
        if hasattr(self, 'quality'):
            aDict[self.QUALITY_KEY] = self.quality
            
        if hasattr(self, 'bayesianDepth'):
            aDict[self.BAYESIANDEPTH_KEY] = self.bayesianDepth
            
        if hasattr(self, 'bayesianRange'):
            aDict[self.BAYESIANRANGE_KEY] = self.bayesianRange
            
        if hasattr(self, 'depthImportance'):
            aDict[self.DEPTHIMPORTANCE_KEY] = self.depthImportance
            
        if hasattr(self, 'locatorExitCode'):
            aDict[self.LOCATOREXITCODE_KEY] = self.locatorExitCode
            
        if hasattr(self, 'errorEllipse'):
            if not self.errorEllipse.isEmpty():
                aDict[self.ERRORELLIPSE_KEY] = self.errorEllipse.toDict()
                
        return aDict
    
    #Checks to see if object is valid
    def isValid(self):
        
        errorList = self.getErrors()
        
        return not errorList
    
    #Gets a list of object validation errors
    def getErrors(self):
        
        errorList = []
        
        #required keys
        #Hypocenter
        try:
            if not self.hypocenter.isValid():
                errorList.append('Invalid Hypocenter in LocationResult Class.')
        except(NameError, AttributeError):
            errorList.append('No Hypocenter in LocationResult Class.')
            
        #SupportingData
        
        #Minimum Distance
        try:
            if self.minimumDistance < 0:
                errorList.append('MinimumDistance in LocationResult Class is not greater than 0.')
        except(NameError, AttributeError):
            errorList.append('No MinimumDistance in LocationResult Class.')
        
        #Optional Keys
        #Gap
        if hasattr(self, 'gap'):
            if self.gap < 0 or self.gap > 360:
                errorList.append('Gap in LocationResult Class not in the range of 0 to 360.')
        
        #Secondary Gap
        if hasattr(self, 'secondaryGap'):
            if self.secondaryGap < 0 or self.secondaryGap > 360:
                errorList.append('Secondary gap in LocationResult Class not in the range of 0 to 360.')
        
        #Locator Exit Code
        try:
            match = False
            
            if self.locatorExitCode == "Success":
                match = True
                
            elif self.locatorExitCode == "DidNotMove":
                match = True
                
            elif self.locatorExitCode == "ErrorsNotComputed":
                match = True
                
            elif self.locatorExitCode == "Failed":
                match = True
                
            elif self.locatorExitCode == "Unknown":
                match = True
                
            else:
                match = False
                
            if not match:
                errorList.append("Invalid locator exit code in LocationResult Class")
        except(NameError, AttributeError):
            errorList.append('No locator exit code in LocationResult Class.')
                
        
        #Error Ellipse
        if hasattr(self, 'errorEllipse'):
            if not self.errorEllipse.isEmpty():
                if not self.errorEllipse.isValid():
                    errorList.append('Invalid ErrorEllipse in LocationResult Class.')
        
        return errorList
