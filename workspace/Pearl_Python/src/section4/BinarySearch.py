'''
Created on 2011-8-2

@author: t-hawa
'''

def binary_search(list, x):
    l=0
    r=len(list)
    while l<r:
        mid=l+(r-l)/2
        if list[mid]>x:
            r=mid
        elif list[mid]<x:
            l=mid+1
        else:
            return mid
    return -1

if __name__ == '__main__':
    print binary_search([1,3,4,5,6,7,8,9,11,22],121)