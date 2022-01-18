#!/usr/bin/env python3
# -*- coding: utf-8 -*-

#stdlib imports
import json

class Site:
    """
    A conversion class used to create, parse, and validate site data as part of
    processing data
    """
    
    #JSON Keys
    STATION_KEY = "Station" #Required
    CHANNEL_KEY = "Channel" #Optional
    NETWORK_KEY = "Network" #Required
    LOCATION_KEY = "Location" #Optional
    LATITUDE_KEY = "Latitude" #Required
    LONGITUDE_KEY = "Longitude" #Required
    ELEVATION_KEY = "Elevation" #Required
    
    def __init__ (self, newStation = None, newChannel = None, newNetwork = None, 
                  newLocation = None, newLatitude = None, newLongitude = None, 
                  newElevation = None):
        ''' Initialize the site object, creates empty constructor if all are None
        
            newStation: a String containing the station to use
            newChannel: a String containing the channel to use
            newNetwork: a String containing the network to use
            newLocation: a String containing the location to use
            newLatitude: a Double containing the latitude in degrees to use
            newLongitude: a Double containing the longitude in degrees to use
            newElevation: a Double containing the elevation in meters to use
        '''    
        
        #required keys
        if newStation is not None:
            self.station = newStation
        
        if newNetwork is not None:
            self.network = newNetwork
        
        if newLatitude is not None:
            self.latitude = newLatitude
        
        if newLongitude is not None:
            self.longitude = newLongitude
        
        if newElevation is not None:
            self.elevation = newElevation
            
        #optional keys
        if newChannel is not None:
            if newChannel != '':
                self.channel = newChannel
        
        if newLocation is not None:
            if newLocation != '':
                self.location = newLocation
           
    def fromJSONString (self, JSONString):
        ''' Populates object from json formatted string
        
            JSONString: a required String containing the JSON formatted text
        '''
        JSONObject = json.loads(JSONString)
        self.fromDict(JSONObject)
      
    def fromDict(self, aDict):
        ''' Populates object from a dictionary
        
            aDict: a required dictionary
        '''
        
        #required keys
        try:
            self.station = aDict[self.STATION_KEY]
            self.network = aDict[self.NETWORK_KEY]
            self.latitude = aDict[self.LATITUDE_KEY]
            self.longitude = aDict[self.LONGITUDE_KEY]
            self.elevation = aDict[self.ELEVATION_KEY]
        
        except(ValueError, KeyError, TypeError) as e:
            print("Dictionary format error, missing required keys: %s" % e)
            
        #optional keys
        if self.CHANNEL_KEY in aDict:
            self.channel = aDict[self.CHANNEL_KEY]
            
        if self.LOCATION_KEY in aDict:
            self.location = aDict[self.LOCATION_KEY]
            
    def toJSONString(self):
        ''' Converts object to a JSON formatted string
        
            Returns: The JSON formatted message as a String
        '''
        JSONObject = self.toDict()
        
        return json.dumps(JSONObject, ensure_ascii=False)
    
    def toDict(self):
        ''' Converts the object to a dictionary
        
            Returns: the dictionary
        '''
        
        aDict = {}
        
        #required keys
        try:
            aDict[self.STATION_KEY] = self.station
            aDict[self.NETWORK_KEY] = self.network
            aDict[self.LATITUDE_KEY] = self.latitude
            aDict[self.LONGITUDE_KEY] = self.longitude
            aDict[self.ELEVATION_KEY] = self.elevation
            
        except(NameError, AttributeError) as e:
            print("Missing required data error: %s" % e)
            
        #optional keys
        if hasattr(self, 'channel'):
            if self.channel != '':
                aDict[self.CHANNEL_KEY] = self.channel
        
        if hasattr(self, 'location'):
            if self.location != '':
                aDict[self.LOCATION_KEY] = self.location
                
        return aDict
    
    def isValid(self):
        ''' Checks to see if the object is valid
            
            Returns: true if the object is valid, false otherwise
        '''
        errorList = self.getErrors()
        
        return not errorList
    
    def getErrors(self):
        ''' Gets a list of object validation errors
        
            Returns: a list of strings containing the validation error messages
        '''    
        errorList = []
        
        #Station
        try:
            if self.station == '':
                errorList.append('Empty Station in Site Class.')
        
        except(NameError, AttributeError):
            errorList.append('No Station in Site Class.')
        
        #Network
        try:
            if self.network == '':
                errorList.append('Empty Network in Site Class')
                
        except(NameError, AttributeError):
            errorList.append('No Network in Site Class')
        
        #Latitude
        try:
            if self.latitude < -90 or self.latitude > 90:
                errorList.append('Latitude in Site Class not in the range of -90 to 90.')
                
        except(NameError, AttributeError):
            errorList.append('No Latitude in Site Class')
        
        #Longitude
        try:
            if self.longitude < -180 or self.longitude > 180:
                errorList.append('Longitude in Site Class not in the range of -180 to 180.')
                
        except(NameError, AttributeError):
            errorList.append('No Longitude in Site Class')
        
        #Elevation
        try:
            if self.elevation < -20000 or self.elevation > 10000:
                errorList.append('Elevation in site class not in the range of -20000 to 10000 meters..')
                
        except(NameError, AttributeError):
            errorList.append('No Elevation in Site Class')
            
            
        return errorList
            
