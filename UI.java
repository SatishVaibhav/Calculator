//RUN THIS FILE TO WITNESS THE MAJIC OF A CALCULATOR 

//Imports
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.*; 
import java.awt.event.ActionListener; 

public class UI extends javax.swing.JFrame{

    // Universal Variables needed for the UI
    static javax.swing.JFrame mframe = new javax.swing.JFrame();  
    static javax.swing.JPanel mpanel = new javax.swing.JPanel(); 
    static ArrayList <JButton> buttons = new ArrayList <JButton> (); 
    static JTextArea g_equation; 
    static JTextArea equation = new JTextArea(); 
    static JButton back = new JButton("Back"); 
    static JButton hback = new JButton("Back"); 
    static JButton graph = new JButton("Graph"); 
    static ArrayList<String> computations = new ArrayList <String> (); 
    static DefaultListModel<String> model = new DefaultListModel<>(); 
	static javax.swing.JList<String> disp = new JList(model); 
    static String solution = ""; 
   

    //Key Method that sets up the UI
    public UI(){
        setTitle("The Greatest Calculator");
        setSize(595, 600); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CreateForm();
    }

    //Creates our UI, and makes it visible to the user
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
        UI ui = new UI();
        ui.setVisible(true);
        });
    }

    //Creates the UI components
    public void CreateForm(){
        getContentPane().add(mpanel); 
        mpanel.setBackground(Color.BLUE);
        mpanel.setLayout(null); 

        //0-9 Buttons
        for (int i = 0; i<10; i++){
            JButton num = new JButton(String.valueOf(i)); 
            buttons.add(num); 
            num.setActionCommand(String.valueOf(i));
            num.setFont(new Font("San-Serif", num.getFont().getStyle(), 16)); 
            num.addActionListener(new ButtonClickListener()); 
            mpanel.add(num);
        }
        
        //sets 0-9 positions and size
        buttons.get(0).setBounds(30,280, 50,50); 
        buttons.get(1).setBounds(110,280, 50,50); 
        buttons.get(2).setBounds(190,280, 50,50); 
        buttons.get(3).setBounds(270,280, 50,50); 
        buttons.get(4).setBounds(350 ,280, 50,50); 
        buttons.get(5).setBounds(30,350, 50,50); 
        buttons.get(6).setBounds(110,350, 50,50); 
        buttons.get(7).setBounds(190,350, 50,50); 
        buttons.get(8).setBounds(270,350, 50,50); 
        buttons.get(9).setBounds(350, 350, 50,50); 

        //other important buttons 
        JButton multiplication = ButtonCreator("*");
        multiplication.setBounds(425, 350 , 50, 50);
        JButton division = ButtonCreator("/"); 
        division.setBounds(425, 420, 50, 50);
        JButton subtract = ButtonCreator("-"); 
        subtract.setBounds(425, 280, 50, 50);
        JButton add = ButtonCreator("+"); 
        add.setBounds(500, 210, 50, 50);
        JButton exponent = ButtonCreator("^"); 
        exponent.setBounds(500, 280, 50, 50);
        JButton decimal = ButtonCreator("."); 
        decimal.setBounds(500, 350, 50, 50);
        JButton Parenthesis_left = ButtonCreator("(");
        Parenthesis_left.setBounds(500, 420, 50, 50);
        JButton Parenthesis_right = ButtonCreator(")");
        Parenthesis_right.setBounds(500, 490, 50, 50);
        JButton squared = ButtonCreator("^2"); 
        squared.setBounds(30, 490, 50,50); 
        squared.setFont(new Font("San-Serif", squared.getFont().getStyle(), 14));
        JButton cubed = ButtonCreator("^3"); 
        cubed.setBounds(110, 490, 50,50); 
        cubed.setFont(new Font("San-Serif", cubed.getFont().getStyle(), 14));
        JButton ans = new JButton("ans"); 
        ans.setBounds(190, 490, 50,50); 
        ans.setFont(new Font("San-Serif", ans.getFont().getStyle(), 9));
        ans.addActionListener(new AnsButtonClickListener());
        ans.setBackground(Color.YELLOW);
        mpanel.add(ans); 
        buttons.add(ans); 
        JButton factorial = new JButton("x!"); 
        factorial.setBounds(270, 490, 50,50); 
        factorial.setFont(new Font("San-Serif", factorial.getFont().getStyle(), 9));
        buttons.add(factorial); 
        factorial.setBackground(Color.YELLOW);
        factorial.setForeground(Color.BLACK);
        mpanel.add(factorial); 
        factorial.setActionCommand("!");
        factorial.addActionListener(new factorialButtonClickListener());
        JButton sqrt = new JButton("\u221A"); 
        sqrt.setActionCommand("\u221A"); 
        sqrt.addActionListener(new sqrtButtonClickListener()); 
        mpanel.add(sqrt); 
        sqrt.setBackground(Color.YELLOW);
        sqrt.setForeground(Color.BLACK);
        sqrt.setBounds(350,490, 50,50); 
        sqrt.setFont(new Font("San-Serif", sqrt.getFont().getStyle(), 8));
        buttons.add(sqrt); 
        //equals buttion
        JButton equal = new JButton("="); 
        equal.setBounds(425, 490, 50, 50);
        buttons.add(equal); 
        equal.setBackground(Color.MAGENTA);
        equal.setForeground(Color.BLACK);
        equal.addActionListener(new EqualButtonClickListener()); 
        mpanel.add(equal); 
        equal.setFont(new Font("San-Serif", equal.getFont().getStyle(), 20)); 

        //Graph_problem Button
        JButton Graph_problem = new JButton("Graph An Equation"); 
        buttons.add(Graph_problem); 
        Graph_problem.setBackground(Color.CYAN);
        Graph_problem.setForeground(Color.BLACK);
        mpanel.add(Graph_problem); 
        Graph_problem.addActionListener(new GraphAnEquationButtonClickListener()); 
        Graph_problem.setFont(new Font("San-Serif", Graph_problem.getFont().getStyle(), 20)); 
        Graph_problem.setBounds(30,420,370,50); 

        //Clear Button 
        JButton clear = new JButton("Clear");
        clear.setBounds(370, 210, 100, 50);
        clear.setFont(new Font("San-Serif", clear.getFont().getStyle(), 20));
        clear.setBackground(Color.RED);
        clear.setForeground(Color.BLACK);
        buttons.add(clear); 
        clear.addActionListener(new ClearButtonClickListener()); 
        mpanel.add(clear); 

        //History Button
        JButton History = new JButton("History");
        History.setBounds(30, 210, 195, 50);
        History.setFont(new Font("San-Serif", History.getFont().getStyle(), 20));
        buttons.add(History); 
        History.setBackground(Color.CYAN);
        History.setForeground(Color.BLACK);
        History.addActionListener(new HistoryButtonClickListener()); 
        mpanel.add(History); 

        //Single Character Delete Button 
        JButton delete = new JButton("Del");
        delete.setBounds(260, 210, 80, 50);
        delete.setFont(new Font("San-Serif", clear.getFont().getStyle(), 20));
        delete.setBackground(Color.RED);
        delete.setForeground(Color.BLACK);
        buttons.add(delete); 
        delete.addActionListener(new DelButtonClickListener()); 
        mpanel.add(delete); 

        //Button Color Code
        for (int i = 0; i<10; i++){
        buttons.get(i).setBackground(Color.GREEN);
        buttons.get(i).setForeground(Color.BLACK); 
        }

        //Displaying the selected numbers and operation
        equation.setBounds(0,0,600,180); 
        equation.setFont(new Font("San-Serif", equation.getFont().getStyle(), 48));
        equation.setLineWrap(true);
        equation.setWrapStyleWord(true);
        equation.setForeground(Color.WHITE); 
        equation.setBackground(Color.BLACK);
        equation.setEditable(false); 
        mpanel.add(equation); 
    }

    //creates buttons other than the number buttons
    public JButton ButtonCreator(String use) {
        JButton button_type =  new JButton(use);
        buttons.add(button_type); 
        button_type.setBackground(Color.YELLOW);
        button_type.setForeground(Color.BLACK);
        mpanel.add(button_type); 
        button_type.setActionCommand(use);
        button_type.addActionListener(new ButtonClickListener());
        button_type.setFont(new Font("San-Serif", button_type.getFont().getStyle(), 20)); 
        return button_type; 

    }

}//End of UI class

