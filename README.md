#Configuration specifications

----------


There are two files db_config.properties and environment.properties which are located in C:/dssforut/database and C:/dssforut/env respectively.

- *db_config.properties*, contains configuration info about database
- *environment.properties*, contains configuration info about environment like, if we are connected to XBee or not.



There are two posible environments: REAL and DISCONNECTED. Real Environment, should be understood as the Xbee is connected to pc and getting real data.

Disconnected Environmen, should be understood as no Xbee is connected, then all the data obtained is fake. 

Environment.properties must be configured for each machine.

##Important

All those properties files should be located in: "C:/dssforut/" path.