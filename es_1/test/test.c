#include <assert.h>
#include <string.h>
#include "../include/iterator.h"
#include "../../Resources/C/Unity/unity.h"
#include "../src/Merge.c"


static int cmp(void *a, void *b){
    return (*(int*)a < *(int*)b) ? -1 : (*(int*)a > *(int*)b);
}

int cmp_decr(void* a, void* b){
    return (*(int*)a > *(int*)b) ? -1 : (*(int*)a < *(int*)b);
}

int* list_elem(int n){
    int*p=malloc(sizeof(int));
    *p=n;
    return p;
}

char* list_elem_char(char n){
    char*p = malloc(sizeof(char)+1);
    *p = n;
    return p;
}


static void print_list(List *l){
    printf("INIZIO:\n");
    for(int i=0; i<list_size(l);i++){
        printf("%i\n", *(int*)list_get(l,i));
    }
    printf("FINE:\n");
}

/* Returns {1,2,3,4,5} */
List* example_list_int(){
    List* l = list_create();

    list_insert(l,list_elem(1));
    list_insert(l,list_elem(2));
    list_insert(l,list_elem(3));
    list_insert(l,list_elem(4));
    list_insert(l,list_elem(5));
    return l;
}

/* Returns {a,b,c,d,e} */
List* example_list_char(){
    List* l = list_create();
    list_insert(l,"a");
    list_insert(l,"b");
    list_insert(l,"c");
    list_insert(l,"d");
    list_insert(l,"e");
    return l;
}


/****************************************START TEST****************************************/

static void test_list_create(){
    List* l = list_create();
    TEST_ASSERT_NOT_NULL(l);
    TEST_ASSERT_EQUAL(list_isEmpty(l),1);
    list_destroy(l);
}

static void test_list_isEmpty_empty(){
    List* l = list_create();
    TEST_ASSERT_EQUAL(list_isEmpty(l),1);
    list_destroy(l);
}

static void test_list_isEmpty_notEmpty(){
    List* l = example_list_int();
    TEST_ASSERT_EQUAL(list_isEmpty(l),0);
    list_destroy(l);
}

static void test_list_isEmpty_invalid(){
    List* l = NULL;
    TEST_ASSERT_EQUAL(list_isEmpty(l),-1);
    list_destroy(l);
}

static void test_list_insert_empty(){
    List* l = list_create();
    list_insert(l,"x");
    TEST_ASSERT_EQUAL(0,list_isEmpty(l));
    list_destroy(l);
}

static void test_list_insert_index(){ //BUS ERROR // Linked list
    List* l = example_list_char();
    list_insert_index(l,(char*)"x", 1);
    list_insert_index(l,(char*)"y", 3);
    list_insert_index(l,(char*)"z", 5);
    TEST_ASSERT_EQUAL_STRING("a", (char*)list_get(l,0));
    TEST_ASSERT_EQUAL_STRING("x", (char*)list_get(l,1));
    TEST_ASSERT_EQUAL_STRING("b", (char*)list_get(l,2));
    TEST_ASSERT_EQUAL_STRING("y", (char*)list_get(l,3));
    TEST_ASSERT_EQUAL_STRING("c", (char*)list_get(l,4));
    TEST_ASSERT_EQUAL_STRING("z", (char*)list_get(l,5));
    TEST_ASSERT_EQUAL_STRING("d", (char*)list_get(l,6));
    TEST_ASSERT_EQUAL_STRING("e", (char*)list_get(l,7));
    list_destroy(l);
}

static void test_list_insert_index_head(){
    List* l = example_list_char();
    list_insert_index(l,(char*)"x", 0);
    TEST_ASSERT_EQUAL_STRING("x", (char*)list_get(l,0));
    TEST_ASSERT_EQUAL_STRING("a", (char*)list_get(l,1));
    TEST_ASSERT_EQUAL_STRING("b", (char*)list_get(l,2));
    TEST_ASSERT_EQUAL_STRING("c", (char*)list_get(l,3));
    TEST_ASSERT_EQUAL_STRING("d", (char*)list_get(l,4));
    TEST_ASSERT_EQUAL_STRING("e", (char*)list_get(l,5));
    TEST_ASSERT_EQUAL(6,list_size(l));
    list_destroy(l);
}

