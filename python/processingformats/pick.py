#!/usr/bin/env python3
# -*- coding: utf-8 -*-

#package imports
import processingformats.source
import processingformats.site

#stdlib imports
import json
import datetime

class Pick:
    """
    A conversion class used to create, parse, and validate pick processing data
    """
    
    #JSON Keys
    ID_KEY = "ID" #Required unique identifier for this Pick
    SITE_KEY = "Site" #Required
    SOURCE_KEY = "Source" #Required
    TIME_KEY = "Time" #Required
    AFFINITY_KEY = "Affinity" #Required
    QUALITY_KEY = "Quality" #REquired
    USE_KEY = "Use" #Required
    PICKED_PHASE_KEY = "PickedPhase" #REquired
    ASSOCIATED_PHASE_KEY = "AssociatedPhase" #Required
    LOCATED_PHASE_KEY = "LocatedPhase" #Optional
    RESIDUAL_KEY = "Residual" #Optional
    DISTANCE_KEY = "Distance" #Optional
    AZIMUTH_KEY = "Azimuth" #Optional
    WEIGHT_KEY = "Weight" #Optional
    IMPORTANCE_KEY = "Importance" #Optional
    
    def __init__ (self, newID = None, newSite = None, newSource = None, newTime = None, 
                  newAffinity = None, newQuality = None, newUse = None, newPickedPhase = None, 
                  newAssociatedPhase = None, newLocatedPhase = None, newResidual = None,
                  newDistance = None, newAzimuth = None, newWeight = None, newImportance = None):
    ''' Initializes the pick object. Contructs empty object if all are none
    
        newID: A string containing the id to use
        newSite: a processingformats.site.Site containing desired site (and supporting info)
        newSource: a processingformats.source.Source containing desired source (and supporting info)
        newTime: a datetime containing the pick time
        newAffinity: a Double containing the affinity to use
        newQuality: a double containing the quality to use
        newUse: a boolean containing the flag to use
        newPickedPhase: a string containing the picked phase to use
        newAssociatedPhase: a string containing the associated phase to use
        newLocatedPhase: a string containing the located phase to use
        newResidual: a double containing the residual to use
        newDistance: a double containing the distance to use
        newAzimuth: a double containing the azimuth to use
        newWeight: a double containing the weight to use
        newImportance: a double containing the importance to use
    '''
        
        #Required Keys
        if newID is not None:
            self.id = newID
            
        if newSite is not None:
            self.site = newSite
        else:
            self.site = processingformats.site.Site()
            
        if newSource is not None:
            self.source = newSource
        else:
            self.source = processingformats.source.Source()
        
        if newTime is not None:
            self.time = newTime
            
        if newAffinity is not None:
            self.affinity = newAffinity
            
        if newQuality is not None:
            self.quality = newQuality
            
        if newUse is not None:
            self.use = newUse
            
        if newPickedPhase is not None:
            self.pickedPhase = newPickedPhase
            
        if newAssociatedPhase is not None:
            self.associatedPhase = newAssociatedPhase
            
        #Optional Keys
        if newLocatedPhase is not None:
            self.locatedPhase = newLocatedPhase
            
        if newResidual is not None:
            self.residual = newResidual
            
        if newDistance is not None:
            self.distance = newDistance
        
        if newAzimuth is not None:
            self.azimuth = newAzimuth
            
        if newWeight is not None:
            self.weight = newWeight
            
        if newImportance is not None:
            self.importance = newImportance
            
    def fromJSONString (self, JSONString):
    ''' Populates object from a JSON formatted string
    
        JSONString: a required string containing the JSON formatted text
    '''
        
        JSONObject = json.loads(JSONString)
        self.fromDict(JSONObject)
        
    def fromDict(self, aDict):
    ''' Populates object from a dictionary
    
        aDict: a required dictionary
    '''    
        #Required Keys
        try:
            self.id = aDict[self.ID_KEY]
            self.site.fromDict(aDict[self.SITE_KEY])
            self.source.fromDict(aDict[self.SOURCE_KEY])
            timeString = aDict[self.TIME_KEY] [:-1] + "000Z"
            self.time = datetime.datetime.strptime(timeString, "%Y-%m-%dT%H:%M:%S.%fZ")
            self.affinity = aDict[self.AFFINITY_KEY]
            self.quality = aDict[self.QUALITY_KEY]
            self.use = aDict[self.USE_KEY]
            self.pickedPhase = aDict[self.PICKED_PHASE_KEY]
            self.associatedPhase = aDict[self.ASSOCIATED_PHASE_KEY]
        
        except(ValueError, KeyError, TypeError) as e:
            print("Dictionary format error, missing required keys: %s" % e)
            
        #Optional Keys
        
        if self.LOCATED_PHASE_KEY in aDict:
            self.locatedPhase = aDict[self.LOCATED_PHASE_KEY]
            
        if self.RESIDUAL_KEY in aDict:
            self.residual = aDict[self.RESIDUAL_KEY]
            
        if self.DISTANCE_KEY in aDict:
            self.distance = aDict[self.DISTANCE_KEY]
            
        if self.AZIMUTH_KEY in aDict:
            self.azimuth = aDict[self.AZIMUTH_KEY]
            
        if self.WEIGHT_KEY in aDict:
            self.weight = aDict[self.WEIGHT_KEY]
            
        if self.IMPORTANCE_KEY in aDict:
            self.importance = aDict[self.IMPORTANCE_KEY]
            
    def toJSONString(self):
    ''' Converts object to a JSON formatted String
    
        Returns: JSON formatted message as a String
    '''
        JSONObject = self.toDict()
        
        return json.dumps(JSONObject, ensure_ascii=False)
    
    def toDict(self):
    ''' Converts object to a dictionary
    
        Returns: the dictionary
    '''
        
        aDict = {}
        
        #Required Keys
        try:
            aDict[self.ID_KEY] = self.id
            aDict[self.SITE_KEY] = self.site.toDict()
            aDict[self.SOURCE_KEY] = self.source.toDict()
            timestring = self.time.isoformat(timespec='milliseconds') + "Z"
            aDict[self.TIME_KEY] = timestring
            aDict[self.AFFINITY_KEY] = self.affinity
            aDict[self.QUALITY_KEY] = self.quality
            aDict[self.USE_KEY] = self.use
            aDict[self.PICKED_PHASE_KEY] = self.pickedPhase
            aDict[self.ASSOCIATED_PHASE_KEY] = self.associatedPhase
            
        except(NameError, AttributeError) as e:
            print("Missing required data error: %s" % e)
            
        #Optional Keys
        if hasattr(self, 'locatedPhase'):
            aDict[self.LOCATED_PHASE_KEY] = self.locatedPhase
            
        if hasattr(self, 'residual'):
            aDict[self.RESIDUAL_KEY] = self.residual
            
        if hasattr(self, 'distance'):
            aDict[self.DISTANCE_KEY] = self.distance
            
        if hasattr(self, 'azimuth'):
            aDict[self.AZIMUTH_KEY] = self.azimuth
            
        if hasattr(self, 'weight'):
            aDict[self.WEIGHT_KEY] = self.weight
            
        if hasattr(self, 'importance'):
            aDict[self.IMPORTANCE_KEY] = self.importance
        
    def isValid(self):
    ''' Checks to see if object is valid
    
        Returns: true if object is valid, false otherwise
    '''
        
        errorList = self.getErrors()
        
        return not errorList

    def getErrors(self):
    ''' Gets a list of object validation errors
    
        Returns: a list of strings containing the validation error messages
    '''
        
        errorList = []
        
        #required keys
        try:
            if self.id == '':
                errorList.append('Empty ID in Pick Class')
        except(NameError, AttributeError):
            errorList.append('No ID in Pick Class')
            
        try:
            if not self.site.isValid():
                errorList.append('Invalid Site in Pick Class')
        except(NameError, AttributeError):
            errorList.append('No Site in Pick Class.')
            
        try:
            if not self.source.isValid():
                errorList.append('Invalid Source in Pick Class')
        except(NameError, AttributeError):
            errorList.append('No Source in Pick Class')
            
        try:
            self.time
        except(NameError, AttributeError):
            errorList.append('No Time in Pick Class')
            
        try:
            self.affinity
        except(NameError, AttributeError):
            errorList.append('No Affinity in Pick Class')
            
        try:
            self.quality
        except(NameError, AttributeError):
            errorList.append('No Quality in Pick Class')
            
        try:
            self.use
        except(NameError, AttributeError):
            errorList.append('No Use in Pick Class')
            
        try:
            self.pickedPhase
        except(NameError, AttributeError):
            errorList.append('No PickedPhase in Pick Class.')
            
        try:
            self.associatedPhase
        except(NameError, AttributeError):
            errorList.append('No AssociatedPhase in Pick Class')
            
            
        return errorList
            
