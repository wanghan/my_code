/*
 * exercise.cpp
 *
 *  Created on: Oct 6, 2011
 *      Author: t-hawa
 */

#include "list.h"
#include "../fatal.h"
#include <stdio.h>
#include <time.h>
#include <stdlib.h>


/*
 * exercise 3.2
 * P is a list with increasing order,
 * print all ele in L where position of ele is indicated in P
 */

void PrintLots(List l, List p) {
	int l_index = 0;

	Position curl = First(l);
	Position curp = First(p);

	while (curp != NULL && curl != NULL) {
		//find position curp->ele in list l
		while (l_index < curp->Element) {
			l_index++;
			curl = curl->Next;
		}
		printf("%d\t", curl->Element);
		curp = curp->Next;
	}
	printf("\n");
}
void TestPrintLots() {
	List l = NULL;
	l = MakeArrayStackEmpty(l);
	int i=0;
	for (i = 0; i < 10; ++i) {
		Insert(i * i, l, l);
	}

	PrintList(l);

	List p = NULL;
	p = MakeArrayStackEmpty(p);
	Insert(7, p, p);
	Insert(5, p, p);
	Insert(3, p, p);
	Insert(1, p, p);
	PrintList(l);
	PrintLots(l, p);
}

/**
 * exercise 3.4 & 3.5
 * measure L1 && L2
 * measure L1 || L2
 */

List Intersect(List l1, List l2) {
	List result = NULL;
	result = MakeArrayStackEmpty(result);
	Position curInsertPos = result;
	Position p1 = First(l1);
	Position p2 = First(l2);

	while (p1 != NULL && p2 != NULL) {
		if (p1->Element < p2->Element) {
			p1 = p1->Next;
		} else if (p1->Element > p2->Element) {
			p2 = p2->Next;
		} else {
			curInsertPos = Insert(p1->Element, result, curInsertPos);
			p1 = p1->Next;
			p2 = p2->Next;
		}
	}

	return result;
}

List Union(List l1, List l2) {
	List result = NULL;
	result = MakeArrayStackEmpty(result);
	Position curInsertPos = result;
	Position p1 = First(l1);
	Position p2 = First(l2);

	while (p1 != NULL && p2 != NULL) {
		if (p1->Element < p2->Element) {
			curInsertPos = Insert(p1->Element, result, curInsertPos);
			p1 = p1->Next;
		} else if (p1->Element > p2->Element) {
			curInsertPos = Insert(p2->Element, result, curInsertPos);
			p2 = p2->Next;
		} else {
			curInsertPos = Insert(p1->Element, result, curInsertPos);
			p1 = p1->Next;
			p2 = p2->Next;
		}
	}

	//add remaining
	while (p1 != NULL) {
		curInsertPos = Insert(p1->Element, result, curInsertPos);
		p1 = p1->Next;
	}

	while (p2 != NULL) {
		curInsertPos = Insert(p2->Element, result, curInsertPos);
		p2 = p2->Next;
	}
	return result;
}

void TestIntersectAndUnion() {
	// make list 1
	List l1 = NULL;
	l1 = MakeArrayStackEmpty(l1);
	Position curl1 = l1;
	for (int i = 0; i < 100; ++i) {
		curl1 = Insert(i * 3, l1, curl1);
	}
	//make list 2
	List l2 = NULL;
	l2 = MakeArrayStackEmpty(l2);
	Position curl2 = l2;
	for (int i = 0; i < 100; ++i) {
		curl2 = Insert(i * 5, l2, curl2);
	}

	//test
	List re = Intersect(l1, l2);
	PrintList(re);

	List re2 = Union(l1, l2);
	PrintList(re2);
}

/**
 *exercise 3.10
 *josephus
 *n: total num of people
 *m: interval m people to remove one
 *e.g. m=1, n=5
 *1,2,3,4,5->1,3,4,5->1,3,5...
 */
void Josephus(int n, int m) {
	//speed up
	m = m % n;

	//make list
	List result = NULL;
	result = MakeArrayStackEmpty(result);
	Position curInsertPos = result;
	for (int i = 1; i <= n; ++i) {
		curInsertPos = Insert(i, result, curInsertPos);
	}
	//make it cycle
	curInsertPos->Next = First(result);

	Position curPerson = First(result);
	Position previous = result;
	for (int i = 0; i < n - 1; ++i) {
		for (int j = 0; j < m; ++j) {
			curPerson = curPerson->Next;
			previous = previous->Next;
		}
		Position temp = curPerson->Next;
		printf("%d->", curPerson->Element);
		previous->Next = temp;
		free(curPerson);
		curPerson = temp;
	}
	printf("\nremain %d\n", curPerson->Element);
}

/**
 * exercise 3.11
 * find element x in list recursively
 */
Position FindRecursively(int x, Position l) {
	if (l == NULL)
		Error("List do not contain x");

	if (l->Element == x)
		return l;
	else {
		return FindRecursively(x, l->Next);
	}
}

void TestFindRecursively() {
	// make list 1
	List l1 = NULL;
	l1 = MakeArrayStackEmpty(l1);
	Position curl1 = l1;
	for (int i = 0; i < 30000; ++i) {
		curl1 = Insert(i * 3, l1, curl1);
	}
	time_t stime, etime;
	stime = clock();
	Position re = FindRecursively(87300, First(l1));
	etime = clock();

	printf("recursively %d, runtime: %d\n", re->Element, etime - stime);
	stime = clock();
	Position re1 = Find(87300, l1);
	etime = clock();
	printf("normally %d, runtime: %d\n", re1->Element, etime - stime);
}

/**
 * exercise 3.12
 * reverse list
 */
void ReverseList(List l) {
	if (IsListEmpty(l)) {
		return;
	}
	Position cur = First(l);
	Position next = cur->Next;
	while (next != NULL) {
		Position temp = next->Next;
		next->Next = cur;
		cur = next;
		next = temp;
	}
	l->Next->Next = NULL;
	l->Next = cur;
}
void TestReverseList() {
	// make list 1
	List l1 = NULL;
	l1 = MakeArrayStackEmpty(l1);
	Position curl1 = l1;
	for (int i = 0; i < 1; ++i) {
		curl1 = Insert(i * 3, l1, curl1);
	}
	PrintList(l1);
	ReverseList(l1);
	PrintList(l1);
}

/**
 * exercise 3.15
 * self-adjusting table
 * array implement and list implement
 * Find x and move x to the table head
 */
Position FindInSelfAdList(int x, List l){
	Position prev=FindPerious(x,l);
	if(prev==NULL){
		return NULL;
	}
	else{
		Position re=prev->Next;
		prev->Next=re->Next;
		re->Next=l->Next;
		l->Next=re;
		return re;
	}
}

//int main() {
//	TestReverseList();
//}
