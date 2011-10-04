'''
Created on 2011-7-29

@author: t-hawa
'''

import random;



if __name__ == '__main__':
    k=1000000;
    
    file_object=open('./randomNumbers.txt','w');
    
    for i in range(k):
        r= random.randrange(0,10000000,1);
        file_object.write(str(r)+'\n')
    
    
    file_object.close();
    