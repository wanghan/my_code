/*
 * stack_array.h
 *
 *  Created on: 2011-10-7
 *      Author: wanghan
 */
typedef int ElementType;

#ifndef STACK_ARRAY_H_
#define STACK_ARRAY_H_

struct StackRecord;
typedef struct StackRecord *ArrayStack;

int IsArrayStackEmpty(ArrayStack S);
int IsArrayStackFull(ArrayStack S);
ArrayStack CreateArrayStack(int MaxElements);
void DisposeArrayStack(ArrayStack S);
void MakeArrayStackEmpty(ArrayStack S);
void PushArrayStack(ElementType X, ArrayStack S);
ElementType TopArrayStack(ArrayStack S);
void PopArrayStack(ArrayStack S);
ElementType TopAndPopArrayStack(ArrayStack S);

#endif /* STACK_ARRAY_H_ */