static void test_list_insert_index_tail(){
    List* l = example_list_char();
    list_insert_index(l,(char*)"z", 5);
    TEST_ASSERT_EQUAL_STRING("z", (char*)list_get(l,5));
    TEST_ASSERT_EQUAL(6,list_size(l));
    list_destroy(l);
}

static void test_list_delete(){
    List* l = example_list_char();
    TEST_ASSERT_EQUAL(5,list_size(l));
    list_delete(l);
    TEST_ASSERT_EQUAL(4,list_size(l));
    list_destroy(l);
}

static void test_list_delete_one(){
    List* l = list_create();
    list_insert(l,list_elem(1));
    TEST_ASSERT_EQUAL(1,list_size(l));
    list_delete(l);
    TEST_ASSERT_EQUAL(0,list_size(l));
    list_destroy(l);
}

static void test_list_delete_index_head(){
    //versione char
    /*
    List* l = example_list_char();
    TEST_ASSERT_EQUAL(5,list_size(l));
    list_delete_index(l,0);// remove 1
    TEST_ASSERT_EQUAL_STRING("b",list_get(l,0));
    TEST_ASSERT_EQUAL_STRING("c",list_get(l,1));
    TEST_ASSERT_EQUAL_STRING("d",list_get(l,2));
    TEST_ASSERT_EQUAL_STRING("e",list_get(l,3));
    TEST_ASSERT_EQUAL(4,list_size(l));
    list_destroy(l);
    */
    // int
    List* l = example_list_int();
    TEST_ASSERT_EQUAL(5,list_size(l));
    list_delete_index(l,0);// remove 1
    TEST_ASSERT_EQUAL_INT(2,*(int*)list_get(l,0));
    TEST_ASSERT_EQUAL_INT(3,*(int*)list_get(l,1));
    TEST_ASSERT_EQUAL_INT(4,*(int*)list_get(l,2));
    TEST_ASSERT_EQUAL_INT(5,*(int*)list_get(l,3));
    TEST_ASSERT_EQUAL(4,list_size(l));
    list_destroy(l);
  
}

static void test_list_delete_index_tail(){
   //versione char
   /*
    List* l = example_list_char();
    TEST_ASSERT_EQUAL(5,list_size(l));
    list_delete_index(l,4);// remove 1
    TEST_ASSERT_EQUAL_STRING("a",list_get(l,0));
    TEST_ASSERT_EQUAL_STRING("b",list_get(l,1));
    TEST_ASSERT_EQUAL_STRING("c",list_get(l,2));
    TEST_ASSERT_EQUAL_STRING("d",list_get(l,3));
    TEST_ASSERT_EQUAL(4,list_size(l));
    list_destroy(l);*/
    //versione int
    List* l = example_list_int();
    TEST_ASSERT_EQUAL(5,list_size(l));
    list_delete_index(l,4);// remove 1
    TEST_ASSERT_EQUAL_INT(1,*(int*)list_get(l,0));
    TEST_ASSERT_EQUAL_INT(2,*(int*)list_get(l,1));
    TEST_ASSERT_EQUAL_INT(3,*(int*)list_get(l,2));
    TEST_ASSERT_EQUAL_INT(4,*(int*)list_get(l,3));
    TEST_ASSERT_EQUAL(4,list_size(l));
    list_destroy(l);
}

