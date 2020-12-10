#include "../include/iterator.h"


typedef struct myNode Node;

struct myNode{
    void* elem;
    Node* prev;
    Node* next;
};

struct myList{
    Node* head;
    Node* tail;
    int size;
};

typedef struct myIterator Iterator;

struct myIterator{
    List* list;
    Node* current;
};

/* linked list implemented */

List* list_create(){
    List* l = (List*)malloc(sizeof(List));
    l->head = l->tail = NULL;
    l->size = 0;
    return l;
}

void list_destroy(List* l){
    if(l){
        Node* x = l->head;
        while(x){
            Node* current = x;
            x = x->next;
            free(current); 
        }
        free(l);
    }
}

int list_isEmpty(List* l){
        return l ? l->size == 0 : -1;
}

void list_insert(List* l, void* elem){
    if(l){
        Node* new = (Node*)malloc(sizeof(Node));
        new->elem = elem;
        new->next = NULL;

        if(l->size == 0){
            l->head = l->tail = new;
            new->prev = NULL;
        }
        else{ // l->size > 0
            new->prev = l->tail;
            l->tail->next = new;
            l->tail = new;
        }
        l->size++;
    }
}

void list_insert_index(List* l, void* elem, int i){
    if(l){
        Node* new = (Node*)malloc(sizeof(Node));
        new->elem = elem;

        if(i < l->size){
            if(i == 0){ // head insert
                l->head->prev = new;
                new->next = l->head;
                new->prev = NULL;
                l->head = new;
            }
            else{
                Node* p = l->head; // node prev.
                for(int j = 1; j < i;j++)
                    p = p->next;
                new->next = p->next;
                new->prev = p;
                p->next = new; 
                new->next->prev = new;
            }
            l->size++;
        }
        else if(i == l->size)
            list_insert(l,elem);
        else{
            perror("\nError! Index out of List's bound");
        }
    }
}


void list_delete(List * l){
    if(l && l->size > 0){
        Node* t = l->tail;
        if(l->size == 1){
            l->head = l->tail = NULL;
        }
        else{
            Node* k = t->prev;
            k->next = NULL;
            l->tail = k;
        }
        free(t);
        l->size--;
    }
}

void list_delete_index(List* l, int i){
    if(l && l->size > 0 && i < l->size){
    Node* x;
        if(l->size-1 == i)
                list_delete(l);
        else{
            if(i == 0){ // head delete
                x = l->head;
                Node* current = x->next;
                l->head = current;
                current->prev = NULL;
                
            }
        else{ // Not head nor tail
                    Node* p = l->head; // node prev.
                    for(int j = 1; j < i;j++)
                        p = p->next;
                    x = p->next;
                    Node* n = x->next; // node next
                    p->next = n;
                    n->prev = p;
                } 
            free(x);
            l->size--;  
        }     
    }
}

void* list_get(List *l, int i){
    if(l && i < l->size){
        Node* current = l->head;
        for(int c = 0; c < i; c++)
            current = current->next;
        return current->elem;
    }
    perror("Error : List or index not valid.");
    return NULL;
}

int list_size(List *l){
    return l ? l->size : -1;
}

Iterator* iter_create(List* l){
    if(l){
        Iterator* it = (Iterator*)malloc(sizeof(Iterator));
        it->list = l;
        it->current = it->list->head;
        return it;
    }
    else perror("Error: impossible to create an iterator because List is NULL");
    return NULL;
}

void iter_destroy(Iterator* it){
    if(it)
        free(it);
}

int iter_isValid(Iterator* it){
    return it && it->current ? 1 : 0;
}

void* iter_get(Iterator* it){
    if(iter_isValid(it)) 
        return it->current->elem;
    else{
        perror("Iterator not valid!");
        return NULL;
    }
}

void iter_next(Iterator* it){
    if(iter_isValid(it))
    it->current = it->current->next;
}