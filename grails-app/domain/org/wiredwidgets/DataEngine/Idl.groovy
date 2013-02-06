package org.wiredwidgets.DataEngine

import groovy.json.JsonSlurper
import grails.converters.JSON

/** IDL is the Ingest Description Language */

class Idl
{
    /* ----- constants ----- */
    static String kIdlVersion = "Ingest Description Language (IDL) v1.1212.27"

//    String id
//    String idl_uri     // URL to this IDL, basically, http(s)://localhost_url/DataEngine/idl/uuid, used as a UUID
                         // this is generated at the controller's GET method, and is not stored
                         // in the database
    String title
    String poc
    Date creationDate
    Date modificationDate
    String source       // URL to the source
    String source_format    // source format (e.g., json, csv, etc.)
    String source_uri   // URI that identifies the source URL with a UUID.  It does not
                        //  make use of the original since the original could have "per use" parameters as well
                        //  as "per class" parameters.
                        //  It is basically, http(s)://localhost_url/DataEngine/source/uuid
    String description
//    String version = kIdlVersion     // IDL schema version number (e.g., "Ingest Description Language v1.121205")
    String dslv     // the domain specific language value (IDL mapping, in this case) --- MUST BE a JSON string

    static mapping = {
        description type: 'text'    // enables description to be very large
        dslv        type: 'text'    // enables dslv to be very large
//        id generator: 'uuid'
    }

    static constraints = {
//        version( blank: true )
        title( size:0..1024 )
        poc( size:0..1024 )
        source( nullable: true, size:0..2048 )
        dslv( nullable: true )
    }


    /** returns the dslv as a Map */
    Map<String, String> getDslvAsMap()
    {
        def slurper = new JsonSlurper()
        def result = slurper.parseText( dslv )
        Map<String,String> dslvMap = result
        println dslvMap.latlon
        return dslvMap
    }


    /** returns this object as a grails json object */
    grails.converters.JSON toJson()
    {
        def outmap = [
                "version" : Idl.kIdlVersion,
                "idl_uuid" : this.id,
                "title" : this.title,
                "poc" : this.poc,
                "creationDate" : this.creationDate,
                "modificationDate" : this.modificationDate,
                "source" : this.source,
                "source_format" : this.source_format,
                "source_uri" : this.source_uri,
                "description" : this.description,
                "dslv" : this.getDslvAsMap()
        ]
        def out = outmap as JSON
        out
    }


}
