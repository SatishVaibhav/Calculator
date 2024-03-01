import java.lang.Math;
import java.text.DecimalFormat;
public class calculator 
{

    public static double roundToDecimalPlaces(double number, int decimalPlaces) {
        String pattern = "#.";
        for (int i = 0; i < decimalPlaces; i++) {
            pattern += "#";
        }

        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String formattedNumber = decimalFormat.format(number);
        return Double.parseDouble(formattedNumber);
    }

    static int compute(int num1, char opperator, int num2)
    {
        int answer = 0;
        if (opperator == '+')
        {
            answer = num1 + num2;
        }
        if (opperator == '-')
        {
            answer = num1 - num2;
        }
        if (opperator == '*')
        {
            answer = num1 * num2;
        }
        if (opperator == '/')
        {
            answer = num1 / num2;
        }
        if (opperator == '^')
        {
            answer = (int)Math.pow((int)num1, (int)num2);
        }
        
        return answer;
    }

    static double compute_double(double num1, char opperator, double num2)
    {
        double answer = 0;
        if (opperator == '+')
        {
            answer = num1 + num2;
        }
        if (opperator == '-')
        {
            answer = num1 - num2;
        }
        if (opperator == '*')
        {
            answer = num1 * num2;
        }
        if (opperator == '/')
        {
            answer = num1 / num2;
        }
        if (opperator == '^')
        {
            answer = Math.pow((double) num1, (double)num2);
        }
        
        return roundToDecimalPlaces(answer,15);
    }

    static int find_int_left_of_index(String equation, int index)
    {
        int start_ind = index;
        for(int i = index; i > 0; i--)
        {
            if (equation.charAt(i) != '*' && equation.charAt(i) != '/' && equation.charAt(i) != '+' && equation.charAt(i) != '-' 
                && equation.charAt(i) != '^' && equation.charAt(i) != '(' && equation.charAt(i) != ')')
            {
                start_ind -= 1;
            }
            else
            {
                start_ind += 1;
                break;
            } 
        }
        return Integer.valueOf(equation.substring(start_ind, index + 1));
    }
    
    static int find_int_right_of_index(String equation, int index)
    {
        int end_ind = index;
        for(int i = index; i < equation.length(); i++)
        {
            if (equation.charAt(i) != '*' && equation.charAt(i) != '/' && equation.charAt(i) != '+' 
                && equation.charAt(i) != '-' && equation.charAt(i) != '^' && equation.charAt(i) != '(' && equation.charAt(i) != ')'
                && equation.charAt(i) != 'x')
            {
                end_ind += 1;
            }
            else
            {
                
                break;
            } 
        }
        return Integer.valueOf(equation.substring(index, end_ind));
    }

    static double find_double_left_of_index(String equation, int index)
    {
        int start_ind = index;
        for(int i = index; i > 0; i--)
        {
            if (equation.charAt(i) != '*' && equation.charAt(i) != '/' && equation.charAt(i) != '+' && equation.charAt(i) != '-' 
                && equation.charAt(i) != '^' && equation.charAt(i) != '(' && equation.charAt(i) != ')' && equation.charAt(i) != 'x')
            {
                start_ind -= 1;
            }
            else
            {
                start_ind += 1;
                break;
            } 
        }
        return Double.valueOf(equation.substring(start_ind, index + 1));
    }
    
    static double find_double_right_of_index(String equation, int index)
    {
        int end_ind = index;
        for(int i = index; i < equation.length(); i++)
        {
            if(i == index && equation.charAt(i) == '-')
            {
                end_ind += 1;
                continue;
            }
            if (equation.charAt(i) != '*' && equation.charAt(i) != '/' && equation.charAt(i) != '+' && equation.charAt(i) != '-' && equation.charAt(i) != '^'
            && equation.charAt(i) != '(' && equation.charAt(i) != ')')
            {
                end_ind += 1;
            }
            else
            {
                
                break;
            } 
        }
        return Double.valueOf(equation.substring(index, end_ind));
    }
    
