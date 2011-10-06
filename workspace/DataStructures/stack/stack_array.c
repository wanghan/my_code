/*
 * stack_array.c
 *
 *  Created on: 2011-10-7
 *      Author: wanghan
 */
#include "stack_array.h"
#include "../fatal.h"

#define EmptyTOS ( -1 )
#define MinStackSize ( 5 )

struct StackRecord {
	int Capacity;
	int TopOfStack;
	ElementType *Array;
};

int IsArrayStackEmpty(ArrayStack S) {
	return S->TopOfStack == EmptyTOS;
}

int IsArrayStackFull(ArrayStack S) {
	return S->TopOfStack == S->Capacity - 1;
}

ArrayStack CreateArrayStack(int MaxElements) {
	ArrayStack s = malloc(sizeof(struct StackRecord));
	if (s == NULL) {
		FatalError("Out of space!!!");
	}

	s->Capacity=MaxElements;
	s->Array=malloc(sizeof(ElementType)*MaxElements);
	if(s->Array==NULL) {
		FatalError("Out of space!!!");
	}

	MakeArrayStackEmpty(s);
	return s;
}

void DisposeArrayStack(ArrayStack S) {
	if (S != NULL) {
		free(S->Array);
		free(S);
	}
}

void MakeArrayStackEmpty(ArrayStack S) {
	S->TopOfStack = EmptyTOS;
}

void Push(ElementType X, ArrayStack S) {
	if (IsArrayStackFull(S)) {
		Error("Array Stack is full");
	}
	S->Array[++S->TopOfStack] = X;
}

ElementType Top(ArrayStack S) {
	if (IsArrayStackEmpty(S)) {
		Error("Array Stack is empty");
		return 0;
	} else {
		return S->Array[S->TopOfStack];
	}
}

void Pop(ArrayStack S) {
	if (IsArrayStackEmpty(S)) {
		Error("Array Stack is empty");

	}
	S->TopOfStack--;
}

ElementType TopAndPop(ArrayStack S) {
	ElementType top = 0;
	if (IsArrayStackEmpty(S)) {
		Error("Array Stack is empty");

	} else {
		top = S->Array[S->TopOfStack--];
	}
	return top;
}
