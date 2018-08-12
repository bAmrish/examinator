### Docker Mongo Database

#### Command to create docker mongo database
docker run -d --name examinator -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=mongoadmin -e MONGO_INITDB_ROOT_PASSWORD=secret mongo:3.6.6

##### Commands to create database

```mongo
use examinator

db.temp.insertOne({'x': 1});
```    

##### Commands to create a user and set permission

- Create `examinator-server-app` user for application usage
```mongo

use examinator
﻿db.createUser({ 
    user: "examinator-server-app",
    pwd: "Examinator123",
    roles: [
        { role: "readWrite", db: "examinator" }
    ]
})
```

- Create `examinator-server-app-admin` user for administrating the application database

use examinator

```mongo
﻿db.createUser({ 
    user: "examinator-server-app-admin",
    pwd: "Examinator123",
    roles: [
        { role: "readWrite", db: "examinator" },
        { role: "dbAdmin", db: "examinator" }
    ]
})
```
