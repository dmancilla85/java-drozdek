package trees;

import org.drozdek.trees.ExpressionTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ExpressionTreeTest {
    ExpressionTree tree;

    @BeforeEach
    void setUp(){
        tree=new ExpressionTree();
    }

    @Test
    @DisplayName("Create and print an expression tree in-order")
    void createExpressionTree(){
        // postfix expression
        String expression = "AB*CD/+";
        // is A*B+C/D

        // calling our algorithm
        tree=new ExpressionTree(expression);

        System.out.print("Inorder Expression: ");
        // printing the expression tree inorder
        tree.inorder();
        System.out.println();

        assertFalse(tree.isEmpty(),"The tree shouldn't be empty");
    }

    @Test
    @DisplayName("Create and print the expression result")
    void evaluateExpression(){
        // postfix expression
        String expression ="(5+7)*4+23";
        // is A*B+C/D

        int result  = ExpressionTree.evaluateExpression(expression);
        System.out.println("Result is " + result);
        assertEquals(101, result);
    }

    @Test
    @DisplayName("Print the expression tree")
    void print(){
        // postfix expression
        String expression = "AB*CD/+";
        // is A*B+C/D

        // calling our algorithm
        tree=new ExpressionTree(expression);
        System.out.println(tree);

        assertFalse(tree.isEmpty(),"The tree shouldn't be empty");
    }
}
