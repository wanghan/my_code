/*
 * List.h
 *
 *  Created on: Oct 6, 2011
 *      Author: t-hawa
 */

typedef int ElementType;

#ifndef LIST_H_
#define LIST_H_

struct Node;
typedef struct Node *PtrToNode;
typedef PtrToNode List;
typedef PtrToNode Position;

/**
 * define Node
 */
struct Node {
	ElementType Element;
	Position Next;
};

/**
 *
 */
List MakeListEmpty(List L);

int IsListEmpty(List L);

int IsLast(Position p, List l);

Position Find(ElementType x, List l);

void Delete(ElementType x, List l);

Position FindPerious(ElementType x, List l);

Position Insert(ElementType x, List l, Position p);

void DeleteList(List l);

Position Header(List L);

Position First(List l);

ElementType Retrieve(Position p);

void PrintList(List l);

#endif /* LIST_H_ */
