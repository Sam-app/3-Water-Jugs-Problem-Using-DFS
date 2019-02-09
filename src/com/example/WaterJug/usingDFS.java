package com.example.WaterJug;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class usingDFS {
    //declare the global function
    public static int A,B,C; // the capcity of jugs
    public static Stack<Node> stack = new Stack<>();
    public static Set<String> alreadyVisted;
    public static int numberOfNodesTraversed = 0;

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the capacities of the 3 jugs");
        System.out.println("The capacity of the jugs should be postive");
        // read the maximum capacity of jugs
        A = sc.nextInt();
        B = sc.nextInt();
        C = sc.nextInt();

        Node root = new Node(0,0,0);

        stack.push(root);                   // add the intial state to the stack
        alreadyVisted = new HashSet<>();
        String rootStr = root.a+" "+root.b+" "+root.c;
        alreadyVisted.add(rootStr);         // add the intial state to the visted list


        while(!stack.isEmpty()){            //check if the stack is not empty
            numberOfNodesTraversed++;       //count how many states traversed
            Node node = stack.pop();

            int a = node.a;
            int b = node.b;
            int c = node.c;

            System.out.println("( "+a+" , "+b+" , "+c+" )");   // print the state
            //Pour A  into B/C
            if (a > 0) {                 // check if jugs a is not empty

                //pour a into b first
                if ((a + b) <= B) {
                    // empty a into b
                    getStates(0,a + b, c);

                } else {

                    getStates((a + b - B), B, c);   //leave the remain water in A
                    //pour a into b and then into c
                    if (((a + b + c) - B) <= C) {
                        //empty a into b then into c
                        getStates(0, B, (a + b + c - B));
                    } else {
                        //pour a into b and c
                        getStates(a + b + c - (B + C), B, C);
                    }

                }
                //pour a into c first
                if ((a + c) <= C) {
                    // empty a into c
                    getStates(0, b, a + c);
                } else {
                    getStates(a + c - C, b, C);
                    //pour a into c then into b
                    if (a + b + c - C <= B) {
                        getStates(0, a + b + c - C, C);
                    } else
                        getStates(a + b + c - (B + C), B, C);

                }
                //Empty A
                getStates(0, b, c);


            }
            // pour b into a|c
            if (b > 0) {                // check if b has water

                //pour b into A then into C
                if ((a + b) <= A) {
                    // empty b into a
                    getStates(a + b, 0, c);
                } else {
                    getStates(A, a + b - A, c);  // leave the remain in
                    if ((a + b + c - A) <= C) {
                        //empty b into c after pouring into A
                        getStates(A, 0, a + b + c - A);
                    } else {
                        getStates(A, a + b + c - A - C, C);
                    }

                }

                //pour b into C then into A
                if ((b + c) <= C) {
                    getStates(a, 0, b + c);
                } else {
                    getStates(a, b + c - C, C);
                    if ((b + c + a - C) <= A) {
                        //empty b into A after pouring into C
                        getStates(a + b + c - C, 0, C);
                    } else {
                        getStates(A, (a + b + c - (A + C)), C);
                    }
                }

                //Empty B
                getStates(a,0,c);

            }
            if (c > 0) {     //check if c is not empty
                //System.out.println("C scoop");
                //pour C into B then into A
                if ((c + b) <= B) {
                    //empty c into B
                    getStates(a, b + c, 0);
                } else {
                    getStates(a, B, b + c - B);
                    if ((b + c + a - B) <= A) {
                        //empty c into A after pouring into B
                        getStates(a + b + c - B, B, 0);
                    } else {
                        getStates(A, B, (a + b + c - (A + B)));
                    }
                }

                //pour C into A then into B
                if ((c + a) <= A) {
                    //empty C into A
                    getStates(a + c, b, 0);
                } else {
                    getStates(A, b, a + b - C);
                    if ((a + b + c - A) <= B) {
                        getStates(A, a + b + c - A, 0);
                    } else {
                        getStates(A, B, a + b + c - (A + B));

                    }
                }

                //Empty C
                getStates(a,b,0);

            }

            //fill A
            if(a<A)
                getStates(A,b,c);

            //fill B
            if(b<B)
                getStates(a,B,c);

            //fill C
            if(c<C)
                getStates(a,b,C);




        }

        System.out.println(numberOfNodesTraversed+ " Nodes traversed");

    }



    public static void  getStates(int a , int b, int c){
        if(a<=A && b<=B && c<=C && a>=0 && b>=0 && c>=0){
            Node newNode =new Node(a,b,c);
            String nodeStr = newNode.a+" "+newNode.b+" "+newNode.c;
            boolean isVisted = alreadyVisted.contains(nodeStr);
            if(!isVisted) {
                stack.add(newNode);
                alreadyVisted.add(nodeStr);//Child added to the queue

            }

        }

    }

}


