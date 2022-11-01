#include "redcat.h"
#include <stdio.h>
#include <string.h>

static char c[255] = "The red cat used to roam around in the neighbourhood. For some reason this cat found in Lucy a connection and became Lucy's friend\0";
static char str[sizeof(char*)*256];
static char delim[] = " ";
static char result[sizeof(char*)*256];
static char array[sizeof(char*)*256];

char* tell_story() {
    char *namePtr;
    namePtr = c;
    return namePtr;
}

char* scramble_story() {
	char* story = tell_story();
	memcpy(str, story, sizeof(char*)*256);
	int init_size = strlen(str);
	char *ptr = strtok(str, delim);
	while (ptr != NULL)
	{
		memcpy(array, ptr, sizeof(char*)*256);
		strcat(result, array);
		strcat(result, delim);
		ptr = strtok(NULL, delim);
	}
	strcat(result, "\0");
    char *namePtr;
    namePtr = result;
	return namePtr;
}
int answer() {
  return 42;
}

char love() {
    return 'L';
}
