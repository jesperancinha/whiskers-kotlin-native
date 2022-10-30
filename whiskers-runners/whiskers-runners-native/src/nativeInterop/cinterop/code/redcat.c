#include "redcat.h"

char* tell_story() {
    static char c[255] = "The red cat used to roam around in the neighbourhood. For some reason this cat found in Lucy a connection and became Lucy's friend\0";
    char *namePtr;
    namePtr = c;
    return namePtr;
}

int answer() {
  return 42;
}

char love() {
    return 'L';
}