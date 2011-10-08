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
typedef struct TreeNode *TreePosition;
typedef struct TreeNode *BinarySearchTree;

BinarySearchTree MakeBSTEmpty(BinarySearchTree T);
TreePosition FindInBST(ElementType X, BinarySearchTree T);
TreePosition FindMinInBST(BinarySearchTree T);
TreePosition FindMaxInBST(BinarySearchTree T);
BinarySearchTree InsertBST(ElementType X, BinarySearchTree T);
BinarySearchTree DeleteBST(ElementType X, BinarySearchTree T);
ElementType RetrieveBST(TreePosition P);

#endif /* BINARY_TREE_H_ */