    static String convert_to_double(String equation)
    {
        // check if double
        int ind = 0;
        for (int i = 0; i < equation.length(); i++);
        {
            ind += 1;
            if (equation.charAt(ind) == '.')
            {
                return(equation);
            }
        }
        String converted = "";
        int index = 0;
        Boolean done = false;
        while(!done)
        {
            if(equation.charAt(index) != '*' && equation.charAt(index) != '/' && equation.charAt(index) != '+' 
                && equation.charAt(index) != '-' && equation.charAt(index) != '^' && equation.charAt(index) != '(' 
                && equation.charAt(index) != ')' && equation.charAt(index) != 'x')
            {
                double current_num = find_double_right_of_index(equation, index);
                index += String.valueOf((int)current_num).length();
                converted += current_num;
            }
            else
            {
                converted += equation.charAt(index);
                index += 1;
            }
            if (index >= equation.length())
            {
                done = true;
            }
        }

        return converted;
    }
    
    static String recursive_solve(String equation)
    {
        //Logic:
        //  given a string, check using PEMDAS to find first opperation, left to right
        //  starting with just */+- for simplicity

        // Handle exponant,multiplication,division
        for (int i = 1; i < equation.length(); i ++)
        {
            int num1;
            int num2;
            char opporator;
            int comp;
            String new_equation;
            if (equation.charAt(i) == '^')
            {
                num1 = find_int_left_of_index(equation, i-1);
                num2 = find_int_right_of_index(equation, i+1);
                opporator = equation.charAt(i);
                comp = compute(num1, opporator, num2);
                new_equation = equation.substring(0, i - String.valueOf(num1).length()) + String.valueOf(comp) + equation.substring(i+String.valueOf(num2).length()+1, equation.length());
                //System.out.println(new_equation);
                return recursive_solve(new_equation);
            }
            if (equation.charAt(i) == '*')
            {
                num1 = find_int_left_of_index(equation, i-1);
                num2 = find_int_right_of_index(equation, i+1);
                opporator = equation.charAt(i);
                comp = compute(num1, opporator, num2);
                new_equation = equation.substring(0, i - String.valueOf(num1).length()) + String.valueOf(comp) + equation.substring(i+String.valueOf(num2).length()+1, equation.length());
                //System.out.println(new_equation);
                return recursive_solve(new_equation);
            }
            if (equation.charAt(i) == '/')
            {
                num1 = find_int_left_of_index(equation, i-1);
                num2 = find_int_right_of_index(equation, i+1);
                opporator = equation.charAt(i);
                if (num1 % num2 != 0)
                {
                    System.out.println("ERROR: A division led to a non-whole number, which can't yet be handled.");
                    System.exit(1);
                }
                comp = compute(num1, opporator, num2);
                new_equation = equation.substring(0, i - String.valueOf(num1).length()) + String.valueOf(comp) + equation.substring(i+String.valueOf(num2).length()+1, equation.length());
                //System.out.println(new_equation);
                return recursive_solve(new_equation);
            }
        }
        
        // Handle Addition, subtraction
        for (int i = 1; i < equation.length(); i ++)
        {
            int num1;
            int num2;
            char opporator;
            int comp;
            String new_equation;
            if (equation.charAt(i) == '+')
            {
                num1 = find_int_left_of_index(equation, i-1);
                num2 = find_int_right_of_index(equation, i+1);
                opporator = equation.charAt(i);
                comp = compute(num1, opporator, num2);
                new_equation = equation.substring(0, i - String.valueOf(num1).length()) + String.valueOf(comp) + equation.substring(i+String.valueOf(num2).length()+1, equation.length());
                //System.out.println(new_equation);
                return recursive_solve(new_equation);
            }
            
            if (equation.charAt(i) == '-')
            {
                num1 = find_int_left_of_index(equation, i-1);
                num2 = find_int_right_of_index(equation, i+1);
                opporator = equation.charAt(i);
                comp = compute(num1, opporator, num2);
                new_equation = equation.substring(0, i - String.valueOf(num1).length()) + String.valueOf(comp) + equation.substring(i+String.valueOf(num2).length()+1, equation.length());
                //System.out.println(new_equation);
                return recursive_solve(new_equation);
            }
        }
        return (equation);
    }

