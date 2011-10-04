'''
Created on 2011-7-29

@author: t-hawa
'''


from BitMap import *;
if __name__ == '__main__':
    map=BitMap(10000000);
    file_object=open('./randomNumbers.txt');
    lines=file_object.readlines();
    for row in lines :
        map.set_value(int(row),1)
    for i in range(0,map.get_length()-1):
        if(map.get_value(i)==1):
            print i;
     
