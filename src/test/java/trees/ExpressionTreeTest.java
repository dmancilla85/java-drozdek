package trees;

import org.drozdek.trees.ExpressionTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(71, result);
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

    @Test
    @DisplayName("New tree should be empty")
    void isEmpty() {
        assertTrue(tree.isEmpty());
    }

    @Test
    @DisplayName("Size of non-empty tree")
    void size() {
        tree = new ExpressionTree("AB+");
        assertFalse(tree.isEmpty());
    }

    @Test
    @DisplayName("Evaluate simple expression")
    void evaluateSimple() {
        assertEquals(5, ExpressionTree.evaluateExpression("2+3"));
    }

    @Test
    @DisplayName("Evaluate expression with parentheses")
    void evaluateWithParens() {
        assertEquals(35, ExpressionTree.evaluateExpression("(2+3)*7"));
    }

    @Test
    @DisplayName("Null return from evaluateExpression")
    void evaluateNull() {
        assertEquals(0, ExpressionTree.evaluateExpression(""));
        assertEquals(0, ExpressionTree.evaluateExpression(null));
    }

    @Test
    @DisplayName("Check operator")
    void isOperator() {
        assertTrue(ExpressionTree.isOperator('+'));
        assertTrue(ExpressionTree.isOperator('-'));
        assertTrue(ExpressionTree.isOperator('*'));
        assertTrue(ExpressionTree.isOperator('/'));
        assertTrue(ExpressionTree.isOperator('^'));
        assertFalse(ExpressionTree.isOperator('a'));
    }
}
