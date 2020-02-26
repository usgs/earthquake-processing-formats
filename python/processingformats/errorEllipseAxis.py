#!/usr/bin/env python3
# -*- coding: utf-8 -*-

#stdlib import
import json

class ErrorEllipseAxis:
  """
  A conversion class usd to create, parse, and validate error ellipse axis data
  """

  # JSON Keys
  ERROR_KEY = "Error" # Required
  AZIMUTH_KEY = "Azimuth" # Required
  DIP_KEY = "Dip" # Required

  def __init__ (self, newError = None, newAzimuth = None, newDip = None):
    ''' Initializing the object. Constructs an empty object if all arguments are None.
  
      newError: a double containing the length of the error ellipse axis
          in kilometers
      newAzimuth: a double containing the azimuth of the error ellipse axis
          in degrees
      newDip: a double containing the dip  of the error ellipse axis
          in degrees
    '''
        
    if newError is not None:
      self.error = newError
        
    if newAzimuth is not None:
      self.azimuth = newAzimuth
        
    if newDip is not None:
      self.dip = newDip

  def fromJSONString (self, JSONString):
    ''' Populates object from a JSON string
    
        JSONString: a required string containing the JSON formatted text
    '''
    JSONObject = json.loads(JSONString)
    self.fromDict(JSONObject)
    
  def fromDict (self, aDict):
    ''' Populates object from a dictionary
        
        aDict: a required dictionary
    '''
    
    #Required Keys
    try:
      self.error = aDict[self.ERROR_KEY]
      self.azimuth = aDict[self.AZIMUTH_KEY]
      self.dip = aDict[self.DIP_KEY]
        
    except(ValueError, KeyError, TypeError) as e:
      print("Dictionary format error, missing required keys: %s" % e)
      
  def toJSONString(self):
    ''' Converts object to a JSON formatted string
    
        Returns: JSON formatted message as a string
    '''
    JSONObject = self.toDict()
    
    return json.dumps(JSONObject, ensure_ascii = False)

  def toDict(self):
    ''' Converts object to a dictionary
    
        Returns: the dictionary
    '''
    
    aDict = {}
    
    #Required Keys
    try:
      aDict[self.ERROR_KEY] = self.error
      aDict[self.AZIMUTH_KEY] = self.azimuth
      aDict[self.DIP_KEY] = self.dip

    except(NameError, AttributeError) as e:
      print("Missing required data error: %s" % e)
    
    return aDict

  def isValid(self):
    ''' Checks to see if object is valid
    
        Returns: true if object is valid, false otherwise
    '''
    errorList = self.getErrors()
    
    return not errorList

  def getErrors (self):
    ''' Gets a list of object validation errors
    
        Returns: a list of string containing the validation error messages
    '''
    errorList = []
    
    # required keys
    
    # error
    try:
      self.error
    except(NameError, AttributeError):
      errorList.append('No Error in ErrorEllipseAxis Class.')
        
    # azimuth
    try:
      self.azimuth
    except(NameError, AttributeError):
      errorList.append('No Azimuth in ErrorEllipseAxis Class')
    
    # dip
    try:
      self.dip
    except(NameError, AttributeError):
      errorList.append('No Dip in ErrorEllipseAxis Class.')
         
    return errorList    

  def isEmpty(self):
    ''' Checks to see if object is empty
    
        Returns: True if the object has no attributes, False otherwise
    '''
    
    if hasattr(self, 'error'):
        return False
    
    if hasattr(self, 'azimuth'):
        return False
    
    if hasattr(self, 'dip'):
        return False
    
    return True