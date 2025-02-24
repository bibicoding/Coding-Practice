#include <stdio.h>

void bubbleSort(int[], int);

int main(int argc, char *argv[]) {

  int data[5];
  int n;

  printf("== Bubble Sort (from smallest to biggest) ==\n\n");
  printf("...Please enter five integers consecutively (separate them with a "
         "space)...\n\n");
  printf("Before Bubble Sort: ");
  for (int i = 0; i < 5; i++) {
    scanf("%d", &n);
    data[i] = n;
  }

  bubbleSort(data, 5);

  printf("\nAfter Bubble Sort: ");
  for (int i = 0; i < 5; i++) {
    printf("%d ", data[i]);
  }

  printf("\n\n");
  return 0;
}

void bubbleSort(int ary[], int len) {

  int tmp;
  for (int i = len - 1; i > 0; i--) {
    for (int j = 0; j < i; j++) {
      if (ary[j] > ary[j + 1]) {
        tmp = ary[j];
        ary[j] = ary[j + 1];
        ary[j + 1] = tmp;
      }
    }
  }
}
