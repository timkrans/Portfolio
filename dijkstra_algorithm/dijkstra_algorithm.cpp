/******************************************************************************
Timothy Kransberger
CSE 310
*******************************************************************************/
#include <iostream>
#include <fstream>
#include <cmath> 
#include <vector>
#include <string>
using namespace std;
//setting constant for flt_max
const float FLT_MAX = 200000;
//max size for the stack
const int MAX_SIZE = 400;

//linked list for nodes
struct Node {
    int index;
    int u;
    int v;
    float w;
    Node *next;
};

//creates nodes
Node* createEdge(int dest, int start ,float weight) {
    Node* newNode = new Node;
    newNode->u  = start;
    newNode->w = weight;
    newNode->v = dest;
    newNode->next = nullptr;
    return newNode;
}

// vertex for dijkstra algorithm
struct Vertex{
    int index;
    float key;
    Vertex* pi;
};

//struct for graph
struct Graph {
    int numVertices;
    std::vector<int> vertices;
    Node** adjLists;
    Vertex** V;
};
//stack for results to print the order
struct Stack {
    Vertex* items[MAX_SIZE];
    int top;
};

//pushes to stack
void push(Stack *s, Vertex* new_item) {
    s->top++;
    s->items[s->top] = new_item;
}

//pops from stack
Vertex* pop(Stack *s) {
    Vertex* ret = s->items[s->top];
    s->top--;
    return ret;
}

//goes through and prints the graph
void printGraph(Graph* graph) {
    for (int v = 0; v < graph->numVertices; v++) {
        std::cout <<"ADJ["<< graph->vertices[v]<< "]";
        Node* temp = graph->adjLists[v];
        while (temp!= NULL) {
            std::cout<< "-—> " << "["<< temp->u << " " <<temp->v<< ": "<< std::fixed << std::setprecision(2) <<temp->w<< "]";
            temp = temp->next;
        }
        std::cout<<"\n";
    }
}

//adds the Node
void addNode(Graph* graph, int src, int dest, float weight, int dir) {
    if(src <= graph->numVertices && src >= 1){
        Node* newEdge = createEdge(dest,src, weight);
        if(dir==1){
            newEdge->next = graph->adjLists[src-1];
            graph->adjLists[src-1] = newEdge;
        }
        else{
            Node* current = graph->adjLists[src-1];
            if(current != NULL){
                while (current->next != NULL) {
                    current = current->next;
                }
                current->next = newEdge;
            }
            else{
                graph->adjLists[src-1] = newEdge;
            }
        }
    }
     else{
        std::cout<<src<<" not found in the list\n";
    }
}

//creates the adj list of edges
Graph* createGraph(int vertices) {
    Graph* graph = new Graph;
    graph->numVertices = vertices;
    graph->adjLists = new Node*[vertices];
    graph->vertices.resize(vertices); 
    graph->V = new Vertex*[vertices];
    for (int i = 0; i < vertices; i++){
        graph->adjLists[i] = nullptr;
        graph->vertices[i] = i+1;
        graph->V[i] = new Vertex;
        graph->V[i]->index = i+1;
        graph->V[i]->key = FLT_MAX;
        graph->V[i]->pi = nullptr;
    }
    return graph;
}

//builds the min heap given q
Vertex**  buildMinHeap(Vertex**  q,int num){
    for(int i = 0; i < num; i++){
        int k = 1;
        int j = 0;
        k= i+1;
        while(k!= 1){
            j = floor(k/2);
            if(q[k-1]->key<q[j-1]->key){
                Vertex* temp = q[k-1];
                q[k-1]= q[j-1];
                q[j-1]= temp;
            }
            k = floor(k/2);
        }
    }
    return q;
}

//since we will rebuild min heap we can just delete by swaping last and first then making last null
Vertex**  deleteNode(Vertex** q, int num){
    q[0]=q[num-1];
    q[num-1]= NULL;
    return q;
}

void relax(Vertex* u, Vertex* v , int distFromStart, int distanceApart){
    if(v->key>distanceApart+distFromStart){
        v->pi = u;
        v->key = distanceApart+distFromStart;
    }
}

