'''
Created on 2011-7-29

@author: t-hawa
'''

class BitMap(object):
    '''
    classdocs
    '''
    

    def __init__(self, len):
        '''
        Constructor
        '''
        self.map=[0 for x in range(len)]
        self.len=len;
        
    def set_value(self, position, value):
        if value==0:
            self.map[position]=0;
        else:
            self.map[position]=1;
    
    
    def get_value(self,position):
        if(position>=len(self.map)):
            return -1;
        else:
            return self.map[position]
    
    def get_length(self):
        return self.len