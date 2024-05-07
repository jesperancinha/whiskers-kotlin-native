#include "redcat.h"

char* tell_story() {
    static char c[255] = "In the eyes of this cat, Lucy was always an inspiring friend to see. Always coming along the street on the way to the office and giving the cat a pet and saying nice things\0";
    char *namePtr;
    namePtr = c;
    return namePtr;
}
