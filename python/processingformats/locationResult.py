#!/usr/bin/env python3
# -*- coding: utf-8 -*-

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
    def __init__ (self, newID = None, newLatitude = None, newLongitude = None, 
                  newTime = None, newDepth = None, newLatitudeError = None, 
                  newLongitudeError = None, newTimeError = None, newDepthError = None,
                  newSupportingData = None, newAssociatedStations = None, 
                  newAssociatedPhases = None, newUsedStations = None, newUsedPhases = None,
                  newGap = None, newSecondaryGap = None, newMinimumDistance = None, 
                  newRMS = None, newQuality = None, newBAyesianDepth = None, 
                  newBAyesianRange = None, newDepthImportance = None, 
                  newLocatorExitCode = None, newE0Error = None, newE0Azimuth = None,
                  newE0Dip = None, newE1Error = None, newE1Azimuth = None, 
                  newE1Dip = None, newE2Error = None, newE2Azimuth = None, 
                  newE2Dip = None, newMaximumHorizontalProjection = None, 
                  newMaximumVerticalProjection = None, newEquivalentHorizontalRadius = None):
        
        
