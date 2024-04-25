import java.util.Scanner;

public class Demo{

    public static void main (String args[] ){

        char repeat = 'y';
        Calculator calc = new Calculator();
        Scanner sc = new Scanner(System.in);


     do{
        System.out.println("Enter First Number");

        int first=sc.nextInt();
        System.out.println("Enter second Number");

        int second=sc.nextInt();

        System.out.println("Enter number beside the operator to perform action");
        System.out.println("1.Add \n2.Substarct \n3.Multiply\n4.Divide ");

        int operator=sc.nextInt();
        
      int result =calc.calculate(first, second, operator);

      System.out.println("Result :"+result);
      System.out.println("do you want to continue : 'y/n'");
       String re=sc.next();
       repeat = re.charAt(0);

    
    }while(repeat=='y');
    sc.close();
    }
}


 class Calculator{

    int calculate(int a,int b,int o){

     int result=0;

    switch (o) {
        case 1:
            result=a+b;
        
            break;
        case 2:
            result=a-b;
    
            break;
        case 3:
            result=a*b;
    
            break;
        case 4:
            if(b!=0){
                result=a/b;
                return result;
            }
            else{
                System.out.println("Division by zero not possible");
            }
            
            break;
    
        default:
        System.out.println("give proper input");
            break;
    }
    return result;

}
 }
