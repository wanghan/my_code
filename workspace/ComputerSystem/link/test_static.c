/*
 * test_static.c
 *
 *  Created on: Oct 4, 2011
 *      Author: t-hawa
 */
int f(){
	static int x=0;
	return x;
}

int g(){
	static int x=1;
	return x;
}
