#include "../include/iterator.h"

typedef int (*sort_how)( void*, void*);

void list_insert_iter_next(List* l, Iterator* it){
    list_insert(l,iter_get(it));
    iter_next(it);
}

List* merge(List* a,List* b,sort_how principle){
    void* elem_a;
    void* elem_b;
    if(list_isEmpty(a)){
        return b;
    } 
        
    if(list_isEmpty(b)){ 
        return a;
    }
    Iterator* it_a = iter_create(a);
    Iterator* it_b = iter_create(b);
    List* m = list_create();
    while(iter_isValid(it_a) && iter_isValid(it_b)){
        elem_a = iter_get(it_a);
        elem_b = iter_get(it_b); 
        if(principle(elem_a,elem_b) < 0){
            list_insert_iter_next(m,it_a);
        }    
        else if(principle(elem_a,elem_b) > 0){
            list_insert_iter_next(m,it_b);
        }  
        else {
            list_insert_iter_next(m,it_a);
            list_insert_iter_next(m,it_b);
        }    
    }
    while(iter_isValid(it_a)){
        list_insert_iter_next(m,it_a);
    }
    while(iter_isValid(it_b)){
        list_insert_iter_next(m,it_b);
    } 
    return m;       
}