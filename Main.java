import java.util.Scanner;
import java.lang.Math;
import java.util.Arrays;
import java.io.*;

class Main
{
  public static void main(String[] args)
  {
    
  }

  public static void stats(String filename) throws IOException
  {
    Scanner fileReader = new Scanner(new File(filename));
    int[] buffer = new int[5000]; // A buffer is a place in memory you read data into
    int count = 0;

    while(fileReader.hasNext())
    {
      buffer[count] = fileReader.nextInt();
      count++;
    }

    int[] numbers = Arrays.copyOf(buffer, count);
    buffer = null;
    Arrays.sort(numbers);

    int total = 0;
    for(int i = 0; i < numbers.length; i++)
    {
      total += numbers[i];
    }
    double mean = total / (numbers.length - 1);

    double sd = 0;
    for(int i = 0; i < numbers.length; i++)
    {
      sd += ((numbers[i] - mean)*(numbers[i] - mean)) / (numbers.length - 1);
    }
    double standardDeviation = Math.sqrt(sd);

    int medianLocation = (numbers.length / 2) + 1;
    double median = numbers[medianLocation];
    if(numbers.length % 2 == 0)
    {
      median = (numbers[(int)medianLocation - 1] + numbers[(int)medianLocation]) / 2;
    }
    
    PrintWriter writer = new PrintWriter(new File("output.txt"));
    writer.println("Mean: " + mean + "\n" + "Median: " + median + "\n" + "Standard Deviation: " + standardDeviation);
  }



  // Assume no dull in between non-null data
  // Won't have this -> [Hello, Bob, null, Happy]
  public static String[] insert(String s, int i, String[] ar, int logicalSize)
  {
    String [] a = Arrays.copyOf(ar, ar.length);
    if(a.length == logicalSize)
    {
      return null;
    }

    // Before I can insert, I have to move everyone down
    for(int n = logicalSize-1; n >= i; n--)
    {
      a[n+1] = a[n];
    }
    a[i] = s;

    return a;
  }

  public static int[] delete(int target, int[] ar, int logicalSize)
  {
    int[] a = Arrays.copyOf(ar, ar.length);
    boolean targetFound = false;
    int targetIndex = 0;
    for(int n = 0; n < ar.length; n++)
    {
      if(ar[n] == target)
      {
        targetFound = true;
        targetIndex = n;
        break;
      }
    }

    if(targetFound == false)
    {
      return ar;
    }

    // This loop should take care of deleting the object, as it just replaces the target with the object at the next index
    for(int n = targetIndex; n < logicalSize; n++)
    {
      a[n] = a[n+1];
    }
    
    return a;
  }
}