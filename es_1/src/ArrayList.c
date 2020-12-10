#include "../include/iterator.h"

void list_extend(List* l);
void list_reduce(List* l);
void list_lshift(List* l, int i);
void list_rshift(List* l, int i);

struct myList
{
    void** arrayList;
    int size;           //size of array
    int counter;        //counts occupied space in the array
};

typedef struct myIterator Iterator;

struct myIterator{
    List* list;
    int index;  //index of current element of the array pointed by the Iterator
};


List* list_create(){
    List* l = (List*)malloc(sizeof(List));
    l->arrayList = (void**)malloc(2*sizeof(void*));
    l->size = 2;
    l->counter = 0;
    return l;
}


void list_destroy(List* l){
    if(l){
        free(l->arrayList);
        free(l);
    }
}


int list_isEmpty(List *l){
    return l ? l->counter == 0 : -1;
}


void list_insert(List* l, void* elem){
    if(l){
        if(l->counter == l->size){  //is full
            list_extend(l);
        }
        l->arrayList[l->counter] = elem;
        l->counter++;
    }else{
        printf("\nError! List is null");
    }
}


void list_insert_index(List *l, void * elem, int i){
    if(l){
        if(i < l->size){
            if(!list_isEmpty(l)){
                if(l->counter == l->size){
                    list_extend(l);
                }
                list_rshift(l,i);
            }
            l->arrayList[i] = elem;
            l->counter++;
        }
    }else{
        perror("\nError! Invalid List");
    }
}


void list_delete(List * l){
    if(l && !list_isEmpty(l)){
        l->counter--;
        list_reduce(l);
    }
}


void list_delete_index(List* l, int i){
    if(l && !list_isEmpty(l)){
        list_lshift(l,i+1);
        l->counter--;
        list_reduce(l);
    }
}


void* list_get(List *l, int i){
    if(l && i < l->counter)
        return l->arrayList[i];  
    perror("\nError! Invalid index/List");
    return NULL; // 0 if printed
}


int list_size(List *l){
    return (l) ? l->counter : -1;
}


void list_extend(List* l){
    if(l){
        l->arrayList = (void**)realloc(l->arrayList, (l->size*2) * sizeof(void*));
        l->size *= 2;
    }
}


void list_reduce(List *l){
    if(l){
        if(l->counter < l->size/3){
            l->arrayList = (void**)realloc(l->arrayList, (l->size/2) * sizeof(void*));
            l->size /= 2 ;
        }
    }
}


void list_lshift(List* l, int i){
    if(l){
        for(; i < l->counter; i++){
            l->arrayList[i-1] = l->arrayList[i];
        }
    }
}

void list_rshift(List* l, int i){
    if(l){
        for(int j=l->counter ; i < j; j--){
            l->arrayList[j] = l->arrayList[j-1];
        }
    }
}

Iterator* iter_create(List* l){
    if(l){
        Iterator* it = (Iterator*)malloc(sizeof(Iterator));
        it->list = l;
        it->index = 0;
        return it;
    }
    else perror("Error: impossible to create an iterator because List is NULL.");
    return NULL;
}

void iter_destroy(Iterator* it){
    if(it)
        free(it);
}

int iter_isValid(Iterator* it){
    return (it && it->list && it->index < it->list->counter) ? 1 : 0;
}

void* iter_get(Iterator* it){
    if(iter_isValid(it))
        return list_get(it->list,it->index);
    else{
        perror("Iterator not valid!"); 
        return NULL; // 0 if printed
    }
}

void iter_next(Iterator* it){
    if(iter_isValid(it))
        it->index++;
}