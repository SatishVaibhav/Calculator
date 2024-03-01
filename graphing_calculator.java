import java.lang.Math;

public class graphing_calculator 
{
    public static String[][] graph_maker(double[] y_vals, int max, int steps)
    {
        String[][] graph = new String[((2*max)*steps) + 1][((2*max)*steps) + 1];
        
        for(int y = 0; y < ((2*max)*steps)+1; y++)
        {
            for(int x = 0; x < ((2*max)*steps)+1; x++)
            {
                
                if (y != max*steps && x != max*steps)
                {
                    graph[y][x] = "   ";
                }

                if (x % steps == 0)
                {
                    graph[y][x] = " 1 ";
                }

                if (y % steps == 0)
                {
                    graph[y][x] = " 1 ";
                }

                if (y == max*steps)
                {   
                    graph[y][x] = " - ";
                }
                
                
                if (y != max*steps && x == max*steps)
                {
                    graph[y][x] = " | ";
                }

                
                //margain of error can be controled here
                /* 
                if (Math.abs(y_vals[x] - ((max*steps) - y)/steps) <= 0.1/steps)
                {
                    graph[y][x] = " @ ";
                }
                */

                if(x>0 && y>0 && x<2*max*steps && y<2*max*steps)
                {
                    if (Math.abs(y_vals[x] - (double)((max*steps) - y)/steps) < Math.abs(y_vals[x-1] - y_vals[x+1])/2)
                    {
                        graph[y][x] = " @ ";
                    }
                    else if (Math.abs((y_vals[x] - (double)((max*steps) - y)/steps)) < 1.0/steps)
                    {
                        graph[y][x] = " @ ";
                    }
                    
                }
                else
                {
                    if (Math.abs((y_vals[x] - (double)((max*steps) - y)/steps)) < 1.0/steps)
                    {
                        graph[y][x] = " @ ";
                    }
                }
                
            }
        }
        //print_graph(graph, max, steps);
        return graph;
    }

    public static String[][] graph_equation(String equation, int max, int steps)
    {
        double x_val = -max;
        double[] y_vals = new double[((2*max)*steps) + 1];
        String new_equation = calculator.convert_to_double(equation);

        for(int i = 0; i < ((2*max)*steps) + 1; i++)
        {
            String temp_equation = "";
            for(int j = 0; j < new_equation.length(); j++)
            {
                if(new_equation.charAt(j) == 'x')
                {
                    
                    temp_equation += "(";
                    temp_equation += (x_val);
                    temp_equation += ")";
                    
                }
                else
                {
                    temp_equation += new_equation.charAt(j);
                }
            }
            y_vals[i] = (Double.valueOf(calculator.translate(temp_equation)));
            x_val += 1.0/steps;
            x_val = calculator.roundToDecimalPlaces(x_val, 3);
        }
        return graph_maker(y_vals, max, steps);
    }
}
