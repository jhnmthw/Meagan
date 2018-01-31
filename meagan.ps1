    Write-Host "___       ___                                                
`MMb     dMM'                                                
 MMM.   ,PMM                                                 
 M`Mb   d'MM   ____      ___      __        ___    ___  __   
 M YM. ,P MM  6MMMMb   6MMMMb    6MMbMMM  6MMMMb   `MM 6MMb  
 M `Mb d' MM 6M'  `Mb 8M'  `Mb  6M'`Mb   8M'  `Mb   MMM9 `Mb 
 M  YM.P  MM MM    MM     ,oMM  MM  MM       ,oMM   MM'   MM 
 M  `Mb'  MM MMMMMMMM ,6MM9'MM  YM.,M9   ,6MM9'MM   MM    MM 
 M   YP   MM MM       MM'   MM   YMM9    MM'   MM   MM    MM 
 M   `'   MM YM    d9 MM.  ,MM  (M       MM.  ,MM   MM    MM 
_M_      _MM_ YMMMM9  `YMMM9'Yb. YMMMMb. `YMMM9'Yb._MM_  _MM_
                                6M    Yb                     
                                YM.   d9                     
                                 YMMMM9                   "
								 

Write-Host "============================================================================================================================================================================"
Write-Host "Specify the type of testing: " -ForegroundColor Yellow  -nonewline
Write-Host "performance" -ForegroundColor Red  -nonewline
Write-Host " for Performance Testing or " -ForegroundColor Yellow  -nonewline
Write-Host "security" -ForegroundColor Blue  -nonewline
Write-Host " for Security Testing" -ForegroundColor Yellow								 
$testingProfile = Read-Host

If ($testingProfile -eq "performance"){
Write-Host "Do you wish to review your script before Load Test? (y/n)" -ForegroundColor Yellow
$choice = Read-Host
If ($choice -eq "y"){
mvn clean prepare-package -P $testingProfile -DdownloadDependencies=flase jmeter:gui@jmeter
mvn clean integration-test -P $testingProfile 
Write-Host "Reconfiguring JMeter to create Aggregate Report" -ForegroundColor Yellow
Write-Host "Flushing JMeter lib directory" -ForegroundColor Yellow
mvn -P $testingProfile antrun:run@flush-jmeter-lib
mvn -P $testingProfile jmeter:configure -DdownloadDependencies=false
mvn -P $testingProfile pre-site
#mvn post-integration-test com.googlecode.maven-download-plugin:download-maven-plugin:wget@install-cmd-runner
#mvn antrun:run
}
Else {
Write-Host "============================================================================================================================================================================"
mvn clean integration-test -P $testingProfile jmeter-maven-plugin:jmeter@jmeter-tests
Write-Host "Reconfiguring JMeter to create Aggregate Report" -ForegroundColor Yellow
mvn jmeter:configure -DdownloadDependencies=false
mvn com.googlecode.maven-download-plugin:download-maven-plugin:wget@install-cmd-runner
mvn antrun:run
}
} 						
					
Write-Host ""
Write-Host "Press any key to continue ..."

$x = $host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")