
import java.math.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.LinkedList;




public class OneClass {
	static boolean Decimal=false;
	static boolean DivideInteger=true;
	static HashMap<String,String> hp1=new HashMap<>();
	public static String alternative(String expression,String oldstr,String newstr) {
		int len=expression.length();
		int oldindex=expression.indexOf(oldstr),oldlen=oldstr.length();
		while(oldindex!=-1) {
		if(oldindex+oldlen<len) {
			char adjentafter=expression.charAt(oldindex+oldlen);
			if(oldindex==0) {
				if(adjentafter=='('||adjentafter==')'||adjentafter=='+'||adjentafter=='-'||
						adjentafter=='*'||adjentafter=='/'||adjentafter=='%')
				{
					String sub1=expression.substring(oldindex, oldlen);
					String sub2=expression.substring(oldindex+oldlen);
					//System.out.println(sub1);
					//System.out.println(sub2);
					sub1=newstr;
					
					//expression=expression.replaceAll(oldstr, newstr);
					expression=sub1+sub2;
				}
			}
			else {
				char adjentbefore=expression.charAt(oldindex-1);
				if(adjentbefore=='('||adjentbefore==')'||adjentbefore=='+'||adjentbefore=='-'||
						adjentbefore=='*'||adjentbefore=='/'||adjentbefore=='%')
				{
					String sub1=expression.substring(0,oldindex);
					String sub2=expression.substring(oldindex,oldindex+oldlen);
					String sub3=expression.substring(oldindex+oldlen);
					sub2=newstr;
//					System.out.println(sub1+" "+sub2+" "+sub3);
					//expression=expression.replaceAll(oldstr, newstr);
					expression=sub1+sub2+sub3;
				}
			}
		}
		else {
			char adjentbefore=expression.charAt(oldindex-1);
			if(adjentbefore=='('||adjentbefore==')'||adjentbefore=='+'||adjentbefore=='-'||
					adjentbefore=='*'||adjentbefore=='/'||adjentbefore=='%')
			{
				String sub1=expression.substring(0,oldindex);
				String sub2=expression.substring(oldindex);
				sub2=newstr;
				expression=sub1+sub2;
				//expression=expression.replaceAll(oldstr, newstr);
			}
		}
		len=expression.length();
		oldindex=expression.indexOf(oldstr,oldindex+1);
		}
		return expression;
	}
	
	
	
	
	public static void Findvariable(String expression,HashSet<String> ls) {
		ls.clear();
		String regx="\\w+(\\.\\w)*";
		//String regx="\\w+";
		Matcher m=Pattern.compile(regx).matcher(expression);
		
		
		while(m.find()) {
			String temp=m.group();
			//System.out.println("atsrt");
			//System.out.println(temp);
			if(!temp.matches("\\d+(\\.\\d+)*"))
			{
				//System.out.println(temp);
				ls.add(temp);
			}
			else
			{
				if(temp.contains("."))
					Decimal=true;
			}
		}
		
	}
	public static boolean isType(String type)
	{
		switch(type) {
		case "byte":return true;
		case "short":return true;
		case "char":return true;
		case "int":return true;
		case "long":return true;
		case "float":return true;
		case "double":return true;
		default: return false;
		}
	}

