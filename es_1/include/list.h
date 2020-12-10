#include <stdio.h>
#include <stdlib.h>

typedef struct myList List;

/*Returns new empty List*/
List * list_create();

/*HULK DESTROYS!*/
void list_destroy(List *l);

/*Returns 
*  1 if List is empty 
* -1 if error (ex. List is NULL) 
*  0 otherwise
*/
int list_isEmpty(List *l);

/*Inserts an element in the List*/
void list_insert(List *l, void * elem);

/*Inserts an element in the position of the List specified by the index*/
void list_insert_index(List *l, void * elem, int i);

/*Deletes the last element (tail) of the List*/
void list_delete(List * l);

/*Deletes the element located at the position specified by index in the List*/
void list_delete_index(List *l, int i);

/*Returns the element located at the position specified by the index in the List*/
void * list_get(List *l, int i);

/*Returns the number of elements inside the List or -1 if NULL*/
int list_size(List *l);