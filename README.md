# IntuitChallenge

Author - Ajith Paul


How to run the program
---------

1. Compile IntuitFinancialAdvice including json-simple-1.1.1.jar in classpath.

	javac -cp json-simple-1.1.1.jar IntuitFinancialAdvice.java 

2. Run the executable using the below command

	java -cp .:json-simple-1.1.1.jar IntuitFinancialAdvice

3. Program will ask for the following inputs.

	Stock ticker symbol of the company.
	Offical name of the company

Three Public APIs used:
-----------------

1. Quantl API

	https://www.quandl.com/tools/api
	This gives the stock prices and the variations at day-closing

2. Wikipedia API 

	https://www.mediawiki.org/wiki/API:Main_page
        To fetch detailed intro about the company you wish to invest in

3. NSE API

	https://www.nseindia.com/live_market/dynaContent/live_analysis
   	This API is used to obtain a list of top gainers of the day

Sample Output
-----------------------
Enter the company code you want to invest in : AAPL

Enter the company name : Apple Inc

::::::::::::::::::::::::::
The percentage increase over the time period is 91.95238095238093

:::::Financial Advice:::::
Good showing! OK to invest.

Apple Inc. is an American multinational technology company headquartered in Cupertino, California, that designs, develops, and sells consumer electronics, computer software, and online services. Its hardware products include the iPhone smartphone, the iPad tablet computer, the Mac personal computer, the iPod portable media player, the Apple Watch smartwatch, and the Apple TV digital media player. Apple's consumer software includes the OS X and iOS operating systems, the iTunes media player, the Safari web browser, and the iLife and iWork creativity and productivity suites. Its online services include the iTunes Store, the iOS App Store and Mac App Store, and iCloud.

:::::List of top Gainers today:::::
INFY
TECHM
HCLTECH
RELIANCE
WIPRO
TCS

Your desired company does not figure in the top Gainers List!