//Seperate Class for the Graphing UI
class GraphUI{
    static javax.swing.JFrame g_frame = new javax.swing.JFrame();

    public static void createGrid(String[][] data, String targetString){
        int numRows = data.length;
        int numCols = data[0].length;
        g_frame = new JFrame("Graph"); 
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(numRows, numCols));
        int window_size = 600;
		Dimension cell_size = new Dimension(window_size/numRows, window_size/numRows);
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
               JPanel cellPanel = new JPanel();
               String cellValue = data[row][col];
               if (cellValue.equals("   ")){
                cellPanel.setBackground(Color.BLUE);
			  }else{
                if (cellValue.equals(targetString)){
                cellPanel.setBackground(Color.GREEN);
                }else if(cellValue.equals(" - ")){
                cellPanel.setBackground(Color.BLACK);
                }else if(cellValue.equals(" | ")){
                    cellPanel.setBackground(Color.BLACK);
                }else{
                    cellPanel.setBackground(Color.LIGHT_GRAY);
                }
			  }
                cellPanel.setPreferredSize(cell_size);															
                gridPanel.add(cellPanel);		
                								
            }
        } // end of outer for-loop   
        g_frame.add(gridPanel); 
        //Large space here due to positioning label under axis 
        JTextArea Y_axis = new JTextArea ("                                                                                                      Y Axis");
        Y_axis.setFont(new Font("Serif", Y_axis.getFont().getStyle(), 12)); 
        Y_axis.setBounds(600,300,10,10); 
        Y_axis.setBackground(Color.BLACK);
        Y_axis.setEditable(false);
        JLabel X_axis = new JLabel("X Axis");
        JPanel labelPanelx = new JPanel();
        JPanel labelPanely = new JPanel(); 
        labelPanely.setLayout(new BorderLayout());
        labelPanelx.setLayout(new BorderLayout());
        labelPanely.add(Y_axis, BorderLayout.SOUTH);
        labelPanelx.add(X_axis, BorderLayout.WEST);
        labelPanely.add(Y_axis, BorderLayout.SOUTH);
        labelPanely.setBackground(Color.BLACK);
        Y_axis.setForeground(Color.WHITE);
        labelPanelx.setBackground(Color.BLACK); 
        X_axis.setForeground(Color.WHITE);
        g_frame.add(labelPanely, BorderLayout.SOUTH); 
        g_frame.add(labelPanelx,BorderLayout.WEST); 
        g_frame.pack(); 
        g_frame.setVisible(true);
    }
}//end of GraphUI class

