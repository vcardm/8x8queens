# 8x8queens
Task: Write a program that places 8 queens on an 8x8 board where none of the queens are in conflict with each other.  You are to implement the solution by using the hill-Climbing algorithm with random restarts.

Details:
Java version: 17.0.1

 
Steps:
1.  Create an 8x8 map to begin
2. Using the Random import, randomize the map
3. Next, find conlficts in: columns rows and diagnolally. 
3a. If it is not in goal state, meaning conflict exists, evaluate all of the possible neighbor states by moving each column’s queen (called the moveQueen method) through the rows of its column and generating a heuristic value for each of those states.
4. Once we know the neighbor states, we compare heurisitc value and see if any states have a lower one - if a better was not found, we reached the local minima and perform random start. 
4a. If compared and found a lower heuristic value, then that becomes the current state. 
5. Step 4 is repeated until no lower value is found. 
