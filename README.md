# TimeInIreland
An Instagram bot which posts what time it is in Ireland every hour.

Requires a cron to execute the script once every hour like this:
```bash
0 * * * * java -jar timeinireland.jar -Dinstagram-username=USERNAME-GOES-HERE -Dinstagram-password=PASSWORD-GOES-HERE
```
This will run the timeinireland.jar file at minute 0 of every hour. 

The instagram username and password are to be passed in as environment variables `instagram-username` and 
`instagram-password` respectively.

You can see this in action @ https://www.instagram.com/timeinireland/