//Button Action Code
class ButtonClickListener implements ActionListener{ 
    @Override
    public void actionPerformed(ActionEvent e) {
        String Button_Clicked = e.getActionCommand(); 
        UI.equation.append(Button_Clicked); 
        UI.mpanel.revalidate();  
    }
}//end of ButtonClickListener Class

//Clear Button Functionality
class ClearButtonClickListener implements ActionListener{ 
    @Override
    public void actionPerformed(ActionEvent e) {
        UI.equation.setText("");
    }
}//end of ClearButtonClickListener Class

//Graph An Equation Button Functionality + Graphing Screen Functionality 
class GraphAnEquationButtonClickListener implements ActionListener{ 
    @Override
    public void actionPerformed(ActionEvent e) {
        for (JButton b : UI.buttons){
            b.hide(); 
        }
        UI.equation.hide(); 
        UI.g_equation = new JTextArea("Delete This Text and Type Equation Here"); 
        UI.g_equation.setFont(new Font("San-Serif", UI.back.getFont().getStyle(), 24)); 
        UI.mpanel.add(UI.g_equation); 
        UI.g_equation.setBounds(130,110,320,68); 
        UI.g_equation.setLineWrap(true);
        UI.g_equation.setWrapStyleWord(true); 
        UI.back.setBounds(130,190,320,58);
        UI.back.addActionListener(new BackButtonClickListener()); 
        UI.back.setBackground(Color.RED);
        UI.back.setForeground(Color.BLACK);
        UI.back.setFont(new Font("San-Serif", UI.back.getFont().getStyle(), 20)); 
        UI.back.show(); 
        UI.graph.show(); 
        UI.graph.setFont(new Font("San-Serif", UI.graph.getFont().getStyle(), 20)); 
        UI.graph.setBackground(Color.CYAN);
        UI.graph.setForeground(Color.BLACK);
        UI.graph.setBounds(130,260,320,50);
        UI.graph.addActionListener(new GraphButtonClickListener()); 
        UI.buttons.add(UI.back); 
        UI.mpanel.add(UI.graph); 
        UI.mpanel.add(UI.back); 
        UI.buttons.add(UI.graph); 
        
    }
}//end of GraphAnEquationButtonClickListener Class 

