package org.wiredwidgets.DataEngine

import groovy.json.*
import static java.util.UUID.randomUUID
/**
 * Class that stores each "record"
 * For example, a CSV row is considered a record q
 */
class Record
{
    // required (by CWL)
    String dataType = "EVENT"       // always EVENT for now
    String title = null             // a title/name/label for this record  (IDL specified as "$title")
    String description  = null       // a description (IDL specified as "$description")
    String uuid = null              // UUID of original record or auto-generated by this object if not specified
                                    // (IDL specified as "$uuid")

    // optional parameters
    Date starttime = new Date()     // the start time of this record (IDL specified as "$startTime")
    String lat = null               // the latitude coordinate (IDL specified as "$latitude")
    String lon = null               // the longitude coordinate (IDL specfied as "$longitude")
    String alt                      // the altitude coordinate (IDL specified as "$altitude")



    Feed feed                       // the feed this record belongs to
    HashMap<String, Object> ext     // extended information


        /** adds the ext element by cloning it, and assigns values to Record's data members as specified by the RecordMapper, if any */
    void addExt( Map data, RecordMapper recordMapper )
    {
        ext = data.clone()
        recordMapper?.applyIdl( this )

        // force an arbitrary uuid if one isn't assigned by the recordMapper
        if ( !uuid )
        {
            uuid = randomUUID().toString()
        }
//        if ( !title )
//        {
//            title = "Title " + uuid
//        }
//        if ( !description )
//        {
//            description = "Description " + title
//        }
        if ( !lat )
        {
            lat = "0.0"
        }
        if ( !lon )
        {
            lon = "0.0"
        }
    }

    /** outputs this object in a debug string */
    String toString()
    {
        StringBuffer buf = new StringBuffer()

        buf << """Record [uuid=${uuid?:"???"}]:
  title       = ${title?:""}
  description = ${description?:""}
  starttime   = ${starttime?:""}
  lat/lon     = ${lat?:"???"} / ${lon?:"???"}
  ext = """
        if ( ext )
        {
            def map = ext.sort{it.key}
            map.each {
                buf << "\n    ${it.key}: ${it.value}"
            }
        }
        buf << "\n  -----------------------\n"
        buf.toString()
    }

//    /** outputs this object as a json object */
//    String toJson( boolean prettyprint )
//    {
//        def json = new JsonBuilder()
////        def map = this.sort{it.key}
//
//        json.record {
//            a "a"
//        }
//
//        if ( prettyprint )
//        {
//            JsonOutput.prettyPrint( json.toString() )
//        }
//    }

    /** outputs this object as a CWL object */
    String toCwl( boolean prettyprint )
    {
        def extjson = new JsonBuilder()
        extjson.content = ext
        def extjsonstr = prettyprint ? extjson.toPrettyString() : extjson.toString()

        def json =
"""
{
  "datatype": "${dataType}",
  "label": "${title?:""}",
  "description":  "${description?:""}",
  "parentuuid":  "${feed ? feed.feedUuid : "" }",
  "uuid":  "${uuid}",
  "starttime": "${starttime}",
  "location": { "type": "Point",
                "coordinates": [ ${lat}, ${lon} ],
                "properties": {  "circularError": 10 }
  },

  "ext": ${extjsonstr}
}"""
        return json
    }
}