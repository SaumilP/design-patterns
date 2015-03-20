Pipes-And-Filters Design Pattern
=====================
In many enterprise integration scenarios, a single event triggers a sequence of processing steps/stages. Each performing a specific function.

For example, lets assume you have requirement to encode files in a directory structure and create an output file.
This can be achieved by using Pipes-and-Filters architecture style to divide a larger processing task into sequence of smaller tasks - running independently connected to each other by channels ( pipes ).

To be able to demonstrate this scenario, this example create a very simple pipe loading file and processing it.
It can be extended to run in parallel to improve performance.

#### Design Pattern Benefits ####
There are few obvious benefits by using this architectural style listed below:

1. Divide and conquer : The seperate processes can be independently designed
2. Increase cohesion : The processes have functional cohesion
3. Reduce coupling : The processes have only one input and one output
4. Increase abstraction : The pipeline components are often good abstractions, hiding their internal details.
5. Increase reusability : The processes can often be used in many different contexts
6. Increase reuse : It is often possible to find reusable components to insert into a pipeline
7. Design for flexibility : There are several ways in which the system is flexible
8. Design for testability : It is normally easy to test the individual processes
9. Design defensivily : You regorously check the inputs of each component, or else you can use design by contract

#### Class Diagram ####
![Alt text](pipes-class-diag.png?raw=true "Pipes-And-Filters Pattern")
