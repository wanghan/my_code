/*
 * binary_tree.h
 *
 *  Created on: 2011-10-8
 *      Author: wanghan
 */

#ifndef BINARY_TREE_H_
#define BINARY_TREE_H_

typedef int ElementType;

struct TreeNode;
typedef struct TreeNode *Position;
typedef struct TreeNode *BinarySearchTree;

BinarySearchTree MakeBSTEmpty(BinarySearchTree T);
Position FindInBST(ElementType X, BinarySearchTree T);
Position FindMinInBST(BinarySearchTree T);
Position FindMaxInBST(BinarySearchTree T);
BinarySearchTree InsertBST(ElementType X, BinarySearchTree T);
BinarySearchTree DeleteBST(ElementType X, BinarySearchTree T);
ElementType RetrieveBST(Position P);

#endif /* BINARY_TREE_H_ */
