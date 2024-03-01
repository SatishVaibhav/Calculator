# Advanced_CS_Projects_Project2
The Greatest Calculator App (By Alexander Mihalcea and Vaibhav Satish)

    Project Basics:
        Our program is a calculator, that in addition to completing complex arithmetic, can also autodetect a graphing
        equation(eq. y=x^2), and opens a visual ui of the graph. Our program can handle the following operators[+,-,*,/,^],
        it follows order of operations. It can also handle parenthesis, including nested parenthesis. We can also handle both
        equations that use whole numbers, and equations that use doubles, but not a combination. *NOTE: Since our translator takes 
        the equation as a string, if you put spaces anywhere in your input it won't work. Don't put spaces!!! Also, if you're graphing an equation that has a coefficient, 
        use the multiplcation symbol(*) between x and the coefficent. Ex: y=3*x. 

    Architecture:
    
        * Our program has a primary UI class, which is what needs to be run(done by Vaibhav)

            -The UI(RUN THIS CLASS). 
             The UI allows the user to interact with our calculator. 
             UI.java consists of one primary class (UI), and several private classes within the UI class. 
             While creating, this class i thought about what every calculator consists of. In the end i came up with 3 buttons 
             and an area to input the equation. I have setup the UI in such a way that the user can type directly into the screen 
             rather than a compiler console. 
            -Submit Button
              The submit button allow the user to send their input off to the calculator class, which reads the input 
              and provides the answer. In order to make the screen more appealing, 
              i hid the input area, the submit button, and the history button 
              (Will talk about this later). Now you will see the answer to your equation and a back button. 
            -Graphing Ability
              This calculator is fairly advanced considering that it has a graphing ability. 
              to use the graphing ability, enter you equation in the format "y=[Your Equation here (USE the variable x)]. 
              Once the input is understood by the compiler, the compiler will call the Graphing Calculator class. 
              This will cause the method CreateGrid() to be called, which will create the graph in a seperate screen. 
            -Back Button
              The back button allows the user to return to the main screen and input another equation to graph or to solve. 
              The back button works by hiding and showing the other buttons and the text area. 
              If you want to go back to the main screen after graphing an equation, look at your taskbar and find the 
              Java UI icon (the Java logo),  when you hover over it you should see the graph screen and another screen. 
              Click the other screen and the you should see the back button.
            -History Button
              This button will allow the user to see what they have typed in a session of using the calculator. 
              In order to make this happen, i had to create an ArrayList, to which i appended the equation and its solution. 
              For graphing, i appended the equation and a message saying that it was graphed. 
              Once you click the History button and want to return to the main screen, look at your taskbar and 
              find the Java UI icon (the Java logo), when you hover over it you should see the graph screen and another screen. 
              Click the other screen and the you should see the back button. 
            -TextField 
                This is where the user will input their equation. the computer will read this equation once the submit button 
                is pushed and the program will do the necessary to provide a result. 
              
        * The Calculator class is the main arithmatic class (done by Alex with minor edits by Vaibhav):
            
            - Its primary meathod, "Translate", is given a string of the equation,
            and its primary function is to recursivly find parenthases, espesially
            nested ones. Once an instance of the function gets to a section with no
            more parenthases, it returns a call to the "recursive_solve_double"
            function, which gets added to the "clean_equation" variable of the
            previous translate instance in the recursive tree. At the end of all of this
            the original translate instance will have a symplified version of the equation
            in its "clean_equation" variable, where it will do one final call to the recursive_solve_double
            function, and return the resulting number.
            
            - The "recursive_solve_double" function is also a recursive function, which given a string of an equation
            with no parenthases, will recurivly solve it by following PEMDAS. This function uses several meathods,
            like ones that take the equation string and an index, and find the number to the right or left of that index,
            and convert it to a double. These doubles are than used for the actual arithmatic, and are later converted back
            to a string and added to the simplified equation.
        
        * The Graphing Calculator class is called when an equation starting with "y=" is provided, and heavily relies on
        the calculator class (done by Alex with minor edits by Vaibhav)
            
            - Its primary function, Graph_equation, takes the user inputed equation, along with the presets for 
            graph size (ie. 10 would mean graph size would be [-10, 10]x and [-10,10] y), and also the preset
            for steps (ie. 2 would mean 2 points for each whole number in the x range,(x=-10,-9.5,-9, etc.)). 
            Then, it makes a list of lists of strings, which represent the graph. Then, starting at x = min val, it 
            replaces the "x" in the user equation with the specific value, calls the calculator translate class to solve it,
            and adds the number to a list of "y_vals"(which represent the y values for each x value, starting at the min), 
            and increments the value of x by 1/steps. Finnaly it calls the the graph_maker function.
            
            - The graph_maker function goes through the list of list representing the graph, and populates it as follows:
                # if the cell has an x_index of max, it makes it contain "|" to represent the y axis (because if max is 10, the range is -10 to 10, but the list index starts at 0)
                # if the cell has an y_index of max, it makes it contain "-" to represent the x axis
                # if the cell has an x_index % steps = 1, than it makes it contain "1", which is used to make the lines on the graph
                # if the cell has an y_index that represents a Y value within a set error margain of the value of y_vals[cell_x_index],
                sets cell to contain "@", which represents a point on graph.

                Extra Feature: A graph like a parabula would have points that are distant, making it look like a graph of points and not a continues line. To fix this,
                there is a system which finds the distance between the y_value at that x cordinate, and the y_value before and after. It then uses this to
                draw a vertical line instead of a point, which gets longer the bigger the diffrence, which when graphed visualy gives the illusion of a continues line.
  
            
        
