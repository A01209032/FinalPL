
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

/*----------------------------------------------------------------
*
* Autor: Samuel Ramirez
* Contacto: samuel.i.ramirez@gmail.com
*
*--------------------------------------------------------------*/

int main(int argc, char *argv[]) {
    int pid;
    int n;
    if (argc != 2 ){
        printf("usage: %s requests\n", argv[0]);
        return -2;
    }

    n = atoi(argv[1]);

    
    if(0>n){
        printf("N debe ser Mayor que 0\n");
        return -2;
    }
    char *newargv[] = { NULL };
    //printf("hola %d ",*running);
    int pids[n];
    for(int i=0;i<n;i++){
        if((pids[i]=fork())==-1){
            perror("fork");
        }
        else if(pids[i]==0){
        execv("script", newargv);   
        }        
    }
    
    while(wait(NULL) > 0);
    return 0;
}