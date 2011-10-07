#include "stack_array.h"
#include <stdio.h>

void TestStackArray() {
	ArrayStack S;
	int i;

	S = CreateArrayStack(12);
	for (i = 0; i < 10; i++)
		PushArrayStack(i, S);

	while (!IsArrayStackEmpty(S)) {
		printf("%d\n", TopArrayStack(S));
		PopArrayStack(S);
	}

	DisposeArrayStack(S);
	return;
}
//int main(){
//	TestStackArray();
//}
