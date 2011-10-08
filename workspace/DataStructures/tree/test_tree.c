/*
 * test_tree.c
 *
 *  Created on: 2011-10-8
 *      Author: wanghan
 */

#include "binary_search_tree.h"
#include <stdio.h>

int main() {
	BinarySearchTree T;
	TreePosition P;
	int i;
	int j = 0;

	T = MakeBSTEmpty(NULL);
	for (i = 0; i < 50; i++, j = (j + 7) % 50)
		T = InsertBST(j, T);
	for (i = 0; i < 50; i++)

		if ((P = FindInBST(i, T)) == NULL || RetrieveBST(P) != i)
			printf("Error at %d\n", i);

	for (i = 0; i < 50; i += 2)
		T = DeleteBST(i, T);

	for (i = 1; i < 50; i += 2)
		if ((P = FindInBST(i, T)) == NULL || RetrieveBST(P) != i)
			printf("Error at %d\n", i);
	for (i = 0; i < 50; i += 2)
		if ((P = FindInBST(i, T)) != NULL)
			printf("Error at %d\n", i);

	printf("Min is %d, Max is %d\n", RetrieveBST(FindMinInBST(T)), RetrieveBST(
			FindMaxInBST(T)));

	return 0;
}
