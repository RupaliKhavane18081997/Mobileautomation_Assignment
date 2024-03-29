package Base;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.KeyEventMetaModifier;

public class UtilsKeys extends BaseTest{
	
	private static AndroidKey getKeyForNumber(int valueToEnter)
		{
			AndroidKey keyToEnter = null;
			AndroidKey [] codes = AndroidKey.values();
			for(AndroidKey key : codes)
			{
				if(key.getCode() == valueToEnter)
				{
					keyToEnter = key;
				}
			}
			return keyToEnter;
		}
	
		public static void press(String str) throws Exception
		{
			String specialCharacterSet = "!@#$%^&*";
			String [] navigateOptionSet = {"HOME_Button", "BACK_Button", "CLEAR", "TAB", "ENTER", "BACKSPACE"};
			String commaClearSet = ",_. ";
			int valueToEnter = 0;
			boolean flag = false;
	
			for(int i = 0; i < navigateOptionSet.length; i++)
			{
				if(str.equals(navigateOptionSet[i]))
				{
					flag = true;
					if(i <= 1)
					{
						valueToEnter = 3 + i;
					}
					else if(i == 2)
					{
						valueToEnter = 26 + i;
					}
					else if(i == 3)
					{
						valueToEnter = 58 + i;
					}
					else if(i > 3)
					{
						valueToEnter = 62 + i;
					}
					else
					{
						System.out.println("No key found");
					}
	
					System.out.print(" " + valueToEnter);
					sleep(200);
					driver.pressKey(new KeyEvent(getKeyForNumber(valueToEnter)));
					// driver.pressKeyCode(valueToEnter);
				}
			}
	
			for(int i = 0; i < str.length(); i++)
			{
				if(flag)
				{
					break;
				}
				else
				{
					char inputLetter = str.charAt(i);
	
					if(Character.isUpperCase(inputLetter))
					{
						int asciiValueOfinputChar = (int) inputLetter;
						int lowercaseValue = asciiValueOfinputChar + 32;
						valueToEnter = lowercaseValue - 68;
						System.out.print(" " + valueToEnter);
						sleep(200);
						driver.pressKey(new KeyEvent(getKeyForNumber(valueToEnter)).withMetaModifier(KeyEventMetaModifier.SHIFT_ON));
						// driver.pressKeyCode(valueToEnter,Integer.valueOf(1));
					}
					else if(Character.isLowerCase(inputLetter))
					{
						int asciiValueOfinputChar = (int) inputLetter;
						valueToEnter = asciiValueOfinputChar - 68;
						System.out.print(" " + valueToEnter);
						sleep(200);
						driver.pressKey(new KeyEvent(getKeyForNumber(valueToEnter)));
						// driver.pressKeyCode(valueToEnter);
					}
					else if(Character.isDigit(inputLetter))
					{
						int digitValue = Character.getNumericValue(inputLetter);
						valueToEnter = digitValue + 7;
						System.out.print(" " + valueToEnter);
						sleep(200);
						driver.pressKey(new KeyEvent(getKeyForNumber(valueToEnter)));
						// driver.pressKeyCode(valueToEnter);
					}
					else if(specialCharacterSet.contains(Character.toString(inputLetter)))
					{
						for(int j = 0; j < specialCharacterSet.length(); j++)
						{
	
							if(inputLetter == specialCharacterSet.charAt(j))
							{
								valueToEnter = 8 + j;
								System.out.print(" " + valueToEnter);
								sleep(200);
								driver.pressKey(new KeyEvent(getKeyForNumber(valueToEnter)).withMetaModifier(KeyEventMetaModifier.SHIFT_ON));
								// driver.pressKeyCode(valueToEnter,Integer.valueOf(1));
							}
						}
					}
					else if(commaClearSet.contains(Character.toString(inputLetter)))
					{
	
						for(int j = 0; j < commaClearSet.length(); j++)
						{
							if(inputLetter == commaClearSet.charAt(j))
							{
								if(j == 0)
								{
									valueToEnter = 55 + j;
									System.out.print(" " + valueToEnter);
									sleep(200);
									driver.pressKey(new KeyEvent(getKeyForNumber(valueToEnter)));
									// driver.pressKeyCode(valueToEnter);
								}
								else if(j == 1)
								{
									valueToEnter = 68 + j;
									System.out.print(" " + valueToEnter);
									sleep(200);
									driver.pressKey(new KeyEvent(getKeyForNumber(valueToEnter)).withMetaModifier(KeyEventMetaModifier.SHIFT_ON));
									// driver.pressKeyCode(valueToEnter,Integer.valueOf(1));
								}
								else if(j == 2)
								{
									driver.pressKey(new KeyEvent(AndroidKey.PERIOD));
								}
								else
								{
									valueToEnter = 59 + j;
									System.out.print(" " + valueToEnter);
									sleep(200);
									driver.pressKey(new KeyEvent(getKeyForNumber(valueToEnter)));
									// driver.pressKeyCode(valueToEnter);
								}
							}
						}
					}
					else
					{
						System.out.println("No key found to enter");
					}
				}
			}
		}

		public static void sleep(int i) throws Exception {
			Thread.sleep(i);
			
		}
	
}