    public static String recursive_solve_double(String equation)
    {
        //Logic:
        //  given a string, check using PEMDAS to find first opperation, left to right
        //  starting with just */+- for simplicity

        // Handle exponant,multiplication,division
        for (int i = 1; i < equation.length(); i ++)
        {
            double num1;
            double num2;
            char opporator;
            double comp;
            String new_equation;
            if (equation.charAt(i) == '^')
            {
                num1 = find_double_left_of_index(equation, i-1);
                num2 = find_double_right_of_index(equation, i+1);
                opporator = equation.charAt(i);
                comp = compute_double(num1, opporator, num2);
                new_equation = equation.substring(0, i - String.valueOf(num1).length()) + String.valueOf(comp) + equation.substring(i+String.valueOf(num2).length()+1, equation.length());
                //System.out.println(new_equation);
                return recursive_solve_double(new_equation);
            }
            if (equation.charAt(i) == '*')
            {
                num1 = find_double_left_of_index(equation, i-1);
                num2 = find_double_right_of_index(equation, i+1);
                opporator = equation.charAt(i);
                comp = compute_double(num1, opporator, num2);
                new_equation = equation.substring(0, i - String.valueOf(num1).length()) + String.valueOf(comp) + equation.substring(i+String.valueOf(num2).length()+1, equation.length());
                //System.out.println(new_equation);
                return recursive_solve_double(new_equation);
            }
            if (equation.charAt(i) == '/')
            {
                num1 = find_double_left_of_index(equation, i-1);
                num2 = find_double_right_of_index(equation, i+1);
                opporator = equation.charAt(i);
                comp = compute_double(num1, opporator, num2);
                new_equation = equation.substring(0, i - String.valueOf(num1).length()) + String.valueOf(comp) + equation.substring(i+String.valueOf(num2).length()+1, equation.length());
                //System.out.println(new_equation);
                return recursive_solve_double(new_equation);
            }
        }
        
        // Handle Addition, subtraction
        for (int i = 1; i < equation.length(); i ++)
        {
            double num1;
            double num2;
            char opporator;
            double comp;
            String new_equation;
            if (equation.charAt(i) == '+')
            {
                num1 = find_double_left_of_index(equation, i-1);
                num2 = find_double_right_of_index(equation, i+1);
                opporator = equation.charAt(i);
                comp = compute_double(num1, opporator, num2);
                new_equation = equation.substring(0, i - String.valueOf(num1).length()) + String.valueOf(comp) + equation.substring(i+String.valueOf(num2).length()+1, equation.length());
                //System.out.println(new_equation);
                return recursive_solve_double(new_equation);
            }
            
            if (equation.charAt(i) == '-')
            {
                num1 = find_double_left_of_index(equation, i-1);
                num2 = find_double_right_of_index(equation, i+1);
                opporator = equation.charAt(i);
                comp = compute_double(num1, opporator, num2);
                new_equation = equation.substring(0, i - String.valueOf(num1).length()) + String.valueOf(comp) + equation.substring(i+String.valueOf(num2).length()+1, equation.length());
                //System.out.println(new_equation);
                return recursive_solve_double(new_equation);
            }
        }
        return (equation);
    }

    static String translate(String equation)
    {
        String clean_equation = "";
        int index_to_skip_to = 0;
        //Finding nested pharentases recursivly
        for(int i = 0; i < equation.length(); i++)
        {
            if(i < index_to_skip_to)
            {
                continue;
            }

            if(equation.charAt(i) == '(')
            {
                int num_open = 1;
                int num_close = 0;
                String this_pharentases = "";
                index_to_skip_to = i+1;

                for(int j = i + 1; j < equation.length(); j++)
                {
                    index_to_skip_to += 1;

                    if(equation.charAt(j) == '(')
                    {
                        num_open += 1;
                    }
                    if(equation.charAt(j) == ')')
                    {
                        num_close += 1;
                    }
                    if(num_close == num_open)
                    {
                        if(i > 0 && equation.charAt(i-1) != '+' && equation.charAt(i-1) != '-' && equation.charAt(i-1) != '*' 
                        && equation.charAt(i-1) != '/' && equation.charAt(i-1) != '^' )
                        {
                            clean_equation += "*";
                        }
                        clean_equation += translate(this_pharentases);
                        break;
                    }
                    else
                    {
                        this_pharentases += equation.charAt(j);
                    }
                }
            }
            else
            {
                clean_equation += equation.charAt(i);
            }
        }
        return recursive_solve_double(clean_equation);
    }
    
    public static void main(String[] args) 
    {
        String equation = "y=x^3/10";
        
        if (equation.substring(0, 2).equals("y="))
        {
            int max_x_y_value = 10;
            int steps_per_1 = 2;

            System.out.println("\n\n\n\n\n");
            System.out.println(equation + "\n");
            graphing_calculator.graph_equation(equation.substring(2), max_x_y_value, steps_per_1);
        }
        else
        {
            System.out.println("\n\n\n\n\n");
            System.out.println(equation + "\n");
            System.out.println(translate(convert_to_double(equation)));
        }
        
    }
}
