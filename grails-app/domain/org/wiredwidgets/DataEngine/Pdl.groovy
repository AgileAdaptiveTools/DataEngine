package org.wiredwidgets.DataEngine

import grails.converters.JSON
import groovy.json.JsonSlurper

/** PDL is the Process Description Language */

class Pdl
{
    /* ----- constants ----- */
    static String kPdlVersion = "Process Description Language (PDL) v1.1302.08"

//    String id
//    String idl_uri     // URL to this IDL, basically, http(s)://localhost_url/DataEngine/idl/uuid, used as a UUID
                         // this is generated at the controller's GET method, and is not stored
                         // in the database
    String title
    String poc
    Date creationDate
    Date modificationDate
    String description
    String selectSpec
    String whereSpec

    static mapping = {
        description type: 'text'    // enables description to be very large
    }

    static constraints = {
//        version( blank: true )
        title( size:0..1024 )
        poc( size:0..1024 )
        description( nullable: true )
        selectSpec( nullable: true, size: 0..2048 )
        whereSpec( nullable: true, size: 0..2048 )
    }


    /** returns the selectSpec as an Array */
//    Map<String, String> getDslvAsMap()
//    {
//        def slurper = new JsonSlurper()
//        def result = slurper.parseText( dslv )
//        Map<String,String> dslvMap = result
//        println dslvMap.latlon
//        return dslvMap
//    }


    /** returns this object as a grails json object */
    grails.converters.JSON toJson()
    {
        def outmap = [
                "version" : Pdl.kPdlVersion,
                "pdl_uuid" : this.id,
                "title" : this.title,
                "poc" : this.poc,
                "creationDate" : this.creationDate,
                "modificationDate" : this.modificationDate,
                "description" : this.description,
                "select" : this.selectSpec,
                "where" : this.whereSpec
        ]
        def out = outmap as JSON
        out
    }


}
