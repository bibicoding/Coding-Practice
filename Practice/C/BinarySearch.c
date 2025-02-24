#include <stdio.h>

void bubbleSort(int[], int);
int binarySearch(int[], int, int);

int main(int argc, char *argv[]) {

  int data[5];
  int n, tgt, result;
  char *str;

  printf("===== Binary Search =====\n\n");
  printf(
      "Please enter five integers consecutively (separate with a space)\n\n");
  printf("Before Sorting: ");
  for (int i = 0; i < 5; i++) {
    scanf("%d", &n);
    data[i] = n;
  }

  bubbleSort(data, 5);

  printf("\n After Sorting: ");
  for (int i = 0; i < 5; i++) {
    printf("%d ", data[i]);
  }

  printf("\n\n Please enter the number you would like to find: ");
  scanf("%d", &tgt);

  result = binarySearch(data, 5, tgt);

  switch (result) {
  case 0:
    str = "first";
    break;
  case 1:
    str = "second";
    break;
  case 2:
    str = "third";
    break;
  case 3:
    str = "fourth";
    break;
  case 4:
    str = "fifth";
    break;
  }

  if (result != -1) {
    printf("\n Number found at %s position!\n", str);
  } else {
    printf("\n Number not found. \n");
  }

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

int binarySearch(int ary[], int len, int tgt) {

  int low = 0, high = len - 1, midNum = 0, num = -1;

  do {
    midNum = (low + high) / 2;
    if (ary[midNum] > tgt) {
      high = midNum - 1;
    } else if (ary[midNum] < tgt) {
      low = midNum + 1;
    } else {
      num = midNum;
      break;
    }
  } while (low <= high);

  return num;
}
