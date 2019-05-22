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
    
    '''Intialize source object, constructs empty object if all arguments are None
    
       newAgencyID: a required String containing the agency identifier
       newAuthor: a required String containing the author
       newType: a required String containing the type
    '''
    def __init__ (self, newAgencyID = None, newAuthor = None, newType = None):
        
        if newAgencyID is not None:
            self.agencyID = newAgencyID
            
        if newAuthor is not None:
            self.author = newAuthor
            
        if newType is not None:
            self.type = newType
            
    '''Populate object from JSON formatted string
    
       JSONString: a required String containing the JSON formatted text
    '''
    def fromJSONString (self, JSONString):
        JSONObject = json.loads(JSONString)
        self.fromDict(JSONObject)
        
    '''Populates object from a dictionary
       
       aDict: a required dictionary
    '''
    def fromDict(self, aDict):
        
        try:
            self.agencyID = aDict[self.AGENCYID_KEY]
            self.author = aDict[self.AUTHOR_KEY]
            self.type = aDict[self.TYPE_KEY]
            
        except(ValueError, KeyError, TypeError) as e:
            print ("Dictionary format error, missing required keys: %s" % e)
            
            
    '''Converts object to JSON formatted string
    
       Returns: JSON formatted message as a String
    '''
    def toJSONString(self):
        JSONObject = self.toDict()
        
        return json.dumps(JSONObject, ensure_ascii=False)
    
    '''Converts the object to a dictionary
    
       Returns: the dictionary
    '''
    def toDict(self):
        
        aDict = {}
        
        try:
            aDict[self.AGENCYID_KEY] = self.agencyID
            aDict[self.AUTHOR_KEY] = self.author
            aDict[self.TYPE_KEY] = self.type
            
        except(NameError, AttributeError) as e:
            print("Missing required data error: %s" % e)
            
            
        return aDict
    
    '''Checks to see if object is valid
       
       Returns: true if object is valid, false otherwise
    '''
    def isValid(self):
        errorList = self.getErrors()
        
        return not errorList
    
    '''Gets a list of object validation errors
       
       Returns: a list of Strings containing the validation error messages
    '''
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
            match = False
            
            if self.type == '':
                errorList.append('Empty Type in Source Class')
            
            elif self.type == 'Unknown':
                match = True
                    
            elif self.type == 'LocalHuman':
                match = True
                    
            elif self.type == 'LocalAutomatic':
                match = True
    
            elif self.type == 'ContributedHuman':
                match = True
                
            elif self.type == 'ContributedAutomatic':
                match = True
                
            else:
                match = False
                
            if not match:
                errorList.append('Invalid Type in Source Class')
                    
        except(NameError, AttributeError):
            errorList.append('No Type in Source Class')
                    
        
        return errorList
        
    '''Checks to see if object is empty
    
       Returns: true if object is empty, false otherwise
    '''
    def isEmpty(self):
        if hasattr(self, 'agencyID'):
            return False
        
        if hasattr(self, 'author'):
            return False
        
        if hasattr(self, 'type'):
            return False
        
        return True
    
