package TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
public class Retry implements IRetryAnalyzer{

	int    count = 0;
	int maxc =3;
	
	@Override
	public boolean retry(ITestResult result) {
		if(count<maxc) {
			count++;
			return true;
		}
		
		return false;
	}
	

}
