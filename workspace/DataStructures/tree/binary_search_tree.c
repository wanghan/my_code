/*
 * binary_search_tree.c
 *
 *  Created on: 2011-10-8
 *      Author: wanghan
 */

#include "binary_search_tree.h"
#include <stdlib.h>
#include "../fatal.h"

struct TreeNode {
	ElementType Element;
	BinarySearchTree Left;
	BinarySearchTree Right;
};

BinarySearchTree MakeBSTEmpty(BinarySearchTree T) {
	if (T != NULL) {
		MakeBSTEmpty(T->Left);
		MakeBSTEmpty(T->Right);
		free(T);
	}
	return NULL;
}
TreePosition FindInBST(ElementType X, BinarySearchTree T) {
	if (T == NULL) {
		return NULL;
	}
	if (T->Element == X) {
		return T;
	} else if (T->Element > X) {
		return FindInBST(X, T->Left);
	} else {
		return FindInBST(X, T->Right);
	}
}
TreePosition FindMinInBST(BinarySearchTree T) {
	if (T == NULL) {
		return NULL;
	}
	if (T->Left == NULL) {
		return T;
	} else {
		return FindMinInBST(T->Left);
	}
}
TreePosition FindMaxInBST(BinarySearchTree T) {
	if (T == NULL) {
		return NULL;
	}
	while (T->Right != NULL) {
		T = T->Right;
	}
	return T;
}
BinarySearchTree InsertBST(ElementType X, BinarySearchTree T) {
	if (T == NULL) {
		T = malloc(sizeof(struct TreeNode));
		if (T == NULL) {
			FatalError("out of space");
		}
		else {
			T->Element=X;
			T->Left=T->Right=NULL;
		}
	}
	else if(T->Element==X) {
		;
	}
	else if(T->Element<X) {
		T->Right=InsertBST(X,T->Right);
	}
	else {
		T->Left=InsertBST(X,T->Left);
	}
	return T;
}
BinarySearchTree DeleteBST(ElementType X, BinarySearchTree T) {
	TreePosition TmpCell;

	if (T == NULL) {
		Error( "Element not found" );
	} else if (X < T->Element) {
		/* Go left */

		T->Left = DeleteBST(X, T->Left);
	} else if (X > T->Element) {
		/* Go right */

		T->Right = DeleteBST(X, T->Right);
	} else if (T->Left && T->Right) {
		/* Found element to be deleted */
		/* Two children */
		/* Replace with smallest in right subtree */
		TmpCell = FindMinInBST(T->Right);
		T->Element = TmpCell->Element;
		T->Right = DeleteBST(T->Element, T->Right);
	} else {
		/* One or zero children */
		TmpCell = T;
		if (T->Left == NULL) /* Also handles 0 children */
			T = T->Right;
		else if (T->Right == NULL)
			T = T->Left;
		free(TmpCell);
	}
	return T;
}
ElementType RetrieveBST(TreePosition P) {
	return P->Element;
}
