package org.wiredwidgets.DataEngine

import org.supercsv.io.CsvMapReader
import org.supercsv.io.ICsvMapReader
import org.supercsv.prefs.CsvPreference
import org.supercsv.cellprocessor.ift.CellProcessor
import org.supercsv.cellprocessor.Optional
import org.apache.commons.logging.LogFactory

/**
 * Uses external libraries to correctly ingest CSV-formatted data
 */
class CsvFormatHelper
{
    private static final log = LogFactory.getLog(this)
    /** since we don't usually have prior knowledge of the format of the data,
     *  we're using the generic Optional (String) processor here for all columns
     * @param n number of processors requested
     * @return an array of n CellProcessors
     */
    private static CellProcessor[] getProcessors( int n )
    {
        final CellProcessor[] processors = new Object[n];
        for ( int i=0; i<n; i++ )
        {
            processors[i] = new Optional()
        }

        return processors;
    }


    /** ingests the given stream, and returns the result as a Feed of Records */
    static Feed ingest( Reader reader, String idl )
    {
        log.debug "ingest() >> idl=${idl?:"null"}"
        ICsvMapReader mapReader = null;
        org.wiredwidgets.DataEngine.Feed feed = new Feed( idl )

        try
        {
            mapReader = new CsvMapReader( reader, CsvPreference.STANDARD_PREFERENCE )

            // the header columns are used as the keys to the Map
            final String[] header = mapReader.getHeader( true )
            final CellProcessor[] processors = getProcessors( header.length );

            org.wiredwidgets.DataEngine.Record record
            def ext = [:]
            while ( ( ext = mapReader.read(header, processors ) ) != null )
            {
                record = new Record()
                feed << record
                record.addExt( ext, feed.recordMapper )
            }
        }
        finally
        {
            if( mapReader != null )
            {
                mapReader.close()
            }
        }
        return feed
    }
}
