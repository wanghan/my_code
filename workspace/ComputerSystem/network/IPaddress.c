/*
 * IPaddress.c
 *
 *  Created on: 2011-10-11
 *      Author: wanghan
 */

#include <arpa/inet.h>
#include <stdio.h>
char * hex2dd(char * ad){
	struct in_addr inaddr;
	unsigned int addr;
	sscanf(ad,"%x",&addr);

	inaddr.s_addr=htonl(addr);
	return inet_ntoa(inaddr);
}
unsigned int dd2hex(char * ad){
	struct in_addr inaddr;
	unsigned int addr;
	if(inet_aton(ad, &inaddr)==0){
		fprintf(stderr, "inet_aton error");
	}
	addr=ntohl(inaddr.s_addr);
	return addr;
}

void test(){
	printf("%s\n",hex2dd("0x8003c2f2"));
	printf("0x%x\n",dd2hex("128.2.194.242"));
}
//int main(){
//	test();
//}
