# TimeInIreland
An Instagram bot which posts what time it is in Ireland every hour.

Requires a cron to execute the script once every hour like this:
```bash
0 * * * * java -jar timeinireland.jar USERNAME-GOES-HERE PASSWORD-GOES-HERE
```
This will run the timeinireland.jar file at minute 0 of every hour. 

The instagram username and password are to be passed in as command-line arguments as shown above.

You can see this in action @ https://www.instagram.com/timeinireland/