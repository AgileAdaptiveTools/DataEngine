package org.wiredwidgets.DataEngine

import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import org.apache.commons.logging.LogFactory

import static java.util.UUID.randomUUID

/**
 * Created with IntelliJ IDEA.
 * User: hkong
 * Date: 11/15/12
 * Time: 7:37 PM
 * To change this template use File | Settings | File Templates.
 */
class Feed extends ArrayList<Record>
{
    private static final log = LogFactory.getLog(this)

    String title                        // name of this feed
    String url                          // url source of this feed
    String description                  // description about this feed
    String feedUuid                     // the UUID for this feed
    RecordMapper recordMapper = null    // the recordMapper

//    String raw  // the raw data as it originally came from the source

    public Feed( String idl )
    {
        log.debug "constructor() idl=${idl}"
        if ( idl && idl.isInteger() )
        {
            recordMapper = new RecordMapper( idl.toInteger() )
        }

        // force an arbitrary uuid if one isn't assigned by the recordMapper
        if ( !feedUuid )
        {
            feedUuid = randomUUID().toString()
        }

    }


    Record getRecordByUuid( String uuid )
    {
        Record[] records = this.toArray()
//        println records.size()
        println records[0].class.name
        int i = 0
        Record rec = null
        for ( i = 0; i < records.size(); i++ )
        {
            rec = records[i]
            if ( rec.uuid == uuid )
            {
                return rec
            }
        }
    }


    @Override
    public String toString()
    {
        StringBuffer buf = new StringBuffer()

        buf << "Feed:  \n" \
            << "       url:  ${url}\n" \
            << "       title:  ${title}\n" \
            << "       ${this.size()} Records\n"

        buf.toString() + super.toString()
    }


    /** outputs this object as a generic json object */
    String toJson( boolean prettyprint )
    {
        def json = new JsonBuilder()
//        def records = this.toArray()

        json.feed {
            title title
            url url
            description description
            items this.size()
//            records.each  { item ->
//                def map = item.sort { it.key }
//                map item.toString()
//            }
//            type records.class.name
//            records (
//                super.eachWithIndex  { a, i ->
//                    index i
//                    c 2
//                }
//            )
            records super
        }

        if ( prettyprint )
        {
            JsonOutput.prettyPrint( json.toString() )
        }
    }


    /** outputs this object as a CWL object */
    String toCwl( boolean prettyprint )
    {
//        def uuid = '571afb98-3d0d-4dec-b373-8f8d51849266'
//        Record[] records = this.toArray()
//        StringBuffer recordstr = new StringBuffer()
//        records.each {
//            recordstr << it.toCwl( prettyprint )
//        }
//
//        def json = new JsonBuilder( UUID.fromString(uuid) )
//        json.feed {
//            datatype "CONTAINER"
//            label title
////            uuid UUID.fromString(uuid)
//            parentuuid uuid
//            source url
//
//            children [
//            ]
//        }
//
//        if ( prettyprint )
//        {
//            JsonOutput.prettyPrint( json.toString() )
//        }

        Record[] records = this.toArray()
        StringBuffer buf = new StringBuffer()
        int stopind = records.size() - 1
        records.eachWithIndex { item, i ->
            buf << item.toCwl( true )
            if ( i < stopind  )
                buf << ","
        }

        def json =
"""{
    "datatype": "CONTAINER",
    "label": "${title}",
    "uuid": "${feedUuid}",
    "parentuuid":  "${feedUuid}",
    "source":  "${url}",
    "children": [
        ${buf}
    ]
}"""
    }
}