	public static void diF(String expression) {
		if(expression.contains("/"))
		{
			char[] chararr=expression.toCharArray();
			int di_index=0;
			while(di_index+1<=expression.length())
			{
			
			 di_index=expression.indexOf("/",di_index+1);
			 if(di_index==-1)
				 break;
			String bef="",aft="";
			
			
			for(int i=1;;i++)
			{
				if(di_index-i<0)
					break;
				if(chararr[di_index-i]!='+'&&chararr[di_index-i]!='-'&&chararr[di_index-i]!='*'&&
						chararr[di_index-i]!='/'&&chararr[di_index-i]!='('&&chararr[di_index-i]!=')'&&
						chararr[di_index-i]!='%')
				bef+=chararr[di_index-i];
				else
				{
				break;
				}
			}
			bef=new StringBuilder(bef).reverse().toString();
			//System.out.println(bef);
			if(bef.contains("."))
			{
				OneClass.DivideInteger=false;
				
				break;
			}
			if(OneClass.hp1.containsKey(bef))
				if(OneClass.hp1.get(bef).equals("float")||OneClass.hp1.get(bef).equals("double")){
					OneClass.DivideInteger=false;
					
					break;
				}
				
		
		
		
			if(!OneClass.DivideInteger)
				break;
			for(int i=1;;i++)
			{
				if(di_index+i>expression.length()-1)
					break;
				if((chararr[di_index+i]!='+'&&chararr[di_index+i]!='-'&&chararr[di_index+i]!='*'&&
						chararr[di_index+i]!='/'&&chararr[di_index+i]!='('&&chararr[di_index+i]!=')'&&
						chararr[di_index+i]!='%'&&chararr[di_index+i]!='='))
				aft+=chararr[di_index+i];
				else
				{
				
				break;
				}
			}
			//System.out.println(aft);
			if(aft.contains("."))
			{
				OneClass.DivideInteger=false;
				break;
			}
			if(OneClass.hp1.containsKey(aft))
				if(OneClass.hp1.get(aft).equals("float")||OneClass.hp1.get(aft).equals("double")){
					OneClass.DivideInteger=false;
					break;
				}
			//}
			//System.out.println(bef+"\n"+aft);
			}
		}
		return;
	}

