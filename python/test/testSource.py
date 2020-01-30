#!/usr/bin/env python3
# -*- coding: utf-8 -*-


# package imports
import processingformats.source

# stdlib imports
import unittest

class TestSource(unittest.TestCase):
    
    AGENCYID = 'US'
    AUTHOR = 'TestAuthor'
    TYPE = 'Unknown'
    JSONSTRING = '{"AgencyID": "US", "Author": "TestAuthor", "Type": "Unknown"}'
    DICT = {'AgencyID': 'US', 'Author': 'TestAuthor', 'Type': 'Unknown'}
    
    def test_init(self):
        source = processingformats.source.Source()
        
        self.assertFalse(hasattr(source, 'agencyID'))
        self.assertFalse(hasattr(source, 'author'))
        self.assertFalse(hasattr(source, 'type'))
        
        source = processingformats.source.Source(self.AGENCYID, self.AUTHOR, self.TYPE)
        
        self.assertTrue(hasattr(source, 'agencyID'))
        self.assertTrue(hasattr(source, 'author'))
        self.assertTrue(hasattr(source, 'type'))
        
        self.assertEqual(source.agencyID, self.AGENCYID)
        self.assertEqual(source.author, self.AUTHOR)
        self.assertEqual(source.type, self.TYPE)
        
    def test_toJSON(self):
        source = processingformats.source.Source(self.AGENCYID, self.AUTHOR, self.TYPE)
        self.assertEqual(source.toJSONString(), self.JSONSTRING)
        
    def test_fromJSON(self):
        source = processingformats.source.Source()
        source.fromJSONString(self.JSONSTRING)
        
        self.assertEqual(source.agencyID, self.AGENCYID)
        self.assertEqual(source.author, self.AUTHOR)
        self.assertEqual(source.type, self.TYPE)
        
    def test_toDict(self):
        source = processingformats.source.Source(self.AGENCYID, self.AUTHOR, self.TYPE)
        self.assertEqual(source.toDict(), self.DICT)
        
    def test_fromDict(self):
        source = processingformats.source.Source()
        source.fromDict(self.DICT)
        
        self.assertEqual(source.agencyID, self.AGENCYID)
        self.assertEqual(source.author, self.AUTHOR)
        self.assertEqual(source.type, self.TYPE)
        
    def test_isValid(self):
        source = processingformats.source.Source(self.AGENCYID, self.AUTHOR, self.TYPE)
        self.assertTrue(source.isValid())
        
        badSource = processingformats.source.Source()
        self.assertFalse(badSource.isValid())
        

if __name__ == '__main__':
    unittest.main()