static void test_list_delete_index_else(){
    List* l = example_list_char();
    TEST_ASSERT_EQUAL(5,list_size(l));
    list_delete_index(l,1);
    TEST_ASSERT_EQUAL_STRING("a",(char*)list_get(l,0));
    TEST_ASSERT_EQUAL_STRING("c",(char*)list_get(l,1));
    TEST_ASSERT_EQUAL_STRING("d",(char*)list_get(l,2));
    TEST_ASSERT_EQUAL_STRING("e",(char*)list_get(l,3));
    TEST_ASSERT_EQUAL(4,list_size(l));
    list_destroy(l);
}

static void test_list_get(){
    List* l = list_create();
    list_insert(l,(char*)"x");
    TEST_ASSERT_EQUAL_STRING("x",(char*)list_get(l,0));
    list_destroy(l);
}

static void test_list_size_empty(){
    List* l = list_create();
    TEST_ASSERT_EQUAL(list_size(l),0);
    list_destroy(l);
}

static void test_list_size_invalid(){
    List* l = NULL;
    TEST_ASSERT_EQUAL(list_size(l),-1);
    list_destroy(l);
}

static void test_list_size_notEmpty(){
    List* l = example_list_int();
    TEST_ASSERT_EQUAL(list_size(l),5);
    list_destroy(l);
}

static void test_iter_create_list_null(){
    List* l = NULL;
    Iterator* it = iter_create(l);
    TEST_ASSERT_NULL(it);
}

static void test_iter_create_list_valid(){
    List* l = example_list_char();
    Iterator* it = iter_create(l);
    TEST_ASSERT_NOT_NULL(it);
    list_destroy(l);
    iter_destroy(it);
}

static void test_iter_isValid_list_unvalid(){
    List* l = NULL;
    Iterator* it = iter_create(l);
    TEST_ASSERT_EQUAL(0,iter_isValid(it));
}

static void test_iter_isValid_list_empty(){
    List* l = list_create();
    Iterator* it = iter_create(l);
    TEST_ASSERT_EQUAL(0,iter_isValid(it));
}

static void test_iter_isValid_list_valid(){
    List* l = example_list_char();
    Iterator* it = iter_create(l);
    TEST_ASSERT_EQUAL(1,iter_isValid(it));
}

static void test_iter_get_list_unvalid(){
    List* l = NULL;
    Iterator* it = iter_create(l);
    TEST_ASSERT_NULL(iter_get(it));
}

static void test_iter_get_list_empty(){
    List* l = list_create();
    Iterator* it = iter_create(l);
    TEST_ASSERT_NULL(iter_get(it));
    list_destroy(l);
    iter_destroy(it);
}

static void test_iter_get_list_valid(){
    List* l = example_list_char();
    Iterator* it = iter_create(l);
    TEST_ASSERT_NOT_NULL(iter_get(it));
    list_destroy(l);
    iter_destroy(it);
}

static void test_iter_next(){
    List* l = example_list_char();
    Iterator* it = iter_create(l);
    TEST_ASSERT_EQUAL_STRING("a",(char*)list_get(l,0));
    iter_next(it);
    TEST_ASSERT_EQUAL_STRING("b",(char*)list_get(l,1));
    list_destroy(l);
    iter_destroy(it);
}

static void test_merge_empty_list(){
  List* a = list_create();
  List* b = list_create();
  List* c = merge(a,b, (sort_how) cmp);
  TEST_ASSERT_EQUAL(0, list_size(c));
  list_destroy(a);
  list_destroy(b);
}


