// USED CODE FROM APPLICATION IN PHASE 2 EDX ("JSON SERIALIZATION DEMO")

package persistence;

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