//Back Button Functionality 
class BackButtonClickListener implements ActionListener{ 
    @Override
    public void actionPerformed(ActionEvent e) {
        for (JButton b : UI.buttons){
            b.show(); 
        }
        UI.equation.show(); 
        UI.back.hide(); 
        UI.graph.hide(); 
        UI.g_equation.hide();
    }
}//end of BackButtonClickListener class

// History Back Button Functionality 
class HistoryBackButtonClickListener implements ActionListener{ 
    @Override
    public void actionPerformed(ActionEvent e) {
        for (JButton b : UI.buttons){
            b.show(); 
        }
        UI.equation.show(); 
        UI.hback.hide(); 
        UI.disp.hide(); 
        UI.graph.hide(); 
        UI.back.hide(); 
    }
}//end of HistoryBackButtonListenerClass

//Del Button Functionality
class DelButtonClickListener implements ActionListener{ 
    @Override
    public void actionPerformed(ActionEvent e) {
        String problem = UI.equation.getText();  
        if (problem.length() > 0){
            String updated_problem = problem.substring(0, problem.length()-1); 
            UI.equation.setText(updated_problem);
           } 
        }
    }//end of DelButtonClickListener Class

//Equal Button Functionality
class EqualButtonClickListener implements ActionListener{ 
    @Override
    public void actionPerformed(ActionEvent e) {
        String problem = UI.equation.getText(); 
           try {
                    UI.solution = calculator.translate(calculator.convert_to_double(problem)); 
                    UI.computations.add(problem + " = " + UI.solution);  
                    UI.equation.setFont(new Font("San-Serif", UI.equation.getFont().getStyle(), 48));   
                    UI.equation.setText(UI.solution);
          } catch (NumberFormatException | StringIndexOutOfBoundsException p){
               UI.equation.setText("Make Sure You are NOT Combining Integers and Decimals, NOT Computing an Empty Problem, NOT Dividing by Zero, and Have clicked Clear after Every Computation");    
               UI.equation.setFont(new Font("San-Serif", UI.equation.getFont().getStyle(), 24)); 
              }
            }
        }//end of EqualButtonClickListener Class

