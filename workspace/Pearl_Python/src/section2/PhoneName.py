'''
Created on 2011-7-30

@author: t-hawa
'''

class PhoneName(object):
    '''
    classdocs
    '''
    

    def __init__(self):
        '''
        Constructor
        '''
        self.result=[]
        self.map={'1':"",'2':'ABC','3':'DEF','4':'GHI','5':'JKL','6':'MNO','7':'PRS','8':'TUV','9':'WXY','*':'*'}
    
    def get_all_name(self, code):
        self.result=[];
        self.__find_a_level(code,0,'');
        return self.result;
    
    
    def __find_a_level(self, code, level, cur_str):
        if level==len(code):
            self.result.append(cur_str)
            return
        for c in self.map[code[level]]:
            self.__find_a_level(code,level+1,cur_str+c)
            
    def check_code(self, code, name):
        r=self.get_all_name(code)
        try:
            if r.index(name)>=0:
                return True
            else:
                return False
        except:
            return False

if __name__ == '__main__':
    result=PhoneName().check_code('5375*61','LESK*M*')
    print result 