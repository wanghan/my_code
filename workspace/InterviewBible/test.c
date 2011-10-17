/*
 * test.c
 *
 *  Created on: 2011-10-13
 *      Author: wanghan
 */
#include <stdio.h>

void fn(int *b){
	(*b)++;
}

void calc(double p, double q, double r){
	q=q-1.0;
	r=r+p;
}

int foo(int x,int y){
	if(x<=0||y<=0)
		return 1;
	return 3*foo(x-1,y/2);
}

int main(){

	printf("%d",foo(3,5));
}
