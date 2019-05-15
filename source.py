#!/usr/bin/env python3
# -*- coding: utf-8 -*-

#stdlib imports
import json

class Source:
    """
    A conversion class used to create, parse, and validate source data as part
    of processing data.
    """
    
    #JSON Keys
    AGENCYID_KEY = "AgencyID" #required
    AUTHOR_KEY = "Author" #required
    TYPE_KEY = "Type" #required
    
    #Intialize source object
    def __init__ (self, newAgencyID = None, newAuthor = None, newType = None):
        
        if newAgencyID is not None:
            self.agencyID = newAgencyID
            
        if newAuthor is not None:
            self.author = newAuthor
            
        if newType is not None:
            self.type = newType
            
    #Populate object from JSON formatted string
    def fromJSONString (self, JSONString):
        JSONObject = json.loads(JSONString)
        self.fromDict(JSONObject)
        
    #Populates object from a dictionary
    def fromDict(self, aDict):
        
        try:
            self.agencyID = aDict[self.AGENCYID_KEY]
            self.author = aDict[self.AUTHOR_KEY]
            self.type = aDict[self.TYPE_KEY]
            
        except(ValueError, KeyError, TypeError) as e:
            print ("Dictionary format error, missing required keys: %s" % e)
            
            
    #Converts object to JSON formatted string
    def toJSONString(self):
        JSONObect = self.toDict()
        
        return json.dumps(JSONObject, ensure_ascii=False)
    
    #Converts the object to a dictionary
    def toDict(self):
        
        aDict = {}
        
        try:
            aDict[self.AGENCYID_KEY] = self.agencyID
            aDict[self.AUTHOR_KEY] = self.author
            aDict[self.TYPE_KEY] = self.type
            
        except(NameError, AttributeError) as e:
            print("Missing required data error: %s" % e)
            
            
        return aDict
    
    #Checks to see if object is valid
    def isValid(self):
        errorList = self.getErrors()
        
        return not errorList
    
    #Gets a list of object validation errors
    def getErrors(self):
        
        errorList = []
        
        #AgencyID
        try:
            if self.agencyID == '':
                errorList.append('Empty AgencyID in Source Class')
                
        except(NameError, AttributeError):
            errorList.append('No AgencyID in Source Class')
            
        #Author
        try:
            if self.author == '':
                errorList.append('Empty Author in Source Class')
                
        except(NameError, AttributeError):
            errorList.append('No Author in Source Class')
            
        #Type
        try:
            match = false
            
            if self.type == '':
                errorList.append('Empty Type in Source Class')
            
            elif self.type == 'Unkown':
                match = true
                    
            elif self.type == 'LocalHuman':
                match = true
                    
            elif self.type == 'LocalAutomatic':
                match = true
    
            elif self.type == 'ContributedHuman':
                match = true
                
            elif self.type == 'ContributedAutomatic':
                match = true
                
            else:
                match = false
                
            if not match:
                errorList.append('Invalid Type in Source Class')
                    
        except(NameError, AttributeError):
            errorList.append('No Type in Source Class')
                    
        
        return errorList
    
