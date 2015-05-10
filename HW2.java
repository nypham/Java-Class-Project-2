/*Ny Pham
 *This class contains multiple methods that can be used for
 * string manipulations.*/

public class HW2
{
  /* this method takes a string and returns every k^n letter, starting with
   * n = 0 and incrementing by one until there are no more letters in the string
   * that satisfy the k^n condition. If k is 1, 0, or a negative integer then
   * the method returns the original string.
   * @param string is the input string that will be manipulated
   * @param k is the integer base*/
  public static String everykToTheNthChar(String string, int k)
  {
    if (k < 2)
      return string;
    else
    {
      //stores the length of the original string
      int lengthOfString = string.length();
      //will store the appended characters from the string
      StringBuilder newString  = new StringBuilder();
      newString.append(string.charAt(0));
      int index = k;
      /*this loop appends the character to the return string and then increments by k^n until 
       *there aren't any character indices that satisfy k^n*/
      while(index <= string.length())
      {
        char letter = string.charAt(index - 1);
        newString.append(letter);
        //this increments the index 
        index = index * k;
      }
      return newString.toString();
    }
  }
  
  /*this method checks to see if the first string input contains all of the
   * letters of the second string input, in order. It is assumed that the first
   *string is longer than the second.*/
  public static boolean containsSubSequence(String string1, String string2)
  {
    //stores the length of the first string
    int lengthOfString1 = string1.length();
    //stores the length of the second string
    int lengthOfString2 = string2.length();
    //stores whether the string2 is in string1
    boolean string2InString1 = true;
    //index that corresponds to the first string
    int index1 = 0;
    //index that corresponds to the second string
    int index2 = 0;
    /*this loop works by comparing the first character of string2 against the
     *characters of string1. If it isn't found in the first string then the method
     * returns false. If it is found, it moves onto the second character of the 
     * second string, picking up the comparison of string where the first character 
     * was found.*/
    while(index2 <= (lengthOfString2 - 1) && string2InString1 == true)
    {
        if (string2.charAt(index2) != string1.charAt(index1))
        {
          if (index1 == lengthOfString1 - 1)
            string2InString1 = false;
          else
            index1++;
        }
        else
        {
          index2++;
          index1++;
        }
      }
    return string2InString1;
  }
  
  /* this method removes the first appearance of the characters of 
   * the second string from the first string, but in order. So, if 
   * the second letter of the second string appears before the first
   * appearance of the first letter of the second string, then it is 
   * not removed. */
  public static String subtract(String string1, String string2)
  {
    int lengthOfString1 = string1.length();
    int lengthOfString2 = string2.length();
    StringBuilder newString = new StringBuilder();
    //index that corresponds to string1
    int index1 = 0;
    //index that corresponds to string2
    int index2 = 0;
    /*this loop compares the characters of the second string against 
     * those of the first string. If they do not match, the character at 
     * that index of the first string is appended to the resulting string.
     * If they do match, then the loop increments both string indices by 
     * one to begin checking for the next character of the second string.*/
    while(index2 <= (lengthOfString2 - 1))
    {
      if (string2.charAt(index2) != string1.charAt(index1))
      {
        if(index1 == lengthOfString1 - 1)
          index2 = lengthOfString2;
        else
        {
        newString.append(string1.charAt(index1));
        index1++;
        }
      }
      else
      {
        index2++;
        index1++;
      }
    }
    /*this loop appends the remaining letters that weren't removed from the
     * previous loop*/
    while(index1 <= (lengthOfString1 - 1))
    {
      newString.append(string1.charAt(index1));
      index1++;
    }
    return newString.toString();
  }
  