static void test_merge_int(){
    List* a = list_create();

    list_insert(a,list_elem(1));
    list_insert(a,list_elem(3));
    list_insert(a,list_elem(5));
    list_insert(a,list_elem(7));

    List* b = list_create();
    list_insert(b,list_elem(0));
    list_insert(b,list_elem(2));
    list_insert(b,list_elem(4));
    list_insert(b,list_elem(8));

    List* c1 = merge(a,b,(sort_how)cmp);
    
    TEST_ASSERT_EQUAL(*(int*)list_get(c1,0), 0);
    TEST_ASSERT_EQUAL(*(int*)list_get(c1,1), 1);
    TEST_ASSERT_EQUAL(*(int*)list_get(c1,2), 2);
    TEST_ASSERT_EQUAL(*(int*)list_get(c1,3), 3);
    TEST_ASSERT_EQUAL(*(int*)list_get(c1,4), 4);
    TEST_ASSERT_EQUAL(*(int*)list_get(c1,5), 5);
    TEST_ASSERT_EQUAL(*(int*)list_get(c1,6), 7);
    TEST_ASSERT_EQUAL(*(int*)list_get(c1,7), 8);
    
    list_destroy(a);
    list_destroy(b);
    list_destroy(c1);
}

static void test_merge_char(){
    List* a = list_create();
    list_insert(a,"c");
    list_insert(a,"d");
    list_insert(a,"g");
    list_insert(a,"h");
    list_insert(a,"i");

    List* b = list_create();
    list_insert(b,"a");
    list_insert(b,"b");
    list_insert(b,"e");
    list_insert(b,"f");
    list_insert(b,"j");

    List* c1 = merge(a,b,(sort_how)strcmp);

    TEST_ASSERT_TRUE(strcmp((char*)list_get(c1,0), "a") == 0);
    TEST_ASSERT_TRUE(strcmp((char*)list_get(c1,1), "b") == 0);
    TEST_ASSERT_TRUE(strcmp((char*)list_get(c1,2), "c") == 0);
    TEST_ASSERT_TRUE(strcmp((char*)list_get(c1,3), "d") == 0);
    TEST_ASSERT_TRUE(strcmp((char*)list_get(c1,4), "e") == 0);
    TEST_ASSERT_TRUE(strcmp((char*)list_get(c1,5), "f") == 0);
    TEST_ASSERT_TRUE(strcmp((char*)list_get(c1,6), "g") == 0);
    TEST_ASSERT_TRUE(strcmp((char*)list_get(c1,7), "h") == 0);
    TEST_ASSERT_TRUE(strcmp((char*)list_get(c1,8), "i") == 0);
    TEST_ASSERT_TRUE(strcmp((char*)list_get(c1,9), "j") == 0);

    list_destroy(a);
    list_destroy(b);
    list_destroy(c1);
}

/****************************************END TEST******************************************/
int main(){
    UNITY_BEGIN();
        RUN_TEST(test_list_create);
        RUN_TEST(test_list_isEmpty_empty);
        RUN_TEST(test_list_isEmpty_notEmpty);
        RUN_TEST(test_list_isEmpty_invalid);
        RUN_TEST(test_list_insert_empty);
        RUN_TEST(test_list_insert_index);
        RUN_TEST(test_list_insert_index_head);
        RUN_TEST(test_list_insert_index_tail);
        RUN_TEST(test_list_delete);
        RUN_TEST(test_list_delete_one);
        RUN_TEST(test_list_delete_index_head);
        RUN_TEST(test_list_delete_index_tail);
        RUN_TEST(test_list_delete_index_else);
        RUN_TEST(test_iter_create_list_null);
        RUN_TEST(test_iter_create_list_valid);
        RUN_TEST(test_iter_isValid_list_unvalid);
        RUN_TEST(test_iter_isValid_list_empty);
        RUN_TEST(test_iter_isValid_list_valid);
        RUN_TEST(test_list_get);
        RUN_TEST(test_list_size_empty);
        RUN_TEST(test_list_size_invalid);
        RUN_TEST(test_list_size_notEmpty);
        RUN_TEST(test_iter_get_list_unvalid);
        RUN_TEST(test_iter_get_list_empty);
        RUN_TEST(test_iter_get_list_valid);
        RUN_TEST(test_iter_next);
        RUN_TEST(test_merge_empty_list);
        RUN_TEST(test_merge_int);
        RUN_TEST(test_merge_char);

    return UNITY_END();
}