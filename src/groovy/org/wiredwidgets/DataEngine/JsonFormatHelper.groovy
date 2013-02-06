package org.wiredwidgets.DataEngine

import groovy.json.JsonSlurper
import org.apache.commons.logging.LogFactory

/**
 * Uses external libraries to correctly ingest CSV-formatted data
 */
class JsonFormatHelper
{
    private static final log = LogFactory.getLog(this)

    /** ingests the given stream, and returns the result as a Feed of Records */
    static Feed ingest( Reader reader, String idl )
    {
        log.debug "ingest() >> idl=${idl?:"null"}"
        org.wiredwidgets.DataEngine.Feed feed = new Feed( idl )

        try
        {
            def slurper = new JsonSlurper()
            def result = slurper.parse( reader )
//            log.debug "ingest() >> result=${result}"

            // try to figure out which part of the json is the longest, and use that as
            //  the "data array".  This is known to be inaccurate, but is a good approximation
            //  until the user can indicate the "data array"

            def values = []
            result.each  {
                if ( it.value instanceof ArrayList && ( it.value.size() > values.size() )  )
                {
                    log.debug "ingest() >> estimated data array is in '${it.key}' with ${it.value.size()} elements"
                    values = it.value
                }
            }
            values.each  {
//                log.debug "ingest() >> element = ${it}"
                // try to fit it into a record
                org.wiredwidgets.DataEngine.Record record
                def ext = it
                record = new Record()
                feed << record
                record.addExt( ext, feed.recordMapper )
            }
        }
        catch( Exception e )
        {
            log.debug "ingest() >> error:  ${e}\n${e.stackTrace}"
        }
        return feed
    }
}
