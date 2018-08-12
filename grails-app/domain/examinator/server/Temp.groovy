package examinator.server

import org.bson.types.ObjectId

class Temp {
    ObjectId id
    String value
    boolean flag

    String getId(){
        return id.toString();
    }
    static mapping = {
        collection "temp"
    }
}
