#include <stdio.h>
/*
 * show-bytes.c
 *
 *  Created on: 2011-10-4
 *      Author: wanghan
 */

typedef unsigned char * byte_pointer;

void show_bytes(byte_pointer start, int len){

	int i=0;
	for(i=0;i<len;++i){
		printf(" %.2x",start[i]);
	}
	printf("\n");
}

void show_int(int x){
	show_bytes((byte_pointer)&x, sizeof(int));
}

void show_float(float x){
	show_bytes((byte_pointer)&x, sizeof(float));
}

void show_pointer(void* x){
	show_bytes((byte_pointer)&x, sizeof(void *));
}

void test_show_bytes(int val){
	int ival=val;
	float fval=(float)val;
	int * pval=&val;

	show_int(ival);
	show_float(fval);
	show_pointer(pval);
}

int main(){
	test_show_bytes(12345);
}
