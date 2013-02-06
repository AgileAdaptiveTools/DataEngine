package org.wiredwidgets.DataEngine

import org.apache.commons.logging.LogFactory

class JoinController extends GenericProcessController
{
    private static final log = LogFactory.getLog( this )

    def index()
    {
        log.debug "${actionName}() >> params=${params.sort()}"
        // overrides index going to the scaffolded list, so that the help page can be displayed
    }


    protected Feed getFeedFromIdl( long idl_uuid )
    {
        def idl1 = Idl.findById( idl_uuid )

        println idl1.source

        URL url = new URL( idl1.source )
        Feed feed

        switch ( idl1.source_format.toLowerCase() )
        {
            case 'json':
                feed = JsonFormatHelper.ingest( new InputStreamReader( url.openStream() ), String.valueOf( idl1.id ) )
                println feed.toJson( true )
                break;
            case 'csv':
                feed = CsvFormatHelper.ingest( new InputStreamReader( url.openStream() ), String.valueOf( idl1.id ) )
                println feed.toJson( true )
                break;
        }

        return feed
    }

    /** http GET to this to do an inner join (see online documentation for details) */
    def leftOuter()
    {
        log.debug "${actionName}() >> params=${params.sort()}"

        // get the idls
        def idls = []   // these are going to be a list of ints
        String idlsString = params?.idls
        if ( idlsString.contains( "," ) )
        {
            StringTokenizer tokenizer = new StringTokenizer( idlsString, "," )
            while ( tokenizer.hasMoreTokens() )
            {
                idls << tokenizer.nextToken().toInteger()
            }
        }
//        println idls

        Feed feed1 = getFeedFromIdl( idls[0] )
        Feed feed2 = getFeedFromIdl( idls[1] )

        Feed joinedFeed = JoinService.simplisticJoin( feed1, feed2 )
        super.renderRestResponse( 200, joinedFeed )

//        renderRestResponse( 200, params.toString() )
//        forward controller: "jsonSlurper", action: "get", params: [ url: "http://localhost:8080/DataEngine/sample_files/wikimapia_hospitals.json", format: "CWL" ]
    }
}
