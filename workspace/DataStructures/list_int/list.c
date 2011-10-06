/*
 * list.c
 *
 *  Created on: Oct 6, 2011
 *      Author: t-hawa
 */

#include "list.h"
#include "../fatal.h"
#include <stdio.h>
#include <stdlib.h>


List MakeEmpty(List L) {
	if (L != NULL) {
		DeleteList(L);
	}
	L = malloc(sizeof(struct Node));
	if (L == NULL) {
		FatalError( "Out of memory!");
	}
	L->Next = NULL;
	return L;
}

/**
 *
 */
int IsEmpty(List L) {
	return L->Next == NULL;
}

int IsLast(Position p, List l) {
	return p->Next == NULL;
}

Position Find(ElementType x, List l) {
	if (l == NULL) {
		return NULL;
	}

	Position cur = l->Next;
	while (cur != NULL && cur->Element != x) {
		cur = cur->Next;
	}


	return cur;
}

void Delete(ElementType x, List l) {
	if (IsEmpty(l)) {
		Error("List is empty");
	} else {
		Position previous = FindPerious(x, l);
		if (previous == NULL) {
			Error("List l does not contain x");
		} else if (IsLast(previous->Next, l)) {
			Position next = previous->Next;
			previous->Next = NULL;
			free(next);
		} else {
			Position next = previous->Next;
			previous->Next = next->Next;
			free(next);
		}
	}
}

Position FindPerious(ElementType x, List l) {
	if (l == NULL) {
		return NULL;
	}

	Position cur = l;
	while (cur->Next != NULL && cur->Next->Element != x) {
		cur = cur->Next;
	}

	//not find
	if (cur->Next == NULL || cur->Next->Element != x) {
		cur = NULL;
	}
	return cur;
}

/**
 * insert after position p
 * return new position of x
 */
Position Insert(ElementType x, List l, Position p) {
	Position temp;
	temp = (Position) malloc(sizeof(struct Node));

	if (temp == NULL) {
		FatalError("Out of space!");
	}

	temp->Element = x;
	temp->Next = p->Next;
	p->Next = temp;
	return temp;
}

void DeleteList(List l) {

	Position cur = l->Next;
	l->Next = NULL;

	while (cur != NULL) {
		Position p = cur;
		cur = cur->Next;
		free(p);
	}

	free(l);
	l = NULL;
}

Position Header(List L) {
	return L;
}

Position First(List l) {
	return l->Next;
}

ElementType Retrieve(Position p) {
	return p->Element;
}

void PrintList(List l) {
	if (l == NULL) {
		printf("list is null");
	}
	printf("head");
	Position cur = l->Next;
	while (cur != NULL) {
		printf("->%d", cur->Element);
		cur = cur->Next;
	}
	printf("\n");
}

