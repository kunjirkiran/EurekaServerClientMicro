import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Employee{
	String name;
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", id=" + id + ", salary=" + salary + "]";
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	int id;
	int salary;
	public Employee( int id,String name, int salary) {
		super();
		this.name = name;
		this.id = id;
		this.salary = salary;
	}
	
}
public class Java8{
	
	 public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
     {
         Map<Object, Boolean> map = new ConcurrentHashMap<>();
         return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
     }
	 
	public static void main(String args[]){
		
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		
		//1.print even no filter & foreach
		list.stream().filter(n -> n%2 ==0).forEach(System.out :: print); 
		
		//2.Find all even numbers – Predicate class
		Predicate<Integer> condition = new Predicate<Integer>(){
			@Override
			public boolean test(Integer t) {
				 if(t%2==0) return true;
				return false;
			}
		};
		list.stream().filter(condition).forEach(System.out :: print); 
		
		//3.Filter even numbers and collect into new list
		System.out.println(list.stream().filter(n -> n%2==0).collect(Collectors.toList()));
		
		//4.Filter even numbers and get squares
		System.out.println(list.stream()
				.filter(n -> n%2==0)
				.map(n -> n*n)
				.collect(Collectors.toList()));
		
		//Map examples
		//5.Stream of strings to Stream of Integers
		List<String> stringList = Arrays.asList("1","2","3","4","5","6","7","8","9","10");
		System.out.println(stringList);
		System.out.println(stringList.stream()
				.map(Integer :: valueOf)
				.collect(Collectors.toList())
				);
		
		//6.Stream of employees to stream of distinct salaries
		List<Employee> employeesList = Arrays.asList(
                new Employee(1, "Alex", 100),
                new Employee(2, "Brian", 100),
                new Employee(3, "Charles", 200),
                new Employee(4, "David", 200),
                new Employee(5, "Edward", 300),
                new Employee(6, "Frank", 300),
                new Employee(5, "Edward", 300),
                new Employee(6, "Frank", 300)
            );
		System.out.println(employeesList.stream()
				.map(e -> e.getSalary())
				.distinct()
				.collect(Collectors.toList()));
		//flatMap
		//7.Convert list of lists to single list
		 List<Integer> list1 = Arrays.asList(1,2,3);
	     List<Integer> list2 = Arrays.asList(4,5,6);
	     List<Integer> list3 = Arrays.asList(7,8,9);
	     
	     List<List<Integer>> listOfLists = Arrays.asList(list1,list2,list3);
	     
	     System.out.println(listOfLists.stream()
	    		 .flatMap(l -> l.stream())
	    		 .collect(Collectors.toList())
	    		 );
	     //8.Convert array of arrays to single list
	     
	     String[][] dataArray = new String[][]{{"a","b"},{"c","d"},{"e","f"}};
	     System.out.println(Arrays.stream(dataArray)
	    		 .flatMap(l -> Arrays.stream(l))
	    		 .collect(Collectors.toList())
	    		 );
	     //Distinct
	     //9.Find all distinct elements
	     System.out.println(list.stream()
	    		 .distinct()
	    		 .collect(Collectors.toList())
	    		 );
	     //10.Find distinct objects by object key or attribute
	    
	     
	     System.out.println(employeesList.stream()
	    		 .filter( distinctByKey(e -> e.getId()))
	    		// .distinct()
	    		 .collect(Collectors.toList()));
	     
	     //sort
	     //11.Sort stream elements in natural order
	     List<Integer> unSortlist = Arrays.asList(2, 4, 1, 3, 7, 5, 9, 6, 8);
	     System.out.println(unSortlist.stream()
	    		 .sorted()
	    		 .collect(Collectors.toList())
	    		 );
	     //12.Sort stream elements using comparator in reverse order
	     System.out.println(unSortlist.stream()
	    		 .sorted(Comparator.reverseOrder())
	    		 .collect(Collectors.toList())
	    		 );
	     
		//13.Sort stream elements using custom comparator
	     Comparator<Integer> myReverseComparator = new Comparator<Integer>(){
				@Override
				public int compare(Integer o1, Integer o2) {
					return o2.compareTo(o1);
				}
		     };
	     System.out.println(unSortlist.stream()
	    		 .sorted(myReverseComparator)
	    		 .collect(Collectors.toList())
	    		 );
	     //14.Sort stream elements using lambda expression
	     System.out.println(unSortlist.stream()
	    		 .sorted((i1,i2) -> i2.compareTo(i1))
	    		 .collect(Collectors.toList())
	    		 );
	     //peek
	     //15.Without terminal operation no output
	     unSortlist.stream().peek(System.out :: println);
	     
	   //15.With terminal operation
	     System.out.println(list.stream()
                 .peek(System.out::println)
                 .collect(Collectors.toList()));
	     //limit
	     //16get first 10 even numbers from infinite stream of even numbers.
	     Stream<Integer> evenNumInfiniteStream = Stream.iterate(0, n -> n + 2);
	     System.out.println(evenNumInfiniteStream.limit(10)
	    		 .collect(Collectors.toList())
	    		 );
	     //skip
	     //17. skip first 5 even numbers from infinite stream of even numbers
	     //	   and then collect the next 10 even numbers
	     Stream<Integer> evenNumInfiniteStream1 = Stream.iterate(0, n -> n + 2);
	     System.out.println(evenNumInfiniteStream1.skip(5)
	    		 .limit(10).collect(Collectors.toList()));
	     
	     //forEach
	     //18.Traverse and print all elements
	     list.stream().forEach(System.out :: println );
	     
	     //19.Traverse and print all elements in reverse order
	     list.stream()
	     .sorted(Comparator.reverseOrder())
	     .forEach(System.out :: print );
	     
	     //forEachOrdered
	     //20. Diff for vs foreach
	     System.out.println();
	     list.stream().parallel().forEach(System.out :: print );
	     System.out.println();
	     list.stream().parallel().forEachOrdered(System.out :: print );
	     System.out.println();
	     //21. Traverse and print all elements in reverse order
	     list.stream().parallel().sorted(Comparator.reverseOrder()).forEachOrdered(System.out :: print );
	     
	     //ToArray
	     //22.Convert stream of strings to array
	     System.out.println();
	     System.out.println(Arrays.toString(list.stream().toArray(Integer[] :: new)));
	     
	     //23.Convert infinite stream to array
	     IntStream infiniteNumberStream = IntStream.iterate(1, i -> i+2);
	     System.out.println(Arrays.toString(infiniteNumberStream.limit(10)
         .toArray()));
	     
	     //24. Infinite stream to array to Integers – Boxed stream
	     IntStream infiniteNumberStream1 = IntStream.iterate(1, i -> i+2);
	     System.out.println(Arrays.toString(infiniteNumberStream1.limit(10)
         .boxed()
         .toArray(Integer[]::new)));
	     
	     //25.Filter stream and collect elements to array
	     System.out.println(Arrays.toString(employeesList.stream()
	    		 .filter(e -> e.getSalary() < 400)
	    		 .toArray(Employee[] :: new)
	    		 ));
	     //min
	     //26Smallest element in stream with lambda expression
	     System.out.println(unSortlist.stream()
	     .min((i, j) -> i.compareTo(j)));
	     
	     //27.Smallest element in stream with comparator
	     Comparator<Integer> minComparator = new Comparator<Integer>() {
	            @Override
	            public int compare(Integer n1, Integer n2) {
	                return n1.compareTo(n2);
	            }
	        };
	        System.out.println(unSortlist.stream()
	       	     .min(minComparator));
	        
	     //Max   
	     //29.Largest element in stream with lambda expression
	        System.out.println( list.stream()
            .max((i, j) -> i.compareTo(j)));
	    //30.largest element in stream with comparator
		     Comparator<Integer> maxComparator = new Comparator<Integer>() {
		            @Override
		            public int compare(Integer n1, Integer n2) {
		                return n1.compareTo(n2);
		            }
		        };
		        System.out.println(unSortlist.stream()
		       	     .max(maxComparator));
		   //count
		   //31.count no of elements in list
		        System.out.println(unSortlist.stream()
			       	     .count()
			       	     );
		   //32. use of collectors.counting method 
		        System.out.println(Long.toString(unSortlist.stream()
			       	     .collect(Collectors.counting()))
			       	     );
		  //anyMatch
		    //if any stream element match
		        System.out.println(stringList.stream()
		        		.anyMatch(S -> S.contains("1"))
		        		);
		        //allMatch
		        //34.if all match found
		        Stream<String> stream = Stream.of("1", "2", "3", "4");
		        System.out.println(stringList.stream()
		        		.allMatch(s -> s.contains("\\d+")==true));
		        
		        System.out.println(stream.noneMatch( s -> s.contains("\\d+") ));
		        
		        //findAny
		        //35.finad any use stream
		        Stream.of("one", "two", "three", "four")
                .findAny()
                .ifPresent(System.out::println);
		        
		        
		      //35.finad any use parallel stream
		        Stream.of("one", "two", "three", "four")
	            .parallel()
	            .findAny()
	            .ifPresent(System.out::println);
		        //find First
		        //sequencial 
		        Stream.of("one", "two", "three", "four")
                .findFirst()
                .ifPresent(System.out::println);
         
        //parallel stream
        Stream.of("one", "two", "three", "four")
            .parallel()
            .findFirst()
            .ifPresent(System.out::println);
        
        String str = "ABAABDBCCDDD";
    	
    	char[] arr = str.toCharArray(); 
    	Map<Character,Integer> out = new LinkedHashMap();
    	for(int i=0;i<arr.length ;i++){
    		if(out.containsKey(arr[i]))
    			out.put(arr[i],out.get(arr[i])+1);
    		else
    			out.put(arr[i],1);
    	}
    	
    	System.out.println(out);
    	
    	Map<IntStream,Long> output = Stream.of(str.chars()).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
    	
    	for(Entry<IntStream,Long> entry : output.entrySet() ){
    		System.out.println(entry.getKey() + "  " + entry.getValue());
    	}
    	
	    }
	
	
}

