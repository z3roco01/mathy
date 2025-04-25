package z3roco01.mathy.parser;

/**
 * parses a provided equation string
 */
public class MathyParser {
    public String equation = "";

    public MathyParser(String equation){
        this.equation = equation;
    }

    // if they dont wanna pass an equation right away thats fine
    public MathyParser(){}

    /**
     * parses the held equation string, returns the result, will return 0 on error
     * @return the result of the equation, or 0 if error
     *
     */
    public double parse(){
        // obv cant do anything on a blank string
        if(equation.isBlank())
            return 0;

        // hold the current char, defined outside as optimization
        char curChar = ' ';
        // loop over every character
        for(int idx=0; idx<equation.length(); ++idx){
            curChar = equation.charAt(idx);

            // obv dont do anything with whitespace
            if(Character.isWhitespace(curChar))
                continue;

            System.out.print(curChar);
        }
        return 0;
    }
}