    public static boolean match(String inputStr) {
        int len = inputStr.length();
        LinkedList<Character> stack = new LinkedList<Character>();
        
        for (int i = 0; i < len; i++) {
            
            if (isLeftBracket(inputStr.charAt(i))) {
                stack.push(inputStr.charAt(i));
                
            } else if (isRightBracket(inputStr.charAt(i))) {
                
                if (stack.isEmpty()) {
                    return false;
                    
                } else if (stack.peek().equals(inputStr.charAt(i))) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

  
    public static boolean isLeftBracket(char ch) {
        if (ch == '(' || ch == '[' || ch == '{') {
            return true;
        } else {
            return false;
        }
    }

   
    public static boolean isRightBracket(char ch) {
        if (ch == ')' || ch == ']' || ch == '}') {
            return true;
        } else {
            return false;
        }
    }
	
	
	
    
    
	private static final Pattern EXPRESSION_PATTERN=Pattern.compile("[0-9\\.+-/*%()= ]+");
	static final double flag=1e-7;
	
	private static final Map<String,Integer> OPT_PRIORITY_MAP=new HashMap<String,Integer>(){
		{
			put("(",0);
			put("+",1);
			put("-",1);
			put("*",2);
			put("/",2);
			put("%",2);
			put(")",3);
			put("=",4);
		}
	};
	public static void executeExpression(String expression) {
		if(expression==null||"".equals(expression.trim())) {
			throw new IllegalArgumentException("expression can't be null");
			//throw new Exception("jhyas");
		}
		
	
		Matcher matcher=EXPRESSION_PATTERN.matcher(expression);
		if(!matcher.matches()) {
			//throw new IllegalArgumentException("");
			System.out.println("wrong - variable undefined");
			System.exit(0);
			return;
			
		}
		
		Stack<String> optStack=new Stack<>();
		Stack<BigDecimal> numStack=new Stack<>();
		StringBuilder curNumBuilder=new StringBuilder(16);
		
		for(int i=0;i<expression.length();i++) {
			char c=expression.charAt(i);
			if(c!=' ') {
				if((c>='0'&&c<='9')||c=='.') {
					curNumBuilder.append(c);
				}else {
					if(curNumBuilder.length()>0) {
						numStack.push(new BigDecimal(curNumBuilder.toString()));
						curNumBuilder.delete(0, curNumBuilder.length());
					}
					
					String curOpt=String.valueOf(c);
					if(optStack.empty()) {
						optStack.push(curOpt);
					}else {
						if(curOpt.equals("(")) {
							optStack.push(curOpt);
						}else if(curOpt.equals(")")) {
							directCalc(optStack,numStack,true);
						}else if(curOpt.equals("=")) {
							directCalc(optStack,numStack,false);
							//return numStack.pop().doubleValue();
							double number=numStack.pop().doubleValue();
							if(!OneClass.Decimal)
							{
								System.out.println((int)number);
								System.exit(0);
								return;
							}
							else {
								System.out.println(String.format("%.2f", number));
								System.exit(0);
								return;
							}
							
						}else {
							compareAndCalc(optStack,numStack,curOpt);
						}
					}
				}
			}
		}
		
		if(curNumBuilder.length()>0) {
			numStack.push(new BigDecimal(curNumBuilder.toString()));
		}
		directCalc(optStack,numStack,false);
		double number=numStack.pop().doubleValue();
		if(!OneClass.Decimal)
		{
			System.out.println((int)number);
			System.exit(0);
			return;
		}
		else {
			System.out.println(String.format("%.2f", number));
			System.exit(0);
			return;
		}
	}
	
	private static void directCalc(Stack<String> optStack, Stack<BigDecimal> numStack, boolean isBracket) {
		// TODO Auto-generated method stub
		String opt=optStack.pop();
		try {
		BigDecimal num2=numStack.pop();
		BigDecimal num1=numStack.pop();
		BigDecimal bigDecimal=floatingPointCalc(opt,num1,num2);
		
		numStack.push(bigDecimal);
		
		if(isBracket) {
			if("(".equals(optStack.peek())) {
				optStack.pop();
			}else {
				directCalc(optStack,numStack,isBracket);
			}
		}else {
			if(!optStack.empty()) {
				directCalc(optStack,numStack,isBracket);
			}
		}
		}catch(EmptyStackException e) {
			System.out.println("wrong - error expression");
		}
	}

	private static void compareAndCalc(Stack<String> optStack, Stack<BigDecimal> numStack, String curOpt) {
		// TODO Auto-generated method stub
		String peekOpt=optStack.peek();
		int priority=getPriority(peekOpt,curOpt);
		if(priority==-1||priority==0) {
			String opt=optStack.pop();
			BigDecimal num2=numStack.pop();
			BigDecimal num1=numStack.pop();
			BigDecimal bigDecimal=floatingPointCalc(opt,num1,num2);
			
			numStack.push(bigDecimal);
			
			if(optStack.empty()) {
				optStack.push(curOpt);
			}else {
				compareAndCalc(optStack,numStack,curOpt);
			}
		}else {
			optStack.push(curOpt);
		}
	}
	
	private static int getPriority(String opt1, String opt2) {
		// TODO Auto-generated method stub
		return OPT_PRIORITY_MAP.get(opt2)-OPT_PRIORITY_MAP.get(opt1);
	}
	
	

	public static BigDecimal floatingPointCalc(String opt, BigDecimal bigDecimal1,BigDecimal bigDecimal2) {
			BigDecimal resultBigDecimal = new BigDecimal(0);
			switch(opt) {
			case "+":
				resultBigDecimal=bigDecimal1.add(bigDecimal2);
				break;
			case "-":
				resultBigDecimal=bigDecimal1.subtract(bigDecimal2);
				break;
			case "*":
				resultBigDecimal=bigDecimal1.multiply(bigDecimal2);
				break;
			case "/":
				if(OneClass.DivideInteger)
				{
					//System.out.println(SkillfulExpression.DivideInteger);
				resultBigDecimal=BigDecimal.valueOf(bigDecimal1.divide(bigDecimal2,3).intValue());
				}
				else
				{
					//System.out.println(SkillfulExpression.DivideInteger);
				resultBigDecimal=BigDecimal.valueOf(bigDecimal1.doubleValue()/bigDecimal2.doubleValue()).setScale(4, BigDecimal.ROUND_HALF_DOWN);
				//System.out.println(resultBigDecimal);
				}
				break;
				
			case "%":
				resultBigDecimal=bigDecimal1.divideAndRemainder(bigDecimal2)[1];
				break;
			default:
				break;
			}
			return resultBigDecimal;
		}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		HashMap<String,String> hp2=new HashMap<>();
		boolean stop=false;
		while(!stop)
		{
		Scanner sc=new Scanner(System.in);
		String s=sc.nextLine();
		if(s.charAt(s.length()-1)!='?')
		{
			ArrayList<String> extract =new ArrayList<>();
			//String regx1="\\w+(=\\d+)*";
			String regx1="\\w+(=\\d+(.\\d+)*)*";
			Matcher m=Pattern.compile(regx1).matcher(s);
			while(m.find()) {
				extract.add(m.group());
			}
//			int typecount=0;
//			for(int i=0;i<extract.size();i++)
//			{String tp=extract.get(i);
//				if(tp.equals("byte")||tp.equals("short")||tp.equals("char")||tp.equals("int")
//						||tp.equals("long")||tp.equals("float")||tp.equals("double")) {
//					typecount++;
//				}
//			}
			
			
			String type=extract.get(0);
			//Class c;
	
			if(isType(type))
			{
				if(type.equals("float")||type.equals("double"))
					Decimal=true;
				for(int i=1;i<extract.size();i++) {
					//System.out.println(extract.get(i));
					String tmp=extract.get(i);
					//System.out.println(tmp);
					if(isType(tmp))
					{
						if(tmp.equals("float")||tmp.equals("double"))
							Decimal=true;
						continue;
					}
					int index=tmp.indexOf('=');
					if(index!=-1)
					{
					String variable=tmp.substring(0,index);
					hp1.put(variable, type);
					String value=tmp.substring(index+1);
						
								//System.out.println(value);
							
					hp2.put(variable,value);
					}
					else
					{
						hp1.put(tmp, type);
						//hp1.put(tmp, c);
						hp2.put(tmp,null);
					}
				}
			}
			else {
				for(int i=0;i<extract.size();i++) {
					//System.out.println(extract.get(i));
					String tmp=extract.get(i);
					if(isType(tmp))
					{
						if(tmp.equals("float")||tmp.equals("double"))
							Decimal=true;
						continue;
					}
					int index=tmp.indexOf('=');
					if(index!=-1)
					{
					String variable=tmp.substring(0,index);
					//hp1.put(variable, c);
					String value=tmp.substring(index+1);
					
					hp2.put(variable,value);
					}
					else
					{
						//hp1.put(tmp, c);
						hp2.put(tmp,null);
					}
				}
			}
		}
		
		
		else
		{
			
			stop=true;
			String expression=s.substring(0,s.length()-1);
			//System.out.println(expression);
			//String s1=sc.nextLine();
			//String s2=sc.nextLine();
			
			//System.out.println(expression);
			
			diF(expression);
			HashSet<String> ls=new HashSet<>();
			Findvariable(expression,ls);
			//System.out.println("start");
			if(!match(expression))
			{
				System.out.println("wrong - error expression");
				System.exit(0);
				return;
			}
			for(String temp:ls)
			{
				//System.out.println(temp);
				if(!hp2.containsKey(temp))
				{
					System.out.println("wrong - variable undefined");
				System.exit(0);
				}
				String goal=hp2.get(temp);
				//System.out.println(goal);
				if(goal==null)
				{
					System.out.println("wrong - variable unassigned");
					System.exit(0);
					return;
				}
			expression=alternative(expression,temp,goal);
			}
			//System.out.println("start");
			//System.out.println(expression);
			
			
			//System.out.println(Decimal);
//			if(expression.contains("."))
//				 DivideInteger=false;
			//System.out.println(SkillfulExpression.DivideInteger);
			executeExpression(expression);
		
		}
		
		}
		
	}

}














 

