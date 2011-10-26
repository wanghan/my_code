/*
 * test_macro.c
 *
 *  Created on: 2011-10-6
 *      Author: wanghan
 */

#include <stdio.h>

#define FIND(struc,e) &(((struc*)0)->e)

typedef struct student {
	int score;
	double id;
	char * name;
	double c;
}student;

//int main() {
//	//test macro FIND
//	int offset=FIND(student,c);
//	printf("%d\n",offset);
//
//}
