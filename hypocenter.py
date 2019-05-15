#!/usr/bin/env python3
# -*- coding: utf-8 -*-

#stdlib imports

import json
import datetime

class Hypocenter:
    
    """
    a conversion class used to create, parse, and validate hypocenter
    data as part of processing data.
    """
    
    #JSON Keys
    LATITUDE_KEY = "Latitude" #Required
    LONGITUDE_KEY = "Longitude" #Required
    DEPTH_KEY = "Depth" #Required
    TIME_KEY = "Time" #Required
    LATITUDE_ERROR_KEY = "LatitudeError" #Optional
    LONGITUDE_ERROR_KEY = "LongitudeError" #Optional
    DEPTH_ERROR_KEY = "DepthError" #Optional
    TIME_ERROR_KEY = "TimeError" #Optional
    
    #Constructor for Hypo Class. Initializes all members, null if argument is None
    def __init__ (self, newLatitude = None, newLongitude = None, newDepth = None,
                  newTime = None, newLatitudeError = None, newLongitudeError = None,
                  newDepthError = None, newTimeError = None):
        
        #required keys
        if newLatitude is not None:
            self.latitude = newLatitude
        
        if newLongitude is not None:
            self.longitude = newLongitude
            
        if newDepth is not None:
            self.depth = newDepth
        
        if newTime is not None:
            self.time = newTime
            
        #optional keys
        if newLatitudeError is not None:
            self.latitudeError = newLatitudeError
        
        if newLongitudeError is not None:
            self.longitudeError = newLongitudeError
        
        if newDepthError is not None:
            self.depthError = newDepthError
            
        if newTimeError is not None:
            self.timeError = newTimeError
    
    #Populates object from a JSONString
    def fromJSONString(self, JSONString):
        JSONObject = json.loads(JSONString)
        self.fromDict(JSONObject)
        
    #Populates object from a dictionary    
    def fromDict (self, aDict):
        
        #required keys
        try:
            self.latitude = aDict[self.LATITUDE_KEY]
            self.longitude = aDict[self.LONGITUDE_KEY]
            self.depth = aDict[self.DEPTH_KEY]
            timeString = aDict[self.TIME_KEY] [:-1] + "000Z"
            self.time = datetime.datetime.strptime(timeString, "%Y-%m-%dT%H:%M:%S.%fZ")
        
        except(ValueError, KeyError, TypeError) as e:
            print("Dictionary format error, missing required keys: %s" % e)
            
        #optional keys
        if self.LATITUDE_ERROR_KEY in aDict:
            self.latitudeError = aDict[self.LATITUDE_ERROR_KEY]
            
        if self.LONGITUDE_ERROR_KEY in aDict:
            self.longitudeError = aDict[self.LONGITUDE_ERROR_KEY]
        
        if self.DEPTH_ERROR_KEY in aDict:
            self.depthError = aDict[self.DEPTH_ERROR_KEY]
        
        if self.TIME_ERROR_KEY in aDict:
            self.timeError = aDict[self.TIME_ERROR_KEY]
            
    #converts object to JSON formatted string
    def toJSONString(self):
        JSONObject = self.toDict()
        
        return json.dumps(JSONObject, ensure_ascii = False)
    
    #converts obejct to a dictionary
    def toDict(self):
        
        aDict = {}
        
        #required keys
        try:
            aDict[self.LATITUDE_KEY] = self.latitude
            aDict[self.LONGITUDE_KEY] = self.longitude
            aDict[self.DEPTH_KEY] = self.depth
            timeString = self.time.isoformat(timespec='milliseconds') + "Z"
            aDict[self.TIME_KEY] = timeString
        
        except(NameError, AttributeError) as e:
            print("Missing requried data error: %s" % e)
            
        #optional keys
        if hasattr(self, 'lattitudeError'):
            aDict[self.LATITUDE_ERROR_KEY] = self.latitudeError
        
        if hasattr(self, 'longitudeError'):
            aDict[self.LONGITUDE_ERROR_KEY] = self.longitudeError
            
        if hasattr(self, 'depthError'):
            aDict[self.DEPTH_ERROR_KEY] = self.depthError
        
        if hasattr(self, 'timeError'):
            aDict[self.TIME_ERROR_KEY] = self.timeError
            
        return aDict
    
    #Check to see if object is valid
    def isValid(self):
        errorList = self.getErrors()
        
        return not errorList
    
    #Gets list of object validation errors
    def getErrors(self):
        errorList = []
        
        #required keys:
        #latitude
        try:
            if self.latitude < -90 or self.latitude > 90:
                errorList.append('Lattitude in Hypo Class not in the range of -90 to 90.')
        except(NameError, AttributeError):
            errorList.append('No Latitude in Hypo Class.')
            
        #longitude
        try:
            if self.longitude < -180 or self.longitude > 180:
                errorList.append('Longitude in Hypo Class not in the range of -180 to 180.')
        except(NameError, AttributeError):
            errorList.append('No Longitude in Hypo Class.')
            
        #depth
        try:
            if self.depth < -100 or self.depth > 1500:
                errorList.append('Depth in Hypo Class not in the range of -100 to 1500.')
        except(NameError, AttributeError):
            errorList.append('No Depth in Hypo Class.')
            
        return errorList
        
        
        
