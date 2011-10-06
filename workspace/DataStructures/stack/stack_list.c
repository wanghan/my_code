/*
 * stack_list.c
 *
 *  Created on: 2011-10-7
 *      Author: wanghan
 */
#include "stack_list.h"
#include "../fatal.h"
#include <stdlib.h>

int IsListStackEmpty(ListStack S) {
	return IsListEmpty(S);
}
ListStack CreateListStack(void) {
	ListStack result = NULL;
	result = malloc(sizeof(struct Node));
	if (result == NULL) {
		FatalError("Out of space!!!");
	}

	result->Next=NULL;
	MakeListStackEmpty(result);
	return result;
}
void DisposeListStack(ListStack S){
	MakeListStackEmpty(S);
	free(S);
}
void MakeListStackEmpty(ListStack S) {
	if (S == NULL) {
		Error("Must use CreateListStack first");
	} else {
		while (!IsListStackEmpty(S)) {
			PopListStack(S);
		}
	}
}
void PushListStack(ElementType X, ListStack S) {
	PtrToNode temp = malloc(sizeof(struct Node));
	if (temp == NULL) {
		FatalError("Out of space!!!");
	}
	temp->Element=X;
	temp->Next=S->Next;
	S->Next=temp;
}
ElementType TopListStack(ListStack S) {
	if (!IsListStackEmpty(S)) {
		return S->Next->Element;
	}
	Error("List Stack is empty");
	return 0;
}
void PopListStack(ListStack S) {
	if (!IsListStackEmpty(S)) {
		PtrToNode re = S->Next;
		S->Next = re->Next;
		free(re);
	}
	Error("List Stack is empty");
}
