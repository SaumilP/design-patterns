package design.pattern.methodobject;

/**
 * Method Object Refactoring Client
 */
public class App {
    public static void main(String[] args){
        Account acc = new Account();
        System.out.print("Computed Value : " + acc.calculateValue(120,25, 1920 ) );

        RefactoredAccount refAcc = new RefactoredAccount(120, 25, 1920, 3);
        System.out.print("\nComputed Value : " + refAcc.compute() );
    }
}
