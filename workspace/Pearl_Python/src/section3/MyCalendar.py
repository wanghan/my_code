'''
Created on 2011-8-2

@author: t-hawa
'''


def days_between(date1, date2):
    year1=date1.split('-')[0]
    year2=date2.split('-')[0]
    
    r=365*(int(year2)-int(year1))+day_of_year(date2)-day_of_year(date1)
    for i in range(int(year1)+1,int(year2)):
        if is_leap_year(str(i)):
            r+=1;
    return r
    

def day_of_year(date):
    [year,month,day]=date.split('-')
    days_nleap=(31,28,31,30,31,30,31,31,30,31,30,31)
    days_leap=(31,29,31,30,31,30,31,31,30,31,30,31)
    r=0
    if is_leap_year(year):
        for i in range(int(month)-1):
            r+=days_leap[i]
    else:
        for i in range(int(month)-1):
            r+=days_nleap[i]
    return r+int(day)

def is_leap_year(year):
    if int(year)%4==0 and int(year)%100!=0:
        return True
    elif int(year)%400==0:
        return True
    else: return False

def day_of_week(date):
    
    cur_data=('1900-1-1',1)
    days=days_between(cur_data[0],date)
    week=(1+days)%7
    return week

def get_calendar(year, month):
    r=();
    days_nleap=(31,28,31,30,31,30,31,31,30,31,30,31)
    days_leap=(31,29,31,30,31,30,31,31,30,31,30,31)
    if is_leap_year(str(year)):
        for i in range(1,days_leap[month-1]+1):
            date=str(year)+'-'+str(month)+'-'+str(i)
            r=r+(date+': week '+str(day_of_week(date)),)
    else:
        for i in range(1,days_nleap[month-1]+1):
            date=str(year)+'-'+str(month)+'-'+str(i)
            r=r+(date+': week '+str(day_of_week(date)),)
    return r

if __name__=='__main__':
    print day_of_year('2011-2-1')
    print is_leap_year('1900')
    print days_between('2011-1-1','2011-2-1')
    print day_of_week('2011-8-2')
    print get_calendar(2000, 2)