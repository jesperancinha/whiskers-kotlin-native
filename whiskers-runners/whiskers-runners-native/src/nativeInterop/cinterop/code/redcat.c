#include "redcat.h"
#include <stdio.h>
#include <string.h>

char* tell_story() {
    static char c[255] = "The red cat used to roam around in the neighbourhood. For some reason this cat found in Lucy a connection and became Lucy's friend\0";
    char *namePtr;
    namePtr = c;
    return namePtr;
}

char* scramble_story() {
	static char str[sizeof(char*)*256];
	char* story = tell_story();
	memcpy(str, story, sizeof(char*)*256);
	int init_size = strlen(str);
	static char delim[] = " ";
	char *ptr = strtok(str, delim);
	static char result[sizeof(char*)];
	while (ptr != NULL)
	{
		static char array[sizeof(char*)];
		memcpy(array, ptr, sizeof(char*));
		strcat(result, array);
		strcat(result, delim);
		ptr = strtok(NULL, delim);
	}
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