//calculated the shortest path using dijkstra algorithm
void singlePair(Graph* graph, int start, int end){
    Vertex** q = new Vertex*[graph->numVertices];
    for(int i = 0; i < graph->numVertices; i++) {
        graph->V[i]->key = FLT_MAX;
        graph->V[i]->pi = nullptr;
        q[i] = new Vertex;
        q[i] = graph->V[i];
    }
    q[start-1]->key =0;
    q = buildMinHeap(q,graph->numVertices);
    int z = graph->numVertices;
    float distFromStart;
    float distBtween;
    Vertex* u;
    while(z!=1){
        if(q[0]->key== FLT_MAX||q[0]->index == end){
            break;
        }
        u =q[0];
        distFromStart = u->key;
        q = deleteNode(q, z);
        z= z-1;
        Node* current = graph->adjLists[u->index-1];
        if(current != NULL){
            distBtween= current->w;
            relax(u,graph->V[current->v-1], distFromStart, distBtween);
            while(current->next!= NULL){
                current = current->next;
                distBtween= current->w;
                relax(u,graph->V[current->v-1], distFromStart, distBtween);
            }
        }
        q = buildMinHeap(q, z);
    }
    
}

//calculated the shortest path using dijkstra algorithm
void singleSource(Graph* graph, int start){
    Vertex** q = new Vertex*[graph->numVertices];
    for(int i = 0; i < graph->numVertices; i++) {
        graph->V[i]->key = FLT_MAX;
        graph->V[i]->pi = nullptr;
        q[i] = new Vertex;
        q[i] = graph->V[i];
    }
    q[start-1]->key =0;
    q = buildMinHeap(q,graph->numVertices);
    int z = graph->numVertices;
    float distFromStart;
    float distBtween;
    Vertex* u;
    while(z!=1){
        if(q[0]->key== FLT_MAX){
            break;
        }
        u =q[0];
        distFromStart = u->key;
        q = deleteNode(q, z);
        z= z-1;
        Node* current = graph->adjLists[u->index-1];
        if(current != NULL){
            distBtween= current->w;
            relax(u,graph->V[current->v-1], distFromStart, distBtween);
            while(current->next!= NULL){
                current = current->next;
                distBtween= current->w;
                relax(u,graph->V[current->v-1], distFromStart, distBtween);
            }
        }
        q = buildMinHeap(q, z);
    }
}

//prints the minimum distance
void printMin(Graph* graph, int start, int end){
    if(graph->V[end-1]->key == FLT_MAX){
        std::cout<<"There is no path from "<< start << " "<< end;
    }
    else if(graph->V[start-1]->key != 0 ){
        std::cout<<"There is no path from "<< start << " "<< end;
    }
    else{
        std::cout<<"The min distance is: "<<graph->V[end-1]->key;
    }
}

//puts the vertexs from the graph into a stack then prints them out
void printMinPath(Graph* graph, int start, int end){
    if(graph->V[end-1]->key == FLT_MAX){
        std::cout<<"There is no path from "<< start << " "<< end;
    }
    else if(graph->V[start-1]->key != 0 ){
         std::cout<<"There is no path from "<< start << " "<< end;
    }
    else{
        int j = end;
        Stack* s = new Stack();
        Vertex* g = new Vertex();
        s->top = -1;
        while(start != graph->V[j-1]->index){
            push(s, graph->V[j-1]);
            if(graph->V[j-1]->pi != NULL){
                j= graph->V[j-1]->pi->index;
            }
        }
        push(s, graph->V[j-1]);
        while(s->top != -1){
            Vertex* g = pop(s);
            if(s->top != -1){
                std::cout<<"["<<g->index<< ":    "<< std::fixed << std::setprecision(2) <<g->key<< "]-->";
            }
            else{
                std::cout<<"["<<g->index<< ":    "<< std::fixed << std::setprecision(2) <<g->key<< "]";
            }
        }
    }
}