  /*this method takes a string and converts it to a double
   * if the string contains letters, misplaced negative signs,
   * or multiple decimal points then the method returns an error.*/
  public static double doubleValue(String numbers)
  {
    //this will store the return double value
    double resultingNumber = 0;
    //this will keep track of how many places are in the string
    int numberCounter = 0;
    //this will keep track of how many decimals there are
    int decimalCounter = 0;
    //this will keep track of how many values are before the decimal
    int valuesBeforeDecimal = 0;
    /*this loop evaluates the character at the index. If it is a digit then it
     * is converted to an int and then type casted to double. Then the value is
     * divided by 10 as many times as there are numbers in the string and added to 
     * what will be the return double. This insures that the numbers are kept in order.
     * If the character is a decimal, the decimal counter is increased; if there are 
     * more than one decimal points then an error is returned. If the character is a
     * letter then an error is returned.*/
    for (int index = 0; index < numbers.length(); index++)
    {
      if(Character.isDigit(numbers.charAt(index)))
      {
        numberCounter++;
        double number = Character.digit(numbers.charAt(index), 10);
        double numberValue = number;
        if(decimalCounter == 0)
          valuesBeforeDecimal++;
        for(int numberOfTimesToDivide = 1; numberOfTimesToDivide <= numberCounter; numberOfTimesToDivide++)
        {
          numberValue = numberValue / 10;
        }
        resultingNumber = resultingNumber + numberValue;
      }
      else if(numbers.charAt(index) == '.')
      {
        decimalCounter++;
        if(decimalCounter > 1)
          throw new NumberFormatException("The value entered, " + numbers + ", contains more than one decimal point.");
      }
      else if(Character.isLetter(numbers.charAt(index)))
        throw new NumberFormatException("The value entered, " + numbers+ ", contains a letter.");
    }
    /*this loop checks for negative signs. if the negative sign is in the proper 
     * place and there aren't multiple negative signs then the resulting double
     * is multipled by -1. Otherwise, an error is returned.*/
    for(int index = 0; index < numbers.length(); index++)
    {
      if(numbers.charAt(index) == '-')
      {
        if(index != 0)
          throw new NumberFormatException("The value entered, " + numbers + ",contains a misplaced negative sign.");
        else
          resultingNumber = resultingNumber * -1;
      }
    }
    /*this loop adjusts the resulting double by multiplying it by 10 as many times 
     * as there are values before the decimal in a valid string*/
    for(int numberOfTimesToMultiply = 1; numberOfTimesToMultiply <= valuesBeforeDecimal; numberOfTimesToMultiply++)
    {
      resultingNumber = resultingNumber * 10;
    }
    return resultingNumber;
  }
  
  /*this method returns the nth character of every word in 
   * the string. If the word is shorter than the nth letter 
   * then the method puts a space as a place holder*/
  public static String everyNthCharOfWord(String string, int n)
  {
    StringBuilder newString = new StringBuilder();
    //this stores which letter we are on in a word of the string
    int wordPlace = 0;
    /*this loop checks the character at the index. If the character is
     * a space and the nth value is larger than the word, then a space
     * is appended in the resulting string. If it is a letter and the letter
     * we are in the word matches the n value then the letter is appended.*/
    for (int index = 0; index < string.length(); index++)
    {
      if(Character.isWhitespace(string.charAt(index)))
      {
        if(n > wordPlace && wordPlace != 0)
          newString.append(' ');
        wordPlace = 0;
      }
      else if(Character.isLetter(string.charAt(index)))
      {
        if(wordPlace == n - 1)
        {
          newString.append(string.charAt(index));
        }
        wordPlace++;
      }
    }
    /*this conditional statement appends the last word of the string where the n value is greater
     * than the word.*/
    if(n > wordPlace && wordPlace != 0)
          newString.append(' ');
    return newString.toString();
  }
  
  /*this method takes each word of the string and flips it in 
   * place so that the non-letter characters remain at the same
   * index.*/
  public static String flipWords(String string)
  {
    StringBuilder wordsFlipped = new StringBuilder();
    int index = 0;
    //this stores the index of the first letter in a word
    int wordPlace = 0;
    /*this loop checks if a character in the string is a letter. If it
     * isn't then the word before it is flipped, the non-letter is appended
     * after, and a new word begins.*/
    while(index < string.length())
    {
      if(!(Character.isLetter(string.charAt(index))))
      {
        for(int i = index - 1; i >= wordPlace; i--)
        {
          wordsFlipped.append(string.charAt(i));
        }
        wordsFlipped.append(string.charAt(index));
        wordPlace = index + 1;
      }
      index++;
    }
    return wordsFlipped.toString();
  }
}