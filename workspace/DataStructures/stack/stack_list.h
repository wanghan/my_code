/*
 * stack_list.h
 *
 *  Created on: 2011-10-7
 *      Author: wanghan
 */

#ifndef STACK_LIST_H_
#define STACK_LIST_H_

#include "../list/list.h"

typedef PtrToNode ListStack;

int IsListStackEmpty(ListStack S);
ListStack CreateListStack(void);
void DisposeListStack(ListStack S);
void MakeListStackEmpty(ListStack S);
void PushListStack(ElementType X, ListStack S);
ElementType TopListStack(ListStack S);
void PopListStack(ListStack S);

#endif /* STACK_LIST_H_ */
