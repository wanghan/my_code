/*
 * testlist.c
 *
 *  Created on: Oct 6, 2011
 *      Author: t-hawa
 */
#include "list.h"
#include <stdio.h>

void TestMakeList() {
	int i;

	List l = NULL;
	l = MakeArrayStackEmpty(l);
	PrintList(l);

	for (i = 0; i < 10; ++i) {
		Insert(i * i, l, l);
	}

	PrintList(l);
	Delete(32, l);
	PrintList(l);
	Delete(49, l);
	PrintList(l);
}
void TestList() {
	List L;
	Position P;
	int i;

	L = MakeArrayStackEmpty(NULL);
	P = Header(L);
	PrintList(L);

	for (i = 0; i < 10; i++) {
		Insert(i, L, P);
		PrintList(L);
	}
	for (i = 0; i < 10; i += 2)
		Delete(i, L);

	for (i = 0; i < 10; i++)
		if ((i % 2 == 0) == (Find(i, L) != NULL))
			printf("Find fails\n");

	printf("Finished deletions\n");

	PrintList(L);

	DeleteList(L);

	return ;
}
//int main() {
//	TestList();
//
//	return 0;
//}