int main() {
    int choice;
    bool run = true;
    bool dirrect= true;
    std::string name, dir;
    int flag,src,end;
    Graph* graph = NULL;
    std::ifstream file; 
    while (run == true)
	{
		std::cout << endl << endl;
		std::cout << " Homework 5 - CSE 310 " << endl;
		std::cout << " ----------------------------- " << endl;
		std::cout << " 1. Read the graph from the file" << endl;
		std::cout << " 2. Print ADJ " << endl;
		std::cout << " 3. Single Source, enter the strating node number " << endl;
		std::cout << " 4. Single Pair, enter the strating node and ending done numbers " << endl;
		std::cout << " 5. Print Path, enter the strating node and ending done numbers " << endl;
		std::cout << " 6. Print Length, enter the strating node and ending done numbers " << endl;
		std::cout << " 7. Print Path, enter the strating node and ending done numbers " << endl;
        std::cout << " 8. Stop " << endl;
		std::cout << endl<< " Enter your choice 1- 8 : ";
		cin >> choice;
		switch (choice)
		{
		case 1:
            graph = NULL;
            std::cout << "Enter the file name (ex network01.txt): ";
            std::cin>>name;
            file.open(name);
            if (!file) {
                std::cerr << "File not found";
                break;
             }
            std::cout << "Enter d for directed or ud for undirected: ";
            std::cin>>dir;
            for (std::size_t i = 0; i < dir.size(); ++i) {
                dir[i] = std::toupper(dir[i]);
            }
            if(dir== "D"){
                bool dirrect= true;
            }
            else if( dir == "UD"){
                bool dirrect= false;
            }
            else{
                std::cout << "invalid dirention.";
                break;
            }
            std::cout << "Enter 1 or 2 for flag ";
            std::cin>>flag;
            //gets n and m and creats graph
            int n, m;
            file >> n >> m;
            graph = createGraph(n);
            //adds the edges to the graph
            if(dirrect == true){
                for (int i = 0; i < m; i++) {
                    int start, end ,g;
                    float value;
                    file >> g>> start >> end >> value;
                    addNode(graph, start, end, value, flag);
                }
            }
            else{
                for (int i = 0; i < m; i++) {
                    int start, end ,g;
                    float value;
                    file >> g>> start >> end >> value;
                    addNode(graph, start, end, value, flag);
                    addNode(graph, end, start, value, flag);
                }
            }
        	break;
		case 2: 
			if(graph!= NULL){
                printGraph(graph);
            }
            else{
                cout<<"Please enter a graph first";
            }
			break;
		case 3:
            if(graph!= NULL){
                cout<<"Enter the node number for single source: ";
                cin>> src;
                if(src> graph->numVertices||src<1){
                    break;
                }
                singleSource(graph,src);
            }
            else{
                cout<<"Please enter a graph first";
            }
			break;

		case 4:
            if(graph!= NULL){
                cout<<"Enter the strading node: ";
                cin>> src;
                if(src> graph->numVertices||src<1){
                    break;
                }
                cout<<"Enter the Ending node: ";
                cin>> end;
                if(end> graph->numVertices||end<1){
                    break;
                }
                singlePair(graph,src,end);
            }
            else{
                cout<<"Please enter a graph first";
            }
			break;
		case 5:
            if(graph!= NULL){
                cout<<"Enter the strading node: ";
                cin>> src;
                if(src> graph->numVertices||src<1){
                    break;
                }
                cout<<"Enter the Ending node: ";
                cin>> end;
                if(end> graph->numVertices||end<1){
                    break;
                }
                cout<< "The shortest path from " << src<< " to "<<end<< "is: \n";
                printMinPath(graph, src,end);
            }
            else{
                cout<<"Please enter a graph first";
            }
			break;

		case 6:
            if(graph!= NULL){
                cout<<"Enter the strading node: ";
                cin>> src;
                if(src> graph->numVertices||src<1){
                    break;
                }
                cout<<"Enter the Ending node: ";
                cin>> end;
                if(end> graph->numVertices||end<1){
                    break;
                }
                printMin(graph, src,end);
            }
            else{
                cout<<"Please enter a graph first";
            }
			break;
		case 7:
            if(graph!= NULL){
                cout<<"Enter the strading node: ";
                cin>> src;
                if(src> graph->numVertices||src<1){
                    break;
                }  
                cout<<"Enter the Ending node: ";
                cin>> end;
                if(end> graph->numVertices||end<1){
                    break;
                }
                cout<< "The shortest path from " << src<< " to "<<end<< "is: \n";
                printMinPath(graph, src,end);
            }
            else{
                cout<<"Please enter a graph first";
            }
			break;
        case 8:
		    run = false;
			break;
		default:
			std::cout << "Invalid choice";
		}
    }
    return 0;
}