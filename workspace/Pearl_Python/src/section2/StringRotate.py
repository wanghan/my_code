'''
Created on 2011-7-30

@author: t-hawa
'''

def reverse_string(s, begin, end):
    a=s[begin:end];
    b=s[0:begin:1]+a[::-1]+s[end:]
    return b    

def left_rotate(s,i):
    b=reverse_string(s,0,i)
    b=reverse_string(b,i,len(b))
    b=reverse_string(b,0,len(b))
    return b

if __name__ == '__main__':
    s='abcdefgh'
    s=left_rotate(s,3)
    print s