//Graph Button Functionality   
class GraphButtonClickListener implements ActionListener{ 
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            String equation = UI.g_equation.getText(); 
            if(equation.contains("y=") ||equation.contains("Y=")) {
                String[][] G = graphing_calculator.graph_equation(equation.substring(2), 7, 5);
                GraphUI.createGrid(G, " @ "); 
                UI.computations.add(equation + "," + " Equation was Graphed"); 	
            } 
       } catch (NumberFormatException g){
            UI.g_equation.setText("Cannot Graph That Due to Space Between Y and Equal Sign (or Equal Sign and Coefficent or X), Divide by 0, Missing Multiplication Symbol Between X and Coefficient, or Exceeding Java Capabilities");
            UI.g_equation.setFont(new Font("San-Serif", UI.back.getFont().getStyle(), 12)); 
        }
    }
}//end of GraphButtonClickListener Class

//History Button Functionality 
class HistoryButtonClickListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        for (JButton b:UI.buttons){
            b.hide(); 
        }
        UI.hback.show(); 
        UI.equation.hide(); 
        UI.disp.show(); 
        for (String computation:UI.computations) {
            if (UI.model.indexOf(computation) == -1) {
                UI.model.addElement(computation);
            }
        }
        UI.disp.setBounds(35,100,500, 380); 
        UI.disp.setFont(new Font("San-Serif", UI.back.getFont().getStyle(), 14)); 
        UI.hback.setBounds(35,500,500,50);
        UI.hback.setBackground(Color.RED);
        UI.hback.setForeground(Color.BLACK);
        UI.hback.setFont(new Font("San-Serif", UI.back.getFont().getStyle(), 20)); 
        UI.hback.addActionListener(new HistoryBackButtonClickListener()); 
        UI.mpanel.add(UI.hback); 
        UI.mpanel.add(UI.disp); 
    }
}//end of HistoryButtonClickListener Class

//ans button functionality 
class AnsButtonClickListener implements ActionListener{ 
    @Override
    public void actionPerformed(ActionEvent e) {
        UI.equation.setText(UI.solution); 
    } 
}//end of AnsButtonClickListener Class

//factorial button functionality 
class factorialButtonClickListener implements ActionListener{ 
    @Override
    public void actionPerformed(ActionEvent e) {
        int index = 0; 
        try{
            for (int i = 0; i<UI.equation.getText().length()-1; i++){
                if (UI.equation.getText().substring(i, i+1) == "!"){
                    index = i-1; 
                }
            }
            String sol = String.valueOf(factorial(Integer.parseInt(UI.equation.getText().substring(index, index+1)))); 
            UI.computations.add(UI.equation.getText().substring(index, index+1) + "!"  + " = "  + sol); 
            UI.equation.setText(sol); 
        }catch (StringIndexOutOfBoundsException b){
            UI.equation.setText("Make sure there is a number before the factorial symbol"); 
        }
    } 
    public int factorial(int n){
        if (n < 1){
            return 1; 
        }
        return n * factorial(n-1); 
    }
}//end of FactorialButtonClickListener Class

//sqrt button functionality
class sqrtButtonClickListener implements ActionListener{ 
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            int index = 0; 
            for (int i = 0; i<UI.equation.getText().length()-1; i++){
                if (UI.equation.getText().substring(i, i+1) == "\u221A"){
                    index = i+1; 
                }
            }
            String sol = String.valueOf(sqrt(Double.parseDouble(UI.equation.getText().substring(index, index+2)))); 
            UI.equation.setText(sol); 
            UI.computations.add("\u221A" + String.valueOf(Double.parseDouble(sol) * Double.parseDouble(sol)) + " = "  + sol); 
        }catch (NumberFormatException | StringIndexOutOfBoundsException s){
            UI.equation.setText("Make sure there is a number and it is a decimal or in decimal format before the radical"); 
        }
    } 
    public double sqrt(double s){
        double sol = Math.sqrt(s); 
        return sol; 
    }  
} // end of sqrtButtonClickListener Class

