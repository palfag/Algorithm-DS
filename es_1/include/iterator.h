#include "list.h"

typedef struct myIterator Iterator;

/* Returns new Iterator for the List l */
Iterator* iter_create(List* l);

/* HULK DESTROYS! */
void iter_destroy(Iterator* it);

/*Return 1 if the Iterator is valid, 0 otherwise*/
int iter_isValid(Iterator * it);

/*Returns the element of the List currently pointed by the Iterator*/
void * iter_get(Iterator * it);

/*Moves Iterator pointer to the next element of the List*/
void iter_next(Iterator * it);