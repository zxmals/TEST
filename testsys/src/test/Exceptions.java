package test;

public class Exceptions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		div(0);
		try {
			f();
		} catch (Sampleexception e) {
			// TODO Auto-generated catch block			
			System.out.println("caught it!");
		}
		
		try{
			g();
		}catch(Myexception e){
			e.printStackTrace(System.out);
		}
		
	}

	public static void div(int a){
		try{
			System.out.println(1/a);
		}catch(Exception e){
			System.out.println("Exception:"+e.getMessage());
			div(a+1);
		}finally{
			System.out.println("record\t"+a);
		}
	}
	
	public static void f() throws Sampleexception{
		System.out.println("Throw sample exception from f();");
		throw new Sampleexception();
	}
	
	public static void g() throws Myexception{
		System.out.println("Throwing Exception from g();");
		throw new Myexception("originated in g();");
	}
}

class Sampleexception extends Exception{
	
}

class Myexception extends Exception{

	public Myexception() {
	}

	public Myexception(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	
}