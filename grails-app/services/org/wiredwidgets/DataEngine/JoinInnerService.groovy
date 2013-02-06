package org.wiredwidgets.DataEngine

import org.apache.commons.logging.LogFactory

class JoinInnerService
{
    private static final log = LogFactory.getLog( this )

    static Feed simplisticJoin( Feed feed1, Feed feed2 )
    {
        if ( !feed2 )
        {
            log.debug "simplisticJoin() >> returning feeds unchanged"
            return feed1
        }
        else
        {
            log.debug "simplisticJoin() >> "
            Feed main = feed1
            Feed feedn = feed2

            println "main has ${main.size()} elements"
            println "feedn has ${feedn.size()} elements"
            println "main[0].uuid = ${main[0].uuid}"
            println "feedn[0].uuid = ${feedn[0].uuid}"

            feed1.each  { mainRecord ->
                def uuid = mainRecord.uuid
                Record rec = feedn.getRecordByUuid( uuid )
                if ( rec )
                {
                    mainRecord.title = "can join"
                    mainRecord.ext.putAll( rec.ext )    // hk:  should probably use the values in mainRecord instead of doing it this way
                                                        //      which always uses the second record's value if it already exists in the first
                }
            }

            return feed1
        }
    }
}
